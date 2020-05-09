package com.sliit.musicstore.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sliit.musicstore.models.User;
import com.sliit.musicstore.utils.ServletUtil;

public class IndexServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        try {
            User loggedUser = ServletUtil.applyAdminAuthMiddleware(req);

            req.setAttribute("pageTitle", "Admin Dashboard");
            req.setAttribute("pageName", "Dashboard");
		    req.getRequestDispatcher("admin/dashboard.jsp").forward(req, res);
        } catch (Exception e){
            res.sendRedirect(req.getContextPath()+"/admin/login");
        }
    }
}