package com.sliit.musicstore.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.sliit.musicstore.dao.UserDao;
import com.sliit.musicstore.dao.UserTypeDao;
import com.sliit.musicstore.exceptions.MusicStoreException;
import com.sliit.musicstore.models.User;
import com.sliit.musicstore.models.UserType;

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

    /**
     * Returning the logged user details
     * 
     * @return Logged user details
     */
    public static User getLoggedUser(){
        return loggedUser;
    }

    /**
     * Login a user by incremental id
     * 
     * @param userId Incremental Id
     * @return Logged User
     */
    public static User loginById(int userId){
        User user = UserDao.find(userId);

        loggedUser = user;

        return user;
    }

    /**
     * Returning users by page and the offset
     * 
     * @param page
     * @param perPage Records Per Page
     * @return Results
     */
    public static List<User> getPaginatedResults(int page, int perPage){

        if(page<1){
            page = 1;
        }

        if(perPage<1){
            perPage = 25;
        }

        return UserDao.getPaginated(perPage, (perPage-1)*page);
    }

    /**
     * All users count
     * @return
     */
    public static Long count(){
        return UserDao.count();
    }

    /**
     * Fetching all user types
     * 
     * @return
     */
    public static List<UserType> getTypes(){
        return UserTypeDao.getAll();
    }

    /**
     * Creating a user
     * 
     * @param name Name of the user
     * @param email Email Address
     * @param userTypeId
     * @param password
     * @return
     */
    public static boolean create(String name, String email, int userTypeId, String password) 
    throws MusicStoreException {
        User user = new User();

        if(name==null || name.trim() ==""){
            throw new MusicStoreException("Invalid name provided.");
        }

        if(email==null || email.trim()==""){
            throw new MusicStoreException("Invalid email address provided.");
        }

        if(password ==null || password.trim()=="" || password.length()<3){
            throw new MusicStoreException("Invalid password provided. Password must contain at least 8 characters.");
        }

        if(userTypeId==0){
            throw new MusicStoreException("Please select a user type")  ;
        }

        User existUser = UserDao.findByEmail(email);
        if(existUser!=null){
            throw new MusicStoreException("A user is already signed in with this email address. Use another email address") ;
        }

        user.setName(name);
        user.setEmail(email);

        try {
            user.setPassword( hashPassword (password));
        } catch (NoSuchAlgorithmException e){
            throw new MusicStoreException("Something went wrong");
        }
        
        UserType userType = UserTypeDao.find(userTypeId);

        if(userType==null){
            throw new MusicStoreException("Please select a user type.") ;
        }

        user.setType(userType);

        try {
            return UserDao.createUser(user);
        } catch (Exception e){
            throw new MusicStoreException(e.getMessage());
        }
    }

    /**
     * Creating a user
     * 
     * @param name Name of the user
     * @param email Email Address
     * @param userTypeId
     * @param password
     * @return
     */
    public static boolean update(int id, String name, String email, int userTypeId, String password) 
    throws MusicStoreException {
        User user = UserService.find(id);

        if(user==null){
            throw new MusicStoreException("Can not find a user to update");
        }

        if(name==null || name.trim() ==""){
            throw new MusicStoreException("Invalid name provided.");
        }

        if(email==null || email.trim()==""){
            throw new MusicStoreException("Invalid email address provided.");
        }

        if(password ==null || password.trim()=="" || password.length()<3){
            throw new MusicStoreException("Invalid password provided. Password must contain at least 8 characters.");
        }

        if(userTypeId==0){
            throw new MusicStoreException("Please select a user type")  ;
        }

        User existUser = UserDao.findByEmail(email);
        if(existUser!=null && existUser.getId()!= user.getId()){
            throw new MusicStoreException("A user is already signed in with this email address. Use another email address") ;
        }

        user.setName(name);
        user.setEmail(email);

        try {
            user.setPassword( hashPassword (password));
        } catch (NoSuchAlgorithmException e){
            throw new MusicStoreException("Something went wrong");
        }
        
        UserType userType = UserTypeDao.find(userTypeId);

        if(userType==null){
            throw new MusicStoreException("Please select a user type.") ;
        }

        user.setType(userType);

        try {
            return UserDao.updateUser(user);
        } catch (Exception e){
            throw new MusicStoreException(e.getMessage());
        }
    }


    public static User find(int id){
        return UserDao.find(id);
    }

    public static void delete(User user){
        UserDao.delete(user);
    }
}