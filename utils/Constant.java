package utils;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 22/10/2023 01:09
 */
public class Constant {

    public static final String TEXTURE_SNOWING = "textures/snowing.jpg";
    public static final String TEXTURE_SNOW = "textures/snow.jpg";
    public static final String TEXTURE_SNOW2 = "textures/snow2.jpg";
    public static final String SKYBOX_TEXTURE_PX = "textures/posx.jpg";
    public static final String SKYBOX_TEXTURE_PY = "textures/posy.jpg";
    public static final String SKYBOX_TEXTURE_PZ = "textures/posz.jpg";
    public static final String SKYBOX_TEXTURE_NX = "textures/negx.jpg";
    public static final String SKYBOX_TEXTURE_NY = "textures/negy.jpg";
    public static final String SKYBOX_TEXTURE_NZ = "textures/negz.jpg";

    public static final String ALIEN_TEXTURE_1 = "textures/snow.jpg";
    public static final String ALIEN_TEXTURE_2 = "textures/snow_specular.jpg";
    public static final String ALIEN_GLSL_VS = "shaders/glsl/vertex/vs_sphere_04.glsl";
    public static final String ALIEN_GLSL_FS = "shaders/glsl/fragment/fs_sphere_04.glsl";
    public static final String BG_GLSL_VS = "shaders/glsl/vertex/vs_background_1.glsl";
    public static final String BG_GLSL_FS = "shaders/glsl/fragment/fs_background_1.glsl";
    public static final String FLOOR_TEXTURE_1 = "textures/snow.jpg";
    public static final String FLOOR_TEXTURE_2 = "textures/snow_specular.jpg";
    public static final String SPOTLIGHT_TEXTURE_1 = "textures/snow.jpg";
    public static final String SPOTLIGHT_TEXTURE_2 = "textures/snow_specular.jpg";
    public static final String FLOOR_GLSL_VS = "shaders/glsl/vertex/vs_tt_06.glsl";
    public static final String FLOOR_GLSL_FS = "shaders/glsl/fragment/fs_tt_06.glsl";
    public static final String SKYBOX_GLSL_VS = "shaders/glsl/vertex/vs_skybox_1.glsl";
    public static final String SKYBOX_GLSL_FS = "shaders/glsl/fragment/fs_skybox_1.glsl";
    public static final String SPOTLIGHT_GLSL_VS = "shaders/glsl/vertex/vs_sphere_04.glsl";
    public static final String SPOTLIGHT_GLSL_FS = "shaders/glsl/fragment/fs_sphere_04.glsl";



    public static final float[] SKYBOX_VS = new float[]{
            // positions
            -1,  1, -1,
            -1, -1, -1,
            1, -1, -1,
            1, -1, -1,
            1,  1, -1,
            -1,  1, -1,

            -1, -1,  1,
            -1, -1, -1,
            -1,  1, -1,
            -1,  1, -1,
            -1,  1,  1,
            -1, -1,  1,

            1, -1, -1,
            1, -1,  1,
            1,  1,  1,
            1,  1,  1,
            1,  1, -1,
            1, -1, -1,

            -1, -1,  1,
            -1,  1,  1,
            1,  1,  1,
            1,  1,  1,
            1, -1,  1,
            -1, -1,  1,

            -1,  1, -1,
            1,  1, -1,
            1,  1,  1,
            1,  1,  1,
            -1,  1,  1,
            -1,  1, -1,

            -1, -1, -1,
            -1, -1,  1,
            1, -1, -1,
            1, -1, -1,
            -1, -1,  1,
            1, -1,  1
    };
}
