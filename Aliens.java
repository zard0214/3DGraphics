import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import core.camera.Camera;
import core.camera.KeyboardAdapter;
import core.camera.MouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 25/10/2023 05:31
 */
public class Aliens extends JFrame implements ActionListener {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
    private GLCanvas canvas;
    private Aliens_GLEventListener backdropGlEventListener;
    private FPSAnimator animator;
    private JPanel southPanel;
    private JButton rockLeftbody, rockRightbody, rollLeftHead, rollRightHead, turnSpotlightOnOff, turnGenerallightOnOff, turnGenerallight2OnOff;
    private JSlider light1Slider, light2Slider;

    public static void main(String[] args) {
        Aliens aliens = new Aliens("COM6503 assignment1");
        //define the size of the main page
        aliens.getContentPane().setPreferredSize(dimension);
        aliens.pack();
        aliens.setVisible(true);
        aliens.canvas.requestFocusInWindow();
    }

    public Aliens(String textForTitleBar) {
        super(textForTitleBar);
        GLCapabilities glcapabilities = new GLCapabilities(GLProfile.get(GLProfile.GL3));
        canvas = new GLCanvas(glcapabilities);
        getContentPane().add(canvas, BorderLayout.CENTER);
        addMenuBar();
        addComponents();
        setComponentsListener();
        createCamera();
        initAnimator();
    }

    private void initAnimator() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                animator.stop();
                remove(canvas);
                dispose();
                System.exit(0);
            }
        });
        animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(this);
        fileMenu.add(quitItem);
        menuBar.add(fileMenu);
    }

    private void createCamera() {
        Camera camera = new Camera(Camera.DEFAULT_POSITION, Camera.DEFAULT_TARGET, Camera.DEFAULT_UP);
        backdropGlEventListener = new Aliens_GLEventListener(camera);
        canvas.addGLEventListener(backdropGlEventListener);
        canvas.addMouseMotionListener(new MouseAdapter(camera));
        canvas.addKeyListener(new KeyboardAdapter(camera));
    }

    private void addComponents() {
        southPanel = new JPanel();
        rockLeftbody = new JButton("Rock Left Body");
        rockRightbody = new JButton("Rock Right Body");
        rollLeftHead = new JButton("Roll Left Head");
        rollRightHead = new JButton("Roll Right Head");
        turnSpotlightOnOff = new JButton("Turn Spotlight On/Off");
        turnGenerallightOnOff = new JButton("Turn General Light On/Off");
        turnGenerallight2OnOff = new JButton("Turn General Light2 On/Off");
        southPanel.add(rockLeftbody);
        southPanel.add(rockRightbody);
        southPanel.add(rollLeftHead);
        southPanel.add(rollRightHead);
        southPanel.add(turnSpotlightOnOff);
        southPanel.add(turnGenerallightOnOff);
        southPanel.add(turnGenerallight2OnOff);
        getContentPane().add(southPanel, BorderLayout.SOUTH);
    }

    private void setComponentsListener() {
        rockLeftbody.addActionListener(this);
        rockRightbody.addActionListener(this);
        turnSpotlightOnOff.addActionListener(this);
        turnGenerallightOnOff.addActionListener(this);
        turnGenerallight2OnOff.addActionListener(this);
    }

    /**
     * ActionListener
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        float intensity = 0;
        switch (e.getActionCommand()){
            case "Rock Left Body":
                backdropGlEventListener.getSpotLightModel2().startAnimation();
                break;
            case "Rock Right Body":
                backdropGlEventListener.getSpotLightModel2().stopAnimation();
                break;
            case "Roll Left Head":
                break;
            case "Roll Right Head":
                break;
            case "Turn Spotlight On/Off":
                intensity = backdropGlEventListener.getSpotLight().getIntensity();
                if(intensity == 0){
                    backdropGlEventListener.getSpotLightModel().getSphere_lamp().getMaterial().setBrightness(1.0f);
                    backdropGlEventListener.getSpotLight().turnOnLight(true, 0.45f);
                }else {
                    backdropGlEventListener.getSpotLightModel().getSphere_lamp().getMaterial().setBrightness(0.5f);
                    backdropGlEventListener.getSpotLight().turnOnLight(false, 0);
                }
                break;
            case "Turn General Light On/Off":
                intensity = backdropGlEventListener.getLight_1().getIntensity();
                if(intensity == 0){
                    backdropGlEventListener.getLight_1().turnOnLight(true, 0.40f);
                }else {
                    backdropGlEventListener.getLight_1().turnOnLight(false, 0);
                }
                break;
            case "Turn General Light2 On/Off":
                intensity = backdropGlEventListener.getLight_2().getIntensity();
                if(intensity == 0){
                    backdropGlEventListener.getLight_2().turnOnLight(true, 0.80f);
                }else {
                    backdropGlEventListener.getLight_2().turnOnLight(false, 0);
                }
                break;
            case "Quit":
                System.exit(0);
                break;
        }
    }


}




