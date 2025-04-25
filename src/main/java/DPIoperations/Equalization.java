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
public class Equalization {
       
    public int[] getHistogram(BufferedImage img){
        int histogram[] = new int[256];
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                int intensity = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                histogram[intensity]++;
            }
        }
        return histogram;
    }
    
    
    public BufferedImage EqualizationImg(BufferedImage img){
        BufferedImage interpolationImg = new BufferedImage(img.getWidth(), img.getHeight(),img.getType());
        Graphics gpc = interpolationImg.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        int dim = img.getHeight() * img.getWidth();
        int histogram[] = getHistogram(img);
        
        int newHistogram[] = new int[256];
        double previous = 0;
        double sum = 0;
        newHistogram[0] = (int) ((int) 255 * ((double) histogram[0] / (double)dim));
        previous = (double) histogram[0] / dim;
        
        for(int i = 1; i < histogram.length; i++){
            sum += previous;
            previous = (double) histogram[i] / dim;
            newHistogram[i] = (int) (255 * (((double) histogram[i]) / dim + sum));
        }
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x,y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                int intensity = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                int newRGB = newHistogram[intensity] << 16 | newHistogram[intensity] << 8 | newHistogram[intensity];
                interpolationImg.setRGB(x, y, newRGB);
            }
        }
        return interpolationImg;
    }
    
    public void EqualizationImgEver(BufferedImage img){
        int dim = img.getHeight() * img.getWidth();
        int histogram[] = getHistogram(img);
        
        int newHistogram[] = new int[256];
        double previous = 0;
        double sum = 0;
        newHistogram[0] = (int) ((int) 255 * ((double) histogram[0] / (double)dim));
        previous = (double) histogram[0] / dim;
        
        for(int i = 1; i < histogram.length; i++){
            sum += previous;
            previous = (double) histogram[i] / dim;
            newHistogram[i] = (int) (255 * (((double) histogram[i]) / dim + sum));
        }
        
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int rgb = img.getRGB(x,y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                int intensity = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                int newRGB = newHistogram[intensity] << 16 | newHistogram[intensity] << 8 | newHistogram[intensity];
                img.setRGB(x, y, newRGB);
            }
        }
    }
    
    
}
    
    
    
  
