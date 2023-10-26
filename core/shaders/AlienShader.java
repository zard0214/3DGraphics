package core.shaders;

import com.jogamp.opengl.GL3;
import core.Shader;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 07:30
 */
public class AlienShader extends Shader {
    public AlienShader(GL3 gl, String vertexPath, String fragmentPath) {
        super(gl, vertexPath, fragmentPath);
    }
}
