/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author gerson.lucas_unesp
 */
public class AverageFilter {
     
     
    public BufferedImage applyAverageFilter(BufferedImage original, ArrayList<BufferedImage> imgs){
        
        BufferedImage filteredImage = new BufferedImage(original.getWidth(),original.getHeight(),original.getType());
        Graphics gpc = filteredImage.getGraphics();
        gpc.drawImage(original, 0, 0, null);
        gpc.dispose();
        

        
        for(int x = 0; x < original.getWidth(); x++){
            for(int y = 0; y < original.getHeight(); y++){
               
                long rSum = 0, gSum = 0, bSum = 0;
                
                for(BufferedImage image : imgs){
                    
                    int rgb = image.getRGB(x, y);
                    int r = (rgb >> 16)  & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    
                    rSum += r;
                    gSum += g;
                    bSum += b;
                    
                     
                }
                int averageR = (int) rSum / imgs.size();
                int averageG = (int) gSum / imgs.size();
                int averageB = (int) bSum / imgs.size();
                
                int newRGB = (averageR << 16) | (averageG << 8) | (averageB);
                filteredImage.setRGB(x, y, newRGB);
               
            }
        }
        
        
        return filteredImage;
    }
    
    
}
