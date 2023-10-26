import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import core.camera.Camera;
import core.shaders.SkyboxShader;
import gmaths.Mat4;
import util.Constant;
import util.TextureLibrary;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES2.GL_TEXTURE_WRAP_R;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 21/10/2023 18:41
 */
public class BackdropSkybox {
    private int cubemap_id;
    public int[] vertexBufferId = new int[1];
    public int[] vertexArrayId = new int[1];
    private GL3 gl;

    private SkyboxShader skyboxshader;

    public BackdropSkybox(GL3 gl) {
        this.gl = gl;
    }

    public int loadCubemap(String px, String py, String pz, String nx, String ny,
                           String nz) {
        String[] filePath = new String[6];

        filePath[0] = px;
        filePath[1] = py;
        filePath[2] = pz;
        filePath[3] = nx;
        filePath[4] = ny;
        filePath[5] = nz;

        //Generate a texture for cubemap.
        int[] targets = new int[]{GL_TEXTURE_CUBE_MAP_POSITIVE_X, GL_TEXTURE_CUBE_MAP_POSITIVE_Y,
                GL_TEXTURE_CUBE_MAP_POSITIVE_Z, GL_TEXTURE_CUBE_MAP_NEGATIVE_X,
                GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, GL_TEXTURE_CUBE_MAP_NEGATIVE_Z};

        //Get image data buffer
        ByteBuffer[] data_buffers = new ByteBuffer[6];
        for (int i = 0; i < 6; i++) {
            data_buffers[i] = TextureLibrary.getTextureImageData(filePath[i]);
        }

        IntBuffer texture_ids = Buffers.newDirectIntBuffer(1);
        gl.glGenTextures(1, texture_ids);
        cubemap_id = texture_ids.get(0);
        gl.glBindTexture(GL_TEXTURE_CUBE_MAP, cubemap_id);
        for (int i = 0; i < 6; i++) {
            int textureImageWidth = TextureLibrary.getTextureImageWidth(filePath[i]);
            int textureImageHeight = TextureLibrary.getTextureImageHeight(filePath[i]);
            gl.glTexImage2D(targets[i], 0, GL_RGB, textureImageWidth, textureImageHeight, 0, GL_RGB,
                    GL_UNSIGNED_BYTE, data_buffers[i]);
        }

        gl.glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        gl.glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        gl.glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);

        gl.glGenerateMipmap(GL_TEXTURE_CUBE_MAP);
        gl.glBindTexture(GL_TEXTURE_CUBE_MAP, 0);

        return cubemap_id;
    }

    public void render(GL3 gl, int cubemap_id, Camera camera, double startTime) {

        gl.glGenVertexArrays(1, vertexArrayId, 0);
        gl.glGenBuffers(1, vertexBufferId, 0);
        gl.glBindVertexArray(vertexArrayId[0]);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferId[0]);
        FloatBuffer fb = Buffers.newDirectFloatBuffer(Constant.SKYBOX_VS);
        gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * Constant.SKYBOX_VS.length, fb, GL.GL_STATIC_DRAW);
        gl.glEnableVertexAttribArray(0);
        gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, 3 * Float.BYTES, 0);

        gl.glDepthMask(false);
        skyboxshader = new SkyboxShader(gl);
        Mat4 view = Mat4.noTranslation(camera.getViewMatrix());
        Mat4 projection = camera.getPerspectiveMatrix();
        skyboxshader.use(gl);
        skyboxshader.setFloatArray(gl, "view", view.toFloatArrayForGLSL());
        skyboxshader.setFloatArray(gl, "projection", projection.toFloatArrayForGLSL());

        gl.glActiveTexture(GL.GL_TEXTURE0);
        gl.glBindTexture(GL.GL_TEXTURE_CUBE_MAP, cubemap_id);

        gl.glDrawArrays(GL.GL_TRIANGLES, 0, 36);
        gl.glDepthMask(true);
    }

}
