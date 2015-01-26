/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.gameObject;

import com.rpg.physics.Collision;
import java.util.ArrayList;

/**
 * Reprezentacja pocisków oraz innych przemieszczających się obiektów
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
    
    /**
     * 
     * @param x współrzędna x
     * @param y współrzędna y
     * @param sx szerokość obiektu
     * @param sy wysokość obiektu
     * @param imageCode kod obrazka do renderingu
     * @param targetX współrzędna x celu, w którym poruszał się będzie pocisk
     * @param targetY współrzędna y celu, w którym poruszał się będzie pocisk
     * @param objects lista obiektów, które mogą zatrzymać pocisk
     */
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

    /**
     * Sprawdza czy pocisk istnieje
     * @return false jeśli pocisk w coś trafił
     */
    public boolean isExists()
    {
	return exists;
    }
    
}
