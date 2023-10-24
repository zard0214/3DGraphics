package model.alien;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.texture.Texture;
import core.Material;
import core.camera.Camera;
import core.light.Light;
import core.light.SpotLight;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import model.Mesh;
import model.Model;
import model.SGNode;
import model.Sphere;
import shaders.shaders.LightShader;
import shaders.shaders.SpotlightShader;
import utils.Constant;
import utils.TextureLibrary;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 18:17
 */
public class SpotlightModel {

    private SGNode alienNode;
    private Texture texture;

    private Material lightMaterial;
    private float intensity;

    //left: 0, right: 1
    private Model sphere_body, sphere_head, sphere_lamp;
    private SpotLight spotLight;
    private Mat4 modelMatrix;

    public SpotlightModel(GL3 gl, Camera camera, Light light, Light light_2, Mat4 translate) {

        int[] textureId0 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_1);
        int[] textureId1 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_2);

        /***********  sphere_body  ***************/
        Mesh m = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        SpotlightShader shader = new SpotlightShader(gl);
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f), new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.5f,12,0.5f), Mat4Transform.translate(0,0.5f,0));

        modelMatrix = Mat4.multiply(translate, modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0.0f,-8.0f,5.0f), modelMatrix);
        sphere_body = new Model(gl, camera, light, light_2, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_head  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(60), Mat4Transform.scale(1.0f,3,1.0f));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0,0.5f,0), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0f,12f,0), modelMatrix);

        modelMatrix = Mat4.multiply(translate, modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0.0f,-8.0f,5.0f), modelMatrix);
        sphere_head = new Model(gl, camera, light, light_2, shader, material, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_lamp  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(60), Mat4Transform.scale(0.5f,1,0.5f));
        modelMatrix = Mat4.multiply(Mat4Transform.translate(1.7f,11.5f,0), modelMatrix);

        LightShader lightShader2 = new LightShader(gl);
        lightMaterial = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);

        modelMatrix = Mat4.multiply(translate, modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0.0f,-8.0f,5.0f), modelMatrix);
        sphere_lamp = new Model(gl, camera, light, light_2, lightShader2, lightMaterial, modelMatrix, m, textureId0, textureId1);

        /***********  sphere_light  ***************/
        spotLight = new SpotLight(gl, modelMatrix);
//        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(60), Mat4Transform.scale(0.5f,1,0.5f));
//        modelMatrix = Mat4.multiply(Mat4Transform.translate(1.7f,11.5f,0), modelMatrix);
//        spotLight.setModelMatrix(modelMatrix);
        spotLight.setCamera(camera);
        spotLight.setIntensity(0.15f);
    }

    public void setIntensity(float intensity){
        this.intensity = intensity;
        lightMaterial.setAmbient(intensity * 0.5f, intensity * 0.5f, intensity * 0.5f);
        lightMaterial.setDiffuse(intensity, intensity, intensity);
        lightMaterial.setSpecular(intensity, intensity, intensity);
    }

    public float getIntensity() {
        return intensity;
    }


    public void rockBody() {

    }

    public void render(GL3 gl) {
        sphere_body.render(gl);
        sphere_head.render(gl);
        sphere_lamp.render(gl);
//        spotLight.render(gl);

    }

    public void dispose(GL3 gl) {
        sphere_body.dispose(gl);
        sphere_head.dispose(gl);
        sphere_lamp.dispose(gl);
//        spotLight.dispose(gl);

    }

    public SpotLight getSpotLight() {
        return spotLight;
    }

    public void setSpotLight(SpotLight spotLight) {
        this.spotLight = spotLight;
    }
}
