package com.sndyuk.jee6.persistence.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sndyuk.jee6.persistence.entity.UserEntity;

public class UserTest extends TestCase {

	private static final Logger LOG = LoggerFactory.getLogger(UserTest.class);

	private static UserDomain userDomain = new UserDomain();

	@Test
	public void getUsers() throws Exception {
		List<UserEntity> users = userDomain.getUsers();
		assertTrue(users.size() > 0);
		for (UserEntity user : users) {
			LOG.debug(user.toString());
		}
	}

	@Test
	public void getUsersByName() throws Exception {
		List<UserEntity> users = userDomain.getUsersByName("Yui");
		assertTrue(users.size() == 1);
		LOG.debug(users.get(0).toString());
	}

	@Test
	public void getUser() throws Exception {
		UserEntity user = userDomain.getUser(1L);
		assertNotNull(user);
		LOG.debug(user.toString());
	}
	
	@BeforeClass
	public static void runBeforeClass() {
		TestCase.runBeforeClass(PU_TEST_MOMORY_HSQLDB);
		userDomain.setEm(EM);
	}

	@AfterClass
	public static void runAfterClass() {
		TestCase.runAfterClass();
	}
}
