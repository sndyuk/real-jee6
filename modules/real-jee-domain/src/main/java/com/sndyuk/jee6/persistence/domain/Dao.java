package com.sndyuk.jee6.persistence.domain;

import javax.persistence.EntityManager;

public interface Dao {
	
	public static final String SCHEME_SECURE_DOMAIN = "PUBLIC";
	public static final String SCHEME_SECURE_DOMAIN_PREFIX = SCHEME_SECURE_DOMAIN + ".";
	
	void setEm(EntityManager em);
}
