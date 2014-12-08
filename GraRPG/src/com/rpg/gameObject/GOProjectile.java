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
public class GOProjectile extends GameObject
{

    private float speed = 6;
    private float speedX;
    private float speedY;
    private boolean exists = true;
    ArrayList<GOTerrain> objects;
    private boolean test;
    
    public GOProjectile(float x, float y, float sx, float sy, int imageCode, float targetX, float targetY, ArrayList<GOTerrain> objects)
    {
	super(x, y, sx, sy, imageCode);
	float vectorLength = (float) Math.sqrt(Math.pow(targetX - x, 2)+Math.pow(targetY - y, 2));
	float cosAlpha = (targetX - x) / vectorLength;
	speedX = cosAlpha * speed;
	float sinAlpha = (targetY - y) / vectorLength;
	speedY = sinAlpha * speed;
	this.objects = objects;
    }

    @Override
    public void update()
    {
	x += speedX;
	y += speedY;
	if (Collision.checkIntersection(this, objects))
	    exists = false;
    }

    public boolean isExists()
    {
	return exists;
    }
    
}
