package model;

import com.jogamp.opengl.GL3;
import core.Shader;
import core.camera.Camera;
import core.Material;
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
    private Model sphere, cube;
    private SGNode alienRoot;
    private TransformNode translateX, translateY;
    private TransformNode bodyMoveTranslate, bodyRotateTranslate, bodyTransform, bodyTranslate, bodyRotate;
    private TransformNode headMoveTranslate, headTransform, headTranslate, headRotate;
    private TransformNode antennaBottomTransform, antennaBottomTranslate, antennaBottomRotate;
    private TransformNode antennaTopTransform, antennaTopTranslate, antennaTopRotate;
    private TransformNode leftEyeTransform, leftEyeTranslate, leftEyeRotate;
    private TransformNode rightEyeTransform, rightEyeTranslate, rightEyeRotate;
    private TransformNode leftArmMoveTranslate, leftArmTransform, leftArmTranslate, leftArmRotate;
    private TransformNode rightArmMoveTranslate, rightArmTransform, rightArmTranslate, rightArmRotate;
    private TransformNode leftEarMoveTranslate, leftEarTransform, leftEarTranslate, leftEarRotate;
    private TransformNode rightEarMoveTranslate, rightEarTransform, rightEarTranslate, rightEarRotate;
    private TransformNode bodyRoll, headRoll, antennaBottomRoll, antennaTopRoll, leftEyeRoll, rightEyeRoll, leftArmRoll, rightArmRoll,  leftEarRoll, rightEarRoll;
    private float xPosition = 0;

    private NameNode body, head, antennaBottom, antennaTop, leftArm, rightArm, leftEye, rightEye, leftEar, rightEar;
    private ModelNode bodyShape, headShape, antennaBottomShape, antennaTopShape, leftArmShape, rightArmShape, leftEyeShape, rightEyeShape, leftEarShape, rightEarShape;
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
        float eyeScale = 0.18f;
        float armScale1 = 0.18f;
        float armScale2 = 1.0f;

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

        /***********  leftarm  ***************/
        leftArm = new NameNode("rightArm");
        leftArmMoveTranslate = new TransformNode("alien leftArm transform", Mat4Transform.translate(xPosition,0,0));
        leftArmRoll = new TransformNode("leftArm roll",  Mat4Transform.rotateAroundY(0));

        leftArmTranslate = new TransformNode("alien leftArm transform2",Mat4Transform.translate(0, 0,0));
        leftArmRotate = new TransformNode("alien leftArm rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.rotateAroundZ(45));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(armScale1, armScale2, armScale1));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(-6.30f, 0.60f,0));
        leftArmTransform = new TransformNode("leftArm transform2", m2);
        leftArmShape = new ModelNode("Alien(leftArm)", sphere);

        /***********  rightarm  ***************/
        rightArm = new NameNode("rightArm");
        rightArmMoveTranslate = new TransformNode("alien rightArm transform", Mat4Transform.translate(xPosition,0,0));
        rightArmRoll = new TransformNode("rightArm roll",  Mat4Transform.rotateAroundY(0));

        rightArmTranslate = new TransformNode("alien rightArm transform2",Mat4Transform.translate(0, 0,0));
        rightArmRotate = new TransformNode("alien rightArm rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.rotateAroundZ(-45));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(armScale1, armScale2, armScale1));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(6.30f, 0.60f,0));
        rightArmTransform = new TransformNode("rightArm transform2", m2);
        rightArmShape = new ModelNode("Alien(rightArm)", sphere);

        /***********  head  ***************/
        head = new NameNode("head");
        headMoveTranslate = new TransformNode("alien head transform",Mat4Transform.translate(xPosition,0,0));
        headRoll = new TransformNode("head roll",  Mat4Transform.rotateAroundY(0));

        headTranslate = new TransformNode("alien head transform2",Mat4Transform.translate(0, 0,0));
        headRotate = new TransformNode("alien head rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(headScale, headScale, headScale));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, headScale / 2 - 0.18f,0));
        headTransform = new TransformNode("head transform2", m2);
        headShape = new ModelNode("Alien(head)", sphere);

        /***********  lefteye  ***************/

        leftEye = new NameNode("leftEye");
        leftEyeRoll = new TransformNode("leftEye roll",  Mat4Transform.rotateAroundY(0));

        leftEyeTranslate = new TransformNode("alien leftEye transform2",Mat4Transform.translate(0, 0,0));
        leftEyeRotate = new TransformNode("alien leftEye rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(eyeScale, eyeScale, eyeScale));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(-1.3f,3 + headScale / 2 - 0.18f,3.70f));
        leftEyeTransform = new TransformNode("leftEye transform2", m2);
        leftEyeShape = new ModelNode("Alien(leftEye)", sphere);

        /***********  righteye  ***************/

        rightEye = new NameNode("rightEye");
        rightEyeRoll = new TransformNode("rightEye roll",  Mat4Transform.rotateAroundY(0));

        rightEyeTranslate = new TransformNode("alien rightEye transform2",Mat4Transform.translate(0, 0,0));
        rightEyeRotate = new TransformNode("alien rightEye rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(eyeScale, eyeScale, eyeScale));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(1.3f,3 + headScale / 2 - 0.18f,3.70f));
        rightEyeTransform = new TransformNode("rightEye transform2", m2);
        rightEyeShape = new ModelNode("Alien(rightEye)", sphere);

        /***********  antenna_bottom  ***************/
        antennaBottom = new NameNode("antennaBottom");
        antennaBottomRoll = new TransformNode("antennaBottom roll",  Mat4Transform.rotateAroundY(0));

        antennaBottomTranslate = new TransformNode("alien antennaBottom transform2",Mat4Transform.translate(0, 0,0));
        antennaBottomRotate = new TransformNode("alien antennaBottom rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(antennaBottomScale1, antennaBottomScale2, antennaBottomScale1));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, 2.55f + headScale / 2 - 0.18f,0));
        antennaBottomTransform = new TransformNode("antennaBottom transform2", m2);
        antennaBottomShape = new ModelNode("Alien(antennaBottom)", sphere);

        /***********  antenna_top  ***************/
        antennaTop = new NameNode("antennaTop");
        antennaTopRoll = new TransformNode("antennaTop roll",  Mat4Transform.rotateAroundY(0));

        antennaTopTranslate = new TransformNode("alien antennaTop transform2",Mat4Transform.translate(0, 0,0));
        antennaTopRotate = new TransformNode("alien antennaTop rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(antennaTopScale2, antennaTopScale2, antennaTopScale2));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, 9.80f + headScale / 2 - 0.18f,0));
        antennaTopTransform = new TransformNode("antennaTop transform2", m2);
        antennaTopShape = new ModelNode("Alien(antennaTop)", sphere);

        /***********  leftear  ***************/

        leftEar = new NameNode("leftEar");
        leftEarRoll = new TransformNode("leftEar roll",  Mat4Transform.rotateAroundY(0));

        leftEarTranslate = new TransformNode("alien leftEar transform2",Mat4Transform.translate(0, 0,0));
        leftEarRotate = new TransformNode("alien leftEar rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(armScale1, armScale2, armScale1));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(-3.3f,0.8f + headScale / 2 - 0.18f,0));
        leftEarTransform = new TransformNode("leftEar transform2", m2);
        leftEarShape = new ModelNode("Alien(leftEar)", sphere);

        /***********  rightear  ***************/

        rightEar = new NameNode("rightEar");
        rightEarRoll = new TransformNode("rightEar roll",  Mat4Transform.rotateAroundY(0));

        rightEarTranslate = new TransformNode("alien rightEar transform2",Mat4Transform.translate(0, 0,0));
        rightEarRotate = new TransformNode("alien rightEar rotate",  Mat4Transform.rotateAroundY(0));

        m2 = new Mat4(1);
        m2 = Mat4.multiply(m2, Mat4Transform.translate(0, bodyHeight,0));
        m2 = Mat4.multiply(m2, Mat4Transform.scale(armScale1, armScale2, armScale1));
        m2 = Mat4.multiply(m2, Mat4Transform.translate(3.3f,0.8f + headScale / 2 - 0.18f,0));
        rightEarTransform = new TransformNode("rightEar transform2", m2);
        rightEarShape = new ModelNode("Alien(rightEar)", sphere);



//        createNode();
        createNode2();
        alienRoot.update();
    }

    private void createNode2() {
        alienRoot.addChild(bodyRotateTranslate);
        bodyRotateTranslate.addChild(bodyMoveTranslate);
        bodyMoveTranslate.addChild(body);
        bodyMoveTranslate.addChild(bodyRoll);

        body.addChild(bodyTranslate);
        bodyTranslate.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);

        body.addChild(leftArm);
        leftArm.addChild(leftArmRotate);
        leftArmRotate.addChild(leftArmTransform);
        leftArmTransform.addChild(leftArmShape);

        body.addChild(rightArm);
        rightArm.addChild(rightArmRotate);
        rightArmRotate.addChild(rightArmTransform);
        rightArmTransform.addChild(rightArmShape);

        body.addChild(headRoll);
        headRoll.addChild(head);
        head.addChild(headRotate);
        headRotate.addChild(headTransform);
        headTransform.addChild(headShape);

        headRoll.addChild(head);
        head.addChild(headRotate);
        headRotate.addChild(headTransform);
        headTransform.addChild(headShape);

        headRoll.addChild(leftEye);
        leftEye.addChild(leftEyeRotate);
        leftEyeRotate.addChild(leftEyeTransform);
        leftEyeTransform.addChild(leftEyeShape);

        headRoll.addChild(rightEye);
        rightEye.addChild(rightEyeRotate);
        rightEyeRotate.addChild(rightEyeTransform);
        rightEyeTransform.addChild(rightEyeShape);

        headRoll.addChild(leftEar);
        leftEar.addChild(leftEarRotate);
        leftEarRotate.addChild(leftEarTransform);
        leftEarTransform.addChild(leftEarShape);

        headRoll.addChild(rightEar);
        rightEar.addChild(rightEarRotate);
        rightEarRotate.addChild(rightEarTransform);
        rightEarTransform.addChild(rightEarShape);

        headRoll.addChild(leftEar);
        leftEar.addChild(leftEarRotate);
        leftEarRotate.addChild(leftEarTransform);
        leftEarTransform.addChild(leftEarShape);

        headRoll.addChild(antennaBottom);
        antennaBottom.addChild(antennaBottomRotate);
        antennaBottomRotate.addChild(antennaBottomTransform);
        antennaBottomTransform.addChild(antennaBottomShape);

        headRoll.addChild(antennaTop);
        antennaTop.addChild(antennaTopRotate);
        antennaTopRotate.addChild(antennaTopTransform);
        antennaTopTransform.addChild(antennaTopShape);
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
        bodyTranslate.addChild(headRotate);
        headRotate.addChild(head);
        head.addChild(headMoveTranslate);

        head.addChild(headTranslate);
        headTranslate.addChild(headTransform);
        headTransform.addChild(headShape);

        /*****  leftArm  ******/
        bodyTranslate.addChild(bodyRoll);
        bodyRoll.addChild(leftArm);
        leftArm.addChild(leftArmRotate);
        leftArmRotate.addChild(leftArmMoveTranslate);

        leftArm.addChild(leftArmTranslate);
        leftArmTranslate.addChild(leftArmTransform);
        leftArmTransform.addChild(leftArmShape);

        /*****  rightArm  ******/
        bodyTranslate.addChild(bodyRoll);
        bodyRoll.addChild(rightArm);
        rightArm.addChild(rightArmRotate);
        rightArmRotate.addChild(rightArmMoveTranslate);

        rightArm.addChild(rightArmTranslate);
        rightArmTranslate.addChild(rightArmTransform);
        rightArmTransform.addChild(rightArmShape);

        /*****  antennaBottom  ******/
        headRotate.addChild(antennaBottom);
        antennaBottom.addChild(antennaBottomRotate);

        antennaBottomRotate.addChild(antennaBottomTranslate);
        antennaBottomTranslate.addChild(antennaBottomTransform);
        antennaBottomTransform.addChild(antennaBottomShape);

        /*****  antennaTop  ******/
        headRotate.addChild(antennaTop);
        antennaTop.addChild(antennaTopRotate);

        antennaTopRotate.addChild(antennaTopTranslate);
        antennaTopTranslate.addChild(antennaTopTransform);
        antennaTopTransform.addChild(antennaTopShape);

        /***********  lefteye  ***************/
        headRotate.addChild(leftEye);
        leftEye.addChild(leftEyeRotate);

        leftEyeRotate.addChild(leftEyeTranslate);
        leftEyeTranslate.addChild(leftEyeTransform);
        leftEyeTransform.addChild(leftEyeShape);
        /***********  righteye  ***************/
        headRotate.addChild(rightEye);
        rightEye.addChild(rightEyeRotate);

        rightEyeRotate.addChild(rightEyeTranslate);
        rightEyeTranslate.addChild(rightEyeTransform);
        rightEyeTransform.addChild(rightEyeShape);

        /***********  leftear  ***************/
        headRotate.addChild(leftEar);
        leftEar.addChild(leftEarRotate);

        leftEarRotate.addChild(leftEarTranslate);
        leftEarTranslate.addChild(leftEarTransform);
        leftEarTransform.addChild(leftEarShape);
        /***********  rightear  ***************/
        headRotate.addChild(rightEar);
        rightEar.addChild(rightEarRotate);

        rightEarRotate.addChild(rightEarTranslate);
        rightEarTranslate.addChild(rightEarTransform);
        rightEarTransform.addChild(rightEarShape);
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
        float rotateAngleZ = 45f * (float)Math.sin(elapsedTime + 90f);
        float rotateAngleX = 45f * (float)Math.cos(elapsedTime + 90f);

        Mat4 rollMat = Mat4Transform.rotateAroundZ(rotateAngleZ);
        rollMat = Mat4.multiply(rollMat, Mat4Transform.rotateAroundX(rotateAngleX));
        bodyRoll.setTransform(rollMat);
        bodyRoll.update();

        rollMat = Mat4Transform.rotateAroundZ(rotateAngleZ * (1 / 5.1f));
        rollMat = Mat4.multiply(rollMat, Mat4Transform.rotateAroundX(rotateAngleX * (1 / 5.1f)));
        headRoll.setTransform(rollMat);
        headRoll.update();

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
