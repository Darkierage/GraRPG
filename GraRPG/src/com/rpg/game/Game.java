/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Klasa odpowiedzialna za uruchomienie gry i jej głównej pętli
 *
 * @author Konrad
 */
public class Game
{
    GameLogic game;

    public Game()
    {
	this.game = new GameLogic();
    }

    /**
     * Uruchamia gre:
     * inicjalizuje wyświetlanie
     * inicjalizuje openGL'a
     * uruchamia główną pętlę gry
     * sprząta po zakończeniu
     */
    public void startGame()
    {
	initDisplay(1280, 720);
	initGL();
	mainLoop();
	cleanUp();
    }

    /**
     * Uruchmia możliwość wyświetlania oraz ustawia rozdzielczość okna
     *
     * @param width DisplayMode width
     * @param height DisplayMode height
     */
    private void initDisplay(int width, int height)
    {
	try
	{
	    Display.setDisplayMode(new DisplayMode(width, height));
	    Display.create();
	    Display.setVSyncEnabled(true);
	    Keyboard.create();
	    Mouse.create();
	} catch (LWJGLException ex)
	{
	    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Inicjalizuje openGL'a
     */
    private void initGL()
    {
	/*	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
	glMatrixMode(GL_MODELVIEW);
	glDisable(GL_DEPTH_TEST);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glClearColor(0.6f, 0.6f, 0.6f, 1f);
	GL11.glEnable(GL11.GL_TEXTURE_2D);
	GL11.glEnable(GL11.GL_BLEND);
	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);*/
	 
        GL11.glEnable(GL11.GL_TEXTURE_2D);               
         
        GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);          
         
            // enable alpha blending
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         
        GL11.glViewport(0,0,1280, 720);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 1280, 720, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	
    }

    /**
     * Sprzątanie po urządzeniach wyjścia i wejścia
     */
    private void cleanUp()
    {
	Display.destroy();
	Keyboard.destroy();
	Mouse.destroy();
    }

    /**
     * Główna pętla gry
     */
    private void mainLoop()
    {
	while (!Display.isCloseRequested())
	{
	    game.update();
	    game.render();
	    game.getInput();
	    Display.update();
	    if(game.isEnded())
		break;
	}
    }
}
