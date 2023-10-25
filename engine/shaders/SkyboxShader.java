package engine.shaders;

import com.jogamp.opengl.GL3;
import engine.Shader;
import util.Constant;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 22/10/2023 01:04
 */
public class SkyboxShader extends Shader {

    public SkyboxShader(GL3 gl) {
        super(gl, Constant.SKYBOX_GLSL_VS, Constant.SKYBOX_GLSL_FS);
    }

}
