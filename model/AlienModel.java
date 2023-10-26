package model;

import com.jogamp.opengl.GL3;
import core.camera.Camera;
import core.camera.Material;
import core.light.Light;
import core.light.SpotLight;
import core.shaders.AlienShader;
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
public class AlienModel {

    private Model sphere_body, sphere_head, sphere_lefteye, sphere_righteye, sphere_leftarm, sphere_rightarm;
    private Model sphere_antenna_bottom, sphere_antenna_top;
    private Model sphere_leftear, sphere_rightear;
    private Mat4 modelMatrix;

    public AlienModel(GL3 gl, Camera camera, Light light_1, Light light_2, SpotLight spotLight, AlienShader alienShader, Material alienMaterial, Mat4 mat4, Mesh m, int[] alienTexture, Mat4 transition) {

        int[] alientextureId0 = TextureLibrary.loadTexture(gl, Constant.ALIEN_TEXTURE_GRAY);
        /***********  sphere_body  ***************/
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f), new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(1.7f, 1.7f, 1.7f), Mat4Transform.translate(0f, 0.5f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_body = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_head  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(1.3f, 1.3f, 1.3f), Mat4Transform.translate(0f, 1.80f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_head = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_antenna_bottom  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.125f, 0.5f, 0.125f), Mat4Transform.translate(0f, 6.5f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_antenna_bottom = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_antenna_top  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.18f, 0.18f, 0.18f), Mat4Transform.translate(0f, 19.9f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_antenna_top = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_lefteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.18f, 0.18f, 0.18f), Mat4Transform.translate(-1.5f, 14.5f, 3.4f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_lefteye = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_righteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.18f, 0.18f, 0.18f), Mat4Transform.translate(1.5f, 14.5f, 3.4f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_righteye = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_leftarm  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(45), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-1.15f, 1.2f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_leftarm = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_rightarm  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(-45), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(1.15f, 1.2f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_rightarm = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_leftear  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(0), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-0.65f, 3.0f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_leftear = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
//        /***********  sphere_rightear  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(0), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0.65f, 3.0f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_rightear = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
    }


    public void render(GL3 gl) {
        sphere_body.render(gl);
        sphere_head.render(gl);
        sphere_antenna_bottom.render(gl);
        sphere_antenna_top.render(gl);
        sphere_lefteye.render(gl);
        sphere_righteye.render(gl);
        sphere_leftarm.render(gl);
        sphere_rightarm.render(gl);
        sphere_leftear.render(gl);
        sphere_rightear.render(gl);
    }


    public void dispose(GL3 gl) {
        sphere_body.dispose(gl);
        sphere_head.dispose(gl);
        sphere_antenna_bottom.dispose(gl);
        sphere_antenna_top.dispose(gl);
        sphere_lefteye.dispose(gl);
        sphere_righteye.dispose(gl);
        sphere_leftarm.dispose(gl);
        sphere_rightarm.dispose(gl);
        sphere_leftear.dispose(gl);
        sphere_rightear.dispose(gl);
    }
}
