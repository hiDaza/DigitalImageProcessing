/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author gerson.lucas_unesp
 */
public class Interpolation {
    
    
    public BufferedImage NearstNeighborInterpolation(BufferedImage img, int newWidth, int newHeight){
        BufferedImage interpoledImg = new BufferedImage(newWidth,newHeight,img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        int width = img.getWidth();
        int height = img.getHeight();
        
        for(int j = 0; j < newWidth; j++){
            for(int i = 0; i < newHeight; i++){
                int neighborI =  Math.min((int)(j * (width / (double)newWidth)), width - 1);
                int neighborJ = Math.min((int)(i * (height / (double)newHeight)), height - 1);
                int rgb = img.getRGB(neighborI, neighborJ);
                interpoledImg.setRGB(j, i, rgb);
            }
            
        }
        
        return interpoledImg;
    }
    
    public void NearstNeighborInterpolationEver(BufferedImage img, int newWidth, int newHeight){
        
        int width = img.getWidth();
        int height = img.getHeight();
        
        for(int j = 0; j < newWidth; j++){
            for(int i = 0; i < newHeight; i++){
                int neighborI =  Math.min((int)(j * (width / (double)newWidth)), width - 1);
                int neighborJ = Math.min((int)(i * (height / (double)newHeight)), height - 1);
                int rgb = img.getRGB(neighborI, neighborJ);
                img.setRGB(j, i, rgb);
            }
        }
    }
    
    
    public BufferedImage BilinearInterpolation(BufferedImage img, int newWidth, int newHeight){
       BufferedImage interpoledImg = new BufferedImage(newWidth,newHeight,img.getType());
       Graphics gpc = img.getGraphics();
       gpc.drawImage(img, 0, 0, null);
       gpc.dispose();
       
       int width = img.getWidth();
       int height = img.getHeight();
       

       for(int j = 0; j < newWidth; j++){
           for(int i = 0; i < newHeight; i++){
               
               float xScaled = Math.min((int)(j * (width / (double) newWidth)), width -1);
               float yScaled = Math.min((int)(i * (height / (double) newHeight)), height -1);
               
               int xFloor = (int) Math.floor(xScaled);
               int xFloorCeil = (int) Math.min(width - 1,Math.ceil(xScaled));
               int yFloor = (int) Math.floor(yScaled);
               int yFloorCeil = (int) Math.min(height - 1,Math.ceil(yScaled));
               
               int rgb00 = img.getRGB(xFloor,yFloor);
               int rgb10 = img.getRGB(xFloorCeil, yFloor);
               int rgb01 = img.getRGB(xFloor,yFloorCeil);
               int rgb11 = img.getRGB(xFloorCeil, yFloorCeil);
               
               
               double weightX = xScaled - xFloor;
               double weightY = yScaled - yFloor;
               
               int r = (int) (((rgb00 >> 16) * (1 - weightX) *(1 - weightY))
                       + ((rgb10 >> 16 &0xFF) * (weightX) * (1-weightY)) 
                       + ((rgb01 >> 16 &0xFF) * (1 - weightX) * (weightY)) 
                       + ((rgb11 >> 16 &0xFF) * (weightX) * (weightY)));
               
               int g = (int) (((rgb00 >> 8) * (1 - weightX) *(1 - weightY))
                       + ((rgb10 >> 8 &0xFF) * (weightX) * (1-weightY)) 
                       + ((rgb01 >> 8 &0xFF) * (1 - weightX) * (weightY)) 
                       + ((rgb11 >> 8 &0xFF) * (weightX) * (weightY)));
               
               int b = (int) (((rgb00) * (1 - weightX) *(1 - weightY))
                       + ((rgb10 &0xFF) * (weightX) * (1-weightY)) 
                       + ((rgb01 &0xFF) * (1 - weightX) * (weightY)) 
                       + ((rgb11 &0xFF) * (weightX) * (weightY)));
               
               int newRGB = (r << 16) | (g << 8) |  b;
               
               interpoledImg.setRGB(j, i, newRGB);       
           }
       }
        return interpoledImg;
    }
    
    public void BilinearInterpolationEver(BufferedImage img, int newWidth, int newHeight){
       
       int width = img.getWidth();
       int height = img.getHeight();
       

       for(int j = 0; j < newWidth; j++){
           for(int i = 0; i < newHeight; i++){
               
               float xScaled = Math.min((int)(j * (width / (double) newWidth)), width -1);
               float yScaled = Math.min((int)(i * (height / (double) newHeight)), height -1);
               
               int xFloor = (int) Math.floor(xScaled);
               int xFloorCeil = (int) Math.min(width - 1,Math.ceil(xScaled));
               int yFloor = (int) Math.floor(yScaled);
               int yFloorCeil = (int) Math.min(height - 1,Math.ceil(yScaled));
               
               int rgb00 = img.getRGB(xFloor,yFloor);
               int rgb10 = img.getRGB(xFloorCeil, yFloor);
               int rgb01 = img.getRGB(xFloor,yFloorCeil);
               int rgb11 = img.getRGB(xFloorCeil, yFloorCeil);
               
               
               double weightX = xScaled - xFloor;
               double weightY = yScaled - yFloor;
               
               int r = (int) (((rgb00 >> 16) * (1 - weightX) *(1 - weightY))
                       + ((rgb10 >> 16 &0xFF) * (weightX) * (1-weightY)) 
                       + ((rgb01 >> 16 &0xFF) * (1 - weightX) * (weightY)) 
                       + ((rgb11 >> 16 &0xFF) * (weightX) * (weightY)));
               
               int g = (int) (((rgb00 >> 8) * (1 - weightX) *(1 - weightY))
                       + ((rgb10 >> 8 &0xFF) * (weightX) * (1-weightY)) 
                       + ((rgb01 >> 8 &0xFF) * (1 - weightX) * (weightY)) 
                       + ((rgb11 >> 8 &0xFF) * (weightX) * (weightY)));
               
               int b = (int) (((rgb00) * (1 - weightX) *(1 - weightY))
                       + ((rgb10 &0xFF) * (weightX) * (1-weightY)) 
                       + ((rgb01 &0xFF) * (1 - weightX) * (weightY)) 
                       + ((rgb11 &0xFF) * (weightX) * (weightY)));
               
               int newRGB = (r << 16) | (g << 8) |  b;
               
               img.setRGB(j, i, newRGB);       
           }
       }
    }
    
}
