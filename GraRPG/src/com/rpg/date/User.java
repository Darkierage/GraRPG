/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.date;

/**
 * Klasa reprezentujaca uzytkownika z podstawowymi seterami i geterami
 * @author Darkrage
 */
public class User 
{
    String Login;
    String Password;

    public User(String Login, String Password) {
        this.Login = Login;
        this.Password = Password;
    }    
    /**
     * Ustawienie loginu
     * @param log aktualny login
     */
    void setLogin(String log)
    {
        this.Login = log;
    }
    /**
     * Ustawienie hasła
     * @param pass aktualne hasło
     */
    void setPassword(String pass)
    {
        this.Password = pass;
    }
    /**
     * Uzyskanie loginu
     * @return zmienna typu String z loginem
     */
    String getLogin()
    {      
        return Login;
    }
    /**
     * Uzyskanie hasła
     * @return zmienna typu String z hasłem
     */
    String getPassword()
    {
        return Password;
    }
}
