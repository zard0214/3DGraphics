package engine.light;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import engine.Shader;
import engine.camera.Camera;
import engine.camera.Material;
import engine.shaders.SpotLightShader;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 05:45
 */
public class SpotLight {

    private Vec3 direction = new Vec3(1.0f, -1.25f, 0.0f);
    private float cutOff = (float) Math.cos(Math.toRadians(0.953));
    private float outerCutOff = (float) Math.cos(Math.toRadians(17.5));
    private float constant = 1.0f;
    private float linear = 0.09f;
    private float quadratic = 0.0028f;
    private Vec3 ambient = new Vec3(0.1f, 0.1f, 0.1f);
    private Vec3 diffuse = new Vec3(0.2f, 0.2f, 0.2f);
    private Vec3 specular = new Vec3(0.3f, 0.3f, 0.3f);

    private Material material;
    private Vec3 position;
    private Mat4 model;
    private Shader shader;
    private Camera camera;

    private float intensity;

    public SpotLight(GL3 gl) {
        material = new Material();
        material.setAmbient(0.3f, 0.3f, 0.3f);
        material.setDiffuse(0.5f, 0.5f, 0.5f);
        material.setSpecular(0.7f, 0.7f, 0.7f);

        turnOnLight(true, 0.80f);
        position = new Vec3(3f, 2f, 1f);
        model = new Mat4(1);
        shader = new SpotLightShader(gl, "engine/shaders/vertex/vs_texture.glsl", "engine/shaders/fragment/fs_texture.glsl");
        fillBuffers(gl);
    }

    public SpotLight(GL3 gl, Material material) {
        this.material = material;
        position = new Vec3(3f, 2f, 1f);
        model = new Mat4(1);
        shader = new SpotLightShader(gl, "engine/shaders/vertex/vs_texture.glsl", "engine/shaders/fragment/fs_texture.glsl");
        fillBuffers(gl);
    }

    public void setPosition(Vec3 v) {
        position.x = v.x;
        position.y = v.y;
        position.z = v.z;
    }

    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public Vec3 getPosition() {
        return position;
    }

    public void setMaterial(Material m) {
        material = m;
    }

    public Material getMaterial() {
        return material;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void render(GL3 gl) {
        Mat4 model = new Mat4(1);
        model = Mat4.multiply(Mat4Transform.scale(0.3f, 0.3f, 0.3f), model);
        model = Mat4.multiply(Mat4Transform.translate(position), model);

        Mat4 mvpMatrix = Mat4.multiply(camera.getPerspectiveMatrix(), Mat4.multiply(camera.getViewMatrix(), model));

        shader.use(gl);
        shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

        gl.glBindVertexArray(vertexArrayId[0]);
        gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);
        gl.glBindVertexArray(0);
    }

    public void dispose(GL3 gl) {
        gl.glDeleteBuffers(1, vertexBufferId, 0);
        gl.glDeleteVertexArrays(1, vertexArrayId, 0);
        gl.glDeleteBuffers(1, elementBufferId, 0);
    }

    // ***************************************************
    /* THE DATA
     */
    // anticlockwise/counterclockwise ordering

    private float[] vertices = new float[]{  // x,y,z
            -0.5f, -0.5f, -0.5f,  // 0
            -0.5f, -0.5f, 0.5f,  // 1
            -0.5f, 0.5f, -0.5f,  // 2
            -0.5f, 0.5f, 0.5f,  // 3
            0.5f, -0.5f, -0.5f,  // 4
            0.5f, -0.5f, 0.5f,  // 5
            0.5f, 0.5f, -0.5f,  // 6
            0.5f, 0.5f, 0.5f   // 7
    };

    private int[] indices = new int[]{
            0, 1, 3, // x -ve
            3, 2, 0, // x -ve
            4, 6, 7, // x +ve
            7, 5, 4, // x +ve
            1, 5, 7, // z +ve
            7, 3, 1, // z +ve
            6, 4, 0, // z -ve
            0, 2, 6, // z -ve
            0, 4, 5, // y -ve
            5, 1, 0, // y -ve
            2, 3, 7, // y +ve
            7, 6, 2  // y +ve
    };

    private int vertexStride = 3;
    private int vertexXYZFloats = 3;

    // ***************************************************
    /* THE LIGHT BUFFERS
     */

    private int[] vertexBufferId = new int[1];
    private int[] vertexArrayId = new int[1];
    private int[] elementBufferId = new int[1];

    private void fillBuffers(GL3 gl) {
        gl.glGenVertexArrays(1, vertexArrayId, 0);
        gl.glBindVertexArray(vertexArrayId[0]);
        gl.glGenBuffers(1, vertexBufferId, 0);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferId[0]);
        FloatBuffer fb = Buffers.newDirectFloatBuffer(vertices);

        gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * vertices.length, fb, GL.GL_STATIC_DRAW);

        int stride = vertexStride;
        int numXYZFloats = vertexXYZFloats;
        int offset = 0;
        gl.glVertexAttribPointer(0, numXYZFloats, GL.GL_FLOAT, false, stride * Float.BYTES, offset);
        gl.glEnableVertexAttribArray(0);

        gl.glGenBuffers(1, elementBufferId, 0);
        IntBuffer ib = Buffers.newDirectIntBuffer(indices);
        gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, elementBufferId[0]);
        gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, Integer.BYTES * indices.length, ib, GL.GL_STATIC_DRAW);
        gl.glBindVertexArray(0);
    }

    public Vec3 getDirection() {
        return direction;
    }

    public float getCutOff() {
        return cutOff;
    }

    public float getOuterCutOff() {
        return outerCutOff;
    }

    public float getConstant() {
        return constant;
    }

    public void setConstant(float constant) {
        this.constant = constant;
    }

    public float getLinear() {
        return linear;
    }

    public float getQuadratic() {
        return quadratic;
    }

    public Vec3 getAmbient() {
        return ambient;
    }

    public Vec3 getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Vec3 diffuse) {
        this.diffuse = diffuse;
    }

    public Vec3 getSpecular() {
        return specular;
    }

    public void setSpecular(Vec3 specular) {
        this.specular = specular;
    }

    public Mat4 getModel() {
        return model;
    }

    public void setModel(Mat4 model) {
        this.model = model;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
        material.setAmbient(intensity * 0.5f, intensity * 0.5f, intensity * 0.5f);
        material.setDiffuse(intensity, intensity, intensity);
        material.setSpecular(intensity, intensity, intensity);
    }

    public float getIntensity() {
        return intensity;
    }

    public void turnOnLight(boolean turnon, float intensity) {
        if (turnon) {
            this.intensity = intensity;

            this.ambient = new Vec3(intensity * 0.5f, intensity * 0.5f, intensity * 0.5f);
            this.diffuse = new Vec3(intensity, intensity, intensity);
            this.specular = new Vec3(intensity, intensity, intensity);
        } else {
            this.intensity = 0;

            this.ambient = new Vec3(0f, 0f, 0f);
            this.diffuse = new Vec3(0f, 0f, 0f);
            this.specular = new Vec3(0f, 0f, 0f);
        }
    }

}
