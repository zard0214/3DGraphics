package shaders.shaders;

import com.jogamp.opengl.GL3;
import core.Shader;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 22/10/2023 01:04
 */
public class FloorShader extends Shader {

    public FloorShader(GL3 gl) {
        super(gl, "shaders/glsl/vertex/vs_tt_05.glsl", "shaders/glsl/fragment/fs_tt_05.glsl");
    }

}
