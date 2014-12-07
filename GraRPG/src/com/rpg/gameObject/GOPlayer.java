/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.gameObject;

import com.rpg.physics.Collision;

/**
 *
 * @author Konrad
 */
public class GOPlayer extends GameObject
{
    private final float SPEED = 2f;
    private int direction;

    public int getDirection()
    {
	return direction;
    }

    public GOPlayer(float x, float y, float sx, float sy)
    {
	super(x, y, sx, sy);
    }

    @Override
    public void update()
    {
	
    }

    public void moveY(int magnitude)
    {
	for(int i=0; i<SPEED; i++)
	{
	    this.y += magnitude;
	}
	direction = 2 - magnitude; //do góry = 3, w dół = 1
    }

    public void moveX(int magnitude)
    {
	for(int i=0; i<SPEED; i++)
	{
	    this.x += magnitude;
	}
	direction = 1 - magnitude; //W prawo = 0, w lewo = 2
    }
    
}
