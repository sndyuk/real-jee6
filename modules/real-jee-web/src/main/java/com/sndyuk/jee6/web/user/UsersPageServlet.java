package com.sndyuk.jee6.web.user;

import java.io.IOException;
import java.util.List;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sndyuk.jee6.persistence.entity.UserEntity;
import com.sndyuk.jee6.security.Roles;
import com.sndyuk.jee6.service.UserService;

@RunAs(Roles.ADMIN)
@WebServlet(urlPatterns = { "/users" }, asyncSupported = false)
@SuppressWarnings("serial")
public class UsersPageServlet extends HttpServlet {

	@EJB
	private UserService userService;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		List<UserEntity> users = userService.getUsers();
		req.setAttribute("users", users);
		getServletConfig().getServletContext().getRequestDispatcher("/users.jsp").forward(req, res);
	}
}
