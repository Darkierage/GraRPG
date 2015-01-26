/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.map;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Klasa wczytuje mapę gry
 * @author Konrad
 */
public class Map 
{

    private final File MAP;
    private BufferedImage mapImage;
    private int[] pixels;
    private Color[] pixelColors;

    /**
     * Konstruktor domyślny. Otwiera plik z mapą.
     */
    public Map()
    {
	this.MAP = new File("map.bmp");
	try
	{
	    mapImage = ImageIO.read(MAP);
	} catch (IOException ex)
	{
	    Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Odczytuje kolejne pixele z pliku mapy, tworząc mape bitową.
     */
    private void getPixels()
    {
	pixels = mapImage.getRGB(0, 0, mapImage.getWidth(), mapImage.getHeight(), null, 0, mapImage.getWidth());
	pixelColors = new Color[pixels.length];
	for (int i = 0; i < pixels.length; i++)
	{
	    Color c = new Color(pixels[i]);
	    pixelColors[i] = c;
	}
    }
    
    /**
     * Zwraca tablicę liczb całkowitych, reprezentujących różne kolory pixeli
     * 1 - czerwony
     * 2 - zielony
     * 3 - niebieski
     * 4 - biały
     * @return dwuwymiarowa tablica odpowiadająca kolorom z mapy.
     */
    public int[][] getColorCodes()
    {
	this.getPixels();
	int[][] pixelTable = new int[mapImage.getHeight()][mapImage.getWidth()];
	for (int i = 0; i < mapImage.getHeight(); i++)
	{
	    for (int j = 0; j < mapImage.getWidth(); j++)
	    {
		if (pixelColors[i * mapImage.getWidth() + j].getRed() == 255
			&& pixelColors[i * mapImage.getWidth() + j].getBlue() == 0
			&& pixelColors[i * mapImage.getWidth() + j].getGreen() == 0)
		{
		    pixelTable[i][j] = 1;
		} else if (pixelColors[i * mapImage.getWidth() + j].getRed() == 0
			&& pixelColors[i * mapImage.getWidth() + j].getBlue() == 0
			&& pixelColors[i * mapImage.getWidth() + j].getGreen() == 255)
		{
		    pixelTable[i][j] = 2;
		} else if (pixelColors[i * mapImage.getWidth() + j].getRed() == 255
			&& pixelColors[i * mapImage.getWidth() + j].getBlue() == 255
			&& pixelColors[i * mapImage.getWidth() + j].getGreen() == 255)
		{
		    pixelTable[i][j] = 4;
		} else if (pixelColors[i * mapImage.getWidth() + j].getRed() == 0
			&& pixelColors[i * mapImage.getWidth() + j].getBlue() == 255
			&& pixelColors[i * mapImage.getWidth() + j].getGreen() == 0)
		{
		    pixelTable[i][j] = 3;
		}
	    }
	}
	return pixelTable;
    }
}
