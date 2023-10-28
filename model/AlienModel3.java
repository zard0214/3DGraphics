package model;

import com.jogamp.opengl.GL3;
import core.Shader;
import core.camera.Camera;
import core.camera.Material;
import core.light.Light;
import core.light.SpotLight;
import core.shaders.AlienShader;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import model.node.NameNode;
import model.node.SGNode;
import model.node.TransformNode;
import util.Constant;
import util.TextureLibrary;
import util.TimeUtils;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 08:20
 */
public class AlienModel3 {

    private Model floor, sphere, cube, cube2;
    private SGNode alienRoot;
    private TransformNode translateX, bodyMoveTranslate, headMoveTranslate, leftArmRotate, rightArmRotate, bodyTranslate, headTranslate;
    private float xPosition = 0;
    public AlienModel3(GL3 gl, Camera camera, Light light_1, Light light_2, SpotLight spotLight, AlienShader alienShader, Material alienMaterial, Mat4 mat4, Mesh m, int[] alienTexture, Mat4 transition) {

        int[] alientextureId0 = TextureLibrary.loadTexture(gl, Constant.ALIEN_TEXTURE_GRAY);
        Mesh mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        Shader shader = new Shader(gl, "core/shaders/vertex/vs_default.glsl", "core/shaders/fragment/fs_texture.glsl");
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f), new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        Mat4 modelMatrix = Mat4.multiply(Mat4Transform.scale(4,4,4), Mat4Transform.translate(0,0.5f,0));
        sphere = new Model(gl, camera, light_1, light_2, spotLight, shader, material, modelMatrix, mesh, alientextureId0, alientextureId0);

        mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
        shader = new Shader(gl, "core/shaders/vertex/vs_default.glsl", "core/shaders/fragment/fs_texture.glsl");
        material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f), new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(4,4,4), Mat4Transform.translate(0,0.5f,0));
        cube = new Model(gl, camera, light_1, light_2, spotLight, shader, material, modelMatrix, mesh, alientextureId0, alientextureId0);


        float bodyHeight = 1.7f;
        float bodyWidth = 1.7f;
        float bodyDepth = 1.7f;
        float headScale = 1.3f;
        float armLength = 3.5f;
        float armScale = 0.5f;
        float legLength = 3.5f;
        float legScale = 0.0f;

        alienRoot = new NameNode("root");
        bodyMoveTranslate = new TransformNode("alien transform",Mat4Transform.translate(xPosition,0,0));

        bodyTranslate = new TransformNode("alien transform2",Mat4Transform.translate(0, legLength,0));

        NameNode body = new NameNode("body");
        Mat4 m2 = Mat4Transform.scale(bodyWidth, bodyHeight, bodyDepth);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0,-1.5f,0));
        TransformNode bodyTransform = new TransformNode("body transform", m2);
        ModelNode bodyShape = new ModelNode("Alien(body)", cube);

        NameNode head = new NameNode("head");
        headMoveTranslate = new TransformNode("head transform",Mat4Transform.translate(xPosition,0,0));

        headTranslate = new TransformNode("head transform2",Mat4Transform.translate(0, legLength,0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(headScale,headScale,headScale));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, -2.15f,0));
        TransformNode headTransform = new TransformNode("head transform2", m2);
        ModelNode headShape = new ModelNode("Alien(head)", sphere);


        alienRoot.addChild(bodyMoveTranslate);
            bodyMoveTranslate.addChild(bodyTranslate);
                bodyTranslate.addChild(body);
                    body.addChild(bodyTransform);
                        bodyTransform.addChild(bodyShape);
                    body.addChild(head);
                        head.addChild(headTransform);
                        headTransform.addChild(headShape);

//        body.addChild(headMoveTranslate);
//
////        head.addChild(headMoveTranslate);
//            headMoveTranslate.addChild(headTranslate);
//                headTranslate.addChild(head);
//                    head.addChild(headTransform);
//                        headTransform.addChild(headShape);
////                    body.addChild(head);
////                        head.addChild(headTransform);
////                        headTransform.addChild(headShape);
        alienRoot.update();
    }


    public void render(GL3 gl) {
        if (animation) updateLeftArm();
        alienRoot.draw(gl);
    }

    private void updateLeftArm() {
        double elapsedTime = getSeconds()-startTime;
        float rotateAngle = 25f * (float)Math.sin(elapsedTime);

        bodyMoveTranslate.setTransform(Mat4Transform.rotateAroundZ(rotateAngle));
        bodyMoveTranslate.update();
//        headMoveTranslate.setTransform(Mat4Transform.rotateAroundZ(rotateAngle));
//        headMoveTranslate.update();
    }

    public void dispose(GL3 gl) {
        sphere.dispose(gl);
        cube.dispose(gl);
    }

    private double startTime;

    private double getSeconds() {
        return System.currentTimeMillis()/1000.0;
    }

    private boolean animation = false;
    private double savedTime = 0;

    public void startAnimation() {
        animation = true;
        startTime = getSeconds() - savedTime;
    }

    public void stopAnimation() {
        animation = false;
        double elapsedTime = getSeconds()-startTime;
        savedTime = elapsedTime;
    }

}
