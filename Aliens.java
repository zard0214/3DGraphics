import core.camera.KeyboardAdapter;
import core.camera.MouseAdapter;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import core.camera.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Zhecheng Zhao
 * @RegistrationNo 220186627
 * @date Created in 20/10/2023 15:41
 */
public class Aliens extends JFrame implements ActionListener {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    private static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
    private GLCanvas canvas;
    private Aliens_GLEventListener backdropGlEventListener;
    private FPSAnimator animator;
    private JPanel soutPanel;
    private JButton rockLeftbody, rockRightbody, rollLeftHead, rollRightHead, turnSpotlightOnOff, turnGenerallightOnOff, turnGenerallight2OnOff;

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

        addButton();
        setButtonListener();

        createCamera();

        initAnimator();

    }

    private void initAnimator() {
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


    private void addButton() {
        soutPanel = new JPanel();

        rockLeftbody = new JButton("Rock Left Body");
        rockRightbody = new JButton("Rock Right Body");

        rollLeftHead = new JButton("Roll Left Head");
        rollRightHead = new JButton("Roll Right Head");

        turnSpotlightOnOff = new JButton("Turn Spotlight On/Off");

        turnGenerallightOnOff = new JButton("Turn General Light On/Off");
        turnGenerallight2OnOff = new JButton("Turn General Light2 On/Off");

        soutPanel.add(rockLeftbody);
        soutPanel.add(rockRightbody);
        soutPanel.add(rollLeftHead);
        soutPanel.add(rollRightHead);
        soutPanel.add(turnSpotlightOnOff);
        soutPanel.add(turnGenerallightOnOff);
        soutPanel.add(turnGenerallight2OnOff);

        getContentPane().add(soutPanel, BorderLayout.SOUTH);
    }

    private void setButtonListener() {

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
                System.out.println("Rock Left Body");
                break;
            case "Rock Right Body":
                System.out.println("Rock Right Body");
                break;
            case "Roll Left Head":
                System.out.println("Roll Left Head");
                break;
            case "Roll Right Head":
                System.out.println("Roll Right Head");
                break;
            case "Turn Spotlight On/Off":
                System.out.println("Turn Spotlight On/Off");
                break;
            case "Turn General Light On/Off":
                System.out.println("Turn General Light On");
                intensity = backdropGlEventListener.getLight().getIntensity();
                if(intensity == 0){
                    backdropGlEventListener.getLight().setIntensity(0.15f);
                }else {
                    backdropGlEventListener.getLight().setIntensity(0.0f);
                }
                break;
            case "Turn General Light2 On/Off":
                System.out.println("Turn General Light2 On");
                intensity = backdropGlEventListener.getLight_2().getIntensity();
                if(intensity == 0){
                    backdropGlEventListener.getLight_2().setIntensity(0.45f);
                }else {
                    backdropGlEventListener.getLight_2().setIntensity(0.0f);
                }
                break;
            case "Quit":
                System.exit(0);
                break;
        }
    }
}
