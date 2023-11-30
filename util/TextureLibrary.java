package util;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.spi.JPEGImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class TextureLibrary {

    // no mip-mapping (see next example)
    public static Texture loadPNGTexture(GL3 gl3, String filename) {
        Texture t = null;
        try {
            File f = new File(filename);
            t = TextureIO.newTexture(f, false);
            t.bind(gl3);
            t.setTexParameteri(gl3, GL3.GL_TEXTURE_MIN_FILTER, GL3.GL_LINEAR);
            t.setTexParameteri(gl3, GL3.GL_TEXTURE_MAG_FILTER, GL3.GL_LINEAR);
            t.setTexParameteri(gl3, GL3.GL_TEXTURE_WRAP_S, GL3.GL_CLAMP_TO_EDGE);
            t.setTexParameteri(gl3, GL3.GL_TEXTURE_WRAP_T, GL3.GL_CLAMP_TO_EDGE);
        } catch (Exception e) {
            System.out.println("Error loading texture " + filename);
        }
        return t;
    }

    // only deals with rgb jpg files
    public static int[] loadTexture(GL3 gl, String filename) {
        return loadTexture(gl, filename, GL.GL_REPEAT, GL.GL_REPEAT,
                GL.GL_LINEAR, GL.GL_LINEAR);
    }

    // only deals with rgb jpg files
    public static int[] loadTexturePNG(GL3 gl, String filename) {
        return loadTexturePNG(gl, filename, GL.GL_REPEAT, GL.GL_REPEAT,
                GL.GL_LINEAR, GL.GL_LINEAR);
    }

    public static int[] loadTexture(GL3 gl, String filename,
                                    int wrappingS, int wrappingT, int filterS, int filterT) {
        int[] textureId = new int[1];
        try {
            File f = new File(filename);
            JPEGImage img = JPEGImage.read(new FileInputStream(f));
            gl.glGenTextures(1, textureId, 0);
            gl.glBindTexture(GL3.GL_TEXTURE_2D, textureId[0]);
            gl.glTexParameteri(GL3.GL_TEXTURE_2D, GL3.GL_TEXTURE_WRAP_S, wrappingS);
            gl.glTexParameteri(GL3.GL_TEXTURE_2D, GL3.GL_TEXTURE_WRAP_T, wrappingT);
            gl.glTexParameteri(GL3.GL_TEXTURE_2D, GL3.GL_TEXTURE_MIN_FILTER, filterS);
            gl.glTexParameteri(GL3.GL_TEXTURE_2D, GL3.GL_TEXTURE_MAG_FILTER, filterT);
            gl.glTexImage2D(GL3.GL_TEXTURE_2D, 0, GL3.GL_RGB, img.getWidth(), img.getHeight(), 0, GL3.GL_RGB, GL3.GL_UNSIGNED_BYTE, img.getData());
            //using mipmap
            gl.glGenerateMipmap(GL3.GL_TEXTURE_2D);
            gl.glTexParameteri(GL3.GL_TEXTURE_2D, GL3.GL_TEXTURE_BASE_LEVEL, 0);
            gl.glTexParameteri(GL3.GL_TEXTURE_2D, GL3.GL_TEXTURE_MAX_LEVEL, 4);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);

            gl.glBindTexture(GL3.GL_TEXTURE_2D, 0);
        } catch (Exception e) {
            System.out.println("Error loading texture " + filename);
        }
        return textureId;
    }

    public static int[] loadTexturePNG(GL3 gl, String filename,
                                       int wrappingS, int wrappingT, int filterS, int filterT) {
        int[] textureId = new int[1];
        try {
            File f = new File(filename);
            JPEGImage img = JPEGImage.read(new FileInputStream(f));
            gl.glGenTextures(1, textureId, 0);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textureId[0]);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, wrappingS);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, wrappingT);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, filterS);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, filterT);
            gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGBA, img.getWidth(), img.getHeight(), 0, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, img.getData());
            gl.glGenerateMipmap(GL.GL_TEXTURE_2D);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
            gl.glBindTexture(GL.GL_TEXTURE_2D, 0);
        } catch (Exception e) {
            System.out.println("Error loading texture " + filename);
        }
        return textureId;
    }

    public static ByteBuffer getTextureImageData(String image) {
        ByteBuffer byteBuffer;
        File f = new File(image);
        try {
            JPEGImage img = JPEGImage.read(new FileInputStream(f));
            byteBuffer = img.getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteBuffer;
    }

    public static int getTextureImageWidth(String image) {
        int width;
        File f = new File(image);
        try {
            JPEGImage img = JPEGImage.read(new FileInputStream(f));
            width = img.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return width;
    }

    public static int getTextureImageHeight(String image) {
        int height;
        File f = new File(image);
        try {
            JPEGImage img = JPEGImage.read(new FileInputStream(f));
            height = img.getHeight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return height;
    }
}