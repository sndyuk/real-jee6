package com.sndyuk.jee6.web.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sndyuk.jee6.domain.core.Job;
import com.sndyuk.jee6.domain.core.JobContainer;
import com.sndyuk.jee6.domain.core.JobListner;

@WebListener
public class JobWatcherManager implements ServletContextListener {

	private static AtomicLong lastJobId = new AtomicLong(0);
	
	private static final Logger LOG = LoggerFactory.getLogger(JobWatcherManager.class);

	private static final Map<String, List<Watcher>> JOB_WATCHERS = new HashMap<>();
	private static final Map<String, JobContainer> JOBS = new ConcurrentHashMap<>();
	public static final String CONTEXT_KEY_JOB_WATCHERS = JobWatcherManager.class + "_jobwatchers";

	private static final ExecutorService WATCHER_EXECUTOR = Executors.newCachedThreadPool();

	public static long createNewJobId() {
		return lastJobId.addAndGet(1);
	}

	public static void addWatcher(String jobId, AsyncContext aCtx, ServletContext servletContext, long timeoutMillis) {
		
		aCtx.setTimeout(timeoutMillis);
		addWatcher(jobId, aCtx, servletContext);
	}
	
	@SuppressWarnings("unchecked")
	public static void addWatcher(String jobId, AsyncContext aCtx, ServletContext servletContext) {
		
		Map<String, List<Watcher>> jobWatchers = (Map<String, List<Watcher>>) servletContext.getAttribute(CONTEXT_KEY_JOB_WATCHERS);
		List<Watcher> watchers = (List<Watcher>) jobWatchers.get(jobId);
		if (watchers == null) {
			watchers = new ArrayList<>();
			jobWatchers.put(jobId, watchers);
		}
		watchers.add(new Watcher(aCtx));
	}

	public static synchronized boolean addJob(String jobId, Job job) {

		if (JOBS.containsKey(jobId)) {
			return false;
		}
		
		final JobContainer jobContainer = new JobContainer(jobId, job);
		
		job.addListner(new JobListner() {
			
			@Override
			public void afterUpdate() {

				final List<Watcher> watchers = JOB_WATCHERS.get(jobContainer.jobId);
				if (watchers != null) {
					for (final Iterator<Watcher> ite = watchers.iterator(); ite.hasNext();) {
						final Watcher watcher = ite.next();
						final AsyncContext aCtx = watcher.aCtx;
						if (watcher.lastModified < jobContainer.lastModified.get()) {
							watcher.lastModified = System.currentTimeMillis();
							ite.remove();
							
						WATCHER_EXECUTOR.execute(new Runnable() {
							public void run() {
								try {
									aCtx.getResponse().getWriter().print(new Date(jobContainer.lastModified.get()) + " : " + jobContainer.job.getStatusMessage());
								} catch (IOException e) {
									LOG.warn("Client has been closed.", e.getMessage());
								} finally {
									aCtx.complete();
								}
							};
						});
						}
					}
				}
			}

			@Override
			public void afterClose() {
				JOBS.remove(jobContainer.jobId);
			}
		});
		JOBS.put(jobContainer.jobId, jobContainer);
		return true;
	}
	
	public static boolean setJobStatus(String jobId, String statusMessage) {
		
		 JobContainer jobContainer = JOBS.get(jobId);
		 if (jobContainer == null) {
			 return false;
		 }
		 jobContainer.job.setStatusMessage(statusMessage);
		 jobContainer.lastModified.set(System.currentTimeMillis());
		 return true;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		sce.getServletContext().setAttribute(CONTEXT_KEY_JOB_WATCHERS, JOB_WATCHERS);
		LOG.debug("Starting job manager");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		shutdownAndAwaitTermination(WATCHER_EXECUTOR);
	}

	private static final void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown();
		try {
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if (!pool.awaitTermination(60, TimeUnit.SECONDS))
					LOG.error("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	
	private static class Watcher {

		long lastModified = -1;
		final AsyncContext aCtx;
		
		Watcher(AsyncContext aCtx) {
			this.aCtx = aCtx;
		}
	}
}
