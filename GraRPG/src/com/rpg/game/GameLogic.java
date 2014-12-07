/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.game;

import com.rpg.draws.Render;
import com.rpg.gameObject.GOPlayer;
import com.rpg.gameObject.GOTerrain;
import com.rpg.map.Map;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Konrad
 */
public class GameLogic
{

    private final int[][] colorCode;
    ArrayList<GOTerrain> terrain;
    GOPlayer player;
    Render renderer;

    public GameLogic()
    {
	Map map = new Map();
	renderer = new Render();
	renderer.zrobilemFunkcjeBoNieChceMiSieMyslecJakZaladowacObrazki();
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
		else if(colorCode[i][j] == 1)
		    terrain.add(new GOTerrain(false, posx, posy, 40, 40));
		else if(colorCode[i][j] == 3)
		{
		    player = new GOPlayer(posx, posy, 40, 40);
		    terrain.add(new GOTerrain(true, posx, posy, 40, 40));
		}
		posx += 40;
	    }
	    posy +=40;
	    posx = 0;
	}
	return true;
    }

    void render()
    {
	renderer.renderTerrain(terrain);
	renderer.renderPlayer(player);
    }

    void getInput()
    {
	if(Keyboard.isKeyDown(Keyboard.KEY_W))
	    player.moveY(-1);
	else if(Keyboard.isKeyDown(Keyboard.KEY_S))
	    player.moveY(1);
	else if(Keyboard.isKeyDown(Keyboard.KEY_D))
	    player.moveX(1);
	else if(Keyboard.isKeyDown(Keyboard.KEY_A))
	    player.moveX(-1);
    }
}
