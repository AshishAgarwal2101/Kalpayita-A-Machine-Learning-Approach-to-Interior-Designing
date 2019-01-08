/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalpayita1;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import static kalpayita1.HelloJME3.canvasApplication;

/**
 *
 * @author Ashish
 */
public class TestClass {
    private static javax.swing.JButton jButton1, jButton2, jButton3, jButton4, jButton5;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextField jTextField1;
    private static javax.swing.JTextField jTextField2;
    private static JFrameTest window;
    private static JPanel panel;
    public static JDialog processingDialog;
    public static JPanel processingPanel;
    public static JLabel processingLabel;
    public static ImageIcon processingIcon;
    private static JmeCanvasContext ctx;
    
    public static void main(String[] args) {
        /*HelloJME3 app = new HelloJME3();
        app.start();*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                initWindowComponents();
                initComponents();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    public static void initComponents() {
        AppSettings settings = new AppSettings(true);
        settings.setWidth(640);
        settings.setHeight(480);
        
        canvasApplication = new HelloJME3();
        canvasApplication.setSettings(settings);
        canvasApplication.createCanvas(); // create canvas!
        ctx = (JmeCanvasContext) canvasApplication.getContext();
        ctx.setSystemListener(canvasApplication);
        Dimension dim = new Dimension(1320, 550);
        ctx.getCanvas().setPreferredSize(dim);
           
        panel = new JPanel(new GridBagLayout()); // a panel
        JPanel panel1 = new JPanel(new GridBagLayout());     
        JPanel panel2 = new JPanel(new GridBagLayout());     
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.99;
        c.gridheight = 2;
        c.ipady = 40;
        panel1.add(jTextField1, c);
        c.gridheight = 1;
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 1;
        panel1.add(jButton1, c);
        c.gridy = 1;
        panel1.add(jButton3, c); //button to record for scene generation
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.weightx = 0.99;
        c.ipady = 40;
        panel2.add(jTextField2, c);
        
        c.gridheight = 1;
        c.weightx = 0.01;
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = 1;
        //c.ipadx = 1;
        panel2.add(jButton2, c);
        c.gridy = 1;
        panel2.add(jButton4, c); //button to record for scene generation
        
        panel1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20), BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new java.awt.Color(102,217,255), 3), "Generation"), BorderFactory.createEmptyBorder(15, 10, 15, 10))));
        panel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20), BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new java.awt.Color(102,217,255), 3), "Manipulation"), BorderFactory.createEmptyBorder(15, 10, 15, 10))));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.35;
        panel.add(panel1, c);
        c.gridx = 1;
        c.weightx = 0.05;
        JSeparator jSeparator = new JSeparator(SwingConstants.VERTICAL);
        jSeparator.setPreferredSize(new Dimension(4,100));
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(jSeparator, c);
        c.gridx = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.35;
        panel.add(panel2, c);
        /*JPanel panel3 = new JPanel(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 0;
        c2.fill = GridBagConstraints.HORIZONTAL;*/
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 3;
        //panel3.add(jButton5, c2);
        
        //panel.add(jButton5, c);
        
        // add the JME canvas
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 1;
        c.ipadx = 1;
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 0;
        c2.fill = GridBagConstraints.HORIZONTAL;
        JPanel panel4 = new JPanel(new GridBagLayout());
        jButton5.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        panel4.add(jButton5, c2);
        c2.gridy = 1;
        panel4.add(ctx.getCanvas(), c2);
        panel.add(panel4, c);
        
        window.add(panel);
        window.setVisible(true);
        canvasApplication.startCanvas();
    }
    public static void initWindowComponents(){
        window = new JFrameTest();
        window.setTitle("Kalpayita! Ease the way of Interior Designing.");

        jButton1 = new javax.swing.JButton("Generate Scene");
        jButton2 = new javax.swing.JButton("Manipulate Scene");
        jButton3 = new javax.swing.JButton("Speak");
        jButton4 = new javax.swing.JButton("Speak");
        jButton5 = new javax.swing.JButton("Randomize Objects Placements");
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField2 = new javax.swing.JTextField();
        
        jButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton1.setBackground(new java.awt.Color(255, 77,77));
        jButton1.setForeground(new java.awt.Color(255,255,255));
        jButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton2.setBackground(new java.awt.Color(255, 77,77));
        jButton2.setForeground(new java.awt.Color(255,255,255));
        jButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton3.setBackground(new java.awt.Color(51, 133,255));
        jButton3.setForeground(new java.awt.Color(255,255,255));
        jButton4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton4.setBackground(new java.awt.Color(51, 133,255));
        jButton4.setForeground(new java.awt.Color(255,255,255));
        jButton5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton5.setBackground(new java.awt.Color(57, 172, 115));
        jButton5.setForeground(new java.awt.Color(255,255,255));
        jTextField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        jTextField2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1380, 800);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try{
                    window.jButton1ActionPerformed(evt,jTextField1.getText());
                }catch(Exception e){
                    
                }
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelloJME3.sceneInteraction(jTextField2.getText());
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //speakGeneration(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //speakInteraction();
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //randomizePlacements();
            }
        });
        
        processingDialog = new JDialog();
        processingPanel = new JPanel();
        //processingIcon = createImageIcon("processing.gif", "Java");
        processingLabel = new JLabel("", processingIcon, JLabel.CENTER);
        processingPanel.add(processingLabel);
        processingDialog.add(processingPanel);
        processingDialog.setBounds(600, 250, 200, 190);
        processingDialog.setUndecorated(true);
        processingDialog.toFront();
        processingDialog.setAlwaysOnTop(true);
        //processingFrame.setVisible(true);
    }
}
