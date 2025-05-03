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
public class GrayToInversal {
    
    public BufferedImage toInversal(BufferedImage img){
        BufferedImage inversalGray = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = inversalGray.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < inversalGray.getWidth(); x++){
            for(int y = 0; y < inversalGray.getHeight(); y++){
                int rgb = inversalGray.getRGB(x, y);
                int r = 255 - (rgb >> 16) & 0xFF;
                int g = 255 - (rgb >> 8) & 0xFF;
                int b = 255 - rgb & 0xFF;
                
               int inversalImage = (r << 16) | (g << 8) | b;
               
               inversalGray.setRGB(x, y, inversalImage);
                
            }
        }
        return inversalGray;       
    }
}
