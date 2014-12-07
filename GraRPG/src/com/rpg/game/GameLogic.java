/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.game;

import com.rpg.draws.Draw;
import com.rpg.draws.Render;
import com.rpg.draws.Texture;
import com.rpg.gameObject.GOTerrain;
import com.rpg.gameObject.GameObject;
import com.rpg.map.Map;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

/**
 *
 * @author Konrad
 */
public class GameLogic
{

    private final int[][] colorCode;
    ArrayList<GOTerrain> terrain;

    public GameLogic()
    {
	Map map = new Map();
	colorCode = map.getColorCodes();
	terrain = new ArrayList<>();
	createObjects();
    }

    public void update()
    {

    }

    private boolean createObjects()
    {
	if (colorCode == null)
	{
	    return false;
	}
	int posx = 0, posy = 0;
	
	for (int i = 0; i < colorCode.length; i++)
	{
	    for (int j = 0; j < colorCode[i].length; j++)
	    {
		if(colorCode[i][j] == 2)
		    terrain.add(new GOTerrain(true, posx, posy, 40, 40));
		else
		    terrain.add(new GOTerrain(false, posx, posy, 40, 40));
		posx += 40;
	    }
	    posy +=40;
	    posx = 0;
	}
	return true;
    }

    void render()
    {
	Render.renderTerrain(terrain);
    }
}
