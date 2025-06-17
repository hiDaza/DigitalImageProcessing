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
public class ColorImage {
    
        public BufferedImage applyColorScale(BufferedImage img) {
        BufferedImage colorImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int gray = getGrayValue(img.getRGB(x, y));
                int rgb = mapGrayToColor(gray);
                colorImage.setRGB(x, y, rgb);
            }
        }
        return colorImage;
    }
    
    private int getGrayValue(int rgb) {
        // Extrai componentes de cor
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        
        // Converte para escala de cinza usando método de luminosidade
        return (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
    }
    
    private int mapGrayToColor(int gray) {
        int r = 0, g = 0, b = 0;
        
        // Escala de cores: preto → azul → ciano → verde → amarelo
        if (gray < 64) {
            // Preto (0,0,0) para Azul (0,0,255)
            b = gray * 4;  // 0-255 em 64 níveis
        } 
        else if (gray < 128) {
            // Azul (0,0,255) para Ciano (0,255,255)
            b = 255;
            g = (gray - 64) * 4;  // 0-255 em 64 níveis
        } 
        else if (gray < 192) {
            // Ciano (0,255,255) para Verde (0,255,0)
            g = 255;
            b = 255 - (gray - 128) * 4;  // 255-0 em 64 níveis
        } 
        else {
            // Verde (0,255,0) para Amarelo (255,255,0)
            g = 255;
            r = (gray - 192) * 4;  // 0-255 em 64 níveis
        }
        
        return (r << 16) | (g << 8) | b;
    }
    
    
    public BufferedImage equalizeHSL(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage equalizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Arrays para armazenar componentes HSL
        float[][] H = new float[width][height];
        float[][] S = new float[width][height];
        float[][] L = new float[width][height];
        
        // Passo 1: Converter RGB para HSL e coletar valores de L
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                
                float[] hsl = rgbToHsl(r, g, b);
                H[x][y] = hsl[0];
                S[x][y] = hsl[1];
                L[x][y] = hsl[2];
            }
        }
        
        // Passo 2: Equalizar o componente L
        float[] equalizedL = equalizeChannel(L, width, height);
        
        // Passo 3: Converter de volta para RGB com L equalizado
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int newRGB = hslToRgb(H[x][y], S[x][y], equalizedL[y * width + x]);
                equalizedImage.setRGB(x, y, newRGB);
            }
        }
        
        return equalizedImage;
    }
    
        private float[] equalizeChannel(float[][] channel, int width, int height) {
            int totalPixels = width * height;
            int[] histogram = new int[256];
            float[] equalizedValues = new float[totalPixels];

            // Construir histograma do componente L (convertido para 0-255)
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int value = (int) (channel[x][y] * 255);
                    if (value < 0) value = 0;
                    if (value > 255) value = 255;
                    histogram[value]++;
                }
            }

            // Calcular histograma cumulativo
            int[] cumulativeHist = new int[256];
            cumulativeHist[0] = histogram[0];
            for (int i = 1; i < 256; i++) {
                cumulativeHist[i] = cumulativeHist[i - 1] + histogram[i];
            }

            // Encontrar mínimo valor não zero no histograma cumulativo
            int cdfMin = 0;
            for (int i = 0; i < 256; i++) {
                if (cumulativeHist[i] != 0) {
                    cdfMin = cumulativeHist[i];
                    break;
                }
            }

            // Aplicar fórmula de equalização
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int idx = y * width + x;
                    int value = (int) (channel[x][y] * 255);
                    if (value < 0) value = 0;
                    if (value > 255) value = 255;

                    float equalized = (cumulativeHist[value] - cdfMin) / (float) (totalPixels - cdfMin);
                    equalizedValues[idx] = Math.min(Math.max(equalized, 0.0f), 1.0f);
                }
            }

            return equalizedValues;
        }

        // Conversão RGB para HSL
        private float[] rgbToHsl(int r, int g, int b) {
            float rf = r / 255.0f;
            float gf = g / 255.0f;
            float bf = b / 255.0f;

            float max = Math.max(rf, Math.max(gf, bf));
            float min = Math.min(rf, Math.min(gf, bf));
            float delta = max - min;

            float h = 0, s, l = (max + min) / 2;

            if (delta == 0) {
                h = 0;
            } else {
                if (max == rf) {
                    h = (gf - bf) / delta + (gf < bf ? 6 : 0);
                } else if (max == gf) {
                    h = (bf - rf) / delta + 2;
                } else {
                    h = (rf - gf) / delta + 4;
                }
                h /= 6;
            }

            if (delta == 0) {
                s = 0;
            } else {
                s = delta / (1 - Math.abs(2 * l - 1));
            }

            return new float[]{h, s, l};
        }

        // Conversão HSL para RGB
        private int hslToRgb(float h, float s, float l) {
            float r, g, b;

            if (s == 0) {
                r = g = b = l; // Tons de cinza
            } else {
                float q = l < 0.5 ? l * (1 + s) : l + s - l * s;
                float p = 2 * l - q;

                r = hueToRgb(p, q, h + 1.0f/3);
                g = hueToRgb(p, q, h);
                b = hueToRgb(p, q, h - 1.0f/3);
            }

            int ir = (int) (r * 255 + 0.5f);
            int ig = (int) (g * 255 + 0.5f);
            int ib = (int) (b * 255 + 0.5f);

            return (ir << 16) | (ig << 8) | ib;
        }

        private float hueToRgb(float p, float q, float t) {
            if (t < 0) t += 1;
            if (t > 1) t -= 1;
            if (t < 1.0f/6) return p + (q - p) * 6 * t;
            if (t < 1.0f/2) return q;
            if (t < 2.0f/3) return p + (q - p) * (2.0f/3 - t) * 6;
            return p;
        }
}
    

