package com.sliit.musicstore.controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sliit.musicstore.models.User;
import com.sliit.musicstore.utils.ServletUtil;

public class IndexController extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        try {
            User loggedUser = ServletUtil.applyAdminAuthMiddleware(req);
        } catch (Exception e){
            res.sendRedirect(req.getContextPath()+"/admin/login");
        }
    }
}