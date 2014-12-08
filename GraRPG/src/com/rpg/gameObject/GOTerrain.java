/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.gameObject;

/**
 * Obiekt reperezentujący ściany i podłoże
 *
 * @author Konrad
 */
public class GOTerrain extends GameObject
{

    /**
     *
     * @param x współrzędna x lewego dolnego rogu
     * @param y współrzędna y lewego dolnego rogu
     * @param sx szerokość obiektu
     * @param sy wysokość obiektu
     */
    public GOTerrain(float x, float y, float sx, float sy, int imageCode)
    {
	super(x, y, sx, sy, imageCode);
    }

    @Override
    public void update()
    {

    }

}
