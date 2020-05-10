package com.sliit.musicstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.sliit.musicstore.models.UserType;
import com.sliit.musicstore.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserTypeDao {
    public static List<UserType> getAll(){
        Session currentSession = HibernateUtil.openCurrentSession();

        Transaction transaction = currentSession.beginTransaction();

        try {
            Query<UserType> query = currentSession.createQuery("from UserType");

            List<UserType> userTypes = (List<UserType>) query.list();

            transaction.commit();
            return userTypes;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e);
            return new ArrayList<UserType>();
        }
    }

    public static UserType find(int id){
        Session currentSession = HibernateUtil.openCurrentSession();

        UserType userType = currentSession.find(UserType.class, id);

        return userType;
    }
}