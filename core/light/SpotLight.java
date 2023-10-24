package core.light;

import com.jogamp.opengl.GL3;
import gmaths.Mat4;

public class SpotLight extends Light{

    public SpotLight(GL3 gl) {
        super(gl);
    }

    public SpotLight(GL3 gl, Mat4 model) {
        super(gl, model);
    }
}