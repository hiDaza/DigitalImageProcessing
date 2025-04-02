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
public class RandomNoises {
    
    
    public BufferedImage randomNoises(BufferedImage img){
        BufferedImage noiseImage = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics gpc = img.getGraphics();
        gpc.drawImage(img, 0, 0, null);
        gpc.dispose();
        
        int noiseValue = (int) ((img.getHeight() * img.getWidth()) * 0.1);
        
        RandomPositions randomP = new RandomPositions();
        int x[] = randomP.randomPointsX(img, noiseValue);
        int y[] = randomP.randomPointsY(img, noiseValue);
        
        
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                if(i == x[i] && j == y[j]){
                    noiseImage.setRGB(i, j, 0);
                }
            }
        }
        return noiseImage;
    }
}
