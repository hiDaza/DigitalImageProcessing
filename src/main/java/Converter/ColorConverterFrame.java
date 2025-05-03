/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Converter;

/**
 *
 * @author Daza_
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ColorConverterFrame extends JFrame {

    private final JTextField[] rgbFields = new JTextField[3];
    private final JTextField[] hsvFields = new JTextField[3];
    private JPanel colorPreview;

    public ColorConverterFrame() {
        setupWindow();
        addComponents();
        setVisible(true);
    }

    private void setupWindow() {
        setTitle("Conversor de Cores Profissional");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
    }

    private void addComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        topPanel.add(createColorPickerPanel());
        topPanel.add(createPreviewPanel());
        
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.add(createInputPanel("Sistema RGB", rgbFields, "R", "G", "B"));
        centerPanel.add(createInputPanel("Sistema HSV", hsvFields, "H", "S", "V"));
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottomPanel.add(createConvertButton("RGB → HSV", this::convertRgbToHsv));
        bottomPanel.add(createConvertButton("HSV → RGB", this::convertHsvToRgb));
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }

    private JPanel createColorPickerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Seleção de Cor"));
        
        JButton colorButton = new JButton("Abrir Seletor de Cores");
        colorButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        colorButton.setBackground(new Color(70, 130, 180));
        colorButton.setForeground(Color.WHITE);
        colorButton.addActionListener(this::abrirSeletorCor);
        
        panel.add(colorButton, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPreviewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Pré-visualização"));
        
        colorPreview = new JPanel();
        colorPreview.setBackground(Color.WHITE);
        colorPreview.setPreferredSize(new Dimension(100, 100));
        
        panel.add(colorPreview, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInputPanel(String title, JTextField[] fields, String... labels) {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(title));
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        
        for(int i = 0; i < 3; i++) {
            JLabel label = new JLabel(labels[i] + ":");
            label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            
            fields[i] = new JTextField();
            fields[i].setFont(new Font("Segoe UI", Font.PLAIN, 12));
            
            panel.add(label);
            panel.add(fields[i]);
        }
        return panel;
    }

    private JButton createConvertButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(new Color(100, 150, 200));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }

    private void abrirSeletorCor(ActionEvent e) {
        Color corSelecionada = JColorChooser.showDialog(
            this,
            "Selecione uma Cor",
            colorPreview.getBackground()
        );

        if(corSelecionada != null) {
            updateColor(corSelecionada);
            colorPreview.setBackground(corSelecionada);
        }
    }

    private void updateColor(Color color) {
        
        rgbFields[0].setText(String.valueOf(color.getRed()));
        rgbFields[1].setText(String.valueOf(color.getGreen()));
        rgbFields[2].setText(String.valueOf(color.getBlue()));
        
        float[] hsv = ColorConverter.rgbToHsv(
            color.getRed(),
            color.getGreen(),
            color.getBlue()
        );
        
        hsvFields[0].setText(String.format("%.1f", hsv[0]));
        hsvFields[1].setText(String.format("%.1f", hsv[1] * 100));
        hsvFields[2].setText(String.format("%.1f", hsv[2] * 100));
    }

    private void convertRgbToHsv(ActionEvent e) {
        try {
            int[] rgb = new int[3];
            for(int i = 0; i < 3; i++) {
                rgb[i] = Integer.parseInt(rgbFields[i].getText());
            }
            
            float[] hsv = ColorConverter.rgbToHsv(rgb[0], rgb[1], rgb[2]);
            
            hsvFields[0].setText(String.format("%.1f", hsv[0]));
            hsvFields[1].setText(String.format("%.1f", hsv[1] * 100));
            hsvFields[2].setText(String.format("%.1f", hsv[2] * 100));
            
        } catch (NumberFormatException ex) {
            showError("Valores RGB inválidos! Use números entre 0 e 255.");
        }
    }

    private void convertHsvToRgb(ActionEvent e) {
        try {
            float[] hsv = new float[3];
            hsv[0] = Float.parseFloat(hsvFields[0].getText());
            hsv[1] = Float.parseFloat(hsvFields[1].getText()) / 100;
            hsv[2] = Float.parseFloat(hsvFields[2].getText()) / 100;
            
            int[] rgb = ColorConverter.hsvToRgb(hsv[0], hsv[1], hsv[2]);
            
            for(int i = 0; i < 3; i++) {
                rgbFields[i].setText(String.valueOf(rgb[i]));
            }
            colorPreview.setBackground(new Color(rgb[0], rgb[1], rgb[2]));
            
        } catch (NumberFormatException ex) {
            showError("Valores HSV inválidos!\nH: 0-360\nS/V: 0-100");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this, 
            message, 
            "Erro de Valores", 
            JOptionPane.ERROR_MESSAGE
        );
    }
}