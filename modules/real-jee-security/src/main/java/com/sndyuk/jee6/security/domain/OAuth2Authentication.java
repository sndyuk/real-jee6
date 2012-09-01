package com.sndyuk.jee6.security.domain;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.sndyuk.jee6.security.domain.dto.UserDto;

@Singleton
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OAuth2Authentication implements Authentication {

	public UserDto login(String username, String password) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		try {
			// FIXME Use OAuth2
			request.login(username, password);
			UserDto user = new UserDto();
			user.setFirstName(username);
			return user;

		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Unknown login"));
			return null; // FIXME Return an anonymous user
		}
	}
}