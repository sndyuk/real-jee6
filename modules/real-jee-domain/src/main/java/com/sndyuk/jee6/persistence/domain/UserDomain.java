package com.sndyuk.jee6.persistence.domain;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sndyuk.jee6.persistence.entity.UserEntity;

//@DeclareRoles(Roles.ADMIN) // FXIME Make secure
@LocalBean
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserDomain implements Dao {
	private static Logger LOG = LoggerFactory.getLogger(UserDomain.class);

	private EntityManager em;

	@Override
	@PersistenceContext(unitName = "OurEntityManager")
	public void setEm(EntityManager em) {
		this.em = em;
	}

//	@RolesAllowed(Roles.ADMIN)
	public List<UserEntity> getUsers() {
		LOG.debug("getUsers");
		List<UserEntity> users = this.em.createQuery("select user from UserEntity user order by user.name", UserEntity.class).getResultList();
		return users;
	}

//	@RolesAllowed(Roles.ADMIN)
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public UserEntity saveUser(UserEntity user) {
		LOG.debug("saveUser: " + user);
		this.em.persist(user);
		return user;
	}
}
