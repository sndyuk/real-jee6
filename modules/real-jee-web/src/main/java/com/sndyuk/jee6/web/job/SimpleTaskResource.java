package com.sndyuk.jee6.web.job;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sndyuk.jee6.persistence.entity.SimpleTaskEntity;
	
@Stateless
@Path("/simpletask/")
public class SimpleTaskResource {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String createNewTask() {

		String jobId = String.valueOf(JobWatcherManager.createNewJobId());
		JobWatcherManager.addJob(jobId, new SimpleTaskEntity());
		return jobId;
	}

	@PUT
	@Path("/{jobId}/status")
	@Consumes(MediaType.TEXT_PLAIN)
	public void updateMessage(@PathParam("jobId") String jobId, String status) {

		JobWatcherManager.setJobStatus(jobId, status);
	}
}
