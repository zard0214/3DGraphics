package shaders.shaders;

import com.jogamp.opengl.GL3;
import core.Shader;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 22/10/2023 01:04
 */
public class SkyboxShader extends Shader {

    public SkyboxShader(GL3 gl) {
        super(gl, "shaders/glsl/vertex/vs_skybox_1.glsl", "shaders/glsl/fragment/fs_skybox_1.glsl");
    }

}
