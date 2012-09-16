package com.sndyuk.jee6.web.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
	"/managejob"
}, asyncSupported = false)
@SuppressWarnings("serial")
public class JobManagePageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		getServletConfig().getServletContext().getRequestDispatcher("/jobmanage.jsp").forward(req, res);
	}
}
