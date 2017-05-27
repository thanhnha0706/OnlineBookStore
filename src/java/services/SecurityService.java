/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityService {
    
    private String RANDOM_SALT = "asdiuq8hnc8idsc";
    
    public String hashPassword(String UserPassword) throws NoSuchAlgorithmException{
        String password = UserPassword + this.RANDOM_SALT;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(password.getBytes(),0,password.length());
        return new BigInteger(1,m.digest()).toString(16);
    }
}
