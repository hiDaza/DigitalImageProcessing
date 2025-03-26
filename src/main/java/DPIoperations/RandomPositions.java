/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author gerson.lucas_unesp
 */
public class RandomPositions {
    
    
    public int[] randomPointsX(BufferedImage img, int totalPoints){
        int vetX[] = new int[totalPoints];
        Random random = new Random();
        for(int i = 0; i < totalPoints; i++){
            
            vetX[i] = random.nextInt(img.getWidth());
        }
    return vetX;
    }
    
    
    public int[] randomPointsY(BufferedImage img, int totalPoints){
        int vetY[] = new int[totalPoints];
        Random random = new Random(); 
        for(int i = 0; i < totalPoints; i++){
            vetY[i] = random.nextInt(img.getWidth());
        }
        return vetY;
    }
    
}
