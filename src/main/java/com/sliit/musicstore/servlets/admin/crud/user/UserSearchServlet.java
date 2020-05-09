package com.sliit.musicstore.servlets.admin.crud.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sliit.musicstore.utils.ServletUtil;

public class UserSearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        try {
            ServletUtil.applyAdminAuthMiddleware(req);

            req.setAttribute("pageTitle", "Manage Users");
            req.setAttribute("pageName", "Users");

            req.getRequestDispatcher("search.jsp").forward(req, res);
        } catch (Exception e){
            res.setStatus(403);
        }
	}
}