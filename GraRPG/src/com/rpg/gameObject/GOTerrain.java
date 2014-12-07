/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.gameObject;

/**
 * Obiekt reperezentujący ściany i podłoże
 * @author Konrad
 */
public class GOTerrain extends GameObject
{
    
    private final boolean canIStandOn;

    /**
     * 
     * @param canIStandOn określa czy obiekt jest ścianą czy podłożem
     * @param x współrzędna x lewego dolnego rogu
     * @param y współrzędna y lewego dolnego rogu
     * @param sx szerokość obiektu
     * @param sy wysokość obiektu
     */
    public GOTerrain(boolean canIStandOn, float x, float y, float sx, float sy)
    {
	super(x, y, sx, sy);
	this.canIStandOn = canIStandOn;
    }
    
    @Override
    public void update()
    {
	
    }

    public boolean CanIStandOn()
    {
	return canIStandOn;
    }
    
    
}
