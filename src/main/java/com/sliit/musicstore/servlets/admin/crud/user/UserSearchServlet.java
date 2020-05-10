package com.sliit.musicstore.servlets.admin.crud.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sliit.musicstore.models.User;
import com.sliit.musicstore.services.UserService;
import com.sliit.musicstore.utils.ServletUtil;

public class UserSearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        try {
            ServletUtil.applyAdminAuthMiddleware(req);

        } catch (Exception e){
            res.setStatus(403);
            return;
        }

        String strPage = req.getParameter("page");
        String strPerPage = req.getParameter("perPage");

        int page = strPage == null?1: Integer.parseInt(strPage);
        int perPage = strPerPage ==null?25: Integer.parseInt(strPerPage);

        List<User> users = UserService.getPaginatedResults(page, perPage);
        Long count = UserService.count();

        req.setAttribute("pageTitle", "Manage Users");
        req.setAttribute("pageName", "Users");
        req.setAttribute("results", users);
        req.setAttribute("count", count);
        req.setAttribute("page", page<1?1:page);
        req.setAttribute("perPage", perPage<1?25:perPage);

        req.getRequestDispatcher("search.jsp").forward(req, res);
	}
}