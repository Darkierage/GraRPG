/*
 * RPG Game - Software Engineering * All rights reserved
 * Konrad Rugala, Krzysztof Sobieraj
 */
package com.rpg.windows;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Klasa ImagePanel bedaca rozszerzeniem JPanel, dolaczenie mozliwosci ustawienia tla jako obrazka
 * @author Darkrage
 */
class ImagePanel extends JPanel {

  private Image img;

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }
  /**
   * Przeladowanie metody paintComponent
   * @param g obiekt klasy Graphics
   */
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}
