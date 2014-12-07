/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.gameObject;

/**
 * Obiekt reperezentujący ściany i podłoże
 * @author Konrad
 */
public class GOTerain extends GameObject
{
    
    private final boolean canIStandOn;

    /**
     * 
     * @param canIStandOn określa czy obiekt jest ścianą czy podłożem
     */
    public GOTerain(boolean canIStandOn)
    {
	this.canIStandOn = canIStandOn;
    }
    
    
    @Override
    public void update()
    {
	
    }
    
}
