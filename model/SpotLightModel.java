package model;

import com.jogamp.opengl.GL3;
import engine.camera.Camera;
import engine.camera.Material;
import engine.light.Light;
import engine.light.SpotLight;
import engine.shaders.AlienShader;
import engine.shaders.LampShader;
import engine.shaders.SpotLightShader;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import util.Constant;
import util.TextureLibrary;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 08:20
 */
public class SpotLightModel{

    private Model sphere_body, sphere_head, sphere_lamp;
    private SpotLight spotLight;

    private Mat4 modelMatrix;
    public SpotLightModel(GL3 gl, Camera camera, Light light_1, Light light_2, SpotLight spotLight, SpotLightShader spotLightShader, Mat4 mat4, Mesh m) {

        this.spotLight = spotLight;

        int[] textureId0 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_1);
        int[] textureId1 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_3);

        /***********  sphere_lamp  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(60), Mat4Transform.scale(0.3f,0.70f,0.3f));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-6.7f, 7.5f, 0f), modelMatrix);

        Material lampMaterial = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);
        LampShader lampShader = new LampShader(gl, Constant.LIGHT_GLSL_VS, Constant.LIGHT_GLSL_FS);
        sphere_lamp = new Model(gl, camera, light_1, light_2, spotLight, lampShader, lampMaterial, modelMatrix, m, textureId1);

        /***********  sphere_head  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(60), Mat4Transform.scale(0.5f,1.2f,0.5f));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-7.5f, 8.0f, 0f), modelMatrix);

        Material headMaterial = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);
        sphere_head = new Model(gl, camera, light_1, light_2, spotLight, spotLightShader, headMaterial, modelMatrix, m, textureId0);

        /***********  sphere_body  ***************/
        modelMatrix = Mat4Transform.scale(0.30f,8,0.30f);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-7.5f, 4.0f, 0f), modelMatrix);

        Material bodyMaterial = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);
        sphere_body = new Model(gl, camera, light_1, light_2, spotLight, spotLightShader, bodyMaterial, modelMatrix, m, textureId0);
        //左右 上下 前后

    }

    public void render(GL3 gl) {
        sphere_body.render(gl);
        sphere_head.render(gl);
        sphere_lamp.render(gl);

    }

    public void dispose(GL3 gl) {
        sphere_body.dispose(gl);
        sphere_head.dispose(gl);
        sphere_lamp.dispose(gl);

    }
}