/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.draws;

import com.rpg.game.Game;
import com.rpg.gameObject.GOPlayer;
import com.rpg.gameObject.GOProjectile;
import com.rpg.gameObject.GOTerrain;
import com.rpg.gameObject.GameObject;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
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
    Texture waterTex;
    Texture spellTex;
    Texture playerFrontTex;
    Texture playerRightTex;
    Texture playerBackTex;
    Texture playerLeftTex;
    Texture treeTex;
    private boolean flag;

    public Render()
    {
	flag = true;
    }
    
    public void renderTerrain()
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
		url = new File("GrassBack2.png").toURI().toURL();
		grassTex = new Texture(url);
		url = new File("Brick_Wall.png").toURI().toURL();
		wallTex = new Texture(url);
		url = new File("Water.png").toURI().toURL();
		waterTex = new Texture(url);
		url = new File("Tree.png").toURI().toURL();
		treeTex = new Texture(url);
		url = new File("Fireball.png").toURI().toURL();
		spellTex = new Texture(url);
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
	Draw.rect(0, 0, Display.getWidth(), Display.getHeight(), grassTex);
    }
    
    public void renderObjects(ArrayList<GameObject> walls)
    {
	for(GameObject t : walls)
	{
	    if(t.getImageCode() == 1)
		Draw.rect(t.getX(), t.getY(), t.getSx()+15, t.getSy()+15, wallTex);
	    else if (t.getImageCode() == 3)
		Draw.rect(t.getX()-25, t.getY()-25, 64, 64, treeTex);
	    else if (t.getImageCode() == 0)
		Draw.rect(t.getX(), t.getY(), t.getSx(), t.getSy(), waterTex);
	    else
		renderPlayer((GOPlayer)t);
	}
    }

    
    public void renderPlayer(GOPlayer player)
    {
	if (player.getDirection() == 1)
	    Draw.rect(player.getX(), player.getY(), player.getSx(), player.getSx(), playerFrontTex);
	else if (player.getDirection() == 2)
	    Draw.rect(player.getX(), player.getY(), player.getSx(), player.getSx(), playerRightTex);
	else if (player.getDirection() == 0)
	    Draw.rect(player.getX(), player.getY(), player.getSx(), player.getSx(), playerLeftTex);
	else
	    Draw.rect(player.getX(), player.getY(), player.getSx(), player.getSx(), playerBackTex);
    }
    
    public void renderSpell(GOProjectile spell)
    {
	Draw.rect(spell.getX(), spell.getY(), spell.getSx(), spell.getSy(), spellTex);
    }
    
}
