/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.game;

import com.rpg.draws.Render;
import com.rpg.gameObject.GOPlayer;
import com.rpg.gameObject.GOProjectile;
import com.rpg.gameObject.GOTerrain;
import com.rpg.gameObject.GameObject;
import com.rpg.map.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Konrad
 */
public class GameLogic
{

    private final int[][] colorCode;
    ArrayList<GOTerrain> walls;
    ArrayList<GOTerrain> border;
    ArrayList<GameObject> objects;
    GOPlayer player;
    Render renderer;
    Thread t1;
    GOProjectile spell = null;

    public GameLogic()
    {
	Map map = new Map();
	renderer = new Render();
	colorCode = map.getColorCodes();
	walls = new ArrayList<>();
	border = new ArrayList<>();
	createObjects();
	t1 = new Thread(new Runnable()
	{

	    @Override
	    public void run()
	    {
		try
		{
		    Thread.sleep(1000);
		} catch (InterruptedException ex)
		{
		    Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	});
    }

    public void update()
    {
	if (spell != null)
	{
	    if (spell.isExists())
		spell.update();
	    else
		spell = null;
	}
	    
    }

    private boolean createObjects()
    {
	if (colorCode == null)
	{
	    return false;
	}
	int posx = 0, posy = 0;

	for (int i = 0; i < colorCode.length; i++)
	{
	    walls.add(new GOTerrain(0, posy, 10, 40, 0));
	    walls.add(new GOTerrain(posy, 0, 40, 10, 0));
	    for (int j = 0; j < colorCode[i].length; j++)
	    {
		if (colorCode[i][j] == 2)
		{
		    walls.add(new GOTerrain(posx, posy, 10, 10, 3));
		}
		if (colorCode[i][j] == 1)
		{
		    walls.add(new GOTerrain(posx, posy, 40, 40, 1));
		} else if (colorCode[i][j] == 3)
		{
		    player = new GOPlayer(posx, posy, 40, 40, walls, 2);
		    //terrain.add(new GOTerrain(true, posx, posy, 40, 40));
		}
		posx += 40;
	    }
	    posy += 40;
	    posx = 0;
	}
	objects = new ArrayList<>(walls);
	walls.addAll(border);
	objects.add(player);
	return true;
    }

    void render()
    {
	renderer.renderTerrain();
	Collections.sort(objects);
	renderer.renderObjects(new ArrayList<GameObject>(border));
	renderer.renderObjects(objects);
	if(spell != null)
	    renderer.renderSpell(spell);
    }

    void getInput()
    {
	if (Keyboard.isKeyDown(Keyboard.KEY_W))
	{
	    player.moveY(-1);
	} else if (Keyboard.isKeyDown(Keyboard.KEY_S))
	{
	    player.moveY(1);
	} else if (Keyboard.isKeyDown(Keyboard.KEY_D))
	{
	    player.moveX(1);
	} else if (Keyboard.isKeyDown(Keyboard.KEY_A))
	{
	    player.moveX(-1);
	}
	if (Mouse.isButtonDown(0))
	{
	    if (!t1.isAlive())
	    {
		//objects.add(new GOTerrain(Mouse.getX(), Display.getHeight() - Mouse.getY(), 10, 10, 3));
		spell = new GOProjectile(player.getX(), player.getY(), 10, 10, 5, Mouse.getX(), Display.getHeight() -  Mouse.getY(), walls);
		t1 = new Thread(new Runnable()
		{

		    @Override
		    public void run()
		    {
			try
			{
			    Thread.sleep(200);
			} catch (InterruptedException ex)
			{
			    Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
			}
		    }
		});
		t1.start();
	    }
	}
    }
}
