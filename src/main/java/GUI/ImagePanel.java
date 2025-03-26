package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private BufferedImage image; // Armazena a imagem
    
    public ImagePanel() {
        // Não definimos um tamanho fixo aqui
    }

    // Define a imagem e ajusta o tamanho do painel
    public void setImage(BufferedImage img) {
        this.image = img;
        // Atualiza o tamanho preferencial do painel para o tamanho da imagem
        setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        // Notifica o layout manager para revalidar o layout
        revalidate();
        repaint(); // Redesenha o painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Se a imagem for null, não tenta desenhá-la
        if (image == null) {
            // Desenhe um fundo em branco para indicar que não há imagem
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight()); // Limpa a tela
        } else {
            // Desenha a imagem em seu tamanho original
            g.drawImage(image, 0, 0, this);
        }
    }
    
    public void eraseImage() {
        this.image = null;
        // Volta ao tamanho padrão quando não há imagem
        setPreferredSize(new Dimension(0, 0));
        revalidate();
        repaint();
    }
    
    public void clearImage() {
        this.eraseImage(); // Podemos usar eraseImage() aqui
    }
    
    public BufferedImage copyImage(BufferedImage img) {
        if (img == null) return null;
        
        BufferedImage copy = new BufferedImage(
            img.getWidth(),
            img.getHeight(),
            img.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        
        return copy;
    }
    
    // Método para obter a imagem atual (pode ser útil)
    public BufferedImage getImage() {
        return this.image;
    }
}