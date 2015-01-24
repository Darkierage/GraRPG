/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */

package com.rpg.draws;

import java.awt.Point;
import static org.lwjgl.opengl.GL11.*;

/**
 * Rysowanie prostych figur
 * @author Konrad
 */
public class Draw
{
    /**
     * Rysowanie prostego prostokąta
     * @param x przesunięcie wzdłuż osi X
     * @param y przesunięcie wzdłuż osi Y
     * @param width szerokość prostokąta
     * @param high wysokość prostokąta
     */
    public static void rect(float x, float y, float width, float high)
    {
        rect(x, y, width, high, 0);
    }
    
    /**
     * Rysowanie prostokąta z możliwością obrotu
     * @param x przesunięcie wzdłuż osi X
     * @param y przesunięcie wzdłuż osi Y
     * @param width szerokość prostokąta
     * @param high wysokość prostokąta
     * @param rotation obrót w stopniach
     */
    public static void rect(float x, float y, float width, float high, float rotation)
    {
        //Wrzucamy macierz na stos. Transformując macierz, działamy cały czas na jednej macierzy. Dzięki tej operacji nie będziemy przesuwać wszystkich kwadratów tylko jeden
        glPushMatrix(); 
        {
            //Przesunięcie obiektu wzdłuz osi XYZ
            glTranslatef(x, y, 0);
            glRotatef(rotation, 0, 0, 1);
            //Wszystko w OpenGL'u rysujemy między glBegin() a glEnd(). GL_QUADS - czworoboki 
            glBegin(GL_QUADS); 
            {
                glVertex2f(0, 0);
                glVertex2f(0, high);
                glVertex2f(width, high);
                glVertex2f(width, 0) ;
            }
            glEnd();
        }
        //Zdjęcie ze stosu
        glPopMatrix();
    }
    
    /**
     * Rysowanie prostokąta z możliwością obrotu
     * @param x przesunięcie wzdłuż osi X
     * @param y przesunięcie wzdłuż osi Y
     * @param width szerokość prostokąta
     * @param high wysokość prostokąta
     * @param rotation obrót w stopniach
     * @param tex tekstura obiektu
     */
    public static void rect(float x, float y, float width, float high, Texture tex)
    {
        //Wrzucamy macierz na stos. Transformując macierz, działamy cały czas na jednej macierzy. Dzięki tej operacji nie będziemy przesuwać wszystkich kwadratów tylko jeden
        glPushMatrix(); 
        {
            //Przesunięcie obiektu wzdłuz osi XYZ
            glTranslatef(x, y, 0);
            //glRotatef(rotation, 0, 0, 1);
	    glEnable(GL_TEXTURE_2D);
	    
	    tex.bind();
	    float u = 0f;
	    float v = 0f;
	    float u2 = 1f;
	    float v2 = 1f;
            //Wszystko w OpenGL'u rysujemy między glBegin() a glEnd(). GL_QUADS - czworoboki 
            glBegin(GL_QUADS); 
            {
                glTexCoord2f(u, v);
		glVertex2f(0, 0);
		glTexCoord2f(u, v2);
                glVertex2f(0, high);
		glTexCoord2f(u2, v2);
                glVertex2f(width, high);
		glTexCoord2f(u2, v);
                glVertex2f(width, 0) ;
            }
            glEnd();
        }
        //Zdjęcie ze stosu
        glPopMatrix();
    }
    
    /**
     * Rysowanie prostokąta z możliwością obrotu
     * @param x przesunięcie wzdłuż osi X
     * @param y przesunięcie wzdłuż osi Y
     * @param width szerokość prostokąta
     * @param high wysokość prostokąta
     * @param rotation obrót w stopniach
     * @param tex tekstura obiektu
     */
    public static void rect(float x, float y, float width, float high, String tex)
    {
        //Wrzucamy macierz na stos. Transformując macierz, działamy cały czas na jednej macierzy. Dzięki tej operacji nie będziemy przesuwać wszystkich kwadratów tylko jeden
        glPushMatrix(); 
        {
            //Przesunięcie obiektu wzdłuz osi XYZ
            glTranslatef(x, y, 0);
            //glRotatef(rotation, 0, 0, 1);
	    glEnable(GL_TEXTURE_2D);
	    
	    
	    float u = 0f;
	    float v = 0f;
	    float u2 = 1f;
	    float v2 = 1f;
            //Wszystko w OpenGL'u rysujemy między glBegin() a glEnd(). GL_QUADS - czworoboki 
            glBegin(GL_QUADS); 
            {
                glTexCoord2f(u, v);
		glVertex2f(0, 0);
		glTexCoord2f(u, v2);
                glVertex2f(0, high);
		glTexCoord2f(u2, v2);
                glVertex2f(width, high);
		glTexCoord2f(u2, v);
                glVertex2f(width, 0) ;
            }
            glEnd();
        }
        //Zdjęcie ze stosu
        glPopMatrix();
    } 
    
    
    public static void circle(float x, float y, float r)
    {
        //Wrzucamy macierz na stos. Transformując macierz, działamy cały czas na jednej macierzy. Dzięki tej operacji nie będziemy przesuwać wszystkich kwadratów tylko jeden
        glPushMatrix(); 
        {
            //Przesunięcie obiektu wzdłuz osi XYZ
            glTranslatef(x+10, y+10, 0);
            Point[] p = new Point[502];
            p[0] = new Point((int)r, (int)(r/2));
            double jump = 2.0 * Math.PI / 500.0;
            double theta = 0.0;
          
            for(int i=0; i<501; i++)
            {
                theta += jump;
                p[i+1] = new Point();
                p[i+1].x = (int)(r * Math.cos(theta)+r/2);
                p[i+1].y = (int)(r * Math.sin(theta) + r/2);
            }
            glBegin(GL_POLYGON); 
            {
                for(int i=0; i<501; i++)
                {
                    glVertex2f(p[i].x,p[i].y);
                }
            }
            glEnd();
        }
        //Zdjęcie ze stosu
        glPopMatrix();
    }
    
}
