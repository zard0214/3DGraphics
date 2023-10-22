package model.alien;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.texture.Texture;
import core.Material;
import core.Shader;
import core.camera.Camera;
import core.light.Light;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import model.Mesh;
import model.Model;
import model.SGNode;
import model.Sphere;
import utils.TextureLibrary;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 18:17
 */
public class AlienModel {

    private SGNode alienNode;
    private Texture texture;
    //left: 0, right: 1
    private Model sphere_body, sphere_head, sphere_lefteye, sphere_righteye, sphere_leftarm, sphere_rightarm;
    private Model sphere_antenna_bottom, sphere_antenna_top;
    private Model sphere_leftear, sphere_rightear;

    private Mat4 modelMatrix;

    public AlienModel(GL3 gl, Camera camera, Light light, Mat4 translate) {

        int[] textureId0 = TextureLibrary.loadTexture(gl, "textures/snow.jpg");
        int[] textureId1 = TextureLibrary.loadTexture(gl, "textures/snow_specular.jpg");

        /***********  sphere_body  ***************/
        Mesh m = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        Shader shader = new Shader(gl, "vs_sphere_04.txt", "fs_sphere_04.txt");
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f), new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(4,4,4), Mat4Transform.translate(0,0.5f,0));

        sphere_body = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_head  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(3,3,3), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0f,4f,0), modelMatrix);

        sphere_head = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_antenna_bottom  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.125f,1f,0.125f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0f,7f,0), modelMatrix);

        sphere_antenna_bottom = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_antenna_top  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.25f,0.25f,0.25f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0f,8f,0), modelMatrix);

        sphere_antenna_top = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_lefteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,0.5f,0.5f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-0.75f,5.2f,1.5f), modelMatrix);

        sphere_lefteye = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_righteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,0.5f,0.5f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0.75f,5.2f,1.5f), modelMatrix);
        sphere_righteye = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_leftarm  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,1.5f,0.5f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-2f,2.2f,0.5f), modelMatrix);

        sphere_leftarm = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);


        /***********  sphere_righteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,1.5f,0.5f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(2f,2.2f,0.5f), modelMatrix);
        sphere_rightarm = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_leftear  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,1.5f,0.5f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-1.5f,5.5f,0.5f), modelMatrix);

        sphere_leftear = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);


        /***********  sphere_righteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,1.5f,0.5f), Mat4Transform.translate(0,0.5f,0));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(1.5f,5.5f,0.5f), modelMatrix);
        sphere_rightear = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);


        //左右 上下 前后
    }


    public void rockBody() {

    }

    public void render(GL3 gl) {
        sphere_body.render(gl);
        sphere_head.render(gl);

        sphere_lefteye.render(gl);
        sphere_righteye.render(gl);

        sphere_leftarm.render(gl);
        sphere_rightarm.render(gl);

        sphere_leftear.render(gl);
        sphere_rightear.render(gl);

        sphere_antenna_bottom.render(gl);
        sphere_antenna_top.render(gl);
    }
}
