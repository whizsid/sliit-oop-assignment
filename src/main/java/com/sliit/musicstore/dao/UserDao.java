package com.sliit.musicstore.dao;


import java.util.List;

import com.sliit.musicstore.models.User;
import com.sliit.musicstore.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDao {

    public static User findByLoginCredentials(String email, String password){
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();

        try {
            Query<User> query = currentSession.createQuery("from User where email=:email and password=:password ");

            query.setParameter("email", email);
            query.setParameter("password", password.trim());

            List<User> users = (List<User>) query.list();

            transaction.commit();

            if(users.size()==0)
                return null;

            return users.get(0);

        } catch (Exception e){
            transaction.rollback();
            return null;
        }

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