package com.sndyuk.jee6.persistence.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestCase {
	private static Logger LOG = LoggerFactory.getLogger(TestCase.class);
	
	public static EntityManagerFactory EMF;
	public static EntityManager EM;

	public static final String PU_TEST_MOMORY_HSQLDB = "pu-test-momory-hsqldb";

	protected static void runBeforeClass(String emName) {
		LOG.info("Retrieving entity manager: " + emName);
		EMF = Persistence.createEntityManagerFactory(emName);
		EM = EMF.createEntityManager();
	}

	protected static void runAfterClass() {
		LOG.info("Closing entity manager factory: " + EMF);
		EMF.close();
	}
}
