import core.camera.Camera;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import model.*;
import core.light.Light;
import model.alien.AlienModel;
import model.alien.Spotlight;
import utils.TimeUtils;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 16:23
 */
public class Aliens_GLEventListener implements GLEventListener {

    private static final boolean DISPLAY_SHADERS = false;
    //should be change
    private Model tt1;
    private BackdropSkybox skybox;

    private int cubemap_id;
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
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL3 gl = drawable.getGL().getGL3();
        render(gl);
    }


    private void render(GL3 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        skybox.render(gl, cubemap_id, camera, startTime);

        Context.spotlight = new Spotlight(gl, camera, startTime);
        Context.leftAlien = new AlienModel(gl, camera, startTime, 0);
        Context.rightAlien = new AlienModel(gl, camera, startTime, 1);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL3 gl = drawable.getGL().getGL3();
        gl.glViewport(x, y, width, height);
        float aspect = (float)width / (float)height;
        camera.setPerspectiveMatrix(Mat4Transform.perspective(45, aspect));
    }

}
