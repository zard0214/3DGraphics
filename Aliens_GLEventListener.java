import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import core.camera.Camera;
import core.camera.Material;
import core.light.Light;
import core.light.SpotLight;
import core.shaders.AlienShader;
import core.shaders.PlaneShader;
import core.shaders.SpotLightShader;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import model.*;
import util.Constant;
import util.TextureLibrary;
import util.TimeUtils;
import util.TwoTriangles;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 05:34
 */
public class Aliens_GLEventListener implements GLEventListener {

    private BackdropSkybox skybox;
    private int cubemap_id;
    private Camera camera;
    private Light light_1, light_2, light_3;
    private SpotLight spotLight;
    private double startTime;
    private Model plane_1, plane_2;
    private AlienModel3 alien3_1, alien3_2;
    private SpotLightModel2 spotLightModel2;

    public Aliens_GLEventListener(Camera camera) {
        this.camera = camera;
        this.camera.setPosition(new Vec3(4f, 6f, 15f));
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LESS);
        gl.glFrontFace(GL.GL_CCW);    // default is 'CCW'
        gl.glEnable(GL.GL_CULL_FACE); // default is 'not enabled'
        gl.glCullFace(GL.GL_BACK);   // default is 'back', assuming CCW
        this.startTime = TimeUtils.getCurrentTime();
        initialise(gl);
    }

    private void initialise(GL3 gl) {

        light_1 = new Light(gl);
        light_1.setCamera(camera);
        light_1.setIntensity(0.15f);
        light_1.turnOnLight(true, 0.15f);

        light_2 = new Light(gl);
        light_2.setCamera(camera);
        light_2.setIntensity(0.3f);
        light_2.turnOnLight(true, 0.3f);

        light_3 = new Light(gl);
        light_3.setCamera(camera);
        light_3.setIntensity(0.3f);
        light_3.turnOnLight(false, 0.3f);

        spotLight = new SpotLight(gl);
        spotLight.setCamera(camera);

        skybox = new BackdropSkybox(gl);
        cubemap_id = skybox.loadCubemap(Constant.SKYBOX_TEXTURE_PX, Constant.SKYBOX_TEXTURE_NY,
                Constant.SKYBOX_TEXTURE_PZ, Constant.SKYBOX_TEXTURE_NX,
                Constant.SKYBOX_TEXTURE_PY, Constant.SKYBOX_TEXTURE_NZ);

        Mesh m = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        PlaneShader planeShader = new PlaneShader(gl, "core/shaders/vertex/vs_texture.glsl", "core/shaders/fragment/fs_texture.glsl");
        int[] textureId1 = TextureLibrary.loadTexture(gl, "textures/snow_floor2.jpg");
        Material planeMaterial = new Material(new Vec3(0.1f, 0.5f, 0.91f), new Vec3(0.1f, 0.5f, 0.91f), new Vec3(0.3f, 0.3f, 0.3f), 4.0f);
        plane_1 = new Model(gl, camera, light_1, light_2, spotLight, planeShader, planeMaterial, new Mat4(1), m, textureId1);

        planeShader = new PlaneShader(gl, "core/shaders/vertex/vs_background_1.glsl", "core/shaders/fragment/fs_background_1.glsl");
        int[] textureId0 = TextureLibrary.loadTexture(gl, "textures/snow3.jpg");
        int[] textureId2 = TextureLibrary.loadTexture(gl, "textures/snowing.jpg");
        plane_2 = new Model(gl, camera, light_1, light_2, spotLight, planeShader, planeMaterial, new Mat4(1), m, textureId0, textureId2);

        m = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        AlienShader alienShader = new AlienShader(gl, "core/shaders/vertex/vs_default.glsl", "core/shaders/fragment/fs_texture.glsl");
        int[] alienTexture = TextureLibrary.loadTexture(gl, Constant.ALIEN_TEXTURE_GRAY);
        Material alienMaterial = new Material(new Vec3(0.1f, 0.5f, 0.91f), new Vec3(0.1f, 0.5f, 0.91f), new Vec3(0.3f, 0.3f, 0.3f), 4.0f);

        Mat4 transition = Mat4Transform.translate(-2.5f, 0.0f, 0.0f);
        alien3_1 = new AlienModel3(gl, camera, light_1, light_2, spotLight, alienShader, alienMaterial, new Mat4(1), m, alienTexture, transition);
//        alien3_1.transition(-5.5f, 0, 1);

        transition = Mat4Transform.translate(2.5f, 0.0f, 0.0f);
        alien3_2 = new AlienModel3(gl, camera, light_1, light_2, spotLight, alienShader, alienMaterial, new Mat4(1), m, alienTexture, transition);
//        alien3_2.transition(5.5f, 0, 1.0f);

        SpotLightShader spotLightShader2 = new SpotLightShader(gl, "core/shaders/vertex/vs_cube_03.txt", "core/shaders/fragment/fs_cube_033.txt");
        spotLightModel2 = new SpotLightModel2(gl, camera, light_1, light_2, spotLight, spotLightShader2, new Mat4(1), m, startTime);

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();

        spotLight.dispose(gl);
        light_1.dispose(gl);
        light_2.dispose(gl);
        light_3.dispose(gl);
        plane_1.dispose(gl);
        plane_2.dispose(gl);
        alien3_1.dispose(gl);
        alien3_2.dispose(gl);
        spotLightModel2.dispose(gl);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        render(gl);
    }

    private void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        renderBg(gl);
        alien3_1.render(gl);
        alien3_2.render(gl);
        spotLightModel2.render(gl);
    }

    private void renderBg(GL3 gl) {
        skybox.render(gl, cubemap_id, camera, startTime);

        spotLight.setPosition(-6.5f, 7.3f, 0.0f);  // changing light position each frame
        spotLight.setPosition(getLightPosition3());  // changing light position each frame

        light_1.setPosition(getLightPosition2());  // changing light position each frame
        light_2.setPosition(getLightPosition1());  // changing light position each frame

        plane_1.setModelMatrix(getMforTT1());       // change transform
        plane_1.render(gl);

        plane_2.setModelMatrix(getMforTT2());       // change transform
        plane_2.setElapsedTime(startTime - TimeUtils.getCurrentTime());
        plane_2.render(gl);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glViewport(x, y, width, height);
        float aspect = (float) width / (float) height;
        camera.setPerspectiveMatrix(Mat4Transform.perspective(45, aspect));
    }

    // The light's postion is continually being changed, so needs to be calculated for each frame.
    private Vec3 getLightPosition1() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float x = -6.1f * (float) (Math.sin(Math.toRadians(elapsedTime * 30)));
        float y = 3.8f;
        float z = 4.0f * (float) (Math.cos(Math.toRadians(elapsedTime * 30)));
        return new Vec3(x, y, z);
    }

    // The light's postion is continually being changed, so needs to be calculated for each frame.
    private Vec3 getLightPosition2() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float x = -8.1f * (float) (Math.sin(Math.toRadians(elapsedTime * 50)));
        float y = 7.8f;
        float z = -2.0f * (float) (Math.cos(Math.toRadians(elapsedTime * 50)));
        return new Vec3(x, y, z);
    }

    // The light's postion is continually being changed, so needs to be calculated for each frame.
    private Vec3 getLightPosition3() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float x = -6.5f * (float) (Math.sin(Math.toRadians(elapsedTime * 25)));
        float y = 7.3f;
        float z = 0.0f * (float) (Math.cos(Math.toRadians(elapsedTime * 25)));
        return new Vec3(x, y, z);
    }

    // As the transforms do not change over time for this object, they could be stored once rather than continually being calculated
    private Mat4 getMforTT1() {
        float size = 16f;
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(size, 1f, size), modelMatrix);
        return modelMatrix;
    }

    // As the transforms do not change over time for this object, they could be stored once rather than continually being calculated
    private Mat4 getMforTT2() {
        float size = 16f;
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(size, 1f, size), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundX(90), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0, size * 0.5f, -size * 0.5f), modelMatrix);
        return modelMatrix;
    }

    public Light getLight_1() {
        return light_1;
    }

    public Light getLight_2() {
        return light_2;
    }


    public SpotLight getSpotLight() {
        return spotLight;
    }

    public SpotLightModel2 getSpotLightModel2() {
        return spotLightModel2;
    }

    public AlienModel3 getAlien2_1() {
        return alien3_1;
    }
    public AlienModel3 getAlien2_2() {
        return alien3_2;
    }

    public SpotLightModel2 getSpotLightModel() {
        return spotLightModel2;
    }
}
