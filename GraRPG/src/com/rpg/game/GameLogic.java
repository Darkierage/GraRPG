/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.game;

import com.rpg.map.Map;

/**
 *
 * @author Konrad
 */
public class GameLogic
{

    private final int[][] colorCode;

    public GameLogic()
    {
	Map map = new Map();
	colorCode = map.getColorCodes();
    }

    private void update()
    {

    }

    private boolean createObjects()
    {
	if (colorCode == null)
	{
	    return false;
	}
	for (int i = 0; i < colorCode.length; i++)
	{
	    for (int j = 0; j < colorCode[i].length; i++)
	    {

	    }
	}
	return true;
    }
}
