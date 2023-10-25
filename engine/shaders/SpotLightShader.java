package engine.shaders;

import com.jogamp.opengl.GL3;
import engine.Shader;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 07:30
 */
public class SpotLightShader extends Shader {
    public SpotLightShader(GL3 gl, String vertexPath, String fragmentPath) {
        super(gl, vertexPath, fragmentPath);
    }
}
