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
public class GOPlayer extends GameObject
{
    private final float SPEED = 2f;
    private int direction;
    ArrayList<GOTerrain> walls;

    public int getDirection()
    {
	return direction;
    }

    public GOPlayer(float x, float y, float sx, float sy, ArrayList<GOTerrain> walls, int imageCode)
    {
	super(x, y, sx, sy, imageCode);
	this.walls = walls;
    }

    @Override
    public void update()
    {
	
    }

    public void moveY(int magnitude)
    {
	for(int i=0; i<SPEED; i++)
	{
	    if(!Collision.checkIntersection(this, walls))
		this.y += magnitude;
	    else
		this.y -= magnitude;
	}
	direction = 2 - magnitude; //do góry = 3, w dół = 1
    }

    public void moveX(int magnitude)
    {
	for(int i=0; i<SPEED; i++)
	{
	    if(!Collision.checkIntersection(this, walls))
		this.x += magnitude;
	    else
		this.x -= magnitude;
	}
	direction = 1 - magnitude; //W prawo = 0, w lewo = 2
    }
    
}
