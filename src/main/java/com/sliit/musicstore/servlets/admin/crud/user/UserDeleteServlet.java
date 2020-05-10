package com.sliit.musicstore.servlets.admin.crud.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sliit.musicstore.models.User;
import com.sliit.musicstore.services.UserService;
import com.sliit.musicstore.utils.ServletUtil;

public class UserDeleteServlet extends HttpServlet{
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

            UserService.delete(user);
            res.sendRedirect(req.getContextPath()+"/admin/user/search?deleted="+user.getId());
        } catch(NumberFormatException e){
            res.setStatus(404);
            return;
        }

    }
}