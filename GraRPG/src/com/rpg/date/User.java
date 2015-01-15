/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.date;

/**
 *
 * @author Darkrage
 * Klasa reprezentujaca uzytkownika z podstawowymi seterami i geterami
 */
public class User 
{
    String Login;
    String Password;

    public User(String Login, String Password) {
        this.Login = Login;
        this.Password = Password;
    }    
    
    void setLogin(String log)
    {
        this.Login = log;
    }
    
    void setPassword(String pass)
    {
        this.Password = pass;
    }
    
    String getLogin()
    {      
        return Login;
    }
    
    String getPassword()
    {
        return Password;
    }
}
