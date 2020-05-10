package com.sliit.musicstore.servlets.admin.crud.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sliit.musicstore.exceptions.MusicStoreException;
import com.sliit.musicstore.models.User;
import com.sliit.musicstore.models.UserType;
import com.sliit.musicstore.services.UserService;
import com.sliit.musicstore.utils.ServletUtil;

public class UserUpdateServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        try {
            ServletUtil.applyAdminAuthMiddleware(req);
        } catch (Exception e){
            res.setStatus(403);
            return;
        }
        try {
            int id = Integer.parseInt(req.getParameter("userId"));

            User user = UserService.find(id);

            if(user==null){
                res.setStatus(404);
                return;
            }

            req.setAttribute("name", user.getName());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("userType", user.getType() != null? user.getType().getId(): null );
        } catch(NumberFormatException e){
            res.setStatus(404);
            return;
        }

        List<UserType> userTypes = UserService.getTypes();

        req.setAttribute("pageTitle", "Update A User");
        req.setAttribute("pageName", "Users");
        req.setAttribute("userTypes", userTypes);
        req.getRequestDispatcher("update.jsp").forward(req, res);

    }


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int userTypeId = Integer.parseInt(req.getParameter("userType"));
        String password = req.getParameter("password");

        try {
            int id = Integer.parseInt(req.getParameter("userId"));

            try {
                boolean updated = UserService.update(id, name, email, userTypeId, password);
                if(updated){
                    req.setAttribute("success", "Successfully updated the user.");
                } else {
                    req.setAttribute("error", "Something went wrong!");
                }
            } catch (MusicStoreException e){
                req.setAttribute("error", e.getMessage());
            }
        } catch(NumberFormatException e){
            res.setStatus(404);
            return;
        }

            
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("userType", userTypeId);

        List<UserType> userTypes = UserService.getTypes();
        req.setAttribute("pageTitle", "Create A User");
        req.setAttribute("pageName", "Users");
        req.setAttribute("userTypes", userTypes);

        req.getRequestDispatcher("update.jsp").forward(req, res);
    }
}