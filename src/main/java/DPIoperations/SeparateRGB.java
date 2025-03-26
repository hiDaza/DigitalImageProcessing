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
public class SeparateRGB {
    
    
    public BufferedImage separateColor(BufferedImage img, int choose){
        BufferedImage separateChannelRGB = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++ ){
                
               int rgb = img.getRGB(x, y);
               
               int r = (rgb >> 16) & 0xFF;
               int g = (rgb >> 8) & 0xFF;
               int b = rgb & 0xFF;
               
               if(choose == 1){
                   separateChannelRGB.setRGB(x, y, (r << 16) | (0 << 8) | 0);
               }
                if(choose == 2){
                    separateChannelRGB.setRGB(x, y, (0 << 16) | (g << 8) | 0);
               }
                if(choose == 3){
                    separateChannelRGB.setRGB(x, y, (0 << 16) | (0 << 8) | b);
                }
            }
        }
        return separateChannelRGB;
    }
}
