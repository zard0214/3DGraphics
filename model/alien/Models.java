package model.alien;

import com.jogamp.opengl.GL3;
import core.camera.Camera;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 19:09
 */
public abstract class Models {

    public double startTime;
    public Camera camera;

    abstract void loadTextures(GL3 gl);
}
