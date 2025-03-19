/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DPIoperations.ColorToGray;
import LogsImage.ImageBuffer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Daza_
 */
public class dpiGUI extends javax.swing.JFrame {
    private final ImagePanel imagePanel;
    private final ImageBuffer imageBuffer;
    private BufferedImage originalImage;
    /**
     * Creates new form dpiGUI
     */
    public dpiGUI() {
            
        initComponents();
        setLocationRelativeTo(null);

        mainPanel.setLayout(new CardLayout());
        
        imagePanel = new ImagePanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(imagePanel, BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
        
        mainPanel.add(ConvertToGray,"ConvertToGray");
        mainPanel.add(jPanel1,"jPanel1");
        imageBuffer = new ImageBuffer();
        
       
        
    }
    
    private BufferedImage copyImage(BufferedImage source) {
        BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = copy.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return copy;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ConvertToGray = new javax.swing.JPanel();
        OriginalImage = new javax.swing.JPanel();
        GrayImage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonConverter = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelOriginalImage = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1360, 720));
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel3.setText("Bem Vindo ao Programa de Processamento Digital De Imagem, Selecione as opções Desejadas no Menu.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel3)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 839, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        getContentPane().add(mainPanel, "card2");

        javax.swing.GroupLayout OriginalImageLayout = new javax.swing.GroupLayout(OriginalImage);
        OriginalImage.setLayout(OriginalImageLayout);
        OriginalImageLayout.setHorizontalGroup(
            OriginalImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        OriginalImageLayout.setVerticalGroup(
            OriginalImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout GrayImageLayout = new javax.swing.GroupLayout(GrayImage);
        GrayImage.setLayout(GrayImageLayout);
        GrayImageLayout.setHorizontalGroup(
            GrayImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );
        GrayImageLayout.setVerticalGroup(
            GrayImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        jLabel1.setText("Imagem Original");

        jLabel2.setText("Imagem Apos Conversão");

        jButtonConverter.setText("Converter");
        jButtonConverter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConverterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConvertToGrayLayout = new javax.swing.GroupLayout(ConvertToGray);
        ConvertToGray.setLayout(ConvertToGrayLayout);
        ConvertToGrayLayout.setHorizontalGroup(
            ConvertToGrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConvertToGrayLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(ConvertToGrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(OriginalImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(ConvertToGrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ConvertToGrayLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConvertToGrayLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButtonConverter, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GrayImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );
        ConvertToGrayLayout.setVerticalGroup(
            ConvertToGrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConvertToGrayLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(ConvertToGrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1))
                .addGroup(ConvertToGrayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConvertToGrayLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OriginalImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConvertToGrayLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(GrayImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConvertToGrayLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jButtonConverter)))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        getContentPane().add(ConvertToGray, "card3");

        jLabelOriginalImage.setText("Imagem Original");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelOriginalImage)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelOriginalImage)
                .addContainerGap(435, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card3");

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Operações");

        jMenuItem2.setText("Conversor Para Cinza");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        imagePanel.clearImage();
        originalImage = null;
        imageBuffer.clear();
        
        CardLayout cardLayout =  (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel,"jPanel1");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg","png");
        
        JFileChooser JFCimage = new JFileChooser();
        
        JFCimage.setFileSelectionMode(JFileChooser.FILES_ONLY);
        JFCimage.setAcceptAllFileFilterUsed(false);
        JFCimage.addChoosableFileFilter(filter);    

        if(JFCimage.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File file = JFCimage.getSelectedFile();
            
            try {
                originalImage = ImageIO.read(file);
                
                imageBuffer.setImage(originalImage);
                
                imagePanel.setImage(originalImage);
                imagePanel.revalidate();
                
                jPanel1.setLayout(new BorderLayout());
                jPanel1.add(imagePanel, BorderLayout.CENTER);
                
                imagePanel.repaint();
                pack();
              
            } catch (IOException ex) {
                Logger.getLogger(dpiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout =  (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel,"ConvertToGray");
        
        
        
        
        BufferedImage img = imageBuffer.getImage();
        if(img != null){
            ImagePanel OriginalPanel = new ImagePanel();
            
            OriginalImage.removeAll();
            OriginalPanel.setImage(img);
            OriginalImage.setLayout(new BorderLayout());
            OriginalImage.add(OriginalPanel, BorderLayout.CENTER);
            OriginalImage.revalidate();
            OriginalImage.repaint();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButtonConverterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConverterActionPerformed
        // TODO add your handling code here:
        
        
        BufferedImage img = imageBuffer.getImage();
        
        
        if(img != null){
            
            BufferedImage copyImage = copyImage(originalImage);
            ColorToGray RGBtoGray = new ColorToGray();
            
            BufferedImage grayImage = RGBtoGray.toGray(copyImage);
            
            ImagePanel grayImagePanel = new ImagePanel();
            grayImagePanel.setImage(grayImage);
            GrayImage.removeAll();
            GrayImage.setLayout(new BorderLayout());
            GrayImage.add(grayImagePanel, BorderLayout.CENTER);
            GrayImage.revalidate();
            GrayImage.repaint();
        }
    }//GEN-LAST:event_jButtonConverterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dpiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dpiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dpiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dpiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dpiGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ConvertToGray;
    private javax.swing.JPanel GrayImage;
    private javax.swing.JPanel OriginalImage;
    private javax.swing.JButton jButtonConverter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelOriginalImage;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
