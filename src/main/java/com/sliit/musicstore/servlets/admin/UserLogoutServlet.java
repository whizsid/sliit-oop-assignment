package com.sliit.musicstore.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogoutServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        HttpSession session = req.getSession();

        session.removeAttribute("userId");
        session.removeAttribute("userName");

        res.sendRedirect(req.getContextPath()+"/admin/login");
    }
}