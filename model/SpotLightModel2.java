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
import gmaths.Vec4;
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
public class SpotLightModel2 extends Model{

    private boolean animation = true;

    private double startTime;

    private double savedTime = 0;

    private Model sphere_body, sphere_head, sphere_lamp;

    private SpotLight spotLight;

    private Mat4 modelMatrix;

    private SGNode lightRoot;

    private TransformNode lightRock, lightRoll, lightTranslate, lampTransform;

    private float xPosition = 0;


    public SpotLightModel2(GL3 gl, Camera camera, Light light_1, Light light_2, SpotLight spotLight, SpotLightShader spotLightShader, Mat4 modelMatrix, Mesh m, double startTime) {
        super(gl, modelMatrix);

        this.spotLight = new SpotLight(gl, new Vec3(-6.5f, 7.3f, 0.0f));
        this.spotLight.setCamera(camera);
        spotLight = this.spotLight;

        this.startTime = startTime;

        int[] textureId0 = TextureLibrary.loadTexture(gl, Constant.SPOTLIGHT_TEXTURE_1);
        int[] textureId1 = TextureLibrary.loadTexture(gl, Constant.TEXTURE_YELLOW);

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
        lightRoot = new NameNode("root");

        lightRock = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));
        lightRoll = new TransformNode("alienRoot transform",Mat4Transform.rotateAroundY(0));

        lightTranslate = new TransformNode("robot transform", Mat4Transform.translate(-7.5f, 4f, 0));
        NameNode body = new NameNode("body");
        Mat4 m1 = Mat4Transform.scale(0.3f, 8.0f, 0.3f);
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0f, 0, 0));
        TransformNode bodyTransform = new TransformNode("body transform", m1);
        ModelNode bodyShape = new ModelNode("Cube(body)", sphere_body);

        NameNode head = new NameNode("head");
        m1 = new Mat4(1);
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0.65f, 3.7f, 0.0f));
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(60));
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundY(45));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.4f, 1.1f, 0.4f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0, 0.5f, 0));
        TransformNode headTransform = new TransformNode("head transform", m1);
        ModelNode headShape = new ModelNode("Sphere(head)", sphere_head);

        NameNode lamp = new NameNode("lamp");
        m1 = new Mat4(1);
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0.95f, 3.5f, 0.0f));
        m1 = Mat4.multiply(m1, Mat4Transform.rotateAroundZ(60));
        m1 = Mat4.multiply(m1, Mat4Transform.scale(0.20f, 0.40f, 0.20f));
        m1 = Mat4.multiply(m1, Mat4Transform.translate(0, 0.5f, 0));
        lampTransform = new TransformNode("lamp scale", m1);
        ModelNode lampShape = new ModelNode("Cube(left arm)", sphere_lamp);


        lightRoot.addChild(lightTranslate);
        lightTranslate.addChild(body);
        body.addChild(bodyTransform);
        bodyTransform.addChild(bodyShape);

        lightTranslate.addChild(lightRoll);
        lightRoll.addChild(head);

        head.addChild(headTransform);
        headTransform.addChild(headShape);

        lightRoll.addChild(lamp);

        lamp.addChild(lampTransform);
        lampTransform.addChild(lampShape);

        lightRoot.update();
    }

    public void render(GL3 gl) {
        if(animation) rotateHead();
        lightRoot.draw(gl);
        this.spotLight.render(gl);
    }

    private void rotateHead() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float rotateAngle = 48f * (float) Math.sin(elapsedTime);
        Mat4 lampTrans = lampTransform.getTransform();

        lightRoll.setTransform(Mat4Transform.rotateAroundY(rotateAngle));
        lightRoll.update();

        Mat4 newLampTrans = Mat4.multiply(Mat4Transform.translate(-7.2f, 3.7f, 3.8f), lampTrans);
        newLampTrans = Mat4.multiply(lightRoll.getTransform(), newLampTrans);
        Vec4 vector1 = Mat4.multiply(newLampTrans, new Vec4(0,0,0,1));

        spotLight.setPosition(vector1.x, vector1.y, -vector1.z);

//        x:-1.2249227  -6.5f;
//        y:3.0929232  7.3f;
//        z:0.76  3.8f
    }


    public Vec3 getLightPosition() {
        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
        float x = -6.5f;
        float y = 7.3f;
        float z = 3.8f * (float) (Math.sin(Math.toRadians(elapsedTime * 58))) * -1;
        return new Vec3(x, y, z);
    }

//    private void rotateHead() {
//        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
//        float rotateAngle = 48f * (float) Math.sin(elapsedTime);
//        lightRoll.setTransform(Mat4Transform.rotateAroundY(rotateAngle));
//        lightRoll.update();
//
//        spotLight.setPosition(getLightPosition());
//    }
//
//    public Vec3 getLightPosition() {
//        double elapsedTime = TimeUtils.getCurrentTime() - startTime;
//        float x = -6.5f;
//        float y = 7.3f;
//        float z = 3.8f * (float) (Math.sin(Math.toRadians(elapsedTime * 58))) * -1;
//        return new Vec3(x, y, z);
//    }

    public void dispose(GL3 gl) {
        sphere_body.dispose(gl);
    }

    public void startAnimation() {
        animation = true;
        startTime = TimeUtils.getCurrentTime() - savedTime;
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

    public boolean isAnimation() {
        return animation;
    }
}
