package com.sliit.musicstore.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


    private static Session currentSession;
     
    private static Transaction currentTransaction;

    public static Session openCurrentSession(){
        if(currentSession!=null){
            return currentSession;
        }

        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public static Transaction openTransaction(){
        if(currentTransaction!=null){
            return currentTransaction;
        }

        Session session = openCurrentSession();

        currentTransaction =  session.beginTransaction();

        return currentTransaction;
    }

    public static void closeTransaction(){
        if(currentTransaction==null){
            return;
        }

        currentTransaction.commit();

        currentTransaction = null;
    }

    protected static final SessionFactory getSessionFactory(){
        try {
            Configuration config = new Configuration();
            return config.configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }  
}