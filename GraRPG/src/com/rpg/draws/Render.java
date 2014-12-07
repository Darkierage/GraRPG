/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.draws;

import com.rpg.game.Game;
import com.rpg.gameObject.GOTerrain;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

/**
 *
 * @author Konrad
 */
public class Render
{

    public static void renderTerrain(ArrayList<GOTerrain> terrain)
    {
	URL grass = null;
	URL wall = null;
	Texture grassTex = null;
	Texture wallTex = null;
	glClear(GL_COLOR_BUFFER_BIT);
	try
	{
	    grass = new File("Grass.png").toURI().toURL();
	    grassTex = new Texture(grass);
	    wall = new File("Wall.png").toURI().toURL();
	    wallTex = new Texture(wall);
	} catch (MalformedURLException ex)
	{
	    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex)
	{
	    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	}
	for(GOTerrain t : terrain)
	{
	    if(t.CanIStandOn() == true)
		Draw.rect(t.getX(), t.getY(), t.getSx(), t.getSx(), grassTex);
	    else
		Draw.rect(t.getX(), t.getY(), t.getSx(), t.getSx(), wallTex);
	}
    }
    
}
