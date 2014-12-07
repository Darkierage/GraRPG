/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.date;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkrage
 * Obsluga systemu logowania, rejestracji, usunięcia, zmiany hasła uzytkownika
 */
public class AccountManagment 
{
    User userN;
    
    public AccountManagment(){}
        
    public AccountManagment(User u) 
    {
        this.userN = u;
    }
    
    public boolean RegisterAccount()
    {       
        File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");      
        if(newFile.exists() && !newFile.isDirectory())
        {            
            return false;
        }
        try
        {
            newFile.createNewFile();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            PrintWriter newWriter = new PrintWriter("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");
            newWriter.println(userN.getPassword());
            newWriter.close();
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    void RemoveAccount()
    {
        
    }
    
    public boolean LogIn()
    {
        File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");
        if(!newFile.exists())
        {            
            return false;
        }
        try 
        {
            Scanner newScanner = new Scanner(newFile);
            String pass = newScanner.nextLine();           
            if(pass.equals(userN.getPassword()))
            {
                return true;
            }
            else
                return false;
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        return true;
    }
    
    void LogOut()
    {
        
    }
    
    
}
