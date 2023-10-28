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
    private TransformNode translateX;
    private TransformNode bodyMoveTranslate, bodyRotateTranslate, bodyTransform, bodyTranslate, bodyRotate;
    private TransformNode headMoveTranslate, headTransform, headTranslate, headRotate;
    private TransformNode antennaBottomMoveTranslate, antennaBottomTransform, antennaBottomTranslate, antennaBottomRotate;
    private TransformNode antennaTopMoveTranslate, antennaTopTransform, antennaTopTranslate, antennaTopRotate;
    private TransformNode bodyRoll, headRoll, antennaBottomRoll, antennaTopRoll;
    private float xPosition = 0;

    private NameNode body, head, antennaBottom, antennaTop;
    private ModelNode bodyShape, headShape, antennaBottomShape, antennaTopShape;
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
        float antennaBottomScale1 = 0.125f;
        float antennaBottomScale2 = 0.5f;
        float antennaTopScale2 = 0.18f;
        float legLength = 3.4f;

        /***********  body  ***************/
        alienRoot = new NameNode("root");
        bodyRotateTranslate = new TransformNode("alien transform", Mat4Transform.rotateAroundY(0));

        bodyTranslate = new TransformNode("alien body transform2",Mat4Transform.translate(0, legLength,0));
        bodyRotate = new TransformNode("alien body bodyRotate", Mat4Transform.rotateAroundY(0));


        body = new NameNode("body");
        bodyMoveTranslate = new TransformNode("alien body transform", Mat4Transform.translate(xPosition,0,0));
        bodyRoll = new TransformNode("alien roll",  Mat4Transform.rotateAroundY(0));

        Mat4 m2 = Mat4Transform.scale(bodyWidth, bodyHeight, bodyDepth);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0,-1.5f,0));
        bodyTransform = new TransformNode("body transform", m2);
        bodyShape = new ModelNode("Alien(body)", cube);

        /***********  head  ***************/
        head = new NameNode("head");
        headMoveTranslate = new TransformNode("alien head transform",Mat4Transform.translate(xPosition,0,0));
        headRoll = new TransformNode("head roll",  Mat4Transform.rotateAroundY(0));

        headTranslate = new TransformNode("alien head transform2",Mat4Transform.translate(0, 0,0));
        headRotate = new TransformNode("alien head rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(headScale,headScale,headScale));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, -2.15f,0));
        headTransform = new TransformNode("head transform2", m2);
        headShape = new ModelNode("Alien(head)", sphere);

        /***********  antenna_bottom  ***************/
        antennaBottom = new NameNode("antennaBottom");
        antennaBottomMoveTranslate = new TransformNode("alien antenna_bottom transform",Mat4Transform.translate(xPosition,0,0));
        antennaBottomRoll = new TransformNode("antennaBottom roll",  Mat4Transform.rotateAroundY(0));

        antennaBottomTranslate = new TransformNode("alien antennaBottom transform2",Mat4Transform.translate(0, 0,0));
        antennaBottomRotate = new TransformNode("alien antennaBottom rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(antennaBottomScale1, antennaBottomScale2, antennaBottomScale1));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, -3.80f,0));
        antennaBottomTransform = new TransformNode("antennaBottom transform2", m2);
        antennaBottomShape = new ModelNode("Alien(antennaBottom)", sphere);

        /***********  antenna_top  ***************/
        antennaTop = new NameNode("antennaTop");
        antennaTopMoveTranslate = new TransformNode("alien antenna_top transform",Mat4Transform.translate(xPosition,0,0));
        antennaTopRoll = new TransformNode("antennaTop roll",  Mat4Transform.rotateAroundY(0));

        antennaTopTranslate = new TransformNode("alien antennaTop transform2",Mat4Transform.translate(0, 0,0));
        antennaTopRotate = new TransformNode("alien antennaTop rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(antennaTopScale2, antennaTopScale2, antennaTopScale2));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, -8.8f,0));
        antennaTopTransform = new TransformNode("antennaTop transform2", m2);
        antennaTopShape = new ModelNode("Alien(antennaTop)", sphere);

        /***********  lefteye  ***************/
        /***********  righteye  ***************/
        /***********  leftarm  ***************/
        /***********  rightarm  ***************/
        /***********  leftear  ***************/
        /***********  rightear  ***************/
        createNode();
        alienRoot.update();
    }

    private void createNode() {
        /*****  body  ******/
        alienRoot.addChild(bodyRotateTranslate);
        bodyRotateTranslate.addChild(bodyMoveTranslate);
        bodyMoveTranslate.addChild(body);

        body.addChild(bodyTranslate);
        bodyTranslate.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);

        /*****  head  ******/
        bodyTranslate.addChild(bodyRoll);
        bodyRoll.addChild(head);
        head.addChild(headRotate);
        headRotate.addChild(headMoveTranslate);

        head.addChild(headTranslate);
        headTranslate.addChild(headTransform);
        headTransform.addChild(headShape);

        /*****  antennaBottom  ******/
        headTranslate.addChild(headRoll);
        headRoll.addChild(antennaBottom);
        antennaBottom.addChild(antennaBottomRotate);

        antennaBottomRotate.addChild(antennaBottomTranslate);
        antennaBottomTranslate.addChild(antennaBottomTransform);
        antennaBottomTransform.addChild(antennaBottomShape);

        /*****  antennaTop  ******/
        antennaBottomTranslate.addChild(antennaBottomRoll);
        antennaBottomRoll.addChild(antennaTop);
        antennaTop.addChild(antennaTopRotate);

        antennaTopRotate.addChild(antennaTopTranslate);
        antennaTopTranslate.addChild(antennaTopTransform);
        antennaTopTransform.addChild(antennaTopShape);
    }

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
