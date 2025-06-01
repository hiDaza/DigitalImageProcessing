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
public class Transformed {
    
    
    double alpha(int k, int N){
        if(k == 0){
            return Math.sqrt(1/N);
        }else{
            return Math.sqrt(2/N);
        }
    }
    
    
public BufferedImage cosTransformed(BufferedImage img) {
    int width = img.getWidth();
    int height = img.getHeight();
    BufferedImage cosImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
    int N = 8;
    double maxVal = Double.MIN_VALUE;
    double minVal = Double.MAX_VALUE;
    double[][] F_global = new double[width][height];

    for (int bx = 0; bx < width; bx += N) {
        for (int by = 0; by < height; by += N) {
            
            double[][] block = new double[N][N];
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    int px = Math.min(bx + x, width - 1);
                    int py = Math.min(by + y, height - 1);
                    
                    int rgb = img.getRGB(px, py);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    
                    double Y = 0.299 * r + 0.587 * g + 0.114 * b;
                    block[x][y] = Y - 128; 
                }
            }
            

            for (int u = 0; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    double sum = 0.0;
                    double cu = (u == 0) ? 1/Math.sqrt(2) : 1;
                    double cv = (v == 0) ? 1/Math.sqrt(2) : 1;
                    
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < N; y++) {
                            double cosX = Math.cos((2*x + 1) * u * Math.PI / (2.0 * N));
                            double cosY = Math.cos((2*y + 1) * v * Math.PI / (2.0 * N));
                            sum += block[x][y] * cosX * cosY;
                        }
                    }
                    
                    double coeff = 0.25 * cu * cv * sum;
                    
                    int gx = Math.min(bx + u, width - 1);
                    int gy = Math.min(by + v, height - 1);
                    F_global[gx][gy] = coeff;
                    
                    if (coeff > maxVal) maxVal = coeff;
                    if (coeff < minVal) minVal = coeff;
                }
            }
        }
    }

    double range = maxVal - minVal;
    System.out.println("Min: " + minVal + " Max: " + maxVal + " Range: " + range);
    
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            double val = F_global[x][y];
            
            double normalized = (val - minVal) / range;
            
            double logVal = Math.log1p(Math.abs(normalized * 1000)) / Math.log(1000);
            
            int gray = (int) (logVal * 255);
            gray = Math.max(0, Math.min(255, gray));
            
            int rgb = (gray << 16) | (gray << 8) | gray;
            cosImage.setRGB(x, y, rgb);
        }
    }

        return cosImage;
    }
    
}   
