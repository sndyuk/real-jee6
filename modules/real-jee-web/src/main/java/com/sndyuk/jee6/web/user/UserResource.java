package com.sndyuk.jee6.web.user;

import static com.sndyuk.jee6.web.WebUtils.toId;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sndyuk.jee6.persistence.entity.UserEntity;
import com.sndyuk.jee6.service.UserService;
	
@Stateless
@Path("/users/")
public class UserResource {

	@EJB
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserEntity> getUsersByName() {

		return userService.getUsers();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserEntity getUser(@PathParam("id") String id) {

		return userService.getUser(toId(id));
	}
}
