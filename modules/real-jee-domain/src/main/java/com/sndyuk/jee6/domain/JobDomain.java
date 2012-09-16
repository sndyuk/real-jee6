package com.sndyuk.jee6.domain;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sndyuk.jee6.persistence.Dao;
import com.sndyuk.jee6.persistence.entity.JobEntity;

@LocalBean
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class JobDomain implements Dao {

	private EntityManager em;

	@Override
	@PersistenceContext(unitName = "OurEntityManager")
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<JobEntity> getJob(String jobId) {
		return em.createQuery("select job from JobEntity job where job.id = :jobId", JobEntity.class)
				.setParameter("jobId", jobId).getResultList();
	}

	public void addJob(JobEntity job) {
		em.merge(job);
	}
}
