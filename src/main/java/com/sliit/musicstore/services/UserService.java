package com.sliit.musicstore.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sliit.musicstore.dao.UserDao;
import com.sliit.musicstore.exceptions.MusicStoreException;
import com.sliit.musicstore.models.User;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.math.BigInteger;

public class UserService {

    protected static User loggedUser;

    /**
     * Sign in a user
     * 
     * @param email
     * @param password
     * @return
     * @throws MusicStoreException
     */
    public static User login(String email, String password) throws MusicStoreException, NoSuchAlgorithmException {
        System.out.println(email);
        
        try {
            User user = UserDao.findByLoginCredentials(email, hashPassword(password));

            if(user ==null){
                throw new MusicStoreException("Can not find a user for the given email address and password");
            }
            
            loggedUser = user;

            return user;
        } catch (NoSuchAlgorithmException e){
            throw new MusicStoreException("Internel server error.");
        }
    }

    /**
     * Hashing the user password
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    protected static String hashPassword(String data)
        throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(data.getBytes(UTF_8));
        return String.format("%032x%n", new BigInteger(1, digest));
    }

    public static void setLoggedUser(User user){
        loggedUser = user;
    }

    public static User getLoggedUser(){
        return loggedUser;
    }

    public static User loginById(int userId){
        User user = UserDao.find(userId);

        loggedUser = user;

        return user;
    }
}