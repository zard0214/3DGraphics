import core.Material;
import core.Shader;
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
import model.alien.Spotlight;
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
    private int cubemap_id;
    private Model floor;
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

        initialise(gl);
        startTime = TimeUtils.getCurrentTime();
    }

    private void initialise(GL3 gl) {
        light = new Light(gl);
        light.setCamera(camera);

        skybox = new BackdropSkybox(gl);
        cubemap_id = skybox.loadCubemap(cubemap_directory + "posx.jpg", cubemap_directory + "negy.jpg",
                cubemap_directory + "posz.jpg", cubemap_directory + "negx.jpg",
                cubemap_directory + "posy.jpg", cubemap_directory + "negz.jpg");

        int[] textureId0 = TextureLibrary.loadTexture(gl, "textures/snow.jpg");

        Mesh mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
        Shader shader = new Shader(gl, "shaders/glsl/vs_tt_05.glsl", "shaders/glsl/fs_tt_05.glsl");
        Material material = new Material(new Vec3(1.0f, 1.0f, 1.0f), new Vec3(1.0f, 1.0f, 1.0f), new Vec3(1.0f, 1.0f, 1.0f), 32.0f);
        Mat4 modelMatrix = Mat4Transform.scale(16,1f,16);
        floor = new Model(gl, camera, light, shader, material, modelMatrix, mesh, textureId0);


        modelMatrix = Mat4Transform.translate(0,0,0);
        alien1 = new AlienModel(gl, camera, light, modelMatrix);

        modelMatrix = Mat4Transform.translate(8,0,0);
        alien2 = new AlienModel(gl, camera, light, modelMatrix);

        modelMatrix = Mat4Transform.translate(-12,0,0);
        spotlight = new Spotlight(gl, camera, light, modelMatrix);

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

}
