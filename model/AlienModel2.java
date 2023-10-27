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
    private TransformNode alienRootTranslate, alienHeadTranslate, alienAntennaBottomTranslate, alienAntennaTopTranslate,
            alienLefteyeTranslate, alienRighteyeTranslate, alienLeftarmTranslate, alienRightarmTranslate, alienLeftearTranslate, alienRightearTranslate;
    private float xPosition = 0;
    public AlienModel2(GL3 gl, Camera camera, Light light_1, Light light_2, SpotLight spotLight, AlienShader alienShader, Material alienMaterial, Mat4 mat4, Mesh m, int[] alienTexture, Mat4 transition) {

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

        alienRoot = new NameNode("alienRoot");
        alienRootTranslate = new TransformNode("alienRoot transform",Mat4Transform.translate(xPosition,0,0));
        alienHeadTranslate = new TransformNode("alienHead transform",Mat4Transform.translate(xPosition,0,0));
        alienAntennaBottomTranslate = new TransformNode("alienAntennaBottom transform",Mat4Transform.translate(xPosition,0,0));
        alienAntennaTopTranslate = new TransformNode("alienAntennaTop transform",Mat4Transform.translate(xPosition,0,0));
        alienLefteyeTranslate = new TransformNode("alienLefteye transform",Mat4Transform.translate(xPosition,0,0));
        alienRighteyeTranslate = new TransformNode("alienRighteye transform",Mat4Transform.translate(xPosition,0,0));
        alienLeftarmTranslate = new TransformNode("alienLeftarm transform",Mat4Transform.translate(xPosition,0,0));
        alienRightarmTranslate = new TransformNode("alienRightarm transform",Mat4Transform.translate(xPosition,0,0));
        alienLeftearTranslate = new TransformNode("alienLeftear transform",Mat4Transform.translate(xPosition,0,0));
        alienRightearTranslate = new TransformNode("alienRightear transform",Mat4Transform.translate(xPosition,0,0));

        /***********  body  ***************/
        NameNode body = new NameNode("body");
        Mat4 m1 = Mat4Transform.translate(0,0.85f,0);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(1.7f, 1.7f, 1.7f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode bodyTransform = new TransformNode("body transform", m1);
        ModelNode bodyShape = new ModelNode("alienModel(body)", sphere_body);

        /***********  head  ***************/
        NameNode head = new NameNode("head");
        m1 = Mat4Transform.translate(0f, 2.30f, 0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(1.3f, 1.3f, 1.3f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0.0f,0));
        TransformNode headTransform = new TransformNode("head transform", m1);
        ModelNode headShape = new ModelNode("alienModel(head)", sphere_head);
        /***********  antenna_bottom  ***************/
        NameNode antenna_bottom = new NameNode("antenna_bottom");
        m1 = Mat4Transform.translate(0f, 3.20f, 0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.125f, 0.5f, 0.125f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode antenna_bottomTransform = new TransformNode("antenna_bottom transform", m1);
        ModelNode antenna_bottomShape = new ModelNode("alienModel(antenna_bottom)", sphere_antenna_bottom);
        /***********  sphere_antenna_top  ***************/
        NameNode antenna_top = new NameNode("antenna_top");
        m1 = Mat4Transform.translate(0f, 3.54f, 0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 0.18f, 0.18f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0f, 0.0f, 0f));
        TransformNode antenna_topTransform = new TransformNode("antenna_top transform", m1);
        ModelNode antenna_topShape = new ModelNode("alienModel(antenna_top)", sphere_antenna_top);
        /***********  sphere_lefteye  ***************/
        NameNode lefteye = new NameNode("lefteye");
        m1 = Mat4Transform.translate(-0.3f,2.25f,0.60f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 0.18f, 0.18f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode lefteyeTransform = new TransformNode("lefteye transform", m1);
        ModelNode lefteyeShape = new ModelNode("alienModel(lefteye)", sphere_lefteye);
        /***********  sphere_righteye  ***************/
        NameNode righteye = new NameNode("righteye");
        m1 = Mat4Transform.translate(0.3f,2.25f,0.60f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 0.18f, 0.18f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode righteyeTransform = new TransformNode("righteye transform", m1);
        ModelNode righteyeShape = new ModelNode("alienModel(righteye)", sphere_righteye);
        /***********  leftarm  ***************/
        NameNode leftarm = new NameNode("leftarm");
        m1 = Mat4Transform.translate(-1.15f, 1.17f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(45));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode leftarmTransform = new TransformNode("leftarm transform", m1);
        ModelNode leftarmShape = new ModelNode("alienModel(leftarm)", sphere_leftarm);
        /***********  rightarm  ***************/
        NameNode rightarm = new NameNode("rightarm");
        m1 = Mat4Transform.translate(1.15f, 1.17f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(-45));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode rightarmTransform = new TransformNode("rightarm transform", m1);
        ModelNode rightarmShape = new ModelNode("alienModel(rightarm)", sphere_rightarm);
        /***********  leftear  ***************/
        NameNode leftear = new NameNode("leftear");
        m1 = Mat4Transform.translate(-0.65f, 2.80f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode leftearTransform = new TransformNode("leftear transform", m1);
        ModelNode leftearShape = new ModelNode("alienModel(leftear)", sphere_leftear);
        /***********  rightear  ***************/
        NameNode rightear = new NameNode("rightear");
        m1 = Mat4Transform.translate(0.65f, 2.80f, 0.0f);
        m1 = Mat4.multiply(m1, transition);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(0));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.18f, 1.0f, 0.18f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode rightearTransform = new TransformNode("rightear transform", m1);
        ModelNode rightearShape = new ModelNode("alienModel(rightear)", sphere_rightear);


        alienRoot.addChild(alienRootTranslate);
        alienRootTranslate.addChild(body);
        body.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);

        body.addChild(alienHeadTranslate);
        alienHeadTranslate.addChild(head);
        head.addChild(headTransform);
        headTransform.addChild(headShape);

        head.addChild(alienAntennaBottomTranslate);
        alienAntennaBottomTranslate.addChild(antenna_bottom);
        antenna_bottom.addChild(antenna_bottomTransform);
        antenna_bottomTransform.addChild(antenna_bottomShape);

        head.addChild(alienAntennaTopTranslate);
        alienAntennaTopTranslate.addChild(antenna_top);
        antenna_top.addChild(antenna_topTransform);
        antenna_topTransform.addChild(antenna_topShape);

        head.addChild(alienLefteyeTranslate);
        alienLefteyeTranslate.addChild(lefteye);
        lefteye.addChild(lefteyeTransform);
        lefteyeTransform.addChild(lefteyeShape);

        head.addChild(alienRighteyeTranslate);
        alienRighteyeTranslate.addChild(righteye);
        righteye.addChild(righteyeTransform);
        righteyeTransform.addChild(righteyeShape);

        head.addChild(alienLeftearTranslate);
        alienLeftearTranslate.addChild(leftear);
        leftear.addChild(leftearTransform);
        leftearTransform.addChild(leftearShape);

        head.addChild(alienRightearTranslate);
        alienRightearTranslate.addChild(rightear);
        rightear.addChild(rightearTransform);
        rightearTransform.addChild(rightearShape);

        body.addChild(alienLeftarmTranslate);
        alienLeftarmTranslate.addChild(leftarm);
        leftarm.addChild(leftarmTransform);
        leftarmTransform.addChild(leftarmShape);

        body.addChild(alienRightarmTranslate);
        alienRightarmTranslate.addChild(rightarm);
        rightarm.addChild(rightarmTransform);
        rightarmTransform.addChild(rightarmShape);

        alienRoot.update();
    }


    public void render(GL3 gl) {
        if (animation) rotateHead();
        if (rock) rockBody();
        if (roll) rollHead();
        alienRoot.draw(gl);
    }

    private void rollHead() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float rotateAngle = 45f * (float)Math.sin(elapsedTime);
        alienHeadTranslate.setTransform(Mat4Transform.rotateAroundX(rotateAngle));
        alienHeadTranslate.update();
    }

    private void rockBody() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float rotateAngle = 45f * (float)Math.sin(elapsedTime);
        alienRootTranslate.setTransform(Mat4Transform.rotateAroundX(rotateAngle));
        alienRootTranslate.update();
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

    public void startAnimation() {
        animation = true;
        startTime = TimeUtils.getCurrentTime() - savedTime;
    }

    public void startRock() {
        rock = true;
        startTime = TimeUtils.getCurrentTime() - savedTime;
    }

    public void startRoll() {
        roll = true;
        startTime = TimeUtils.getCurrentTime() - savedTime;
    }

    private void rotateHead() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float rotateAngle = 45f * (float)Math.sin(elapsedTime);
        alienRootTranslate.setTransform(Mat4Transform.rotateAroundX(rotateAngle));
        alienRootTranslate.update();
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
