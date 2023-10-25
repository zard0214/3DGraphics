package engine.shaders;

import com.jogamp.opengl.GL3;
import engine.Shader;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 07:30
 */
public class LampShader extends Shader {
    public LampShader(GL3 gl, String vertexPath, String fragmentPath) {
        super(gl, vertexPath, fragmentPath);
    }
}
