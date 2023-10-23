package model.alien;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import core.Material;
import core.Shader;
import core.camera.Camera;
import core.light.Light;
import gmaths.Mat4;
import model.Mesh;
import model.Model;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 22/10/2023 19:20
 */
public class FloorModel extends Model {

    private Shader shader;
    private double elapsedTime;
    private int[] textureId1;
    private int[] textureId2;

    public FloorModel(GL3 gl, Camera camera, Light light, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh, int[] textureId1, int[] textureId2, double elapsedTime) {
        super(gl, camera, light, shader, material, modelMatrix, mesh, textureId1, textureId2);
        this.shader = shader;
        this.elapsedTime = elapsedTime;
        this.textureId1 = textureId1;
        this.textureId2 = textureId2;
    }

    @Override
    public void render(GL3 gl, Mat4 modelMatrix) {
        super.render(gl, modelMatrix);

        final double SPEED = 0.2;
        double increment = elapsedTime * SPEED;


    }
}
