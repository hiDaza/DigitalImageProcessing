/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

import java.awt.image.BufferedImage;

/**
 *
 * @author Daza_
 */
public class ColorToGray {
       BufferedImage img; 
       
       
       
       public void toGray(BufferedImage img){
           for(int x = 0; x < img.getWidth(); x++){
               for(int y = 0; y < img.getHeight(); y++ ){
                   int rgb = img.getRGB(x, y);
                   int r = (rgb >> 16) & 0xFF;
                   int g = (rgb >> 8) & 0xFF;
                   int b = rgb & 0xFF;

                   
                  int gray = (int) ((int) (0.299 * r) + (int) (0.587 * g) + (int) (0.114 * b)); 
                  int grayPixel = (gray << 16) | (gray << 8) | gray;
                  img.setRGB(x, y, grayPixel);   
               }
               
           }
       }
}
