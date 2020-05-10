package com.sliit.musicstore.servlets.admin.crud.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sliit.musicstore.models.UserType;
import com.sliit.musicstore.services.UserService;
import com.sliit.musicstore.utils.ServletUtil;

public class UserCreateServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        try {
            ServletUtil.applyAdminAuthMiddleware(req);

        } catch (Exception e){
            res.setStatus(403);
            return;
        }

        List<UserType> userTypes = UserService.getTypes();

        req.setAttribute("pageTitle", "Create A User");
        req.setAttribute("pageName", "Users");
        req.setAttribute("userTypes", userTypes);
        req.getRequestDispatcher("create.jsp").forward(req, res);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int userTypeId = Integer.parseInt(req.getParameter("userType"));
        String password = req.getParameter("password");

        try {
            boolean created = UserService.create(name, email, userTypeId, password);
            if(created){
                req.setAttribute("success", "Successfully created the user.");
            } else {
                req.setAttribute("error", "Something went wrong!");
            }
        } catch (Exception e){
            req.setAttribute("error", e.getMessage());
            req.setAttribute("name", name);
            req.setAttribute("email", email);
            req.setAttribute("userType", userTypeId);
        }

        List<UserType> userTypes = UserService.getTypes();
        req.setAttribute("pageTitle", "Create A User");
        req.setAttribute("pageName", "Users");
        req.setAttribute("userTypes", userTypes);

        req.getRequestDispatcher("create.jsp").forward(req, res);
    }
}