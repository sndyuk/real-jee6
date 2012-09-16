package com.sndyuk.jee6.domain.core;

import java.util.concurrent.atomic.AtomicLong;


public class JobContainer {

	public final String jobId;
	public final Job job;
	public final AtomicLong lastModified = new AtomicLong(System.currentTimeMillis());
	
	public JobContainer(String jobId, Job job) {
		this.jobId = jobId;
		this.job = job;
	}
}
