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
                int neighborI =  Math.min((int)(i * (width / (double)newWidth)), width - 1);
                int neighborJ = Math.min((int)(j * (height / (double)newHeight)), height - 1);
                int rgb = img.getRGB(neighborI, neighborJ);
                interpoledImg.setRGB(i, j, rgb);
            }
            
        }
        
        return interpoledImg;
    }
    
    
}
