/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.draws;

import com.rpg.game.Game;
import com.rpg.gameObject.GOPlayer;
import com.rpg.gameObject.GOProjectile;
import com.rpg.gameObject.GameObject;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

/**
 * Wyświetlanie obiektów.
 * @author Konrad
 */
public class Render
{

    Texture wallTex;
    Texture grassTex;
    Texture waterTex;
    Texture spellTex;
    Texture monster1Tex;
    Texture playerFrontTex;
    Texture playerRightTex;
    Texture playerBackTex;
    Texture playerLeftTex;
    Texture treeTex;
    Texture gameOverTex;
    Texture bloodTex;
    private boolean flag;

    public Render()
    {
	flag = true;
    }
    
    /**
     * Przypisuje obrazki do obiektów oraz wyświetla obszar gry
     */
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
		url = new File("Monster1Front.png").toURI().toURL();
		monster1Tex = new Texture(url);
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
		url = new File("GameOver.png").toURI().toURL();
		gameOverTex = new Texture(url);
		url = new File("Blood.png").toURI().toURL();
		bloodTex = new Texture(url);
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
    
    /**
     * Wyświetla ściany.
     * @param walls lista ścian
     */
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
	    else if (t.getImageCode() == 2)
		renderPlayer((GOPlayer)t);
	    else if (t.getImageCode() == 7)
		Draw.rect(t.getSx(), t.getSy(), 40, 40, bloodTex);
	    else
		Draw.rect(t.getX(), t.getY(), t.getSx(), t.getSx(), monster1Tex);
	}
    }

    /**
     * Wyświetlanie gracza wraz z prostą animacją kierunku ruchu.
     * @param player Obiekt reprezentujący gracza
     */
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
    
    /**
     * Wyświetla pocisk wystrzelony przez gracza.
     * @param spell Obiekt reprezentujący pocisk
     */
    public void renderSpell(GOProjectile spell)
    {
	Draw.rect(spell.getX(), spell.getY(), spell.getSx(), spell.getSy(), spellTex);
    }

    /**
     * Wyświetlenie dużego napisu GAME OVER
     */
    public void gameOver()
    {
	Draw.rect(100, 100, 1000, 600, gameOverTex);
    }
    
}
