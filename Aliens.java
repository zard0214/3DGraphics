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
    private JButton rockLeftbody, rockRightbody, turnSpotlightOn, turnSpotlightOff, turnGenerallightOn, turnGenerallightOff;

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

        turnSpotlightOn = new JButton("Turn Spotlight On");
        turnSpotlightOff = new JButton("Turn Spotlight Off");

        turnGenerallightOn = new JButton("Turn General Light On");
        turnGenerallightOff = new JButton("Turn General Light Off");

        soutPanel.add(rockLeftbody);
        soutPanel.add(rockRightbody);
        soutPanel.add(turnSpotlightOn);
        soutPanel.add(turnSpotlightOff);
        soutPanel.add(turnGenerallightOn);
        soutPanel.add(turnGenerallightOff);

        getContentPane().add(soutPanel, BorderLayout.SOUTH);
    }

    private void setButtonListener() {

        rockLeftbody.addActionListener(this);
        rockRightbody.addActionListener(this);
        turnSpotlightOn.addActionListener(this);
        turnSpotlightOff.addActionListener(this);
        turnGenerallightOn.addActionListener(this);
        turnGenerallightOff.addActionListener(this);

    }

    /**
     * ActionListener
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Rock Left Body":
                System.out.println("Rock Left Body");
                Context.leftAlien.rockBody();
                break;
            case "Rock Right Body":
                System.out.println("Rock Right Body");
                Context.rightAlien.rockBody();
                break;
            case "Turn Spotlight On":
                System.out.println("Turn Spotlight On");
                Context.spotlight.turnOn();
                break;
            case "Turn Spotlight Off":
                System.out.println("Turn Spotlight Off");
                Context.spotlight.turnOff();
                break;
            case "Turn General Light On":
                System.out.println("Turn General Light On");
                break;
            case "Turn General Light Off":
                System.out.println("Turn General Light Off");
                break;
            case "Quit":
                System.exit(0);
                break;
        }
    }
}
