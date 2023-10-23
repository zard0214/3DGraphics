import core.Material;
import core.camera.Camera;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import model.*;
import core.light.Light;
import model.alien.AlienModel;
import model.alien.FloorModel;
import model.alien.Spotlight;
import shaders.shaders.FloorShader;
import shaders.shaders.BackgroundShader;
import utils.TextureLibrary;
import utils.TimeUtils;
import utils.TwoTriangles;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 16:23
 */
public class Aliens_GLEventListener implements GLEventListener {

    private static final boolean DISPLAY_SHADERS = false;
    private BackdropSkybox skybox;
    private int cubemap_id, snowing_texture_id;
    private int[] snowing_texture_id2;
    private FloorModel floor;
    private AlienModel alien1, alien2;
    private Spotlight spotlight;
    private Light light;
    private Camera camera;
    private double startTime;

    private final String cubemap_directory = "textures/";

    /* The constructor is not used to initialise anything */
    public Aliens_GLEventListener(Camera camera) {
        this.camera = camera;
        this.camera.setPosition(new Vec3(4f,6f,15f));
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
        light = new Light(gl);
        light.setCamera(camera);

        skybox = new BackdropSkybox(gl);
        cubemap_id = skybox.loadCubemap(cubemap_directory + "posx.jpg", cubemap_directory + "negy.jpg",
                cubemap_directory + "posz.jpg", cubemap_directory + "negx.jpg",
                cubemap_directory + "posy.jpg", cubemap_directory + "negz.jpg");


        snowing_texture_id = TextureLibrary.loadTexture(gl, cubemap_directory + "snowing.jpg")[0];
        snowing_texture_id2 = TextureLibrary.loadTexture(gl, cubemap_directory + "snowing.jpg");
        int[] textureId0 = TextureLibrary.loadTexture(gl, cubemap_directory + "snow2.jpg");

        Mesh mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
//        FloorShader shader = new FloorShader(gl);
        BackgroundShader shader = new BackgroundShader(gl);
        Material material = new Material(new Vec3(1.0f, 1.0f, 1.0f), new Vec3(1.0f, 1.0f, 1.0f), new Vec3(1.0f, 1.0f, 1.0f), 1.0f);
        Mat4 modelMatrix = Mat4Transform.scale(16,1f,16);
//        floor = new FloorModel(gl, camera, light, shader, material, modelMatrix, mesh, textureId0);
        floor = new FloorModel(gl, camera, light, shader, material, modelMatrix, mesh, textureId0, snowing_texture_id2, startTime);


        Mat4 modelsMatrix = Mat4Transform.translate(0,0,0);
        alien1 = new AlienModel(gl, camera, light, modelsMatrix);

        modelsMatrix = Mat4Transform.translate(8,0,0);
        alien2 = new AlienModel(gl, camera, light, modelsMatrix);

        modelsMatrix = Mat4Transform.translate(-12,0,0);
        spotlight = new Spotlight(gl, camera, light, modelsMatrix);

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();

        floor.dispose(gl);

        alien1.dispose(gl);
        alien2.dispose(gl);

        spotlight.dispose(gl);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        render(gl);
    }


    private void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        skybox.render(gl, cubemap_id, camera, startTime);

        floor.render(gl);
        floor.render(gl, getMforTT2());

        alien1.render(gl);
        alien2.render(gl);

        spotlight.render(gl);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glViewport(x, y, width, height);
        float aspect = (float)width / (float)height;
        camera.setPerspectiveMatrix(Mat4Transform.perspective(45, aspect));
    }

    // As the transforms do not change over time for this object, they could be stored once rather than continually being calculated
    private Mat4 getMforTT2() {
        float size = 16f;
        Mat4 modelMatrix = new Mat4(1);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(size,1f,size), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundX(90), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0,size*0.5f,-size*0.5f), modelMatrix);
        return modelMatrix;
    }

}
