package com.sliit.musicstore.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sliit.musicstore.exceptions.MusicStoreException;
import com.sliit.musicstore.models.User;
import com.sliit.musicstore.services.UserService;

public class ServletUtil {
    public static User applyAdminAuthMiddleware(HttpServletRequest req) throws MusicStoreException{
        HttpSession session = req.getSession();

        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        User user = UserService.loginById(userId);

        if(user==null){
            throw new MusicStoreException("Unauthenticated user access.");
        }

        req.setAttribute("user", user);

        return user;
    }
}