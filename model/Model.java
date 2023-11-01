package model;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import core.Shader;
import core.camera.Camera;
import core.Material;
import core.light.Light;
import core.light.SpotLight;
import gmaths.Mat4;

public class Model {

    private Mesh mesh;
    private Material material;
    private Shader shader;
    private Mat4 modelMatrix;
    private Camera camera;
    private Light light, light2;
    private SpotLight spotLight;
    private int[] textureId1;
    private int[] textureId2;
    private float offset;
    private double elapsedTime;

    public Model(GL3 gl) {
    }

    public Model(GL3 gl, Mat4 modelMatrix) {
        this.modelMatrix = modelMatrix;
    }

    public Model(GL3 gl, Material material) {
        this.material = material;
    }
    public Model(GL3 gl, Camera camera, Light light, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh) {
        this.mesh = mesh;
        this.material = material;
        this.modelMatrix = modelMatrix;
        this.shader = shader;
        this.camera = camera;
        this.light = light;
    }

    public Model(GL3 gl, Camera camera, Light light, Light light2, SpotLight spotLight, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh) {
        this.mesh = mesh;
        this.material = material;
        this.modelMatrix = modelMatrix;
        this.shader = shader;
        this.camera = camera;
        this.light = light;
        this.light2 = light2;
        this.spotLight = spotLight;
    }

    public Model(GL3 gl, Camera camera, Light light, Light light2, SpotLight spotLight, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh, int[] textureId1) {
        this.mesh = mesh;
        this.material = material;
        this.modelMatrix = modelMatrix;
        this.shader = shader;
        this.camera = camera;
        this.light = light;
        this.light2 = light2;
        this.spotLight = spotLight;
        this.textureId1 = textureId1;
    }

    public Model(GL3 gl, Camera camera, Light light, Light light2, SpotLight spotLight, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh, int[] textureId1, int[] textureId2) {
        this.mesh = mesh;
        this.material = material;
        this.modelMatrix = modelMatrix;
        this.shader = shader;
        this.camera = camera;
        this.light = light;
        this.light2 = light2;
        this.spotLight = spotLight;
        this.textureId1 = textureId1;
        this.textureId2 = textureId2;
    }

    public Model(GL3 gl, Camera camera, Light light, Light light2, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh, int[] textureId1, int[] textureId2) {
        this.mesh = mesh;
        this.material = material;
        this.modelMatrix = modelMatrix;
        this.shader = shader;
        this.camera = camera;
        this.light = light;
        this.light2 = light2;
        this.textureId1 = textureId1;
        this.textureId2 = textureId2;
    }

    public Model(GL3 gl, Camera camera, Light light, Light light2, SpotLight spotLight, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh, int[] textureId1, int[] textureId2, double elapsedTime) {
        this.mesh = mesh;
        this.material = material;
        this.modelMatrix = modelMatrix;
        this.shader = shader;
        this.camera = camera;
        this.light = light;
        this.light2 = light2;
        this.spotLight = spotLight;
        this.textureId1 = textureId1;
        this.textureId2 = textureId2;
        this.elapsedTime = elapsedTime;
    }

    // To consider: add constructors without modelMatrix? and then set to identity as the default?

    public void setModelMatrix(Mat4 m) {
        modelMatrix = m;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public void render(GL3 gl, Mat4 modelMatrix) {
        Mat4 mvpMatrix = Mat4.multiply(camera.getPerspectiveMatrix(), Mat4.multiply(camera.getViewMatrix(), modelMatrix));
        shader.use(gl);
        shader.setFloatArray(gl, "model", modelMatrix.toFloatArrayForGLSL());
        shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

        shader.setVec3(gl, "viewPos", camera.getPosition());

        if (light != null) {
            shader.setVec3(gl, "light.position", light.getPosition());
            shader.setVec3(gl, "light.ambient", light.getMaterial().getAmbient());
            shader.setVec3(gl, "light.diffuse", light.getMaterial().getDiffuse());
            shader.setVec3(gl, "light.specular", light.getMaterial().getSpecular());
        }
        if (light2 != null) {
            shader.setVec3(gl, "light2.position", light2.getPosition());
            shader.setVec3(gl, "light2.ambient", light2.getMaterial().getAmbient());
            shader.setVec3(gl, "light2.diffuse", light2.getMaterial().getDiffuse());
            shader.setVec3(gl, "light2.specular", light2.getMaterial().getSpecular());
        }
        if (spotLight != null) {
            shader.setVec3(gl, "spotLight.position", spotLight.getPosition());
            shader.setVec3(gl, "spotLight.direction", spotLight.getDirection());
            shader.setFloat(gl, "spotLight.cutOff", spotLight.getCutOff());
            shader.setFloat(gl, "spotLight.outerCutOff", spotLight.getOuterCutOff());
            shader.setVec3(gl, "spotLight.ambient", spotLight.getAmbient());
            shader.setVec3(gl, "spotLight.diffuse", spotLight.getDiffuse());
            shader.setVec3(gl, "spotLight.specular", spotLight.getSpecular());
            shader.setFloat(gl, "spotLight.constant", spotLight.getConstant());
            shader.setFloat(gl, "spotLight.linear", spotLight.getLinear());
            shader.setFloat(gl, "spotLight.quadratic", spotLight.getQuadratic());
        }

        shader.setVec3(gl, "material.ambient", material.getAmbient());
        shader.setVec3(gl, "material.diffuse", material.getDiffuse());
        shader.setVec3(gl, "material.specular", material.getSpecular());
        shader.setFloat(gl, "material.shininess", material.getShininess());
        shader.setFloat(gl, "material.brightness", material.getBrightness());

        if (textureId1 != null) {
            shader.setInt(gl, "first_texture", 0);  // be careful to match these with GL_TEXTURE0 and GL_TEXTURE1
            gl.glActiveTexture(GL.GL_TEXTURE0);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textureId1[0]);
        }
        if (textureId2 != null) {
            shader.setInt(gl, "second_texture", 1);
            gl.glActiveTexture(GL.GL_TEXTURE1);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textureId2[0]);
        }
        if (offset != 0) {
            shader.setFloat(gl, "offset", 0, offset);
        }
        mesh.render(gl);
    }

    public void render(GL3 gl) {
        render(gl, modelMatrix);
    }

    public void setOffSet(float offset) {
        this.offset = offset;
    }

    public void dispose(GL3 gl) {
        mesh.dispose(gl);
        if (textureId1 != null) gl.glDeleteBuffers(1, textureId1, 0);
        if (textureId2 != null) gl.glDeleteBuffers(1, textureId2, 0);
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
        final double SPEED = 0.05;
        double increment = elapsedTime * SPEED;
        this.setOffSet((float) (increment - Math.floor(increment)));
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}