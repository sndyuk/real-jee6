package com.sndyuk.jee6.web.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/showjobstatus" }, asyncSupported = false)
@SuppressWarnings("serial")
public class JobStatusPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setAttribute("jobId", req.getParameter("jobId"));
		getServletConfig().getServletContext().getRequestDispatcher("/jobstatus.jsp").forward(req, res);
	}
}
