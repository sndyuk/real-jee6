package com.sndyuk.jee6.web.job;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * I want to make this servlet into jax-rs(JobResource.java).<br />
 * Does jax-rs has any async context which is like 'request.startAsync(request, response)'?
 */
@WebServlet(urlPatterns = { "/jobstatus" }, asyncSupported = true)
@SuppressWarnings("serial")
public class JobStatusResource extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		JobWatcherManager.addWatcher(request.getParameter("jobId"), request.startAsync(request, response), getServletContext(), 1000L * 60 * 60 * 1);
	}
}
