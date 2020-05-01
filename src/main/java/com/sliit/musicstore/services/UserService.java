package com.sliit.musicstore.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sliit.musicstore.dao.UserDAO;
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
        User user = UserDAO.findByEmail(email);

        if(user == null){
            throw new MusicStoreException("Can not find a user for the given email address.");
        }

        try {
            if(user.getPassword() != hashPassword(password)){
                throw new MusicStoreException("Passwords not matching!");
            }
            
        } catch (NoSuchAlgorithmException e){
            throw e;
        }

        loggedUser = user;

        return user;
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
}