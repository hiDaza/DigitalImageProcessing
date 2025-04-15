/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.Random;

/**
 *
 * @author gerson.lucas_unesp
 */
public class RandomNoises {
    
    
    public BufferedImage randomNoises(BufferedImage img){
        BufferedImage noiseImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = noiseImage.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();

        int noiseValue = (int) ((img.getHeight() * img.getWidth()) * 0.1);
       
        Random random = new Random();
        
        for(int i = 0; i < noiseValue; i++){

                    int x = random.nextInt(img.getWidth());
                    int y = random.nextInt(img.getHeight());
                    
                    int noiseColor = (255 << 16) | (255 << 8) | 255;
                    
                    noiseImage.setRGB(x, y, noiseColor);
                }
        return noiseImage;
    }
}
