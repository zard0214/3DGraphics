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
import shaders.shaders.SpotlightShader;
import utils.Constant;
import utils.TextureLibrary;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 18:17
 */
public class Spotlight {

    private SGNode alienNode;
    private Texture texture;

    //left: 0, right: 1
    private Model sphere_body, sphere_head, sphere_lamp;
    private Light sphere_light;
    private Mat4 modelMatrix;

    public Spotlight(GL3 gl, Camera camera, Light light, Mat4 translate) {

        int[] textureId0 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_1);
        int[] textureId1 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_2);

        /***********  sphere_body  ***************/
        Mesh m = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        SpotlightShader shader = new SpotlightShader(gl);
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f), new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,12,0.5f), Mat4Transform.translate(0,0.5f,0));

        modelMatrix = Mat4.multiply(translate, modelMatrix);
        sphere_body = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_head  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(60), Mat4Transform.scale(1.0f,3,1.0f));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0,0.5f,0), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0f,12f,0), modelMatrix);

        modelMatrix = Mat4.multiply(translate, modelMatrix);
        sphere_head = new Model(gl, camera, light, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_lamp  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(60), Mat4Transform.scale(0.5f,1,0.5f));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(1.7f,11.5f,0), modelMatrix);

        material = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);
        modelMatrix = Mat4.multiply(translate, modelMatrix);
        Shader shader1 = new Shader(gl, Constant.LIGHT_GLSL_VS, Constant.LIGHT_GLSL_FS);
        sphere_lamp = new Model(gl, camera, light, shader1, material, modelMatrix, m, textureId0, textureId1);

//        sphere_light = new Light(gl, modelMatrix);
//        sphere_light.setModelMatrix(modelMatrix);
//        sphere_light.setCamera(camera);
    }


    public void rockBody() {

    }

    public void render(GL3 gl) {

//        sphere_light.setPosition(getLightPosition());  // changing light position each frame
//        sphere_light.render(gl);

        sphere_body.render(gl);
        sphere_head.render(gl);
        sphere_lamp.render(gl);

    }

    public void dispose(GL3 gl) {
        sphere_body.dispose(gl);
        sphere_head.dispose(gl);
        sphere_lamp.dispose(gl);

    }

//
//    // The light's postion is continually being changed, so needs to be calculated for each frame.
//    private Vec3 getLightPosition() {
//        double elapsedTime = getSeconds()-startTime;
//        float x = 3.0f*(float)(Math.sin(Math.toRadians(elapsedTime*50)));
//        float y = 2.4f;
//        float z = 3.0f*(float)(Math.cos(Math.toRadians(elapsedTime*50)));
//        return new Vec3(x,y,z);
//    }
//
//
//    private double startTime;
//
//    private double getSeconds() {
//        return System.currentTimeMillis()/1000.0;
//    }

}
