/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DPIoperations;

/**
 *
 * @author Daza_
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Daza_
 */
public class Thinning {
    
    public BufferedImage zhangSuenThinning(BufferedImage img) {
        // Criar cópia da imagem de entrada
        BufferedImage thinned = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics g = thinned.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        
        int width = thinned.getWidth();
        int height = thinned.getHeight();
        boolean[][] deleted = new boolean[width][height];
        boolean changed;
        
        do {
            changed = false;
            
            // Passo 1: Subiteração 1
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (isBlack(thinned, x, y)) continue;
                    
                    int[] n = getNeighbors(thinned, x, y);
                    if (checkConditions1(n)) {
                        deleted[x][y] = true;
                        changed = true;
                    }
                }
            }
            
            // Remover pixels marcados na subiteração 1
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (deleted[x][y]) {
                        thinned.setRGB(x, y, 0xFF000000); // Preto
                        deleted[x][y] = false;
                    }
                }
            }
            
            // Passo 2: Subiteração 2
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (isBlack(thinned, x, y)) continue;
                    
                    int[] n = getNeighbors(thinned, x, y);
                    if (checkConditions2(n)) {
                        deleted[x][y] = true;
                        changed = true;
                    }
                }
            }
            
            // Remover pixels marcados na subiteração 2
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (deleted[x][y]) {
                        thinned.setRGB(x, y, 0xFF000000); // Preto
                        deleted[x][y] = false;
                    }
                }
            }
            
        } while (changed);
        
        return thinned;
    }
    
    private boolean isBlack(BufferedImage img, int x, int y) {
        // Considera apenas o componente vermelho para determinar se é preto
        return (img.getRGB(x, y) & 0xFF) == 0;
    }
    
    private int[] getNeighbors(BufferedImage img, int x, int y) {
        // Vizinhos na ordem: P2, P3, P4, P5, P6, P7, P8, P9
        return new int[] {
            isBlack(img, x, y-1) ? 0 : 1,    // P2 (norte)
            isBlack(img, x+1, y-1) ? 0 : 1,  // P3 (nordeste)
            isBlack(img, x+1, y) ? 0 : 1,    // P4 (leste)
            isBlack(img, x+1, y+1) ? 0 : 1,  // P5 (sudeste)
            isBlack(img, x, y+1) ? 0 : 1,    // P6 (sul)
            isBlack(img, x-1, y+1) ? 0 : 1,  // P7 (sudoeste)
            isBlack(img, x-1, y) ? 0 : 1,    // P8 (oeste)
            isBlack(img, x-1, y-1) ? 0 : 1   // P9 (noroeste)
        };
    }
    
    private boolean checkConditions1(int[] n) {
        // Condições para primeira subiteração
        int bp = sum(n); // Número de vizinhos brancos
        
        // Condição A: 2 <= B(P1) <= 6
        if (bp < 2 || bp > 6) return false;
        
        // Condição B: Número de transições 0-1 na sequência (P2,P3,P4,P5,P6,P7,P8,P9,P2)
        int ap = 0;
        for (int i = 0; i < 7; i++) {
            if (n[i] == 0 && n[i+1] == 1) ap++;
        }
        if (n[7] == 0 && n[0] == 1) ap++;
        if (ap != 1) return false;
        
        // Condição C: P2 * P4 * P6 == 0
        if (n[0] * n[2] * n[4] != 0) return false;
        
        // Condição D: P4 * P6 * P8 == 0
        return n[2] * n[4] * n[6] == 0;
    }
    
    private boolean checkConditions2(int[] n) {
        // Condições para segunda subiteração
        int bp = sum(n); // Número de vizinhos brancos
        
        // Condição A: 2 <= B(P1) <= 6 (mesma que na primeira subiteração)
        if (bp < 2 || bp > 6) return false;
        
        // Condição B: Número de transições 0-1 (mesma que na primeira subiteração)
        int ap = 0;
        for (int i = 0; i < 7; i++) {
            if (n[i] == 0 && n[i+1] == 1) ap++;
        }
        if (n[7] == 0 && n[0] == 1) ap++;
        if (ap != 1) return false;
        
        // Condição C: P2 * P4 * P8 == 0
        if (n[0] * n[2] * n[6] != 0) return false;
        
        // Condição D: P2 * P6 * P8 == 0
        return n[0] * n[4] * n[6] == 0;
    }
    
    private int sum(int[] arr) {
        int total = 0;
        for (int val : arr) {
            total += val;
        }
        return total;
    }
}