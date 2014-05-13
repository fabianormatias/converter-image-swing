package com.waiso.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 
 * Sub-classe de JPanel para exibir a imagem
 * @author fabianorodrigues
 *
 */
public class AreaImage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public BufferedImage image;

	public void paintComponent(Graphics g) {
		super.paintComponents(g);

		// desenha a imagem no JPanel
		g.drawImage(image, 0, 0, this);
	}

}
