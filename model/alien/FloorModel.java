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
    private double startTime;
    private int[] textureId1;
    private int[] textureId2;

    public FloorModel(GL3 gl, Camera camera, Light light, Shader shader, Material material, Mat4 modelMatrix, Mesh mesh, int[] textureId1, int[] textureId2, double startTime) {
        super(gl, camera, light, shader, material, modelMatrix, mesh, textureId1, textureId2);
        this.shader = shader;
        this.startTime = startTime;
        this.textureId1 = textureId1;
        this.textureId2 = textureId2;
    }

    @Override
    public void render(GL3 gl, Mat4 modelMatrix) {
        super.render(gl, modelMatrix);

        if (textureId1 != null) {
            shader.setInt(gl, "first_texture", 0);
            gl.glActiveTexture(GL.GL_TEXTURE0);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textureId1[0]);
        }
        if (textureId2 != null) {
            shader.setInt(gl, "second_texture", 1);
            gl.glActiveTexture(GL.GL_TEXTURE1);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textureId2[0]);
        }

        double t = startTime * 1;
        float offsetX = 0.0f;
        float offsetY = (float)(t - Math.floor(t));;
        shader.setFloat(gl, "offset", offsetX, offsetY);
    }
}
