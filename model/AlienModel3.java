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

    private double rockStartTime, rollStartTime;
    private double savedTimeRock = 0, savedTimeRoll = 0;
    private boolean rock = false;
    private boolean roll = false;
    private Model floor, sphere, cube, cube2;
    private SGNode alienRoot;
    private TransformNode translateX, bodyMoveTranslate, bodyRotateTranslate, bodyTransform, bodyTranslate, bodyRotate, headMoveTranslate, headTransform, headTranslate, headRotate;
    private float xPosition = 0;

    private NameNode body, head;
    private ModelNode bodyShape, headShape;
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
        float legLength = 3.4f;
        float legScale = 0.0f;

        alienRoot = new NameNode("root");
        bodyRotateTranslate = new TransformNode("alien transform", Mat4Transform.rotateAroundY(0));

        bodyTranslate = new TransformNode("alien body transform2",Mat4Transform.translate(0, legLength,0));
        bodyRotate = new TransformNode("alien body bodyRotate", Mat4Transform.rotateAroundY(0));

        body = new NameNode("body");
        body = new NameNode("body");
        bodyMoveTranslate = new TransformNode("alien body transform", Mat4Transform.translate(xPosition,0,0));

        Mat4 m2 = Mat4Transform.scale(bodyWidth, bodyHeight, bodyDepth);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0,-1.5f,0));
        bodyTransform = new TransformNode("body transform", m2);
        bodyShape = new ModelNode("Alien(body)", cube);

        head = new NameNode("head");
        headMoveTranslate = new TransformNode("alien head transform",Mat4Transform.translate(xPosition,0,0));

        headTranslate = new TransformNode("alien head transform2",Mat4Transform.translate(0, 0,0));
        headRotate = new TransformNode("alien head rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(headScale,headScale,headScale));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, -2.15f,0));
        headTransform = new TransformNode("head transform2", m2);
        headShape = new ModelNode("Alien(head)", sphere);

//        alienRoot.addChild(bodyMoveTranslate);
//        bodyMoveTranslate.addChild(bodyTranslate);
//        bodyTranslate.addChild(body);
//        body.addChild(bodyTransform);
//        bodyTransform.addChild(bodyShape);
//        body.addChild(head);
//        head.addChild(headTransform);
//        headTransform.addChild(headShape);

        createNode();
        alienRoot.update();
    }

    private void createNode() {
        alienRoot.addChild(bodyRotateTranslate);
        bodyRotateTranslate.addChild(bodyMoveTranslate);
        bodyMoveTranslate.addChild(body);

        body.addChild(bodyTranslate);
        bodyTranslate.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);
    }

//    private void createNode() {
//        alienRoot.addChild(bodyMoveTranslate);
//            bodyMoveTranslate.addChild(bodyTranslate);
//                bodyTranslate.addChild(body);
//                    body.addChild(bodyTransform);
//                        bodyTransform.addChild(bodyShape);
//
//        headShape.addChild(head);
//
//        head.addChild(headMoveTranslate);
//            headMoveTranslate.addChild(headTranslate);
//                headTranslate.addChild(head);
//                    head.addChild(headTransform);
//                        headTransform.addChild(headShape);
//
//
//    }

    public void render(GL3 gl) {
        if (rock) rockAroundZ();
        if (roll) roll();
        alienRoot.draw(gl);
    }

    private void rockAroundZ() {
        double elapsedTime = getSeconds() - rockStartTime;
        float rotateAngle = 25f * (float)Math.sin(elapsedTime);
        bodyRotateTranslate.setTransform(Mat4Transform.rotateAroundZ(rotateAngle));
        bodyRotateTranslate.update();
    }

    private void roll() {
        double elapsedTime = getSeconds() - rockStartTime;
        float rotateAngle = 25f * (float)Math.sin(elapsedTime);

        bodyMoveTranslate.setTransform(Mat4Transform.rotateAroundY(rotateAngle));
        bodyMoveTranslate.update();
    }


    public void dispose(GL3 gl) {
        sphere.dispose(gl);
        cube.dispose(gl);
    }


    private double getSeconds() {
        return System.currentTimeMillis()/1000.0;
    }

    public void startRock() {
        rock = true;
        rockStartTime = getSeconds() - savedTimeRock;
    }

    public void startRoll() {
        roll = true;
        rollStartTime = getSeconds() - savedTimeRoll;
    }

    public void stopRoll() {
        roll = false;
        double elapsedTime = getSeconds() - rollStartTime;
        savedTimeRoll = elapsedTime;
    }

}
