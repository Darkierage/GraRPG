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
public abstract class GameObject implements Comparable<GameObject>
{
    /**
     * Współrzędna x
     */
    protected float x;
    /**
     * Współrzędna y
     */
    protected float y;
    /**
     * Szerokość obiektu
     */
    protected float sx;
    /**
     * Wysokość obiektu
     */
    protected float sy;
    /**
     * Kod obrazka do wyświetlenia
     */
    protected int imageCode;

    /**
     * Konstruktor domyślny.
     * @param x pozycja na osi OX
     * @param y pozycja na osi OY
     * @param sx szerokość obiektu
     * @param sy wysokość obiektu
     * @param imageCode kod obrazka do renderingu
     */
    public GameObject(float x, float y, float sx, float sy, int imageCode)
    {
	this.x = x;
	this.y = y;
	this.sx = sx;
	this.sy = sy;
	this.imageCode = imageCode;
    }

    /**
     * Zwraca kod obrazka przypisanego do obiektu
     * @return kod obrazka do renderingu
     */
    public int getImageCode()
    {
	return imageCode;
    }
    
    /**
     * Aktualizacja stanu obiektu
     */
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

    @Override
    public int compareTo(GameObject o)
    {
	double vectorSize = Math.sqrt(o.x * o.x + o.y * o.y);
	double vector2Size = Math.sqrt(this.x * this.x + this.y * this.y);
	if (vector2Size > vectorSize)
	    return 2;
	else if (vector2Size == vectorSize)
	    return 0;
	else
	    return -2;
    }
}
