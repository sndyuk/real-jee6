package com.sndyuk.jee6.rs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sndyuk.jee6.persistence.entity.UserEntity;
import com.sndyuk.jee6.service.UserService;
import static com.sndyuk.jee6.rs.ResourceUtils.*;
	
@Stateless
@Path("/users/")
public class UserResource {

	@EJB
	private UserService userService;

	@GET
	@Produces({"application/json", "text/plain"})
	public List<UserEntity> getUsersByName(@QueryParam("username") String username) {
		
		return userService.getUsersByName(username);
	}
	
	@GET
	@Path("{id}")
	@Produces({"application/json", "text/plain"})
	public UserEntity getUser(@PathParam("id") String id) {
		
		return userService.getUser(toId(id));
	}
}
