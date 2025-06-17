/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Daza_
 */
public class MinMax {
    
    private int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    private int getBlue(int rgb) {
        return rgb & 0xFF;
    }
    
    private int createRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
    
    public BufferedImage minFilter(BufferedImage img) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int minR = 255;
                int minG = 255;
                int minB = 255;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nx = x + i;
                        int ny = y + j;
                        
                        if (nx >= 0 && nx < img.getWidth() && ny >= 0 && ny < img.getHeight()) {
                            int pixel = img.getRGB(nx, ny);
                            minR = Math.min(minR, getRed(pixel));
                            minG = Math.min(minG, getGreen(pixel));
                            minB = Math.min(minB, getBlue(pixel));
                        }
                    }
                }
                
                int newPixel = createRGB(minR, minG, minB);
                filteredImage.setRGB(x, y, newPixel);
            }
        }
        return filteredImage;
    }
    
    public BufferedImage maxFilter(BufferedImage img) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int maxR = 0;
                int maxG = 0;
                int maxB = 0;
                
                // Percorre vizinhança 3x3
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nx = x + i;
                        int ny = y + j;
                        
                        if (nx >= 0 && nx < img.getWidth() && ny >= 0 && ny < img.getHeight()) {
                            int pixel = img.getRGB(nx, ny);
                            maxR = Math.max(maxR, getRed(pixel));
                            maxG = Math.max(maxG, getGreen(pixel));
                            maxB = Math.max(maxB, getBlue(pixel));
                        }
                    }
                }
                
                int newPixel = createRGB(maxR, maxG, maxB);
                filteredImage.setRGB(x, y, newPixel);
            }
        }
        return filteredImage;
    }
    
    public BufferedImage midpointFilter(BufferedImage img) {
        BufferedImage filteredImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int minR = 255, minG = 255, minB = 255;
                int maxR = 0, maxG = 0, maxB = 0;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int nx = x + i;
                        int ny = y + j;
                        
                        if (nx >= 0 && nx < img.getWidth() && ny >= 0 && ny < img.getHeight()) {
                            int pixel = img.getRGB(nx, ny);
                            int r = getRed(pixel);
                            int g = getGreen(pixel);
                            int b = getBlue(pixel);
                            
                            minR = Math.min(minR, r);
                            minG = Math.min(minG, g);
                            minB = Math.min(minB, b);
                            
                            maxR = Math.max(maxR, r);
                            maxG = Math.max(maxG, g);
                            maxB = Math.max(maxB, b);
                        }
                    }
                }
                
                int midR = (minR + maxR) / 2;
                int midG = (minG + maxG) / 2;
                int midB = (minB + maxB) / 2;
                
                int newPixel = createRGB(midR, midG, midB);
                filteredImage.setRGB(x, y, newPixel);
            }
        }
        return filteredImage;
    }
}