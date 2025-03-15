package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private BufferedImage image; // Armazena a imagem redimensionada
    private static final int WIDTH = 512;  // Largura fixa
    private static final int HEIGHT = 512; // Altura fixa
    

    public ImagePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Define o tamanho fixo do painel
    }

    // Define a imagem e redimensiona para 600x400 sem cortes
    public void setImage(BufferedImage img) {
        this.image = resizeImage(img, WIDTH, HEIGHT);
        repaint(); // Redesenha o painel
    }

    // Redimensiona a imagem sem cortes, apenas reduzindo
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();

        // Ativa interpolação para melhor qualidade
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha a imagem redimensionada SEM cortes
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }
    
    public void eraseImage(){
        this.image = null;
        repaint();
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Se a imagem for null, não tenta desenhá-la
        if (image == null) {
            // Opcional: Desenhe um fundo em branco para indicar que não há imagem
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT); // Limpa a tela
        } else {
            // Caso contrário, desenha a imagem
            g.drawImage(image, 0, 0, WIDTH, HEIGHT, this);
        }
    }
}
