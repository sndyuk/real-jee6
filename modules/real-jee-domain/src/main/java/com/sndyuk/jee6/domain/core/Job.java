package com.sndyuk.jee6.domain.core;

import java.util.ArrayList;
import java.util.List;


public abstract class Job {

	private final List<JobListner> listners = new ArrayList<>();
	
	protected void afterChangeState() {
		
		for (JobListner listner : listners) {
			listner.afterUpdate();
		}
	}
	
	public void addListner(JobListner listner) {
		
		listners.add(listner);
	}
	
	public abstract boolean isClosed();
	
	public abstract void setStatusMessage(String statusMessage);
	public abstract String getStatusMessage();
}
