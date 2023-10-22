package model.alien;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.texture.Texture;
import core.camera.Camera;
import model.Model;
import model.SGNode;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 18:34
 */
public class Spotlight extends Models {

    private SGNode spotlightNode;
    private Texture texture;
    private Model body, left_arm, right_arm, head, left_eye, right_eye, left_ear, right_ear, bottom_antenna, top_antenna;
    public Spotlight(GL3 gl, Camera camera, double startTime) {
        this.startTime = startTime;
        this.camera = camera;
        loadTextures(gl);
        spotlightNode = createSpotLight(gl);
    }
    @Override
    public void loadTextures(GL3 gl) {
    }

    private SGNode createSpotLight(GL3 gl) {
//        spotlightNode.update();
        return spotlightNode;
    }

    public void turnOn() {

    }

    public void turnOff() {

    }
}
