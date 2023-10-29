package model;

import com.jogamp.opengl.GL3;
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
public class AlienModel2 {

    private boolean animation = false;
    private boolean rock = false;
    private boolean roll = false;
    private double startTime;
    private double savedTime = 0;
    private Model sphere_body, sphere_head, sphere_lefteye, sphere_righteye, sphere_leftarm, sphere_rightarm;
    private Model sphere_antenna_bottom, sphere_antenna_top;
    private Model sphere_leftear, sphere_rightear;
    private Mat4 modelMatrix;
    private SGNode alienRoot;
    private TransformNode alienRootTranslate, alienRootMoveTranslate, alienRootRotate,
            alienRock, alienRoll,
            alienBodyTranslate, alienBodyMoveTranslate, alienBodyRotate,
            alienAntennaBottomTranslate, alienAntennaBottomMoveTranslate, alienAntennaBottomRotate,
            alienAntennaTopTranslate, alienAntennaTopMoveTranslate, alienAntennaTopRotate,
            alienLeftEyeTranslate, alienLeftEyeMoveTranslate, alienLeftEyeRotate,
            alienRightEyeTranslate, alienRightEyeMoveTranslate, alienRightEyeRotate,
            alienLeftEarTranslate, alienLeftEarMoveTranslate, alienLeftEarRotate,
            alienRightEarTranslate, alienRightEarMoveTranslate, alienRightEarRotate,
            alienLeftArmTranslate, alienLeftArmMoveTranslate, alienLeftArmRotate,
            alienRightArmTranslate, alienRightArmMoveTranslate, alienRightArmRotate,
            alienHeadTranslate, alienHeadMoveTranslate, alienHeadRotate;
//    private float xPosition = 0;
    public AlienModel2(GL3 gl, Camera camera, Light light_1, Light light_2, SpotLight spotLight, AlienShader alienShader, Material alienMaterial, Mat4 mat4, Mesh m, int[] alienTexture, Mat4 transition, float xPosition) {

        int[] alientextureId0 = TextureLibrary.loadTexture(gl, Constant.ALIEN_TEXTURE_GRAY);
        /***********  sphere_body  ***************/
        Material material = new Material(new Vec3(1.0f, 0.5f, 0.31f), new Vec3(1.0f, 0.5f, 0.31f), new Vec3(0.5f, 0.5f, 0.5f), 32.0f);
        modelMatrix = Mat4.multiply(Mat4Transform.scale(1.7f, 1.7f, 1.7f), Mat4Transform.translate(0f, 0.5f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_body = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_head  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(1.3f, 1.3f, 1.3f), Mat4Transform.translate(0f, 1.80f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_head = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_antenna_bottom  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.125f, 0.5f, 0.125f), Mat4Transform.translate(0f, 6.5f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_antenna_bottom = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_antenna_top  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.18f, 0.18f, 0.18f), Mat4Transform.translate(0f, 19.9f, 0f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_antenna_top = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_lefteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.18f, 0.18f, 0.18f), Mat4Transform.translate(-1.5f, 14.5f, 3.4f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_lefteye = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_righteye  ***************/
        modelMatrix = Mat4.multiply(Mat4Transform.scale(0.18f, 0.18f, 0.18f), Mat4Transform.translate(1.5f, 14.5f, 3.4f));

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_righteye = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_leftarm  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(45), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-1.15f, 1.2f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_leftarm = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_rightarm  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(-45), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(1.15f, 1.2f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_rightarm = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
        /***********  sphere_leftear  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(0), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(-0.65f, 3.0f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_leftear = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);
//        /***********  sphere_rightear  ***************/
        modelMatrix = Mat4Transform.scale(0.18f, 1.0f, 0.18f);
        modelMatrix = Mat4.multiply(Mat4Transform.rotateAroundZ(0), modelMatrix);
        modelMatrix = Mat4.multiply(Mat4Transform.translate(0.65f, 3.0f, 0.0f), modelMatrix);

        modelMatrix = Mat4.multiply(transition, modelMatrix);
        sphere_rightear = new Model(gl, camera, light_1, light_2, spotLight, alienShader, material, modelMatrix, m, alientextureId0);


        /***********  TransformNode  ***************/
        alienRock = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));
        alienRoll = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienRoot = new NameNode("alienRoot");
        alienRootTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienRootMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienRootRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienBodyTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienBodyMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienBodyRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienHeadTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienHeadMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienHeadRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienLeftArmTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienLeftArmMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienLeftArmRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienRightArmTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienRightArmMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienRightArmRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienLeftEarTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienLeftEarMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(0,0,0));
        alienLeftEarRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienRightEarTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienRightEarMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienRightEarRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienLeftEyeTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienLeftEyeMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienLeftEyeRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienRightEyeTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienRightEyeMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienRightEyeRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienAntennaBottomTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienAntennaBottomMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienAntennaBottomRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        alienAntennaTopTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienAntennaTopMoveTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienAntennaTopRotate = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));


        /***********  body  ***************/
        NameNode body = new NameNode("body");
        Mat4 m1 = Mat4Transform.translate(0,0.85f,0);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(1.7f, 1.7f, 1.7f));
        TransformNode bodyTransform = new TransformNode("body transform", m1);
        ModelNode bodyShape = new ModelNode("alienModel(body)", sphere_body);

        /***********  head  ***************/
        NameNode head = new NameNode("head");
        m1 = Mat4Transform.translate(0f, 2.30f, 0f);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(1.3f, 1.3f, 1.3f));
        TransformNode headTransform = new TransformNode("head transform", m1);
        ModelNode headShape = new ModelNode("alienModel(head)", sphere_head);
        /***********  antenna_bottom  ***************/
        NameNode antennaBottom = new NameNode("antenna_bottom");
        m1 = Mat4Transform.translate(0f, 3.20f, 0f);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.125f, 0.5f, 0.125f));
        TransformNode antennaBottomTransform = new TransformNode("antenna_bottom transform", m1);
        ModelNode antennaBottomShape = new ModelNode("alienModel(antenna_bottom)", sphere_antenna_bottom);
        /***********  sphere_antenna_top  ***************/
        NameNode antennaTop = new NameNode("antenna_top");
        m1 = Mat4Transform.translate(0f, 3.54f, 0f);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 0.18f, 0.18f));
        TransformNode antennaTopTransform = new TransformNode("antenna_top transform", m1);
        ModelNode antennaTopShape = new ModelNode("alienModel(antenna_top)", sphere_antenna_top);
        /***********  sphere_lefteye  ***************/
        NameNode leftEye = new NameNode("lefteye");
        m1 = Mat4Transform.translate(-0.3f,2.25f,0.60f);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 0.18f, 0.18f));
        TransformNode leftEyeTransform = new TransformNode("lefteye transform", m1);
        ModelNode leftEyeShape = new ModelNode("alienModel(lefteye)", sphere_lefteye);
        /***********  sphere_righteye  ***************/
        NameNode rightEye = new NameNode("righteye");
        m1 = Mat4Transform.translate(0.3f,2.25f,0.60f);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 0.18f, 0.18f));
        TransformNode rightEyeTransform = new TransformNode("righteye transform", m1);
        ModelNode rightEyeShape = new ModelNode("alienModel(righteye)", sphere_righteye);
        /***********  leftarm  ***************/
        NameNode leftArm = new NameNode("leftarm");
        m1 = Mat4Transform.translate(-1.15f, 1.17f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(45));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        TransformNode leftArmTransform = new TransformNode("leftarm transform", m1);
        ModelNode leftArmShape = new ModelNode("alienModel(leftarm)", sphere_leftarm);
        /***********  rightarm  ***************/
        NameNode rightArm = new NameNode("rightarm");
        m1 = Mat4Transform.translate(1.15f, 1.17f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(-45));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        TransformNode rightArmTransform = new TransformNode("rightarm transform", m1);
        ModelNode rightArmShape = new ModelNode("alienModel(rightarm)", sphere_rightarm);
        /***********  leftear  ***************/
        NameNode leftEar = new NameNode("leftear");
        m1 = Mat4Transform.translate(-0.65f, 2.80f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        TransformNode leftEarTransform = new TransformNode("leftear transform", m1);
        ModelNode leftEarShape = new ModelNode("alienModel(leftear)", sphere_leftear);
        /***********  rightear  ***************/
        NameNode rightEar = new NameNode("rightear");
        m1 = Mat4Transform.translate(0.65f, 2.80f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
//        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        TransformNode rightEarTransform = new TransformNode("rightear transform", m1);
        ModelNode rightEarShape = new ModelNode("alienModel(rightear)", sphere_rightear);

        alienRoot.addChild(alienRootTranslate);
        alienRootTranslate.addChild(alienRock);
        alienRock.addChild(body);

        body.addChild(alienBodyTranslate);
        alienBodyTranslate.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);

        alienBodyTranslate.addChild(alienRoll);
        alienRoll.addChild(head);
        head.addChild(alienHeadTranslate);
        alienHeadTranslate.addChild(alienHeadRotate);
        alienHeadRotate.addChild(headTransform);
        headTransform.addChild(headShape);

        alienHeadRotate.addChild(leftEye);
        leftEye.addChild(leftEyeTransform);
        leftEyeTransform.addChild(leftEyeShape);

        alienHeadRotate.addChild(rightEye);
        rightEye.addChild(rightEyeTransform);
        rightEyeTransform.addChild(rightEyeShape);

        alienHeadRotate.addChild(antennaBottom);
        antennaBottom.addChild(antennaBottomTransform);
        antennaBottomTransform.addChild(antennaBottomShape);

        alienHeadRotate.addChild(antennaTop);
        antennaTop.addChild(antennaTopTransform);
        antennaTopTransform.addChild(antennaTopShape);

        alienHeadRotate.addChild(leftEar);
        leftEar.addChild(leftEarTransform);
        leftEarTransform.addChild(leftEarShape);

        alienHeadRotate.addChild(rightEar);
        rightEar.addChild(rightEarTransform);
        rightEarTransform.addChild(rightEarShape);

        body.addChild(alienLeftArmTranslate);
        alienLeftArmTranslate.addChild(leftArm);
        leftArm.addChild(leftArmTransform);
        leftArmTransform.addChild(leftArmShape);

        body.addChild(alienRightArmTranslate);
        alienRightArmTranslate.addChild(rightArm);
        rightArm.addChild(rightArmTransform);
        rightArmTransform.addChild(rightArmShape);

        alienRoot.update();
    }


    public void render(GL3 gl) {
//        alienRootTranslate.setTransform(Mat4Transform.translate(-2.5f, 0.0f, 0.0f));
//        alienRootTranslate.update();
        if (rock) rockBody();
        if (roll) rollHeadX();
        alienRoot.draw(gl);
    }

    
    private void rollHeadX() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float rotateAngleX = 25f * (float)Math.cos(elapsedTime + 90f);

        Mat4 rollMat = Mat4Transform.rotateAroundY(rotateAngleX);
        alienRoll.setTransform(rollMat);
        alienRoll.update();

        rollMat = Mat4Transform.rotateAroundY(rotateAngleX * 0.8f);
        alienHeadRotate.setTransform(rollMat);
        alienHeadRotate.update();
    }

    private void rockBody() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float rotateAngle = 25f * (float)Math.sin(elapsedTime);
        alienRock.setTransform(Mat4Transform.rotateAroundX(rotateAngle));
        alienRock.update();
    }
    public void translateRoot(Mat4 mat4) {
        alienRootTranslate.setTransform(mat4);
        alienRootTranslate.update();
    }
    public void translateBody(Mat4 mat4) {
        alienBodyTranslate.setTransform(mat4);
        alienBodyTranslate.update();
    }


    public void dispose(GL3 gl) {
        sphere_body.dispose(gl);
        sphere_head.dispose(gl);
        sphere_antenna_bottom.dispose(gl);
        sphere_antenna_top.dispose(gl);
        sphere_lefteye.dispose(gl);
        sphere_righteye.dispose(gl);
        sphere_leftarm.dispose(gl);
        sphere_rightarm.dispose(gl);
        sphere_leftear.dispose(gl);
        sphere_rightear.dispose(gl);
    }


    public void startRock() {
        if(rock){
            rock = false;
            double elapsedTime = TimeUtils.getCurrentTime()-startTime;
            savedTime = elapsedTime;

        } else{
            rock = true;
            startTime = TimeUtils.getCurrentTime()-savedTime;
        }
    }

    public void startRoll() {
        if(roll){
            roll = false;
            double elapsedTime = TimeUtils.getCurrentTime()-startTime;
            savedTime = elapsedTime;

        } else{
            roll = true;
            startTime = TimeUtils.getCurrentTime()-savedTime;
        }
    }

    private void rotateRoot() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float rotateAngle = 45f * (float)Math.sin(elapsedTime);
        alienRootRotate.setTransform(Mat4Transform.rotateAroundY(rotateAngle));
        alienRootRotate.update();
    }

    public void translateBody(Vec3 v) {
        alienRootTranslate.setTransform(Mat4Transform.translate(v));
        alienRootTranslate.update();
    }

    public void stopAnimation() {
        animation = false;
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        savedTime = elapsedTime;
    }

    public void stopRock() {
        rock = false;
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        savedTime = elapsedTime;
    }

    public void stopRoll() {
        roll = false;
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        savedTime = elapsedTime;
    }

    public void reset() {
        stopAnimation();
        stopRock();
        stopRoll();
    }

}
