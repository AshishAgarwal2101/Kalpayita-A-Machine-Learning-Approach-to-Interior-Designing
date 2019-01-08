/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalpayita1;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import com.jogamp.opengl.util.texture.TextureData.Flusher;
import com.sun.prism.paint.Color;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import java.awt.Button;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import static kalpayita1.SpeechToText1.RECORD_TIME;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import com.jme3.scene.TriMesh;
/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class HelloJME3 extends SimpleApplication {
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
    public static Buffer b;
    
    public static float iii=0.0f;
    float maxX=9.6f,maxY=4.1f;
    float roomWidth=9.8f,roomHeight=5.8f;
    static boolean recreate = false, initScene=true;
    static HashMap<Integer,ArrayList<Node1>> levelInfo;
    static Node1 root;
    static Node roomNode;
    static Node roomNodeDuplicate;
    static HashMap<String, Double> scaleHashMap;
    static HashMap<String, double[]> rotationHashMap;
    static HashMap<String, Double> widthHashMap;
    static HashMap<String, Double> supportYHashMap;
    static HashMap<String, String> colorTextureObjHashMap;
    static HashMap<String, String> materialOrPatternMatcher;
    static HashMap<String, ColorRGBA> colorRGBAHashMap;
    static HashMap<String, boolean[][]> pixelsCoveredHashMap;
    static boolean[][] pixelsCovered = new boolean[96][70]; //96 pixels x-axis and 70 pixels in z-axis
    static HelloJME3 objectOfClass, canvasApplication, prevApplication;
    static ArrayList<Node> randomPlacePivot = new ArrayList<>();
    static ArrayList<ArrayList<Node>> randomPlaceObjectsToBePlaced;
    static ArrayList<HashMap<String, Double>> randomPlaceObjWidth;
    static ArrayList<HashMap<String, Double>> randomPlaceObjSupportY;
    static ArrayList<boolean[][]> randomPlaceArrOfPixelsCovered;
    static ArrayList<Integer> randomPlaceMaxX1;
    static ArrayList<Integer> randomPlaceMaxZ1;
    
    static Runnable runnable;

    public static void main(String[] args) {
        /*HelloJME3 app = new HelloJME3();
        app.start();*/
        runnable = new Runnable() {
            public void run() {
                initWindowComponents();
                initComponents();
            }
        };
        java.awt.EventQueue.invokeLater(runnable);
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
        canvasApplication.setPauseOnLostFocus(false);
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
        jButton5 = new javax.swing.JButton("Alter Objects Placements");
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
               // speakGeneration(evt);
               jTextField1.setText(canvasApplication.readFileRecorded());
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               // speakInteraction();
               jTextField2.setText(canvasApplication.readFileRecorded());
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomizePlacements();
            }
        });
        
        processingDialog = new JDialog();
        processingPanel = new JPanel();
        processingIcon = createImageIcon("processing.gif", "Java");
        processingLabel = new JLabel("", processingIcon, JLabel.CENTER);
        processingPanel.add(processingLabel);
        processingDialog.add(processingPanel);
        processingDialog.setBounds(600, 250, 200, 190);
        processingDialog.setUndecorated(true);
        processingDialog.toFront();
        processingDialog.setAlwaysOnTop(true);
        //processingFrame.setVisible(true);
    }
    private static ImageIcon createImageIcon(String path,String description) {
      java.net.URL imgURL = NewJFrame.class.getResource(path);
      
      if (imgURL != null) {
         return new ImageIcon(imgURL, description);
      } else {            
         System.err.println("Couldn't find file: " + path);
         return null;
      }
   }
    /*public static void updateScene(){
        initialwa();
        /*try {
            //System.out.flush();
            //recreate = true;
            //canvasApplication.destroy();
            //canvasApplication.destroyInput();
            //
            /*prevApplication = canvasApplication;
            window.setVisible(false);
            //canvasApplication.stop();
            try{
            window.remove(panel);
            }catch(Exception e){
            e.printStackTrace();
            }
            initComponents();
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(HelloJME3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //prevApplication.stop(true);
        //prevApplication.stop();
        /*Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                canvasApplication.rootNode.detachAllChildren();
                //initialwa();
                return "hello";
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        canvasApplication.enqueue(callable);
        window.repaint();
        //
        //canvasApplication.enqueue(runnable);
        /*AppSettings settings = new AppSettings(true);
        settings.setWidth(640);
        settings.setHeight(480);
                
        //objectOfClass= new HelloJME3();
        canvasApplication = new HelloJME3();
        canvasApplication.setSettings(settings);
        canvasApplication.createCanvas(); // create canvas!
        ctx = (JmeCanvasContext) canvasApplication.getContext();
        ctx.setSystemListener(canvasApplication);
        Dimension dim = new Dimension(1360, 600);
        ctx.getCanvas().setPreferredSize(dim);
        
        panel = new JPanel(new GridBagLayout()); // a panel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 200;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 40;
        //c.ipadx = 200;
        panel.add(jTextField1, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth=50;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 1;
        //c.ipadx = 1;
        panel.add(jButton1, c);
        c.fill = GridBagConstraints.CENTER;
   
        c.gridx = 1;
        c.gridy = 1;
        c.ipady = 1;
        //c.ipadx = 1;
        panel.add(jButton2, c);
        //panel.add(jScrollPane1);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 3;
        c.ipady = 40;
        //c.ipadx = 200;
        //c.gridheight = 4;
        panel.add(jTextField2, c);
        // add the JME canvas
        c.gridy = 4;
        c.ipady = 1;
        //c.ipadx = 1;
        panel.add(ctx.getCanvas(), c);

        window.add(panel);
        //window.pack();
        
        window.setVisible(true);
        
        canvasApplication.startCanvas();
    }*/
    public String readFileRecorded()
    {
        String s=null;
        try{
        FileReader freader = new FileReader("C:\\Users\\Ashish\\Documents\\NetBeansProjects\\komma\\application\\Madam.txt");
		BufferedReader br = new BufferedReader(freader);
		
		while((s = br.readLine()) != null) {
		System.out.println(s);
                return s;
		}
		freader.close();
                
        }
        catch(Exception e)
        {
            System.out.println("File not read");
        }
        System.out.println(s);
        return s;
    }
    @Override
    public void simpleInitApp(){
        updateScene();
    }
    
    public static void updateScene(){
        canvasApplication.flyCam.setDragToRotate(true);
        canvasApplication.assetManager.registerLocator("assets.zip", ZipLocator.class);
        int h = canvasApplication.settings.getAlphaBits();
        int w = canvasApplication.settings.getWidth();
        canvasApplication.guiNode.detachAllChildren();
        //System.out.println("Height="+h+"....Width="+w);
        if(initScene){
            canvasApplication.guiFont = canvasApplication.assetManager.loadFont("Interface/Fonts/Default.fnt");
            BitmapText text1 = new BitmapText(canvasApplication.guiFont, false);
            text1.setSize(canvasApplication.guiFont.getCharSet().getRenderedSize());
            text1.setText("GENERATED SCENE WILL BE DISPLAYED HERE");
            text1.setLocalTranslation(490, text1.getLineHeight()+300, 0);
            canvasApplication.guiNode.attachChild(text1);
            
            canvasApplication.viewPort.setBackgroundColor(ColorRGBA.Brown);
            initScene = false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloJME3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(recreate){
            canvasApplication.viewPort.setBackgroundColor(ColorRGBA.LightGray);
            canvasApplication.rootNode.attachChild(roomNodeDuplicate);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloJME3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
        canvasApplication.viewPort.setBackgroundColor(ColorRGBA.LightGray);
        colorRGBAHashMap = new HashMap<>();
        colorRGBAHashMap.put("brown", ColorRGBA.Brown);
        colorRGBAHashMap.put("blue", ColorRGBA.Blue);
        colorRGBAHashMap.put("black", ColorRGBA.Black);
        colorRGBAHashMap.put("yellow", ColorRGBA.Yellow);
        colorRGBAHashMap.put("green", ColorRGBA.Green);
        colorRGBAHashMap.put("orange", ColorRGBA.Orange);
        colorRGBAHashMap.put("gray", ColorRGBA.Gray);
        colorRGBAHashMap.put("red", new ColorRGBA(0.8f,0f,0f,1f));
        colorRGBAHashMap.put("pink", ColorRGBA.Pink);
        colorRGBAHashMap.put("white", ColorRGBA.White);
        
        
        //TriMesh d;
        scaleHashMap= new HashMap<>();
        rotationHashMap=new HashMap<>();
        widthHashMap = new HashMap<>();
        supportYHashMap=new HashMap<>();
        colorTextureObjHashMap=new HashMap<>();
        pixelsCoveredHashMap = new HashMap<>();
        randomPlacePivot = new ArrayList<>();
        randomPlaceObjectsToBePlaced = new ArrayList<>();
        randomPlaceObjWidth = new ArrayList<>();
        randomPlaceObjSupportY = new ArrayList<>();
        randomPlaceArrOfPixelsCovered = new ArrayList<>();
        randomPlaceMaxX1 = new ArrayList<>();
        randomPlaceMaxZ1 = new ArrayList<>();

        //graph construction 
        /*root = new Node1("room", null, null);
        Node1 bed = new Node1("bed", null, null);
        Node1 nightstand =new Node1("nightstand",null,null);
        Node1 chair0=new Node1("chair",null,null);
        Node1 cake=new Node1("cake",null,null);
        Node1 plate=new Node1("plate",null,null);
        Node1 table0=new Node1("table","wooden","material");
        
        root.next.add(bed);
        root.next.add(nightstand);
        root.next.add(chair0);
        root.next.add(table0);
        root.connectedAs.add("supports");
        root.connectedAs.add("supports");
        root.connectedAs.add("supports");
        root.connectedAs.add("supports");
        
        plate.next.add(cake);
        plate.connectedAs.add("supports");
        
        table0.next.add(plate);
        table0.connectedAs.add("supports");
        
        chair0.next.add(table0);
        chair0.connectedAs.add("left_of");
        //nightstand.next.add(table0);
        //nightstand.connectedAs.add("below");
        
        root.level = 0;
        bed.level = 1;
        nightstand.level = 1;
        chair0.level = 1;
        table0.level = 1;
        plate.level = 2;
        cake.level = 3;
        levelInfo = new HashMap<Integer,ArrayList<Node1>>();
        ArrayList<Node1> level0 = new ArrayList<>();
        level0.add(root);
        ArrayList<Node1> level1 = new ArrayList<>();
        level1.add(bed);
        level1.add(nightstand);
        level1.add(chair0);
        level1.add(table0);
        ArrayList<Node1> level2 = new ArrayList<>();
        level2.add(plate);
        ArrayList<Node1> level3 = new ArrayList<>();
        level3.add(cake);
        
        levelInfo.put(0,level0);
        levelInfo.put(1,level1);
        levelInfo.put(2,level2);
        levelInfo.put(3,level3);*/
        //end graph construction
        
        roomNode = makeRoom();
        initObjectsTransformation();
        initMaterialsAndPatternsMapping();
        placeObjects(roomNode);
        canvasApplication.rootNode.detachAllChildren();
        canvasApplication.rootNode.attachChild(roomNode);
        roomNodeDuplicate=roomNode;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloJME3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //xyz();
        /*Spatial teapot = assetManager.loadModel("Models/cake1.obj");
        Material mat_default = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default.setTexture("ColorMap",assetManager.loadTexture("Models/Laptop4.jpg"));
        //mat_default.setColor("Color",ColorRGBA.Blue);
        teapot.setMaterial(mat_default);
        teapot.setLocalTranslation(0f,-2.37f,0.0f);
        teapot.scale(0.5f);
        teapot.rotate(0f,0f,0f);
       // Node pivot = new Node("pivot");
        //rootNode1.attachChild(pivot);
        rootNode.attachChild(teapot);
        
        Spatial teapot1 = assetManager.loadModel("Models/plate1.obj");
        Material mat_default1 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default1.setTexture("ColorMap",assetManager.loadTexture("Models/Laptop4.jpg"));
        //mat_default.setColor("Color",ColorRGBA.Blue);
        teapot1.setMaterial(mat_default1);
        teapot1.setLocalTranslation(0f,-2.37f,0.0f);
        teapot1.scale(4f);
        teapot1.rotate(0.3f,0f,0f);
       // Node pivot = new Node("pivot");
        //rootNode1.attachChild(pivot);
        rootNode.attachChild(teapot1);

        Spatial teapot2 = assetManager.loadModel("Models/bed1.obj");
        Material mat_default2 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default2.setTexture("ColorMap",assetManager.loadTexture("Models/Laptop4.jpg"));
        //mat_default.setColor("Color",ColorRGBA.Blue);
        teapot2.setMaterial(mat_default2);
        teapot2.setLocalTranslation(-0f,-2.5f,-0f);
        teapot2.scale(0.93f);
        teapot2.rotate(0.0f,-0.1f,0f);
       // Node pivot = new Node("pivot");
        //rootNode1.attachChild(pivot);
        rootNode.attachChild(teapot2);*/
        //table
        /*Spatial table = assetManager.loadModel("Models/Table1.obj");
       
        Material mat_default1 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default1.setTexture("ColorMap",assetManager.loadTexture("Models/Laptop4.jpg"));
        mat_default1.setColor("Color",ColorRGBA.Blue);
        table.setMaterial(mat_default1);
        table.setLocalTranslation(0f,-2.1f,-1.8f);
        table.scale(0.2f);
        table.rotate(0f,-.1f,0f);
        
        rootNode.attachChild(table);
        
        //chair
         Spatial chair= assetManager.loadModel("Models/Chair4.obj");
       
        Material mat_default3 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default3.setTexture("ColorMap",assetManager.loadTexture("Models/light5.jpg"));
        mat_default3.setColor("Color",ColorRGBA.Brown);
        chair.setMaterial(mat_default3);
        chair.setLocalTranslation(-.6f,-2.6f,-1.5f);
        chair.scale(1.5f);
       chair.rotate(0f,-3.5f,0f);
        
        rootNode.attachChild(chair);
        
        //windo
                 Spatial wind= assetManager.loadModel("Models/VenetianBlind.obj");
       
        Material mat_default4 = new Material(
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default4.setTexture("ColorMap",assetManager.loadTexture("Models/light5.jpg"));
        mat_default4.setColor("Color",ColorRGBA.Brown);
        //chair.setMaterial(mat_default3);
        wind.setLocalTranslation(4.7f,-1f,-.5f);
        wind.scale(.1f);
       wind.rotate(-3.1f,.3f,-3.1f);
        
        rootNode.attachChild(wind);*/


        
/*
     Spatial ninja = assetManager.loadModel("Models/Ninja/Ninja.mesh.xml");
        ninja.scale(0.05f, 0.05f, 0.05f);
        ninja.rotate(0.0f, -3.0f, 0.0f);
        ninja.setLocalTranslation(0.0f, -5.0f, -2.0f);
       // ninja.scale(.3f);
        rootNode.attachChild(ninja);

        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
         */

    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }
  

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    public static void placeObjects(Node pivot1){
        ArrayList<Node> level1Nodes = new ArrayList<>();
        ArrayList<Node1> level1Objects = canvasApplication.root.next;
        ArrayList<String> level1ConnectedAs = canvasApplication.root.connectedAs;
        HashMap<String, ArrayList<Node[]>> relations= new HashMap<>();
        HashMap<String,Double> objWidth= new HashMap<>();
        HashMap<String, Double> objSupportY = new HashMap<>();
        HashMap<Node1, Node> node1ToNode = new HashMap<>();
        int i=0;
        for(Node1 level1Object:level1Objects){
            if(level1ConnectedAs.get(i++).equals("supports")){
                Node pivot = new Node(level1Object.name);
                //rootNode.attachChild(pivot);
                level1Nodes.add(pivot);
                node1ToNode.put(level1Object, pivot);
                placeSupportObjects(pivot, level1Object, 0f);
                //pivot.setLocalTranslation(-2.0f, 0f, -2.0f);
            }
        }
        
        //putting HashMap relations of objectsToBePlaced
        for(Node1 objectNode:level1Objects){
            ArrayList<Node1> connectedNodes = objectNode.next;
            ArrayList<String> connectedAs = objectNode.connectedAs;
            i=0;
            for(Node1 connectedNode:connectedNodes){
                if(!connectedAs.get(i).equals("supports") && level1Objects.contains(connectedNode)){
                    Node[] node = new Node[2];
                    node[0] = node1ToNode.get(objectNode);
                    node[1] = node1ToNode.get(connectedNode);
                    ArrayList<Node[]> nodesArrayList;
                    if(relations.containsKey(connectedAs.get(i))){
                        nodesArrayList = relations.get(connectedAs.get(i));
                    }
                    else{
                        nodesArrayList = new ArrayList<>();
                    }
                    nodesArrayList.add(node);
                    relations.put(connectedAs.get(i++), nodesArrayList);
                }
            }
        }
        //putting widths of all objects in the current level
        for(Node1 obj:level1Objects)
        {
            //double width = widthHashMap.get(obj.name.substring(0, obj.name.length()));
            objWidth.put(obj.name,widthHashMap.get(obj.name+"1"));
            objSupportY.put(obj.name, supportYHashMap.get(obj.name+"1"));
            if(obj.attributeType != null){
                if(widthHashMap.containsKey(obj.name+"2")){
                    objWidth.put(obj.name,widthHashMap.get(obj.name+"2"));
                    objSupportY.put(obj.name, supportYHashMap.get(obj.name+"2"));
                }
            }
            //System.out.println("objWidth: "+widthHashMap.get(obj.name+"1"));
        } 
        renderObjects(pivot1,level1Nodes,relations, objWidth, objSupportY, 96, 70);
    }
    public static void placeSupportObjects(Node pivot, Node1 objectNode, double supportYParent){
        String objectNamePresent = objectNode.name+"1";
        HashMap<String, ArrayList<Node[]>> relations1 = new HashMap<>();
        HashMap<String, Double> objectsWidth = new HashMap<>();
        HashMap<String, Double> supportY1 = new HashMap<>();
        
        if(objectNode.attributeType != null && scaleHashMap.containsKey(objectNode.name+"2")){
            objectNamePresent = objectNode.name+"2";
        }
        //System.out.println("objectNamePresent: "+objectNamePresent+"....attribte: "+objectNode.attribute+"...attributeType: "+objectNode.attributeType);
        double s = scaleHashMap.get(objectNamePresent);
        double[] rotation = rotationHashMap.get(objectNamePresent);
        double supportY = supportYHashMap.get(objectNamePresent);
        double width = widthHashMap.get(objectNamePresent);
        String temp = colorTextureObjHashMap.get(objectNamePresent);
        //System.out.println("s:"+s+"...supportY:"+supportY+"...temp:"+temp);
        char colorOrTexture = temp.charAt(0);
        String colorTexture = temp.substring(1);
        
        ArrayList<Node1> nextObjectNodes = objectNode.next;
        ArrayList<String> nextObjectsConnectedAs = objectNode.connectedAs;
        int i=0;
        ArrayList<Node> justSupportObjects = new ArrayList<>();
        for(Node1 nextObjectNode:nextObjectNodes){
            if(nextObjectsConnectedAs.get(i++).equals("supports")){
                Node tempNode = new Node(nextObjectNode.name);
                placeSupportObjects(tempNode, nextObjectNode, supportYParent+supportY);
                justSupportObjects.add(tempNode);
                String name = tempNode.getName();
                double width1 = widthHashMap.get(name+"1");
                if(nextObjectNode.attributeType != null && scaleHashMap.containsKey(objectNode.name+"2")){
                    width1 = widthHashMap.get(name+"2");
                }
                objectsWidth.put(name, width1);
                //break;
            }
        }
        /*for(Node tempNode:justSupportObjects){
            String name = tempNode.getName();
            double width1 = widthHashMap.get(name+"1");
            objectsWidth.put(name, width1);
        }*/
        int maxX1 = ((int)(width*10-3))>2 ? ((int)(width*10-3)) : 3;
        renderObjects(pivot, justSupportObjects, relations1, objectsWidth, supportY1, maxX1, maxX1);
        
        /////////////////////////////////
        String attribute = objectNode.attribute;
        String attributeType = objectNode.attributeType;
        Spatial object;
        if(canvasApplication.assetManager== null)
            System.out.println("DANGER!!!");
        object = null;
        try{
            object = canvasApplication.assetManager.loadModel("Models/"+objectNamePresent+".obj");
        }catch(Exception e){
            try{
                System.out.println("using glb");
                object = canvasApplication.assetManager.loadModel("Models/"+objectNamePresent+".glb");
            }catch(Exception e1){
                //e1.printStackTrace();
            }
            //e.printStackTrace();
        }
        Material mat_default1 = new Material(canvasApplication.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //System.out.println("info: "+colorOrTexture+"..."+colorTexture+"....obj:"+objectNamePresent);
        if(attributeType != null){
            /*try{
                object = canvasApplication.assetManager.loadModel("Models/"+objectNode.name+"2.obj");
            }catch(Exception e){
                try{
                    System.out.println("using glb");
                    object = canvasApplication.assetManager.loadModel("Models/"+objectNode.name+"2.glb");
                }catch(Exception e1){
                    //e1.printStackTrace();
                }
                //e.printStackTrace();
            }*/
            if(attributeType.equals("color")){
                if(colorRGBAHashMap.containsKey(attribute)){
                    mat_default1.setColor("Color",colorRGBAHashMap.get(attribute));
                }
                else{
                    mat_default1.setColor("Color",colorRGBAHashMap.get("white"));
                }
            }
            else if(attributeType.equals("material") || attributeType.equals("pattern")){
                if(materialOrPatternMatcher.containsKey(attribute)){
                    mat_default1.setTexture("ColorMap",canvasApplication.assetManager.loadTexture("Textures/"+materialOrPatternMatcher.get(attribute)+".jpg"));
                }
                else{
                    mat_default1.setTexture("ColorMap",canvasApplication.assetManager.loadTexture("Textures/texture12.jpg"));
                }
            }
            else{ //shape
                
            }
        }
        else{
            if(colorOrTexture == 't'){
                mat_default1.setTexture("ColorMap",canvasApplication.assetManager.loadTexture("Textures/"+colorTexture+".jpg"));
                //mat_default1.setTexture("ColorMap",assetManager.loadTexture("Textures/texture13.jpg"));
            }
            else{
                mat_default1.setColor("Color",colorRGBAHashMap.get(colorTexture));
                //mat_default1.setColor("Color",colorRGBAHashMap.get("brown"));
            }
        }
        //mat_default1.setTexture("ColorMap",assetManager.loadTexture("Textures/texture13.jpg"));
        //mat_default1.setColor("Color",colorRGBAHashMap.get("brown"));
        object.setMaterial(mat_default1);
        if(objectNamePresent.equals("table2")){
            object.setLocalTranslation(0f,-2.5f+(float)supportYParent,0.0f);
        }
        object.setLocalTranslation(0f,-2.5f+(float)supportYParent,0.0f); //-2.5f is the base of y-axis
        object.scale((float)s);
        object.rotate((float)rotation[0],(float)rotation[1],(float)rotation[2]);
        pivot.attachChild(object);
    }
    public static void renderObjects(Node pivot, ArrayList<Node> objectsToBePlaced, HashMap<String, ArrayList<Node[]>> relations, HashMap<String,Double> objWidth, HashMap<String, Double> objSupportY, int maxX1, int maxZ1){
        int numberOfObjects = objectsToBePlaced.size();
        /*HashMap<String, ArrayList<Node1[]>> relations = new HashMap<>(); //relations of objects to be placed
        HashMap<String,Double> objWidth=new HashMap<>();*/
        
       
        
        //putting objects in a relation in a separate Node
        for(String key:relations.keySet()){
            ArrayList<Node[]> objectPairsArrayList = relations.get(key);
            for(Node[] objectPairs:objectPairsArrayList){
                
                Node obj1 = objectPairs[0];
                Node obj2 = objectPairs[1];
                //System.out.println("obj1: "+obj1.getName());
                //System.out.println("obj2: "+obj2.getName());
                //System.out.println("relation: "+key);
                
                double obj1Width = objWidth.get(obj1.getName());
                double obj2Width = objWidth.get(obj2.getName());
                
                //Combined has "obj1_relation_obj2" as its node name
                Node combined= new Node(obj1.getName()+"_"+key+"_"+obj2.getName());
                combined.attachChild(obj1);
                combined.attachChild(obj2);
                double totalWidth = obj1Width+obj2Width+0.3;
                double combinedWidth = 0.0;
                if(key.equals("left_of"))
                {
                    obj1.setLocalTranslation((float)(-totalWidth/2.0+obj1Width/2.0), 0.0f, 0.0f);
                    obj2.setLocalTranslation((float)(totalWidth/2.0-obj2Width/2.0), 0.0f, 0.0f);
                    combinedWidth = totalWidth;
                }
                else if(key.equals("right_of"))
                {
                    obj1.setLocalTranslation((float)(totalWidth/2.0-obj1Width/2.0), 0.0f, 0.0f);
                    obj2.setLocalTranslation((float)(-totalWidth/2.0+obj2Width/2.0), 0.0f, 0.0f);
                    combinedWidth = totalWidth;
                }
                else if(key.equals("back_of"))
                {
                    obj1.setLocalTranslation(0.0f, 0.0f, (float)(-totalWidth/2.0+obj1Width/2.0));
                    obj2.setLocalTranslation(0.0f, 0.0f, (float)(totalWidth/2.0-obj2Width/2.0));
                    combinedWidth = obj1Width > obj2Width ? obj1Width:obj2Width;
                }
                else if(key.equals("front_of"))
                {
                    obj1.setLocalTranslation(0.0f, 0.0f, (float)(totalWidth/2.0-obj1Width/2.0));
                    obj2.setLocalTranslation(0.0f, 0.0f, (float)(-totalWidth/2.0+obj2Width/2.0));
                    combinedWidth = obj1Width > obj2Width ? obj1Width:obj2Width;
                }
                else if(key.equals("below"))
                {
                    obj1.setLocalTranslation(0.0f, (float)(-objSupportY.get(obj2.getName())+objSupportY.get(obj1.getName())) , 0.0f);
                    //obj2.setLocalTranslation( (float)(totalWidth/2.0), 0.0f , 0.0f);
                    combinedWidth = obj1Width > obj2Width ? obj1Width:obj2Width;
                }
                else if(key.equals("above"))
                {
                    obj1.setLocalTranslation(0.0f, (float)(objSupportY.get(obj2.getName())-objSupportY.get(obj1.getName())) , 0.0f);
                    //obj2.setLocalTranslation(0.0f, (float)(-totalWidth/2.0+(obj1Width+1.0)/2.0), 0.0f);
                    combinedWidth = obj1Width > obj2Width ? obj1Width:obj2Width;
                }
                double combinedSupport = (objSupportY.get(obj1.getName())>objSupportY.get(obj2.getName()))?objSupportY.get(obj1.getName()):objSupportY.get(obj2.getName());
                objectsToBePlaced.remove(obj1);
                objectsToBePlaced.remove(obj2);
                objectsToBePlaced.add(combined);
                objWidth.remove(obj1.getName());
                objWidth.remove(obj2.getName());
                objWidth.put(combined.getName(), combinedWidth);
                objSupportY.remove(obj1.getName());
                objSupportY.remove(obj1.getName());
                objSupportY.put(combined.getName(), combinedSupport);
            }
        }
        for(Node node:objectsToBePlaced){
            pivot.attachChild(node);
        }
        boolean [][] arrOfPixelsCovered = new boolean[maxX1][maxZ1];
        randomPlacePivot.add(pivot);
        randomPlaceObjectsToBePlaced.add(objectsToBePlaced);
        randomPlaceObjWidth.add(objWidth);
        randomPlaceObjSupportY.add(objSupportY);
        randomPlaceArrOfPixelsCovered.add(arrOfPixelsCovered);
        randomPlaceMaxX1.add(maxX1);
        randomPlaceMaxZ1.add(maxZ1);
        randomlyPositionObjects(pivot, objectsToBePlaced, objWidth, objSupportY, arrOfPixelsCovered, maxX1, maxZ1);
    }
    private static void randomlyPositionObjects(Node pivot, ArrayList<Node> objectsToBePlaced, HashMap<String,Double> objWidth, HashMap<String, Double> objSupportY, boolean[][] pixelsCoveredParameter, int maxX1, int maxZ1){
        //giving positions to objects and placing them
        //pixelsCoveredParameter = new boolean[maxX1][maxZ1];
        pixelsCovered = pixelsCoveredParameter;
       /* System.out.println(pivot.getName());
        for(int a=0;a<maxX1;a++)
        {
            for(int b=0;b<maxZ1;b++)
            {
                System.out.print(a+" "+b+" pixl "+pixelsCovered[a][b]+"\t");
            }
            System.out.println();
        }*/
        ArrayList<String> placed = new ArrayList<>();
        while(true){
            double largestWidth = 0.0;
            String largestObjName = "";
            Node largestObjNode = null;
            if(objectsToBePlaced.size() == placed.size()){
                break;
            }
            for(Node node:objectsToBePlaced){
                String name = node.getName();
                //System.out.println("Node name: "+name);
                if(!placed.contains(name)){
                    double width = objWidth.get(name);
                    //double supportY = objSupportY.get(name);
                    //pivot.attachChild(node);
                    if(width > largestWidth){
                        largestWidth = width;
                        largestObjName = name;
                        largestObjNode = node;
                    }
                }
            }
            //pivot.attachChild(largestObjNode);
            placed.add(largestObjName);
            //System.out.println("largest object: "+largestObjName+"...width: "+largestWidth);
            //double supportY = objSupportY.get(largestObjName);
            
            Random random = new Random();
            for(int iteration = 1; iteration<=5000; iteration++){ //maximum 100 iterations
                int xPixel = random.nextInt(maxX1-2);
                int zPixel = random.nextInt(maxZ1-2);
                boolean flag = false;
                int minX = (xPixel - (int)(largestWidth/2*10));
                int maxX = (xPixel + (int)(largestWidth/2*10));
                int minZ = (zPixel - (int)(largestWidth/2*10));
                int maxZ = (zPixel + (int)(largestWidth/2*10));
                if(minX<0 || maxX>=maxX1 || minZ<0 || maxZ>=maxZ1){
                    continue;
                }
                for(int i=minX; i<=maxX; i++){
                    for(int j=minZ; j<=maxZ; j++){
                        if(pixelsCovered[i][j]){
                            flag = true; //object can't be placed as pixel is not empty
                           // System.out.println("flag is changing to true for object "+largestObjName);
                        }
                    }
                    if(flag){ 
                        break;
                    }
                }
                
                if(!flag){ //object can be placed
                    float x = (xPixel-(maxX1-2)/2)/10.0f;
                    float z = (zPixel-(maxZ1-2)/2)/10.0f;
                    //System.out.println("Object: "+largestObjName+"....x="+x+"...z="+z);
                    largestObjNode.setLocalTranslation(x, 0, z);
                    
                    //fill the pixels for this object
                    for(int i=minX; i<=maxX; i++){
                        for(int j=minZ; j<=maxZ; j++){
                            pixelsCovered[i][j] = true;
                        }
                    }
                    break;
                }
            }
            /*if(placed.size() == 1){
                //place in the left front corner
                largestObjNode.setLocalTranslation(-maxX/2+(float)largestWidth/2, 0, -3.5f);
                for(int i=0; i<(int)largestWidth*10; i++){
                    for(int j=0; j<(int)largestWidth*10; j++){
                        pixelsCovered[i][j] = true;
                    }
                }
            }*/
        }
        
       pixelsCoveredHashMap.put(pivot.getName(), pixelsCovered);
    }
    public static Node makeRoom(){
        Random random = new Random();
        
        Node node = new Node("room");
        Box box0 = new Box(10f,0.01f,11f);
        Spatial floor = new Geometry("Box", box0 );
        Material mat_brick0 = new Material(
            canvasApplication.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick0.setTexture("ColorMap",canvasApplication.assetManager.loadTexture("Textures/texture2.jpg"));
        //mat_brick0.setColor("Color",new ColorRGBA(0.3f,0.5f,0.1f,1f));
        //mat_brick0.setColor("Color",new ColorRGBA(0.45f,0.7f,0.2f,1f));
        floor.setMaterial(mat_brick0);
        //wall1.rotate(0f,0.00f,0f);
        floor.setLocalTranslation(0f,-3.5f,0.0f);
        node.attachChild(floor);
       
        String sWallName = "sWallTexture"+(random.nextInt(7)+1)+".jpg";
        Box box = new Box(0.05f,2.6f,5.0f);
        Spatial wall1 = new Geometry("Box", box );
        Material mat_brick1 = new Material(
            canvasApplication.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat_brick1.setTexture("ColorMap",canvasApplication.assetManager.loadTexture("Textures/"+sWallName));
        mat_brick1.setColor("Color",new ColorRGBA(0.745f,0.117f,0.243f,1f));
        wall1.setMaterial(mat_brick1);
        //wall1.rotate(0f,0.00f,0f);
        wall1.setLocalTranslation(5.0f,0f,0.0f);
        node.attachChild(wall1);
        Spatial wall2 = new Geometry("Box", box );
        Material mat_brick2 = new Material(
            canvasApplication.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat_brick2.setTexture("ColorMap",canvasApplication.assetManager.loadTexture("Textures/"+sWallName));
        mat_brick2.setColor("Color",new ColorRGBA(0.745f,0.117f,0.243f,1f));
        wall2.setMaterial(mat_brick2);
        //wall2.rotate(0.2f,-0.00f,0f);
        wall2.setLocalTranslation(-5.0f,0f,0.0f);
        node.attachChild(wall2);
        Box box1 = new Box(7.0f,2.6f,0.05f);
        Spatial wall3 = new Geometry("Box", box1 );
        Material mat_brick3 = new Material(canvasApplication.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick3.setTexture("ColorMap",canvasApplication.assetManager.loadTexture("Textures/sWallTexture"+(random.nextInt(7)+1)+".jpg"));
        //mat_brick3.setColor("Color",ColorRGBA.Yellow);
        wall3.setMaterial(mat_brick3);
        wall3.rotate(0.f,0f,0f);
        wall3.setLocalTranslation(0f,0f,-5.0f);
        node.attachChild(wall3);
        return node;
    }
    public static void initMaterialsAndPatternsMapping(){
        materialOrPatternMatcher = new HashMap<>();
        JSONParser parser;
        parser = new JSONParser();
        try{
            JSONObject o = (JSONObject)parser.parse(new FileReader("src\\resources\\json\\materials_and_patterns_mapping.json"));
            Set<String> keys = (Set<String>) o.keySet();
            for(String key:keys){
                String texture = (String)o.get(key);
                materialOrPatternMatcher.put(key, texture);
                //System.out.println("For material or pattern: "+key+".....texture:"+texture);
            }
        }catch(Exception e){
            e.printStackTrace();
        }    
    }
    public static void initObjectsTransformation(){
        JSONParser parser;
        parser = new JSONParser();
        try{
            JSONObject o = (JSONObject)parser.parse(new FileReader("src\\resources\\json\\transformation.json"));
            Set<String> keys = (Set<String>) o.keySet();
            for(String key:keys){
                //System.out.println("For object: "+key);
                JSONObject o1 = (JSONObject) o.get(key);
                double[] rotation = new double[3];
                double s = (double) o1.get("s");
                rotation[0] = (double) o1.get("r1");
                rotation[1] = (double) o1.get("r2");
                rotation[2] = (double) o1.get("r3");
                double width = (double) o1.get("width");
                double supportY = (double) o1.get("supportY");
                String colorTexture = (String) o1.get("looks");
                scaleHashMap.put(key, s);
                rotationHashMap.put(key, rotation);
                widthHashMap.put(key, width);
                supportYHashMap.put(key, supportY);
                colorTextureObjHashMap.put(key, colorTexture);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   
    public static void sceneInteraction(String st)
    {
        //check the first word for modification type
        st=st.toLowerCase();
        String first="",typeOfModification="";
        int i;
        for(i=0;i<st.length();i++)
        {
            if(st.charAt(i)==' ')
            {
                break;
            }
            else
            {
                first=first+st.charAt(i);
            }   
        }
         if(first.equals("insert"))//format: insert a/an object phrase
                {
                    typeOfModification = "insert";
                }
                else if(first.equals("move"))
                {
                    typeOfModification = "move";
                }
                else if(first.equals("delete"))
                {
                    typeOfModification = "delete";
                }
                else if(first.equals("enlarge"))//scaling increase
                {
                    typeOfModification = "enlarge";
                }
                else if(first.equals("shrink"))
                {
                    typeOfModification = "shrink";
                }
                else
                {
                jTextField2.setText("Not a possible modification!");
                }
         
        //System.out.println(first+"    type= "+typeOfModification);
        //Insert , Delete, Move, Scale allowed
        String newWord="";
        st=st.toLowerCase();
        String object1="", object2="", attribute=null, attributeType=null;
        //JFrameTest obj = new JFrameTest();
        List<CoreMap> sentences = window.sceneParsingStanfordPipelineInitialisation(st);
        for(CoreMap sentence:sentences)
        {
            for(CoreLabel token: sentence.get(TokensAnnotation.class))
            {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lem = token.get(CoreAnnotations.LemmaAnnotation.class);
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                //System.out.println("word  "+word);
                //System.out.println("size "+window.relationsMapping.size());
                //System.out.println("word rel  "+window.relationsMapping.containsKey(word));
             //find objects and attributes
             if(window.relationsMapping.containsKey(word)){//the word is a relation
                 attributeType = "relation";
                 attribute = window.relationsMapping.get(word);
             }
             else//not a realtion, check for it being an object or other attribute
             {
                if((window.containsElement(window.toBeObjects, lem) || window.containsElement(window.toBeObjectsPlural, lem) || pos.equals("NN") || pos.equals("NNS")) && !window.relationsMapping.containsKey(word))
                {
                    //mapping objects and attributes
                    //System.out.println("1   found attribute");
                    if(window.physicalObjFinding(lem) || (window.containsElement(window.toBeObjects, lem) || window.containsElement(window.toBeObjectsPlural, lem)))
                    {//if not object found from the WordNet then find in the ArrayList
                        object1 = lem;
                        //System.out.println("obj "+object1);
                    }
                    
                    else if(!window.physicalObjFinding(lem) && !(window.containsElement(window.toBeObjects, lem) || window.containsElement(window.toBeObjectsPlural, lem)))
                    { //attribute (not visualizable)
                        //System.out.println("found attribute");
                        if(window.colorFinding(word)){
                            attributeType= "color";
                            attribute = lem;
                        }
                        else if(window.materialFinding(word)){
                            attributeType= "material";
                            attribute = lem;
                        }
                        else if(window.shapeFinding(word))
                        {
                            attributeType= "shape";
                            attribute = lem;
                        }
                        else
                        {
                            attributeType= "pattern";
                            attribute = lem;
                        }
                    }
                     
                 }
                else if((!(window.containsElement(window.toBeObjects, lem) || window.containsElement(window.toBeObjectsPlural, lem)) && pos.equals("JJ")) && !window.relationsMapping.containsKey(word))
                {
                    //System.out.println("found attribute");
                        if(window.colorFinding(word)){
                            attributeType= "color";
                            attribute = lem;
                        }
                        else if(window.materialFinding(word)){
                            attributeType= "material";
                            attribute = lem;
                        }
                        else if(window.shapeFinding(word))
                        {
                            attributeType= "shape";
                            attribute = lem;
                        }
                        else
                        {
                            attributeType= "pattern";
                            attribute = lem;
                        }
                }
               }
            }   
        } 
        //System.out.println("object= "+object1+" attr= "+attribute+" attr type = "+attributeType);
        //RENDERING
        //find support object for the object to be inserted
        String supportObj="";
        if(typeOfModification.equals("insert"))
        {
            if(window.scene_type.equals("living_room"))
            {
                if(window.living_room_support.containsKey(object1))
                    supportObj=window.living_room_support.get(object1);
            }
            else if(window.scene_type.equals("bedroom"))
            {
                if(window.bedroom_support.containsKey(object1))
                    supportObj=window.bedroom_support.get(object1);
            }
            else if(window.scene_type.equals("office"))
            {
                if(window.office_support.containsKey(object1))
                    supportObj=window.office_support.get(object1);
            }
            Node nodeObjInsert = new Node(object1);
             //System.out.println("supportObj "+supportObj);
            //find supportObj and add nodeObjInsert
            final String supp=supportObj;
            final String attType = attributeType;
            //finding object in scene graph
           SceneGraphVisitor visitor = new SceneGraphVisitor() {
           @Override
            public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(supp)) {
                    //System.out.println("s= "+s);
                    final Node no=(Node)n;
                    final Node no1 = n.getParent();
                    //System.out.println("s= "+s+" no1 = "+no1.getName());
                    no.attachChild(nodeObjInsert);
                    final ArrayList<Node> arrList=new ArrayList<>();
                    arrList.add(nodeObjInsert);
                    final HashMap<String, Double> objWidth= new HashMap<>();
                    final HashMap<String, Double> objSupportY= new HashMap<>();
                    double var = 0.0f;
                    if(!no1.getName().equals("room") && !no1.getName().equals("Root Node")){
                        var=supportYHashMap.get(no1.getName()+"1");
                        //System.out.println(var);
                    }
                    iii=(float)var;
                    //System.out.println("iii="+iii);
                    objWidth.put(nodeObjInsert.getName(),widthHashMap.get(nodeObjInsert.getName()+"1"));
                    objSupportY.put(nodeObjInsert.getName(), (var+supportYHashMap.get(nodeObjInsert.getName()+"1")));
                    if(attType != null){
                        if(widthHashMap.containsKey(nodeObjInsert.getName()+"2")){
                            objWidth.put(nodeObjInsert.getName(),widthHashMap.get(nodeObjInsert.getName()+"2"));
                            objSupportY.put(nodeObjInsert.getName(), (var+supportYHashMap.get(nodeObjInsert.getName()+"2")));
                        }
                    }
                    int j=0;
                    for(Node node:randomPlacePivot){
                        if(node.getName() == no.getName()){ //same pivot
                            //System.out.println("Inserting new random place values: "+no.getName()+"...."+objWidth.get(nodeObjInsert.getName())+"...."+objSupportY.get(nodeObjInsert.getName())+"...."+nodeObjInsert.getName());
                            ArrayList<Node> objectsPlaced1 = randomPlaceObjectsToBePlaced.get(j);
                            HashMap<String, Double> objWidth1 = randomPlaceObjWidth.get(j);
                            HashMap<String, Double> supportY1 = randomPlaceObjSupportY.get(j);
                            objectsPlaced1.add(nodeObjInsert);
                            objWidth1.put(nodeObjInsert.getName(), objWidth.get(nodeObjInsert.getName()));
                            supportY1.put(nodeObjInsert.getName(), objSupportY.get(nodeObjInsert.getName()));
                            //ArrayList<boolean[][]> randomPlaceArrOfPixelsCovered;
                            //Integer> randomPlaceMaxX1;
                            //ArrayList<Integer> randomPlaceMaxZ1;
                        }
                        j++;
                    }
                    //System.out.println(no.getName()+".......");
                    if(!supp.equals("room")){
                       
                        double width = widthHashMap.get(no.getName()+"1");//width of the support
                        int maxX1 = ((int)(width*10-3))>2 ? ((int)(width*10-3)) : 3;
                        //use pixelsCovered to find space for new object
                        randomlyPositionObjects(nodeObjInsert,arrList,objWidth,objSupportY,pixelsCoveredHashMap.get(supp),maxX1,maxX1);
                    }
                    else{
                        randomlyPositionObjects(nodeObjInsert,arrList,objWidth,objSupportY,pixelsCoveredHashMap.get(supp),96,70);
                    }
                }
            }

        };
        roomNodeDuplicate.depthFirstTraversal(visitor);
        //System.out.println("traversal");
           SceneGraphVisitor visitor89 = new SceneGraphVisitor() {
           @Override
            public void visit(Spatial n) {
                
                if(n==null)
                    return;
                String s =  n.getName();
                
                //System.out.println("s = "+s+" parent = "+n.getParent());
                    
                
            }

        };
        roomNodeDuplicate.breadthFirstTraversal(visitor89);
        //int iii=0;
        SceneGraphVisitor visitor1 = new SceneGraphVisitor() {
           @Override
            public void visit(Spatial n) {
                
                if(n==null)
                    return;
                String s =  n.getName();
                if (s!=null) {
                    //System.out.println("s = "+s+" parent = "+n.getParent()+" translate = "+n.getLocalTranslation());
                    
                }
            }

        };
        roomNodeDuplicate.depthFirstTraversal(visitor1);
        Node1 node1= new Node1(nodeObjInsert.getName(),attribute,attributeType);
        if(supp.equals("room"))
        {
            placeSupportObjects(nodeObjInsert,node1,0.0f);
        }
        else{
            double suppYToSend = iii+supportYHashMap.get(supp+"1");
            //System.out.println(suppYToSend+"......."+iii);
            if(attributeType != null){
                if(supportYHashMap.containsKey(supp+"2")){
                    suppYToSend = supportYHashMap.get(supp+"2");
                }
            }
            placeSupportObjects(nodeObjInsert,node1,suppYToSend);//display new object
        }
        xyz();

        }
        else if (typeOfModification.equals("delete"))
        {
            //search for object1 in sceneGraph and delete it
            
            final String ob=object1;
            //finding object in scene graph
            SceneGraphVisitor visitorDel = new SceneGraphVisitor() {
                @Override
                public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(ob)) {
                Node parentOb= n.getParent();
                n.removeFromParent();
                }
                }
            };
            
            canvasApplication.rootNode.depthFirstTraversal(visitorDel);
            xyz();
           
        }
        else if(typeOfModification.equals("enlarge"))
        {
            //find the node and modify the value of scalingHashMap for that object
             final String ob=object1;
            //finding object in scene graph
            SceneGraphVisitor visitorDel = new SceneGraphVisitor() {
                @Override
                public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(ob)) {
                Node parentOb= n.getParent();
                Vector3f r = n.getLocalTranslation();
                //System.out.println("r.y = "+r.y+"..supportY = "+supportYHashMap.get(n.getName()+"1"));
                n.setLocalScale(1.2f);
                n.setLocalTranslation(r.x,(float)(r.y+(supportYHashMap.get(n.getName()+"1")*0.2)),r.z);
                //System.out.println("r.y = "+r.y+"..supportY = "+supportYHashMap.get(n.getName()+"1"));
                
                }
                }
            };
            
            canvasApplication.rootNode.depthFirstTraversal(visitorDel);
            xyz();
           
        }
        else if(typeOfModification.equals("shrink"))
        {
           //find the node and modify the value of scalingHashMap for that object
             final String ob=object1;
            //finding object in scene graph
            SceneGraphVisitor visitorShrink = new SceneGraphVisitor() {
                @Override
                public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(ob)) {
                Node parentOb= n.getParent();
                Vector3f r = n.getLocalTranslation();
                n.setLocalScale(0.8f);
                n.setLocalTranslation(r.x,(float)(r.y-(supportYHashMap.get(n.getName()+"1")*0.2)),r.z);
                //System.out.println("r.y = "+r.y+"..supportY = "+supportYHashMap.get(n.getName()+"1"));
                
                }
                }
            };
            
            canvasApplication.rootNode.depthFirstTraversal(visitorShrink);
            xyz();
           
        }
        else if(typeOfModification.equals("move"))
        {
            
            if(!(object1.equals("")))//single object movement
            {
               if(attributeType.equals("relation")) 
               {
                   //move the object1 to attribute position
                   //find node and translate it to left, right, front, back etc positions
                   if(attribute.equals("left_of"))
                   {
                      //translate in -x direction 
                         final String ob=object1;
            //finding object in scene graph
            SceneGraphVisitor visitorShrink = new SceneGraphVisitor() {
                @Override
                public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(ob)) {
                Node parentOb= n.getParent();
                Vector3f r = n.getLocalTranslation(); 
               
                n.setLocalTranslation(r.x-0.6f,r.y,r.z);
                //System.out.println("r.y = "+r.y+"..supportY = "+supportYHashMap.get(n.getName()+"1"));
                
                }
                }
            };
            
            canvasApplication.rootNode.depthFirstTraversal(visitorShrink);
            xyz();
                   }
                   else if(attribute.equals("right_of"))
                   {
                      //translate in +x direction
                         final String ob=object1;
            //finding object in scene graph
            SceneGraphVisitor visitorShrink = new SceneGraphVisitor() {
                @Override
                public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(ob)) {
                Node parentOb= n.getParent();
                Vector3f r = n.getLocalTranslation(); 
               
                n.setLocalTranslation(r.x+0.6f,r.y,r.z);
                //System.out.println("r.y = "+r.y+"..supportY = "+supportYHashMap.get(n.getName()+"1"));
                
                }
                }
            };
            
            canvasApplication.rootNode.depthFirstTraversal(visitorShrink);
            xyz();
                   }
                   else if(attribute.equals("front_of"))
                   {
                      //translate in +z direction 
                         final String ob=object1;
            //finding object in scene graph
            SceneGraphVisitor visitorShrink = new SceneGraphVisitor() {
                @Override
                public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(ob)) {
                Node parentOb= n.getParent();
                Vector3f r = n.getLocalTranslation(); 
               
                n.setLocalTranslation(r.x,r.y,r.z+0.6f);
                //System.out.println("r.y = "+r.y+"..supportY = "+supportYHashMap.get(n.getName()+"1"));
                
                }
                }
            };
            
            canvasApplication.rootNode.depthFirstTraversal(visitorShrink);
            xyz();
                   }
                   else if(attribute.equals("back_of"))
                   {
                       //translate in -z direction
                         final String ob=object1;
            //finding object in scene graph
            SceneGraphVisitor visitorShrink = new SceneGraphVisitor() {
                @Override
                public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s.equals(ob)) {
                Node parentOb= n.getParent();
                Vector3f r = n.getLocalTranslation(); 
               
                n.setLocalTranslation(r.x,r.y,r.z-0.6f);
                //System.out.println("r.y = "+r.y+"..supportY = "+supportYHashMap.get(n.getName()+"1"));
                
                }
                }
            };
            
            canvasApplication.rootNode.depthFirstTraversal(visitorShrink);
            xyz();
                   }
               }
            }
        }
    }
    public static void xyz()
    {
        /*Node root = new Node("root");
Node a = new Node("a");

Node b = new Node("b");

Spatial aa = new Geometry("aa");

Spatial ab = new Geometry("ab");

Spatial ba = new Geometry("ba");

Spatial bb = new Geometry("bb");
 root.attachChild(a);
root.attachChild(b);
a.attachChild(aa);
a.attachChild(ab);
b.attachChild(ba);
b.attachChild(bb);*/
        //canvasApplication.rootNode.updateGeometricState();
        recreate = true;
        updateScene();
        //rootNode.attachChild(roomNodeDuplicate);
        /* System.out.println("herezzzz...///");
         SceneGraphVisitor visitor = new SceneGraphVisitor() {
           @Override
            public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s!=null) {
                    System.out.println("s = "+s+" parent = "+n.getParent());
                    
                }
            }

        };
    rootNode.depthFirstTraversal(visitor);

        rootNode.detachChild(roomNode);
         System.out.println("here...///");
         SceneGraphVisitor visitor1 = new SceneGraphVisitor() {
           @Override
            public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s!=null) {
                    System.out.println("s = "+s+" parent = "+n.getParent());
                    
                }
            }

        };
    rootNode.depthFirstTraversal(visitor1);

        rootNode.attachChild(roomNodeDuplicate);
             System.out.println("here!!!");
         SceneGraphVisitor visitor2 = new SceneGraphVisitor() {
           @Override
            public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s!=null) {
                    System.out.println("s = "+s+" parent = "+n.getParent());
                    
                }
            }

        };
    roomNodeDuplicate.depthFirstTraversal(visitor2);
   
        rootNode.updateGeometricState();
        

          System.out.println("here....");
         SceneGraphVisitor visitor3 = new SceneGraphVisitor() {
           @Override
            public void visit(Spatial n) {
    
                if(n==null)
                    return;
                String s =  n.getName();
                if (s!=null) {
                    System.out.println("s = "+s+" parent = "+n.getParent());
                    
                }
            }

        };
    roomNodeDuplicate.depthFirstTraversal(visitor3);*/
        System.out.println("here");
    }      
    public static void speakGeneration(java.awt.event.ActionEvent evt){
        canvasApplication.setPauseOnLostFocus(true);
        SpeechToText1 recorder = new SpeechToText1();
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                    System.out.println("waiting");

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    String res = recorder.finish();
                    window.jButton1ActionPerformed(evt, res);
                } catch (Exception ex) {
                    Logger.getLogger(Kalpayita1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        stopper.start();
        // start recording
        recorder.start();
        canvasApplication.setPauseOnLostFocus(false);
    }
    public static void speakInteraction(){
        canvasApplication.setPauseOnLostFocus(true);
        SpeechToText1 recorder = new SpeechToText1();
        //processingDialog.setVisible(true);
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                    System.out.println("waiting");

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    String res = recorder.finish();
                    sceneInteraction(res);
                } catch (Exception ex) {
                    Logger.getLogger(Kalpayita1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        stopper.start();
        recorder.start();
        try{
            stopper.join();
        }catch(Exception e){
            
        }
        //processingDialog.setVisible(false);
        // start recording
        canvasApplication.setPauseOnLostFocus(false);
    }
    public static void randomizePlacements(){
        canvasApplication.setPauseOnLostFocus(true);
        int i=0;
       
        for(Node pivot:randomPlacePivot){
            ArrayList<Node> objectsToBePlaced = randomPlaceObjectsToBePlaced.get(i);
            HashMap<String, Double> objWidth = randomPlaceObjWidth.get(i);
            HashMap<String, Double> objSupportY = randomPlaceObjSupportY.get(i);
            boolean[][] arrOfPixelsCovered = randomPlaceArrOfPixelsCovered.get(i);
            
            int maxX1 = randomPlaceMaxX1.get(i);
            int maxZ1 = randomPlaceMaxZ1.get(i);
            arrOfPixelsCovered = cleaned(arrOfPixelsCovered, maxX1, maxZ1);
            randomlyPositionObjects(pivot, objectsToBePlaced, objWidth, objSupportY, arrOfPixelsCovered, maxX1, maxZ1);
            i++;
        }
        recreate = true;
        updateScene();
        canvasApplication.setPauseOnLostFocus(false);
    }
    public static boolean[][] cleaned(boolean[][] arrOfPixelsCovered, int maxX1, int maxZ1){
        int i,j;
        for(i=0; i<maxX1; i++){
            for(j=0; j<maxZ1; j++){
                arrOfPixelsCovered[i][j] = false;
            }
        }
        return arrOfPixelsCovered;
    }
}
