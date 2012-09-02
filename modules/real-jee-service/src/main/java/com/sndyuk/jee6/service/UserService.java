package com.sndyuk.jee6.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.sndyuk.jee6.persistence.domain.UserDomain;
import com.sndyuk.jee6.persistence.entity.UserEntity;

@Singleton(description = "This bean can use ror controling user.")
public class UserService {
	
	@EJB
	private UserDomain userDomain;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<UserEntity> getUsers() {
		return userDomain.getUsers();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<UserEntity> getUsersByName(String username) {
		return userDomain.getUsersByName(username);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public UserEntity getUser(Long id) {
		return userDomain.getUser(id);
	}
}
