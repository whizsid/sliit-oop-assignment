package com.sliit.musicstore.controllers;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sliit.musicstore.exceptions.MusicStoreException;
import com.sliit.musicstore.models.User;
import com.sliit.musicstore.services.UserService;

public class UserLoginController extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
		req.getRequestDispatcher("login.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String redirect = req.getParameter("redirect");

		req.setAttribute("email", email);

		if(redirect==null){
			redirect = "/";
		}

		try {
			try {
				User user = UserService.login(email, password);

				HttpSession session =  req.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("userName", user.getName());

				res.sendRedirect(req.getContextPath()+redirect);
				return;
			} catch (MusicStoreException e1){
				req.setAttribute("error", e1.getMessage());
			}
		} catch (Exception e){
			req.setAttribute("error", e.getMessage());
		}

		req.getRequestDispatcher("login.jsp").forward(req, res);

	}

}