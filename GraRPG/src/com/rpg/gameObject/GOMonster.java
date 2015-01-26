/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.gameObject;

import com.rpg.physics.Collision;
import java.util.ArrayList;

/**
 * Klasa reprezentująca przeciwników gracza
 * @author Konrad
 */
public class GOMonster extends GameObject
{
    private final GOPlayer player;
    private final float speed = 2;
    private float speedX;
    private float speedY;
    ArrayList<GOTerrain> walls;
    private boolean alive = true;

    /**
     * Pozwala zmienić stan obiektu na martwy w celu eliminacji z gry
     * @param alive true jeśli obiekt bierze udział w rozgrywce, false jeśli został zabity.
     */
    public void setAlive(boolean alive)
    {
	this.alive = alive;
    }

    /**
     * 
     * @param x współrzędna x
     * @param y współrzędna y
     * @param sx szerokość obiektu
     * @param sy wysokość obiektu
     * @param imageCode kod obrazka do renderingu
     * @param player obiekt reprezentujący gracza
     * @param walls lista obiektów terenu
     */
    public GOMonster(float x, float y, float sx, float sy, int imageCode, GOPlayer player, ArrayList<GOTerrain> walls)
    {
	super(x, y, sx, sy, imageCode);
	this.player = player;
	this.walls = walls;
    }

    @Override
    public void update()
    {
	float vectorLength = (float) Math.sqrt(Math.pow(player.x - x, 2)+Math.pow(player.y - y, 2));
	if (vectorLength < 200 && alive)
	{
	    float cosAlpha = (player.x - x) / vectorLength;
	    speedX = cosAlpha * speed;
	    float sinAlpha = (player.y - y) / vectorLength;
	    speedY = sinAlpha * speed;
	    moveY();
	    moveX();
	}
    }
    
    /**
     * Zwraca stan obiektu
     * @return true jeśli żywy
     */
    public boolean isAlive()
    {
	return alive;
    }

    /**
     * Przemieszczenie obiektu wzdłuż osi OY
     */
    private void moveY()
    {
	if (!Collision.checkIntersection(new GOMonster(x-1, y-1, sx+1, sy+1, 1, player, walls), walls))
	    this.y += speedY;
	else{
	    this.y -= speedY;
	    moveX();
	}
	    
	
    }

    /**
     * Przemieszczenie obiektu wzdłuż osi OX
     */
    private void moveX()
    {
	if (!Collision.checkIntersection(new GOMonster(x-1, y-1, sx+1, sy+1, 1, player, walls), walls))
	    this.x += speedX;
	else
	{
	    this.y -= speedY;
	    moveY();
	}

    }
    
}
