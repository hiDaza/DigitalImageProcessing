package DPIoperations;

import java.awt.image.BufferedImage;

public class Transformed {
    private static final int BLOCK_SIZE = 8;

    public static class DCTResult {
        public final BufferedImage spectrumImage;
        public final double[][] coefficients;
        public final int width;
        public final int height;
        
        public DCTResult(BufferedImage spectrumImage, double[][] coefficients, int width, int height) {
            this.spectrumImage = spectrumImage;
            this.coefficients = coefficients;
            this.width = width;
            this.height = height;
        }
    }

    public DCTResult applyDCT(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        double[][] coefficients = new double[width][height];

        for (int bx = 0; bx < width; bx += BLOCK_SIZE) {
            for (int by = 0; by < height; by += BLOCK_SIZE) {
                double[][] block = new double[BLOCK_SIZE][BLOCK_SIZE];
                
                for (int x = 0; x < BLOCK_SIZE; x++) {
                    for (int y = 0; y < BLOCK_SIZE; y++) {
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
                
                for (int u = 0; u < BLOCK_SIZE; u++) {
                    for (int v = 0; v < BLOCK_SIZE; v++) {
                        double cu = (u == 0) ? 1/Math.sqrt(2) : 1;
                        double cv = (v == 0) ? 1/Math.sqrt(2) : 1;
                        double sum = 0;
                        
                        for (int x = 0; x < BLOCK_SIZE; x++) {
                            for (int y = 0; y < BLOCK_SIZE; y++) {
                                double cosX = Math.cos((2*x + 1) * u * Math.PI / 16.0);
                                double cosY = Math.cos((2*y + 1) * v * Math.PI / 16.0);
                                sum += block[x][y] * cosX * cosY;
                            }
                        }
                        
                        sum = 0.25 * cu * cv * sum;
                        
                        int posX = bx + u;
                        int posY = by + v;
                        if (posX < width && posY < height) {
                            coefficients[posX][posY] = sum;
                        }
                    }
                }
            }
        }
        return new DCTResult(makeSpectrumImage(coefficients), coefficients, width, height);
    }

    public BufferedImage applyIDCT(DCTResult dctResult, BufferedImage img) {
        return inverseDCT(dctResult.coefficients, img, dctResult.width, dctResult.height);
    }

    
    public DCTResult applyFilterToDCT(DCTResult dctResult, FilterType type, double cutoff) {
        double[][] filteredCoeffs = new double[dctResult.width][dctResult.height];
        
        // Copia os coeficientes
        for (int i = 0; i < dctResult.width; i++) {
            for (int j = 0; j < dctResult.height; j++) {
                filteredCoeffs[i][j] = dctResult.coefficients[i][j];
            }
        }
        
        // Aplica o filtro
        for (int bx = 0; bx < dctResult.width; bx += BLOCK_SIZE) {
            for (int by = 0; by < dctResult.height; by += BLOCK_SIZE) {
                for (int u = 0; u < BLOCK_SIZE; u++) {
                    for (int v = 0; v < BLOCK_SIZE; v++) {
                        int globalX = bx + u;
                        int globalY = by + v;
                        if (globalX >= dctResult.width || globalY >= dctResult.height) continue;
                        
                        double distance = Math.sqrt(u*u + v*v);
                        boolean keep;
                        if (type == FilterType.PASSA_BAIXA) {
                            keep = (distance <= cutoff);
                        } else {
                            keep = (distance > cutoff);
                        }
                        
                        if (!keep) {
                            filteredCoeffs[globalX][globalY] = 0;
                        }
                    }
                }
            }
        }
        
        return new DCTResult(
            makeSpectrumImage(filteredCoeffs),
            filteredCoeffs,
            dctResult.width,
            dctResult.height
        );
    }
    
    public BufferedImage reconstructFromDCT(DCTResult dctResult, BufferedImage img) {
        return inverseDCT(dctResult.coefficients, img, dctResult.width, dctResult.height);
    }
    
    public BufferedImage inverseDCT(double[][] coeff, BufferedImage img, int width, int height) {
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int bx = 0; bx < width; bx += BLOCK_SIZE) {
            for (int by = 0; by < height; by += BLOCK_SIZE) {
                for (int x = 0; x < BLOCK_SIZE; x++) {
                    for (int y = 0; y < BLOCK_SIZE; y++) {
                        int px = bx + x;
                        int py = by + y;
                        if (px >= width || py >= height) continue;
                        
                        double sum = 0;
                        for (int u = 0; u < BLOCK_SIZE; u++) {
                            for (int v = 0; v < BLOCK_SIZE; v++) {
                                int indexX = bx + u;
                                int indexY = by + v;
                                if (indexX >= width || indexY >= height) continue;
                                
                                double cu = (u == 0) ? 1.0/Math.sqrt(2) : 1.0;
                                double cv = (v == 0) ? 1.0/Math.sqrt(2) : 1.0;
                                double cosX = Math.cos((2*x + 1) * u * Math.PI / 16.0);
                                double cosY = Math.cos((2*y + 1) * v * Math.PI / 16.0);
                                sum += cu * cv * coeff[indexX][indexY] * cosX * cosY;
                            }
                        }
                        
                        sum = 0.25 * sum + 128;
                        int gray = (int) Math.round(sum);
                        gray = Math.max(0, Math.min(255, gray));
                        
                        int originalRGB = img.getRGB(px, py);
                        int r = (originalRGB >> 16) & 0xFF;
                        int g = (originalRGB >> 8) & 0xFF;
                        int b = originalRGB & 0xFF;
                        
                        // Calcula diferença de luminância
                        double originalY = 0.299 * r + 0.587 * g + 0.114 * b;
                        double deltaY = gray - originalY;
                        
                        // Aplica diferença proporcionalmente nos canais
                        int newR = (int) (r + deltaY);
                        int newG = (int) (g + deltaY);
                        int newB = (int) (b + deltaY);
                        
                        newR = Math.max(0, Math.min(255, newR));
                        newG = Math.max(0, Math.min(255, newG));
                        newB = Math.max(0, Math.min(255, newB));
                        
                        int rgb = (newR << 16) | (newG << 8) | newB;
                        out.setRGB(px, py, rgb);
                    }
                }
            }
        }
        return out;
    }
    
    public enum FilterType {
        PASSA_BAIXA, 
        PASSA_ALTA
    }
     
    public BufferedImage applyFilter(FilterType type, int cutoff, BufferedImage img) {
        DCTResult dctResult = applyDCT(img);
        double[][] filteredCoeffs = new double[dctResult.width][dctResult.height];
        
        // Copia todos os coeficientes originalmente
        for (int i = 0; i < dctResult.width; i++) {
            for (int j = 0; j < dctResult.height; j++) {
                filteredCoeffs[i][j] = dctResult.coefficients[i][j];
            }
        }
        
        // Aplica o filtro nos coeficientes
        for (int bx = 0; bx < dctResult.width; bx += BLOCK_SIZE) {
            for (int by = 0; by < dctResult.height; by += BLOCK_SIZE) {
                for (int u = 0; u < BLOCK_SIZE; u++) {
                    for (int v = 0; v < BLOCK_SIZE; v++) {
                        int globalX = bx + u;
                        int globalY = by + v;
                        if (globalX >= dctResult.width || globalY >= dctResult.height) continue;
                        
                        // Filtro baseado na distância da origem (DC)
                        double distance = Math.sqrt(u * u + v * v);
                        boolean keep;
                        
                        if (type == FilterType.PASSA_BAIXA) {
                            keep = (distance <= cutoff);
                        } else {
                            keep = (distance > cutoff);
                        }
                        
                        if (!keep) {
                            filteredCoeffs[globalX][globalY] = 0;
                        }
                    }
                }
            }
        }
        
        return inverseDCT(filteredCoeffs, img, dctResult.width, dctResult.height);
    }

    public BufferedImage makeSpectrumImage(double[][] coeff) {
        int width = coeff.length;
        int height = coeff[0].length;
        BufferedImage spec = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double absVal = Math.abs(coeff[i][j]);
                if (absVal > 0) {
                    double logVal = Math.log1p(absVal);
                    max = Math.max(max, logVal);
                    min = Math.min(min, logVal);
                }
            }
        }
        
        double range = max - min;
        if (range == 0) range = 1;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double absVal = Math.abs(coeff[i][j]);
                double logVal = absVal > 0 ? Math.log1p(absVal) : min;
                int gray = (int) ((logVal - min) / range * 255);
                gray = Math.max(0, Math.min(255, gray));
                int rgb = (gray << 16) | (gray << 8) | gray;
                spec.setRGB(i, j, rgb);
            }
        }
        return spec;
    }
}