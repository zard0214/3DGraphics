package shaders.shaders;

import com.jogamp.opengl.GL3;
import core.Shader;
import utils.Constant;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 22/10/2023 01:04
 */
public class SpotlightShader extends Shader {

    public SpotlightShader(GL3 gl) {
        super(gl, Constant.SPOTLIGHT_GLSL_VS, Constant.SPOTLIGHT_GLSL_FS);
    }

}
