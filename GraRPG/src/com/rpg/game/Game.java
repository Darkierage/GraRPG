/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.game;

import com.rpg.draws.Draw;
import com.rpg.draws.Write;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Class resposnible for main loop of game
 *
 * @author Konrad
 */
public class Game
{

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
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
	glMatrixMode(GL_MODELVIEW);
	glDisable(GL_DEPTH_TEST);
	glClearColor(0, 0, 0, 1);
    }

    /**
     * Sprzątanie
     */
    private void cleanUp()
    {
	Display.destroy();
    }

    private void mainLoop()
    {
	while (!Display.isCloseRequested())
	{
	    glClear(GL_COLOR_BUFFER_BIT);
	    Write.drawString("Konrad", 10, 10, 100);
	    Display.update();
	}
    }
}
