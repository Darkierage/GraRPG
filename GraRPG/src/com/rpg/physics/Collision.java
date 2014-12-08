/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.physics;

import com.rpg.gameObject.GOTerrain;
import com.rpg.gameObject.GameObject;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Klasa zajmująca się wykrywaniem kolizji między obiektami
 * @author Konrad
 */
public class Collision
{
    /**
     * Sprawdza czy dwa obiekty wchodzą ze sobą w kolizję.
     * @param g1 GameObject 1.
     * @param g2 GameObject 2.
     * @return true jeśli obiekty ze sobą kolidują
     */
    public static boolean checkIntersection(GameObject g1, GameObject g2)
    {
        Rectangle r1 = new Rectangle((int)g1.getX(), (int)g1.getY(), (int)g1.getSx(), (int)g1.getSy());
        Rectangle r2 = new Rectangle((int)g2.getX(), (int)g2.getY(), (int)g2.getSx(), (int)g2.getSy());
        return r1.intersects(r2);
    }
    
    public static boolean checkIntersection(GameObject g1, ArrayList<GOTerrain> lstGO)
    {
        for(GameObject g2 : lstGO)
        {
            if(checkIntersection(g1, g2))
                return true;
        }
        return false;
    }
}