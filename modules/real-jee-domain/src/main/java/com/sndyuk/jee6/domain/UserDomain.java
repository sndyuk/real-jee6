package com.sndyuk.jee6.domain;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sndyuk.jee6.persistence.Dao;
import com.sndyuk.jee6.persistence.entity.UserEntity;
import com.sndyuk.jee6.security.Roles;

//@DeclareRoles(Roles.ADMIN) // FXIME Make secure
@LocalBean
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserDomain implements Dao {

	private EntityManager em;

	@Override
	@PersistenceContext(unitName = "OurEntityManager")
	public void setEm(EntityManager em) {
		this.em = em;
	}

//	@RolesAllowed(Roles.ADMIN)
	public List<UserEntity> getUsers() {
		return em.createQuery("select user from UserEntity user order by user.name", UserEntity.class).getResultList();
	}

	public UserEntity getUser(Long id) {
		return em.find(UserEntity.class, id);
	}
	
	public List<UserEntity> getUsersByName(String username) {
		return em.createQuery("select user from UserEntity user where user.name = :name", UserEntity.class).setParameter("name", username).getResultList();
	}

	@RolesAllowed(Roles.ADMIN)
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public UserEntity saveUser(UserEntity user) {
		em.persist(user);
		return user;
	}
}
