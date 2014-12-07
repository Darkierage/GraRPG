/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.gameObject;

/**
 * Abstrakcyjna klasa reprezentująca wszystkie obiekty w grze.
 *
 * @author Konrad
 */
public abstract class GameObject
{

    protected float x;
    protected float y;
    protected float sx;
    protected float sy;
    
    public abstract void update();

    /**
     * Położenie obiektu
     *
     * @return współrzędna x lewego dolnego rogu
     */
    public float getX()
    {
	return x;
    }

    /**
     * Położenie obiektu
     *
     * @param x współrzędna lewego dolnego rogu
     */
    public void setX(float x)
    {
	this.x = x;
    }

    /**
     * Położenie obiektu
     *
     * @return współrzędna y lewego dolnego rogu
     */
    public float getY()
    {
	return y;
    }

    /**
     * Położenie obiektu
     *
     * @param y współrzędna lewego dolnego rogu
     */
    public void setY(float y)
    {
	this.y = y;
    }

    /**
     * Rozmiar obiektu
     *
     * @return szerokość
     */
    public float getSx()
    {
	return sx;
    }

    /**
     * Rozmiar obiektu
     *
     * @param sx szerokość
     */
    public void setSx(float sx)
    {
	this.sx = sx;
    }

    /**
     * Rozmiar obiektu
     *
     * @return wysokość
     */
    public float getSy()
    {
	return sy;
    }

    /**
     * Rozmiar obiektu
     *
     * @param sy wysokość
     */
    public void setSy(float sy)
    {
	this.sy = sy;
    }
}
