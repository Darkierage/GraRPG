/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.gameObject;

import com.rpg.physics.Collision;
import java.util.ArrayList;

/**
 *
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

    public void setAlive(boolean alive)
    {
	this.alive = alive;
    }

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

    public boolean isAlive()
    {
	return alive;
    }

    private void moveY()
    {
	if (!Collision.checkIntersection(new GOMonster(x-1, y-1, sx+1, sy+1, 1, player, walls), walls))
	    this.y += speedY;
	else{
	    this.y -= speedY;
	    moveX();
	}
	    
	
    }

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
