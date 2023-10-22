package model.alien;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.texture.Texture;
import core.camera.Camera;
import model.Model;
import model.SGNode;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 18:17
 */
public class AlienModel extends Models {

    private SGNode alienNode;
    private Texture texture;
    //left: 0, right: 1
    private int location;
    private Model pole, sphere_1, sphere_2;

    public AlienModel(GL3 gl, Camera camera, double startTime, int location) {
        this.startTime = startTime;
        this.camera = camera;
        this.location = location;
        loadTextures(gl);
        alienNode = createAlien(gl);
    }
    @Override
    public void loadTextures(GL3 gl) {
//        texture = TextureLibrary.loadTextures(gl,"src/resource/texture.jpg");
    }

    private SGNode createAlien(GL3 gl) {
        if(location == 0){
            //create left alien
        }else if(location == 1){
            //create right alien
        }
//        alienNode.update();
        return alienNode;
    }

    public void rockBody() {

    }
}
