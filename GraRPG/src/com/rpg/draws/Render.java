/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.draws;

import com.rpg.game.Game;
import com.rpg.gameObject.GOPlayer;
import com.rpg.gameObject.GOTerrain;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

/**
 *
 * @author Konrad
 */
public class Render
{

    Texture wallTex;
    Texture grassTex;
    Texture playerFrontTex;
    Texture playerRightTex;
    Texture playerBackTex;
    Texture playerLeftTex;
    private boolean flag;

    public Render()
    {
	flag = true;
    }
    
    
    public void renderTerrain(ArrayList<GOTerrain> terrain)
    {
	if (flag)
	{
	    URL url = null;
	    grassTex = null;
	    wallTex = null;
	    playerFrontTex = null;
	    glClear(GL_COLOR_BUFFER_BIT);
	    glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	    try
	    {
		url = new File("Grass.png").toURI().toURL();
		grassTex = new Texture(url);
		url = new File("Wall.png").toURI().toURL();
		wallTex = new Texture(url);
		url = new File("HeroFront.png").toURI().toURL();
		playerFrontTex = new Texture(url);
		url = new File("HeroRight.png").toURI().toURL();
		playerRightTex = new Texture(url);
		url = new File("HeroBack.png").toURI().toURL();
		playerBackTex = new Texture(url);
		url = new File("HeroLeft.png").toURI().toURL();
		playerLeftTex = new Texture(url);
	    } catch (MalformedURLException ex)
	    {
		Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex)
	    {
		Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    flag = false;
	}
	for(GOTerrain t : terrain)
	{
	    if(t.CanIStandOn() == true)
		Draw.rect(t.getX(), t.getY(), t.getSx(), t.getSx(), grassTex);
	    else
		Draw.rect(t.getX(), t.getY(), t.getSx(), t.getSx(), wallTex);
	}
    }

    public void zrobilemFunkcjeBoNieChceMiSieMyslecJakZaladowacObrazki()
    {
	
    }
    
    public void renderPlayer(GOPlayer player)
    {
	if (player.getDirection() == 1)
	    Draw.rect(player.getX(), player.getY(), player.getSx()+10, player.getSx()+10, playerFrontTex);
	else if (player.getDirection() == 2)
	    Draw.rect(player.getX(), player.getY(), player.getSx()+10, player.getSx()+10, playerRightTex);
	else if (player.getDirection() == 0)
	    Draw.rect(player.getX(), player.getY(), player.getSx()+10, player.getSx()+10, playerLeftTex);
	else
	    Draw.rect(player.getX(), player.getY(), player.getSx()+10, player.getSx()+10, playerBackTex);
    }
    
}
