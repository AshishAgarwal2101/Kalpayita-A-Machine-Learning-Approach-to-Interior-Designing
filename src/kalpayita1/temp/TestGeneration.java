/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalpayita1;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ashish
 */
public class TestGeneration extends SimpleApplication{
    private static javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton2;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextField jTextField1;
    private static javax.swing.JTextField jTextPane1;
    private static JFrameTest window;
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                initComponents();
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private static void initComponents() {
        AppSettings settings = new AppSettings(true);
        settings.setWidth(640);
        settings.setHeight(480);
                
        TestGeneration canvasApplication = new TestGeneration();
        canvasApplication.setSettings(settings);
        canvasApplication.createCanvas(); // create canvas!
        JmeCanvasContext ctx = (JmeCanvasContext) canvasApplication.getContext();
        ctx.setSystemListener(canvasApplication);
        Dimension dim = new Dimension(1360, 600);
        ctx.getCanvas().setPreferredSize(dim);
        
        window = new JFrameTest();

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextField();

        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Perform Tasks");
        jButton2.setText("Hello");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                window.jButton1ActionPerformed(evt, "hkhj");
            }
        });

        //jScrollPane1.setViewportView(jTextPane1);

        /*javax.swing.GroupLayout layout = new javax.swing.GroupLayout(window.getContentPane());
        window.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(jButton1)))
                
                .addGroup(layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(ctx.getCanvas()))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30,30,30)
                .addComponent(ctx.getCanvas(), 800, 800, 801)
                .addContainerGap())
        );*/
        
        //window.setBounds(100, 100, 529, 300);
        //window.getContentPane().setLayout(null);
        window.setSize(1380, 800);
        JPanel panel = new JPanel(new GridBagLayout()); // a panel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.gridwidth = 50;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 40;
        //c.ipadx = 200;
        panel.add(jTextField1, c);
        c.gridy = 1;
        c.ipady = 1;
        //c.ipadx = 1;
        panel.add(jButton1, c);
        //panel.add(jScrollPane1);
        c.gridy = 2;
        c.ipady = 40;
        //c.ipadx = 200;
        //c.gridheight = 4;
        panel.add(jTextPane1, c);
        // add the JME canvas
        c.gridy = 3;
        c.ipady = 1;
        //c.ipadx = 1;
        panel.add(ctx.getCanvas(), c);

        window.add(panel);
        //window.pack();
        
        window.setVisible(true);
        
        canvasApplication.startCanvas();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setDragToRotate(true);
        
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        viewPort.setBackgroundColor(ColorRGBA.White);
        
        Spatial teapot1 = assetManager.loadModel("Models/table1.obj");
        Material mat_default1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default1.setTexture("ColorMap",assetManager.loadTexture("Models/Laptop4.jpg"));
        //mat_default.setColor("Color",ColorRGBA.Blue);
        teapot1.setMaterial(mat_default1);
        teapot1.setLocalTranslation(0f,0f,0.0f);
        teapot1.scale(0.28f);
        teapot1.rotate(0.2f,-0.0f,0f);
        rootNode.attachChild(teapot1);
        
        Box box = new Box(0.02f,0.4f,0f);
        Spatial wall1 = new Geometry("Box", box );
        Material mat_brick1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat_brick1.setTexture("ColorMap",assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        mat_brick1.setColor("Color",ColorRGBA.Black);
        wall1.setMaterial(mat_brick1);
        //wall1.rotate(0f,0.00f,0f);
        wall1.setLocalTranslation(-4.9f,0.1f,0.0f);
        rootNode.attachChild(wall1);
        makeRoom();
    }
    public void makeRoom(){
        Box box0 = new Box(10f,0.01f,8f);
        Spatial floor = new Geometry("Box", box0 );
        Material mat_brick0 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat_brick1.setTexture("ColorMap",assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        mat_brick0.setColor("Color",new ColorRGBA(0.45f,0.7f,0.2f,1f));
        floor.setMaterial(mat_brick0);
        //wall1.rotate(0f,0.00f,0f);
        floor.setLocalTranslation(0f,-3f,0.0f);
        rootNode.attachChild(floor);
       
        Box box = new Box(0.05f,2.6f,5.0f);
        Spatial wall1 = new Geometry("Box", box );
        Material mat_brick1 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat_brick1.setTexture("ColorMap",assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        mat_brick1.setColor("Color",new ColorRGBA(0.745f,0.117f,0.243f,1f));
        wall1.setMaterial(mat_brick1);
        //wall1.rotate(0f,0.00f,0f);
        wall1.setLocalTranslation(5.0f,0f,0.0f);
        rootNode.attachChild(wall1);
        Spatial wall2 = new Geometry("Box", box );
        Material mat_brick2 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat_brick2.setTexture("ColorMap",assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        mat_brick2.setColor("Color",new ColorRGBA(0.745f,0.117f,0.243f,1f));
        wall2.setMaterial(mat_brick2);
        //wall2.rotate(0.2f,-0.00f,0f);
        wall2.setLocalTranslation(-5.0f,0f,0.0f);
        rootNode.attachChild(wall2);
        Box box1 = new Box(7.0f,2.6f,0.05f);
        Spatial wall3 = new Geometry("Box", box1 );
        Material mat_brick3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Random random = new Random();
        mat_brick3.setTexture("ColorMap",assetManager.loadTexture("Textures/texture"+(random.nextInt(6)+1)+".jpg"));
        //mat_brick3.setColor("Color",ColorRGBA.Yellow);
        wall3.setMaterial(mat_brick3);
        wall3.rotate(0.f,0f,0f);
        wall3.setLocalTranslation(0f,0f,-5.0f);
        rootNode.attachChild(wall3);
    }
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
