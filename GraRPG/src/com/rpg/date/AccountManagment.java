/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.date;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
            newWriter.print(userN.getPassword());
            newWriter.close();
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public void RemoveAccount()
    {
        File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");
        newFile.delete();
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
    
    public void CreateCharacter(String name) throws FileNotFoundException
    {
        File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");
                  
        try 
        {
            FileWriter newFileWriter = new FileWriter(newFile, true);
            BufferedWriter newBufferWritter = new BufferedWriter(newFileWriter);
            newBufferWritter.newLine();
            newBufferWritter.write(name);
            newBufferWritter.close();          
        } //cos
        catch (IOException ex) 
        {
            Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
    public boolean CheckifCharacterPossibility()
    {      
        File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");
               
        try 
        {
            Scanner newScanner = new Scanner(newFile);
            newScanner.nextLine();
            if(newScanner.hasNextLine())
            {
                newScanner.nextLine();
                if(newScanner.hasNextLine())
                {
                    return false;
                }  
                else
                    return true;
            }
            else
                return true;
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        return true;
    }
    
    public String getCharacterName(int n)
    {
        String temp = "<empty slot>";
        
        if(n == 1)
        {
            File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");       
            try 
            {
                Scanner newScanner = new Scanner(newFile);
                if(newScanner.hasNextLine())
                {
                    String pass = newScanner.nextLine();              
                    pass = null;
                    if(newScanner.hasNextLine())
                    {
                        pass = newScanner.nextLine();
                        temp = pass;                       
                    }
                }
                
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(n == 2)
        {
            File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");       
            try 
            {
                Scanner newScanner = new Scanner(newFile);
                if(newScanner.hasNextLine())
                {
                    String pass = newScanner.nextLine();           
                    pass = null;
                    if(newScanner.hasNextLine())
                    {
                        pass = newScanner.nextLine();
                        pass = null;
                        if(newScanner.hasNextLine())
                        {
                            pass = newScanner.nextLine();
                            temp = pass;
                        }
                    }
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return temp;
    }
    
    public void RemoveCharacter(int n)
    {
        if(n == 1)
        {
            File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");
            String temp;
            String temp2;
            try 
            {
                Scanner newScanner = new Scanner(newFile);
                
                temp = newScanner.nextLine();                
                newScanner.nextLine();       
                if(newScanner.hasNextLine())
                {
                    temp2 = newScanner.nextLine();
                    PrintWriter newPrintWriter = new PrintWriter(newFile);
                    newPrintWriter.print(temp);
                    newPrintWriter.close();

                    FileWriter newFileWriter = new FileWriter(newFile, true);
                    BufferedWriter newBuffuredWriter = new BufferedWriter(newFileWriter);
                    newBuffuredWriter.newLine();
                    newBuffuredWriter.write(temp2);    
                    newBuffuredWriter.close();
                }
                else
                {
                    Scanner newScanner2 = new Scanner(newFile);
                    newScanner2.nextLine();                  
                    PrintWriter newPrintWriter = new PrintWriter(newFile);
                    newPrintWriter.print(temp);
                    newPrintWriter.close();                                   
                }
                
                
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
            }                  
        }
        
        if(n == 2)
        {
            File newFile = new File("src/com/rpg/date/Accounts/" + userN.getLogin() + ".txt");
            String temp;
            String temp2;
            try 
            {
                Scanner newScanner = new Scanner(newFile);
                
                temp = newScanner.nextLine();
                temp2 = newScanner.nextLine();
                //newScanner.nextLine();                              
                               
                PrintWriter newPrintWriter = new PrintWriter(newFile);
                newPrintWriter.print(temp);
                newPrintWriter.close();
                
                FileWriter newFileWriter = new FileWriter(newFile, true);
                BufferedWriter newBuffuredWriter = new BufferedWriter(newFileWriter);
                newBuffuredWriter.newLine();
                newBuffuredWriter.write(temp2);    
                newBuffuredWriter.close();
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                Logger.getLogger(AccountManagment.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
        
    }
}
