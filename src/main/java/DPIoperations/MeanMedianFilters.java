/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author Daza_
 */
public class MeanMedianFilters {
    
    private int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    private int getBlue(int rgb) {
        return rgb & 0xFF;
    }
    

    
    public BufferedImage MeanFilter3x3(BufferedImage img){
        BufferedImage filteredImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int sumR = 0;
                int sumG = 0;
                int sumB = 0;
                int[] rgb = new int[5];
                
                rgb[0] = img.getRGB(x, y);
                 
                rgb[1] = (y - 1 >= 0) ? img.getRGB(x, y - 1) : rgb[0];
                rgb[2] = (x - 1 >= 0) ? img.getRGB(x - 1, y) : rgb[0];
                rgb[3] = (x + 1 < img.getWidth()) ? img.getRGB(x + 1, y) : rgb[0];
                rgb[4] = (y + 1 < img.getHeight()) ? img.getRGB(x, y + 1) : rgb[0];
                 
                for(int i = 0; i < 5; i++){
                    sumR += getRed(rgb[i]);
                    sumG += getGreen(rgb[i]);
                    sumB += getBlue(rgb[i]);
                }
                int r = sumR/5;
                int g = sumG/5;
                int b = sumB/5;
                        
                int newRGB = (r << 16) | (g << 8) | b;
                filteredImage.setRGB(x, y, newRGB);
            }
        }
        return filteredImage;
        
    }
  
    
    public BufferedImage MeanFilter9x9(BufferedImage img){
        BufferedImage filteredImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int sumR = 0;
                int sumG = 0;
                int sumB = 0;
                int[] rgb = new int[9];
                
                rgb[0] = img.getRGB(x, y);
                 
                rgb[1] = (y - 1 >= 0) ? img.getRGB(x, y - 1) : rgb[0];
                rgb[2] = (x - 1 >= 0) ? img.getRGB(x - 1, y) : rgb[0];
                rgb[3] = (x + 1 < img.getWidth()) ? img.getRGB(x + 1, y) : rgb[0];
                rgb[4] = (y + 1 < img.getHeight()) ? img.getRGB(x, y + 1) : rgb[0];
                rgb[5] = (x-1 >=0 && y-1 >= 0) ? img.getRGB(x-1, y-1) : rgb[0];
                rgb[6] = (x+1 < img.getWidth() && y-1 >= 0) ? img.getRGB(x+1,y-1) : rgb[0];
                rgb[7] = (x-1 >= 0 && y+1 < img.getHeight()) ? img.getRGB(x-1,y+1) : rgb[0];
                rgb[8] = (x+1 < img.getWidth() && y+1 < img.getHeight()) ? img.getRGB(x+1,y+1) : rgb[0];
                     
                 
                for(int i = 0; i < 8; i++){
                    sumR += getRed(rgb[i]);
                    sumG += getGreen(rgb[i]);
                    sumB += getBlue(rgb[i]);
                }
                int r = sumR/9;
                int g = sumG/9;
                int b = sumB/9;
                        
                int newRGB = (r << 16) | (g << 8) | b;
                filteredImage.setRGB(x, y, newRGB);
            }
        }
        return filteredImage;
        
    }
    
    
    public BufferedImage MedianFilter(BufferedImage img){
        BufferedImage filteredImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0,0, null);
        gpc.dispose();
        int[] vetR = new int[8];
        int[] vetG = new int[8];
        int[] vetB = new int[8];
                
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                
                int[] rgb = new int[9];
                
                rgb[0] = img.getRGB(x, y);
                 
                rgb[1] = (y - 1 >= 0) ? img.getRGB(x, y - 1) : rgb[0];
                rgb[2] = (x - 1 >= 0) ? img.getRGB(x - 1, y) : rgb[0];
                rgb[3] = (x + 1 < img.getWidth()) ? img.getRGB(x + 1, y) : rgb[0];
                rgb[4] = (y + 1 < img.getHeight()) ? img.getRGB(x, y + 1) : rgb[0];
                rgb[5] = (x-1 >=0 && y-1 >= 0) ? img.getRGB(x-1, y-1) : rgb[0];
                rgb[6] = (x+1 < img.getWidth() && y-1 >= 0) ? img.getRGB(x+1,y-1) : rgb[0];
                rgb[7] = (x-1 >= 0 && y+1 < img.getHeight()) ? img.getRGB(x-1,y+1) : rgb[0];
                rgb[8] = (x+1 < img.getWidth() && y+1 < img.getHeight()) ? img.getRGB(x+1,y+1) : rgb[0];
                
                for(int i = 0; i < 8; i++){
                    vetR[i] = getRed(rgb[i]);
                    vetG[i] = getGreen(rgb[i]);
                    vetB[i] = getBlue(rgb[i]);
                    
                }
                
                Arrays.sort(vetR);
                Arrays.sort(vetG);
                Arrays.sort(vetB);
                
                int median = (rgb.length % 2 == 1) ? (rgb.length -1 ) / 2 : rgb.length / 2 -1;
                int r = vetR[median];
                int g = vetG[median];
                int b = vetB[median];
                
                int newRGB = (r << 16) | (g << 8) | b;
                
                filteredImage.setRGB(x, y, newRGB);
                
            }
        } 
        return filteredImage;
    }
    
    
}
