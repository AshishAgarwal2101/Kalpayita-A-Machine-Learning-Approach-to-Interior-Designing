
package kalpayita1;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import static kalpayita1.HelloJME3.canvasApplication;
import static kalpayita1.HelloJME3.colorRGBAHashMap;

/**
 *
 * @author Ashish
 */
public class HelloAssets extends SimpleApplication{
    public static void main(String[] args) {
        HelloAssets app = new HelloAssets();
        app.start();
    }

    @Override
    public void simpleInitApp() {

        viewPort.setBackgroundColor(ColorRGBA.Blue);
        assetManager.registerLocator("assets.zip", ZipLocator.class);

        Material mat_default1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default1.setTexture("ColorMap",assetManager.loadTexture("Textures/texture1.jpg"));
        Material mat_default2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default2.setColor("Color",ColorRGBA.Black);
        Material mat_default3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_default3.setTexture("ColorMap",assetManager.loadTexture("Textures/wallTexture6.jpg"));
        // Load a model from test_data (OgreXML + material + texture)
        Spatial ninja = assetManager.loadModel("Models/cake1.obj");
        ninja.setMaterial(mat_default3);
        ninja.scale(0.005f);
        ninja.rotate(0.5f, 0.0f, 0.0f);
        ninja.setLocalTranslation(0.0f, -0.7f, 0.0f);
        rootNode.attachChild(ninja);
        
        Spatial ninja3 = assetManager.loadModel("Models/sofa1.obj");
        ninja3.setMaterial(mat_default1);
        ninja3.scale(0.0012f);
        ninja3.rotate(0.0f, 0.0f, 0.0f);
        ninja3.setLocalTranslation(-2.0f, -2.42f, 0.0f);
        rootNode.attachChild(ninja3);
        
        Spatial ninja1 = assetManager.loadModel("Models/floor-lamp1.obj");
        ninja1.scale(0.04f);
        //ninja1.rotate(2.7f, 2.15f, 2.0f);
        ninja1.rotate(0.0f, 0.0f, 0.0f);
        ninja1.setMaterial(mat_default1);
        ninja1.setLocalTranslation(0.0f, -1.5f, 0.0f);
        rootNode.attachChild(ninja1);
        
        /*Spatial ninja2 = assetManager.loadModel("Models/desk1.obj");
        ninja2.scale(0.28f);
        ninja2.rotate(0.2f, 0.0f, 0.0f);
        ninja2.setMaterial(mat_default1);
        ninja2.setLocalTranslation(0.0f, 0.0f, 0.0f);
        rootNode.attachChild(ninja2);*/
        
        Box box = new Box(0.01f,0.5f,0.0f);
        Spatial wall = new Geometry("Box", box );
        Material mat_brick = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick.setTexture("ColorMap",assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        wall.setMaterial(mat_brick);
        wall.setLocalTranslation(-1.0f,-1.0f,0.0f);
        rootNode.attachChild(wall);
    }
}
