package util;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 22/10/2023 01:09
 */
public class Constant {

    public static final String TEXTURE_FLOOR = "textures/chequerboard.jpg";
    public static final String TEXTURE_SNOW_FLOOR = "textures/snow_floor.jpg";
    public static final String TEXTURE_SNOWING = "textures/snowing.jpg";
    public static final String TEXTURE_SNOW = "textures/snow3.jpg";
    public static final String TEXTURE_SNOW2 = "textures/snow2.jpg";
    public static final String SKYBOX_TEXTURE_PX = "textures/posx.jpg";
    public static final String SKYBOX_TEXTURE_PY = "textures/posy.jpg";
    public static final String SKYBOX_TEXTURE_PZ = "textures/posz.jpg";
    public static final String SKYBOX_TEXTURE_NX = "textures/negx.jpg";
    public static final String SKYBOX_TEXTURE_NY = "textures/negy.jpg";
    public static final String SKYBOX_TEXTURE_NZ = "textures/negz.jpg";
    public static final String ALIEN_TEXTURE_GRAY = "textures/gray.jpg";
    public static final String ALIEN_BODY_TEXTURE = "textures/alien_body_texture.jpg";
    public static final String ALIEN_TEXTURE_GRAY_2 = "textures/gray_2.jpg";
    public static final String ALIEN_TEXTURE_1 = "textures/snow.jpg";
    public static final String ALIEN_TEXTURE_2 = "textures/snow_specular.jpg";
    public static final String FLOOR_TEXTURE_1 = "textures/snow.jpg";
    public static final String WATER_TEXTURE = "textures/water_texture.jpeg";
    public static final String FLOOR_TEXTURE_2 = "textures/snow_specular.jpg";
    public static final String FLOOR_TEXTURE_3 = "textures/snow_floor2.jpg";
    public static final String SPOTLIGHT_TEXTURE_1 = "textures/snow.jpg";
    public static final String SPOTLIGHT_TEXTURE_2 = "textures/snow_specular.jpg";
    public static final String TEXTURE_YELLOW = "textures/yellow.jpg";

    public static final String ALIEN_GLSL_VS = "core/shaders/glsl/vertex/vs_sphere_04.glsl";
    public static final String ALIEN_GLSL_FS = "core/shaders/glsl/fragment/fs_sphere_04.glsl";
    public static final String BG_GLSL_VS = "core/shaders/glsl/vertex/vs_background_1.glsl";
    public static final String BG_GLSL_FS = "core/shaders/glsl/fragment/fs_background_1.glsl";
    public static final String LIGHT_GLSL_VS = "core/shaders/vertex/vs_light_01.glsl";
    public static final String LIGHT_GLSL_FS = "core/shaders/fragment/fs_light_01.glsl";
    public static final String SPOTLIGHT_GLSL_VS = "core/shaders/vertex/vs_texture.glsl";
    public static final String SPOTLIGHT_GLSL_FS = "core/shaders/fragment/fs_texture.glsl";
    public static final String DEFAULT_GLSL_VS = "core/shaders/vertex/vs_default.glsl";
    public static final String DEFAULT_GLSL_FS = "core/shaders/fragment/fs_texture.glsl";
    public static final String LIGHT_GLSL_FS_2 = "core/shaders/fragment/fs_light_02.glsl";

    public static final String ALIEN_BODY_VS = "core/shaders/vertex/vs_cube_03.glsl";
    public static final String ALIEN_BODY_FS = "core/shaders/fragment/fs_cube_033.glsl";
    public static final String TEXTURE_FLOOR_1 = "core/shaders/vertex/vs_background_1.glsl";
    public static final String TEXTURE_FLOOR_2 = "core/shaders/fragment/fs_background_1.glsl";
    public static final String FLOOR_GLSL_VS = "core/shaders/vertex/vs_background_1.glsl";
    public static final String FLOOR_GLSL_FS = "core/shaders/fragment/fs_background_1.glsl";
    public static final String SKYBOX_GLSL_VS = "core/shaders/vertex/vs_skybox_1.glsl";
    public static final String SKYBOX_GLSL_FS = "core/shaders/fragment/fs_skybox_1.glsl";


    public static final float[] SKYBOX_VS = new float[]{
            // positions
            -1, 1, -1,
            -1, -1, -1,
            1, -1, -1,
            1, -1, -1,
            1, 1, -1,
            -1, 1, -1,

            -1, -1, 1,
            -1, -1, -1,
            -1, 1, -1,
            -1, 1, -1,
            -1, 1, 1,
            -1, -1, 1,

            1, -1, -1,
            1, -1, 1,
            1, 1, 1,
            1, 1, 1,
            1, 1, -1,
            1, -1, -1,

            -1, -1, 1,
            -1, 1, 1,
            1, 1, 1,
            1, 1, 1,
            1, -1, 1,
            -1, -1, 1,

            -1, 1, -1,
            1, 1, -1,
            1, 1, 1,
            1, 1, 1,
            -1, 1, 1,
            -1, 1, -1,

            -1, -1, -1,
            -1, -1, 1,
            1, -1, -1,
            1, -1, -1,
            -1, -1, 1,
            1, -1, 1
    };
}
