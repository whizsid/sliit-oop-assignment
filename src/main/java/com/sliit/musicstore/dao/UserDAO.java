package com.sliit.musicstore.dao;


import java.util.List;

import com.sliit.musicstore.models.User;
import com.sliit.musicstore.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO {

    public static User findByEmail(String email){
        Session currentSession = HibernateUtil.openCurrentSession();

        Query<User> query = currentSession.createQuery("from User where email=:email");

        query.setParameter("email", email);
        List<User> users = (List<User>) query.list();

        return users.get(0);
    }

    public static User find(int id){
        Session currentSession = HibernateUtil.openCurrentSession();

        User user = currentSession.find(User.class, id);

        return user;
    }

    public static void update(User user){
        Session currentSession = HibernateUtil.openCurrentSession();

        currentSession.update(user);
    }

    public static void delete(int id){
        Session currentSession = HibernateUtil.openCurrentSession();

        currentSession.delete(id);
    }
}