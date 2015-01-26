/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.gameObject;

import com.rpg.physics.Collision;
import java.util.ArrayList;

/**
 * Reprezentacja gracza w grze.
 * @author Konrad
 */
public class GOPlayer extends GameObject
{
    private final float SPEED = 2f;
    private int direction;
    ArrayList<GOTerrain> walls;

    /**
     * Zwraca ostatni kierunek ruchu
     * @return do góry = 3, w dół = 1, w prawo = 0, w lewo = 2
     */
    public int getDirection()
    {
	return direction;
    }
    
    /**
     * 
     * @param x współrzędna x
     * @param y współrzędna y
     * @param sx szerokość obiektu
     * @param sy wysokość obiektu
     * @param walls lista ścian, które będą blokowały obiekt
     * @param imageCode kod obrazka do renderingu
     */
    public GOPlayer(float x, float y, float sx, float sy, ArrayList<GOTerrain> walls, int imageCode)
    {
	super(x, y, sx, sy, imageCode);
	this.walls = walls;
    }

    @Override
    public void update()
    {
	
    }

    /**
     * Przemieszczenie gracza wzdłuż osi OY
     * @param magnitude mnożnik prędkości gracza. Wartości ujemne przemieszczają gracza w doł, a dodatnie w górę
     */
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
    
    /**
     * Przemieszczenie gracza wzdłuż osi OX
     * @param magnitude mnożnik prędkości gracza. Wartości ujemne przemieszczają gracza w lewo, a dodatnie w prawo
     */
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
