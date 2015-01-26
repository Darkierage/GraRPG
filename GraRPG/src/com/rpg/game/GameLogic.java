/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.game;

import com.rpg.draws.Render;
import com.rpg.gameObject.GOMonster;
import com.rpg.gameObject.GOPlayer;
import com.rpg.gameObject.GOProjectile;
import com.rpg.gameObject.GOTerrain;
import com.rpg.gameObject.GameObject;
import com.rpg.map.Map;
import com.rpg.physics.Collision;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * Logika gry.
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
    ArrayList<GOMonster> monster = new ArrayList<>();
    private boolean ended = false;

    /**
     * Konstruktor.
     * Tworzy potrzebne zmienne, wątek oczekiwania oraz uruchamia funkcję tworzącą obiekty gry.
     */
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

    /**
     * Aktualizuje stan obiektów w grze. Przemieszcza, tworzy, niszczy oraz sprawdza kolizje.
     */
    public void update()
    {
	if (spell != null)
	{
	    if (spell.isExists())
	    {
		spell.update();
		ArrayList<GOMonster> toRemove = new ArrayList<>();
		for(GOMonster m : monster)
		{
		    if (Collision.checkIntersection(spell, m))
		    {
			m.setAlive(false);
			spell = null;
			toRemove.add(m);
			objects.add(new GOTerrain(1, 1, m.getX(), m.getY(), 7));
		    }
		}
		for (int i=0; i<toRemove.size(); i++)
		{
		    objects.remove(toRemove.get(0));
		    monster.remove(toRemove.get(0));
		}
	    }
	    else
		spell = null;
	}
	for (GOMonster m : monster)
	{
	    m.update();
	    if (m.getX() - player.getX() < 2 && m.getY() - player.getY() < 2)
		ended = true;
	}
	
    }

    /**
     * Tworzy obiekty na podstawie odczytu z pliku mapy.
     * @return false jeśli obiekty nie zostały odczytane z mapy.
     */
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
		} else if (colorCode[i][j] == 4)
		{
		    GOMonster m = new GOMonster(posx, posy, 40, 40, 6, player, walls);
		    monster.add(m);
		}
		posx += 40;
	    }
	    posy += 40;
	    posx = 0;
	}
	objects = new ArrayList<>(walls);
	walls.addAll(border);
	objects.add(player);
	for (GOMonster m : monster)
	    objects.add(m);
	return true;
    }

    /**
     * Uruchamia funkcje wyświetlające obiekty, należące do klasy Render.
     */
    void render()
    {
	renderer.renderTerrain();
	Collections.sort(objects);
	renderer.renderObjects(new ArrayList<GameObject>(border));
	renderer.renderObjects(objects);
	if(spell != null)
	    renderer.renderSpell(spell);
	if(ended)
	    renderer.gameOver();
    }

    /**
     * Pobiera dane wejściowe z myszy i klawiatury.
     */
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
		spell = new GOProjectile(player.getX()+10, player.getY()+10, 10, 10, 5, Mouse.getX(), Display.getHeight() -  Mouse.getY(), walls);
		t1 = new Thread(new Runnable()
		{

		    @Override
		    public void run()
		    {
			try
			{
			    Thread.sleep(2000);
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

    /**
     * Zwraca informacje czy gra została zakończona czy jednak trwa.
     * @return true jeśli gra została skończona
     */
    boolean isEnded()
    {
	if (ended)
	{
	    try
	    {
		Thread.sleep(2000);
	    } catch (InterruptedException ex)
	    {
		Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return ended;
    }
}
