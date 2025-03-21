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
               int rgb = separateChannelRGB.getRGB(x, y);
               if(choose == 1){
                   int r = (rgb << 16) & 0xFF;
                   separateChannelRGB.setRGB(x, y, r);
               }
                if(choose == 2){
                    int g = (rgb << 8) & 0xFF;
                    separateChannelRGB.setRGB(x, y, g);
               }
                if(choose == 3){
                    int b = rgb & 0xFF;
                    separateChannelRGB.setRGB(x, y, b);
                }
            }
        }
        return separateChannelRGB;
    }
    
    
    
}
