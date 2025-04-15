package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private BufferedImage image; // Armazena a imagem
    
    public ImagePanel() {
       
    }


    public void setImage(BufferedImage img) {
        this.image = img;

        setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));

        revalidate();
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        if (image == null) {

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight()); // Limpa a tela
        } else {

            g.drawImage(image, 0, 0, this);
        }
    }
    
    public void eraseImage() {
        this.image = null;

        setPreferredSize(new Dimension(0, 0));
        revalidate();
        repaint();
    }
    
    public void clearImage() {
        this.eraseImage(); 
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
    
    public BufferedImage panelToBufferedImage(JPanel panel){
        int width = panel.getWidth();
        int height = panel.getHeight();
        
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = img.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        
        return img;
        
    }
    

    public BufferedImage getImage() {
        return this.image;
    }
}