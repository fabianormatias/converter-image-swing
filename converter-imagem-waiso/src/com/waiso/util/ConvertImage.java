package com.waiso.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ConvertImage {

	/** 
     * Converte a imagem para escala de cinza.
     * 
     * @author fabianorodrigues 
     * @param image Imagem a ser convertida 
     * @return a Imagem convertida. 
     */  
    public BufferedImage toGrayscale(BufferedImage image) throws IOException {
        BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);  
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return output;
    }  
  
    /** 
     * Converte a imagem para binário usando um algorítmo de threshold simples. 
     * Tudo que estiver abaixo do threshold será convertido para preto. Acima ou 
     * igual ao threshold será convertido para branco. 
     *  
     * @param image 
     *            Imagem a ser convertida (apenas o canal r será considerado). 
     *            Use uma escala já transformada em escala de cinza para 
     *            melhores resultados. 
     * @param t 
     *            Valor do threshold. 
     */  
    public BufferedImage toBinary(BufferedImage image, int t) throws IOException  {  
    	int BLACK = Color.BLACK.getRGB();  
        int WHITE = Color.WHITE.getRGB();  
        BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);  
        // Percorre a imagem definindo na saída o pixel como branco se o valor  
        // na entrada for menor que o threshold, ou como preto se for maior.  
        for (int y = 0; y < image.getHeight(); y++)  
            for (int x = 0; x < image.getWidth(); x++) {  
                Color pixel = new Color(image.getRGB(x, y));  
                output.setRGB(x, y, pixel.getRed() < t ? BLACK : WHITE);  
            }  
  
        return output;  
    }  
}
