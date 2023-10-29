package model;

import com.jogamp.opengl.GL3;
import core.camera.Camera;
import core.Material;
import core.light.Light;
import core.light.SpotLight;
import core.shaders.LampShader;
import core.shaders.SpotLightShader;
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
public class SpotLightModel2 {

    private boolean animation = false;

    private double startTime;

    private double savedTime = 0;

    private Model sphere_body, sphere_head, sphere_lamp;

    private SpotLight spotLight;

    private Mat4 modelMatrix;

    private SGNode lightRoot;

    private TransformNode lightRootTranslate, lightHeadTranslate, lightLampTranslate;

    private float xPosition = 0;

    public SpotLightModel2(GL3 gl, Camera camera, Light light_1, Light light_2, SpotLight spotLight, SpotLightShader spotLightShader, Mat4 mat4, Mesh m, double startTime) {

        spotLight = new SpotLight(gl);
        spotLight.setCamera(camera);
        spotLight.setPosition(-6.5f, 7.3f, 0.0f);  // changing light position each frame

        this.startTime = startTime;

        int[] textureId0 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_1);
        int[] textureId1 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_3);

        /***********  sphere_body  ***************/
        Material bodyMaterial = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);
        sphere_body = new Model(gl, camera, light_1, light_2, spotLight, spotLightShader, bodyMaterial, modelMatrix, m, textureId0);

        /***********  sphere_head  ***************/

        Material headMaterial = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);
        sphere_head = new Model(gl, camera, light_1, light_2, spotLight, spotLightShader, headMaterial, modelMatrix, m, textureId0);

        /***********  sphere_lamp  ***************/

        Material lampMaterial = new Material(new Vec3(0.5f, 0.5f, 0.5f), new Vec3(0.8f, 0.8f, 0.8f), new Vec3(0.8f, 0.8f, 0.8f), 32.0f);
        lampMaterial.setBrightness(1.0f);
        LampShader lampShader = new LampShader(gl, Constant.LIGHT_GLSL_VS, Constant.LIGHT_GLSL_FS_2);
        sphere_lamp = new Model(gl, camera, light_1, light_2, spotLight, lampShader, lampMaterial, modelMatrix, m, textureId1);


        /***********  TransformNode  ***************/

        lightRoot = new NameNode("lightRoot");
        lightRootTranslate = new TransformNode("lightRoot transform",Mat4Transform.translate(xPosition,0,0));
        lightHeadTranslate = new TransformNode("lightHead transform",Mat4Transform.translate(xPosition,0,0));
        lightLampTranslate = new TransformNode("lightLamp transform",Mat4Transform.translate(xPosition,0,0));

        /***********  body  ***************/
        NameNode body = new NameNode("body");
        Mat4 m1 = Mat4Transform.translate(-7.5f, 4.0f, 0f);
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.30f, 8, 0.30f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode bodyTransform = new TransformNode("body transform", m1);
        ModelNode bodyShape = new ModelNode("spotLight(body)", sphere_body);

        /***********  head  ***************/
        NameNode head = new NameNode("head");
        m1 = Mat4Transform.translate(-7.5f, 8.0f, 0f);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(60));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.5f, 1.2f, 0.5f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode headTransform = new TransformNode("head transform", m1);
        ModelNode headShape = new ModelNode("spotLight(head)", sphere_head);

        /***********  sphere_lamp  ***************/
        NameNode lamp = new NameNode("lamp");
        m1 = Mat4Transform.translate(-6.7f, 7.5f, 0f);
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(60));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.3f, 0.70f, 0.3f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0,0,0));
        TransformNode lampTransform = new TransformNode("lamp transform", m1);
        ModelNode lampShape = new ModelNode("spotLight(lamp)", sphere_lamp);

        lightRoot.addChild(lightRootTranslate);
        lightRootTranslate.addChild(body);
        body.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);
        body.addChild(lightHeadTranslate);
        lightHeadTranslate.addChild(head);
        head.addChild(headTransform);
        headTransform.addChild(headShape);
        head.addChild(lightLampTranslate);
        lightLampTranslate.addChild(lamp);
        lamp.addChild(lampTransform);
        lampTransform.addChild(lampShape);
        lightRoot.update();
    }

    public void render(GL3 gl) {
        if (animation) rotateHead();
        lightRoot.draw(gl);
    }


    public void dispose(GL3 gl) {
        sphere_body.dispose(gl);
    }

    public void startAnimation() {
        animation = true;
        startTime = TimeUtils.getCurrentTime() - savedTime;
    }

    private void rotateHead() {
        double elapsedTime = getSeconds() - startTime;
        float rotateAngle = 20f * (float)Math.sin(elapsedTime);
        lightRootTranslate.setTransform(Mat4Transform.rotateAroundZ(rotateAngle));
        lightRootTranslate.update();

        float rotateAngleZ = 50f * (float)Math.sin(elapsedTime + 180f);
        float rotateAngleX = 50f * (float)Math.cos(elapsedTime + 90f);

        Mat4 rollMat = Mat4Transform.rotateAroundZ(rotateAngleZ);
        rollMat = Mat4.multiply(rollMat, Mat4Transform.rotateAroundX(rotateAngleX));
        lightHeadTranslate.setTransform(rollMat);
        lightHeadTranslate.update();

        Mat4 headRotateMat = Mat4Transform.rotateAroundZ(rotateAngleZ * (1 / 5.5f));
        headRotateMat = Mat4.multiply(headRotateMat, Mat4Transform.rotateAroundX(rotateAngleX * (1 / 5.5f)));
        lightHeadTranslate.setTransform(headRotateMat);
        lightHeadTranslate.update();
    }

    private double getSeconds() {
        return System.currentTimeMillis()/1000.0;
    }

    public void stopAnimation() {
        animation = false;
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        savedTime = elapsedTime;
    }

    public SpotLight getSpotLight() {
        return spotLight;
    }

    public void setSpotLight(SpotLight spotLight) {
        this.spotLight = spotLight;
    }

    public Model getSphere_lamp() {
        return sphere_lamp;
    }

    public void setSphere_lamp(Model sphere_lamp) {
        this.sphere_lamp = sphere_lamp;
    }
}
