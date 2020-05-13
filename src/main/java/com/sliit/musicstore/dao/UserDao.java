package com.sliit.musicstore.dao;


import java.util.ArrayList;
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

    public static void delete(User user){
        Session currentSession = HibernateUtil.openCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        try {
            currentSession.delete(user);
            currentSession.flush();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }

    public static List<User> getPaginated(int limit, int offset){
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();
        System.out.println(offset);
        try {
            Query<User> query = currentSession.createQuery("from User")
                .setFirstResult(offset)
                .setMaxResults(limit);

            List<User> users = (List<User>) query.getResultList();
            transaction.commit();
            return users;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e);
            return new ArrayList<User>();
        }
    }

    public static Long count(){
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();

        try {
            Query<Long> query = currentSession.createQuery("select count(*) from User");
            Long count = (Long) query.uniqueResult();
            transaction.commit();
            return count;
        } catch (Exception e){
            transaction.rollback();
            return new Long(0);
        }
        
    }

    public static boolean createUser(User user) throws Exception {
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();

        try {
            currentSession.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    public static User findByEmail(String email){
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();

        try {
            Query<User> query = currentSession.createQuery("from User where email=:email ");

            query.setParameter("email", email);

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

    public static boolean updateUser(User user) throws Exception {
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();

        try {
            currentSession.update(user);
            transaction.commit();
            return true;
        } catch (Exception e){
            transaction.rollback();
            throw e;
        }
    }

    public static List<User> getAll(){
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();
        try {
            Query<User> query = currentSession.createQuery("from User");
            List<User> users = (List<User>) query.getResultList();
            transaction.commit();
            return users;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e);
            return new ArrayList<User>();
        }
    }

}