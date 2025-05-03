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
public class Highlight {
    
    public BufferedImage Binarization(BufferedImage img, int threshold){
        BufferedImage binarizedImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                if(gray < threshold){
                    gray = 0;
                }else{
                    gray = 255;
                }
                int newRGB = gray << 16 | gray << 8 | gray;
                binarizedImage.setRGB(x,y,newRGB);
            }
        }

        return binarizedImage;
    }
    
    
    public BufferedImage thresholding(BufferedImage img, int threshold){
        BufferedImage thresholded = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                int gray = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                if(gray < threshold){
                    gray = 0;
                }
                int newRGB = gray << 16 | gray << 8 | gray;
                thresholded.setRGB(x,y,newRGB);
            }
        }
        return thresholded;
    } 
    
    
    private int intensity(int rgb){
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb) & 0xFF;
        
        int intensity = (int) (0.299 * r + 0.587 * g  + 0.114 * b);
        return intensity;
    }
    
    public BufferedImage Laplacian(BufferedImage img){
        BufferedImage laplacianImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        int[][] mask = {
            {0, -1, 0},
            {-1, 4, -1},
            {0, -1, 0}
        };
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int[][] rgb = new int[3][3];
                
                rgb[1][1] = intensity(img.getRGB(x, y));
                rgb[0][0] = (x - 1 >= 0) && (y - 1 >= 0) ? intensity(img.getRGB(x-1,y-1)) : (rgb[1][1]);
                rgb[1][0] = (y -1 >= 0) ? intensity(img.getRGB(x, y-1)) : (rgb[1][1]);
                rgb[2][0] = (x + 1 < img.getWidth()) && (y - 1 >= 0) ? intensity(img.getRGB(x + 1, y - 1)) : (rgb[1][1]);
                rgb[0][1] = (x - 1 >= 0) ? intensity(img.getRGB(x - 1, y)) : (rgb[1][1]);
                rgb[2][1] = (x + 1 < img.getWidth()) ? intensity(img.getRGB(x + 1, y)) : (rgb[1][1]);
                rgb[0][2] = (x - 1 >= 0) && (y + 1 < img.getHeight()) ? intensity(img.getRGB(x -1, y +1)) : (rgb[1][1]);
                rgb[1][2] = (y + 1 < img.getHeight()) ? intensity(img.getRGB(x, y + 1)) : (rgb[1][1]);
                rgb[2][2] = (x + 1 < img.getWidth()) && (y + 1 < img.getHeight()) ? intensity(img.getRGB(x+1, y+1)) : (rgb[1][1]);  
                
                int result = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        result += mask[i][j] * rgb[i][j];
                    }
                }
                result = Math.abs(result);
                result = Math.min(result,255);
                int newRGB = result << 16 | result << 8 | result;
                laplacianImage.setRGB(x, y, newRGB);
            }
        }
        

        return laplacianImage;
    }
    
    public BufferedImage Laplacian9X9(BufferedImage img){
        BufferedImage laplacianImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        int[][] mask = {
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}
        };
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int[][] rgb = new int[3][3];
                
                rgb[1][1] = intensity(img.getRGB(x, y));
                rgb[0][0] = (x - 1 >= 0) && (y - 1 >= 0) ? intensity(img.getRGB(x-1,y-1)) : (rgb[1][1]);
                rgb[1][0] = (y -1 >= 0) ? intensity(img.getRGB(x, y-1)) : (rgb[1][1]);
                rgb[2][0] = (x + 1 < img.getWidth()) && (y - 1 >= 0) ? intensity(img.getRGB(x + 1, y - 1)) : (rgb[1][1]);
                rgb[0][1] = (x - 1 >= 0) ? intensity(img.getRGB(x - 1, y)) : (rgb[1][1]);
                rgb[2][1] = (x + 1 < img.getWidth()) ? intensity(img.getRGB(x + 1, y)) : (rgb[1][1]);
                rgb[0][2] = (x - 1 >= 0) && (y + 1 < img.getHeight()) ? intensity(img.getRGB(x -1, y +1)) : (rgb[1][1]);
                rgb[1][2] = (y + 1 < img.getHeight()) ? intensity(img.getRGB(x, y + 1)) : (rgb[1][1]);
                rgb[2][2] = (x + 1 < img.getWidth()) && (y + 1 < img.getHeight()) ? intensity(img.getRGB(x+1, y+1)) : (rgb[1][1]);  
                
                int result = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        result += mask[i][j] * rgb[i][j];
                    }
                }
                result = Math.abs(result);
                result = Math.min(result,255);
                int newRGB = result << 16 | result << 8 | result;
                laplacianImage.setRGB(x, y, newRGB);
            }
        }
        return laplacianImage;
    }
    
    
    public BufferedImage applyLaplaciane(BufferedImage img){
        BufferedImage laplacianImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        int[][] mask = {
            {-1, -1, -1},
            {-1, 9, -1},
            {-1, -1, -1}
        };
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int[][] rgb = new int[3][3];
                
                rgb[1][1] = intensity(img.getRGB(x, y));
                rgb[0][0] = (x - 1 >= 0) && (y - 1 >= 0) ? intensity(img.getRGB(x-1,y-1)) : (rgb[1][1]);
                rgb[1][0] = (y -1 >= 0) ? intensity(img.getRGB(x, y-1)) : (rgb[1][1]);
                rgb[2][0] = (x + 1 < img.getWidth()) && (y - 1 >= 0) ? intensity(img.getRGB(x + 1, y - 1)) : (rgb[1][1]);
                rgb[0][1] = (x - 1 >= 0) ? intensity(img.getRGB(x - 1, y)) : (rgb[1][1]);
                rgb[2][1] = (x + 1 < img.getWidth()) ? intensity(img.getRGB(x + 1, y)) : (rgb[1][1]);
                rgb[0][2] = (x - 1 >= 0) && (y + 1 < img.getHeight()) ? intensity(img.getRGB(x -1, y +1)) : (rgb[1][1]);
                rgb[1][2] = (y + 1 < img.getHeight()) ? intensity(img.getRGB(x, y + 1)) : (rgb[1][1]);
                rgb[2][2] = (x + 1 < img.getWidth()) && (y + 1 < img.getHeight()) ? intensity(img.getRGB(x+1, y+1)) : (rgb[1][1]);  
                
                int result = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        result += mask[i][j] * rgb[i][j];
                    }
                }
                result = Math.abs(result);
                result = Math.min(result,255);
                int newRGB = result << 16 | result << 8 | result;
                laplacianImage.setRGB(x, y, newRGB);
            }
        }
        return laplacianImage;
    }
    
    public int Magnitude(BufferedImage img, int x, int y) {
        
        int[][] rgb = new int[3][3];
    
        
        rgb[1][1] = intensity(img.getRGB(x, y));
        rgb[0][0] = (x - 1 >= 0 && y - 1 >= 0) ? intensity(img.getRGB(x-1, y-1)) : rgb[1][1];
        rgb[1][0] = (y - 1 >= 0) ? intensity(img.getRGB(x, y-1)) : rgb[1][1];
        rgb[2][0] = (x + 1 < img.getWidth() && y - 1 >= 0) ? intensity(img.getRGB(x+1, y-1)) : rgb[1][1];
        rgb[0][1] = (x - 1 >= 0) ? intensity(img.getRGB(x-1, y)) : rgb[1][1];
        rgb[2][1] = (x + 1 < img.getWidth()) ? intensity(img.getRGB(x+1, y)) : rgb[1][1];
        rgb[0][2] = (x - 1 >= 0 && y + 1 < img.getHeight()) ? intensity(img.getRGB(x-1, y+1)) : rgb[1][1];
        rgb[1][2] = (y + 1 < img.getHeight()) ? intensity(img.getRGB(x, y+1)) : rgb[1][1];
        rgb[2][2] = (x + 1 < img.getWidth() && y + 1 < img.getHeight()) ? intensity(img.getRGB(x+1, y+1)) : rgb[1][1];


        int[][] maskfx = {
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
        };
        int[][] maskfy = {
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
        };


        int resultx = 0, resulty = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
            resultx += maskfx[i][j] * rgb[i][j];
            resulty += maskfy[i][j] * rgb[i][j];
            }
        }
   
        return Math.min(255, (int) Math.sqrt(resultx * resultx + resulty * resulty));
    }
    
    
    public BufferedImage Sobel(BufferedImage img){
        BufferedImage sobelImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < img.getWidth(); x++) {
            for(int y = 0; y < img.getHeight(); y++) {
                int magnitude = Magnitude(img, x, y);
                int newRGB = magnitude << 16 | magnitude << 8 | magnitude;
                sobelImage.setRGB(x, y, newRGB);
            }
        }
        return sobelImage;
    }
    
    
    public int applyDinamicScale(int color, double c, double yGamma){
        double normalized = color / 255.0;
        double result = c * Math.pow(normalized, yGamma);
        result = Math.min(255, Math.max(0, result * 255));
        
        return (int) Math.round(result);
        
    }
    
    public BufferedImage DinamicScaleCompression(BufferedImage img, double c, double yGamma){
        BufferedImage compressedImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < img.getWidth(); x++) {
            for(int y = 0; y < img.getHeight(); y++) {
               int rgb = img.getRGB(x, y);
               int r = (rgb >> 16) & 0xFF;
               int g = (rgb >> 8) & 0xFF;
               int b = (rgb) & 0xFF;
               
               r = applyDinamicScale(r,c,yGamma);
               g = applyDinamicScale(g,c,yGamma);
               b = applyDinamicScale(b,c,yGamma);
               
               int newRGB = r << 16 | g << 8 | b;
               compressedImage.setRGB(x, y, newRGB);
            }
        }
        return compressedImage;
    }
}
