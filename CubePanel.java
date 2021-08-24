import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CubePanel extends JPanel implements KeyListener {

    private Cube cube;
    private Timer timer;
    private boolean automaticSpin = false;
    private JLabel cubeInfoLabel;
    public CubePanel (Dimension frameSize){
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //This panel now listens for key inputs.
        setFocusable(true);
        addKeyListener(this);

        //Create our cube object
        cube = new Cube((int)frameSize.getWidth()/2,(int)frameSize.getHeight()/2);
        cubeInfoLabel = new JLabel("Side length of Cube (-: 1, +: 2): " + cube.getSideLength() + "px \n Fill sides (Toggle: D): " + cube.getDrawSides());
        cubeInfoLabel.setFont(new Font("Verdana",1,10));
        Dimension size = cubeInfoLabel.getPreferredSize();
        cubeInfoLabel.setBounds(0, 0, size.width, size.height);
        add(cubeInfoLabel);

        int delay = 30; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (automaticSpin)
                    cube.spin();
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        cube.render(g);
        cubeInfoLabel.setText("Side length of Cube (-: 1, +: 2): " + cube.getSideLength() + "px \n\r Fill sides (Toggle: D): " + cube.getDrawSides());


        
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_Y:
                cube.spinY(5);
                break;
            case KeyEvent.VK_X:
                cube.spinX(5);
                break;
            case KeyEvent.VK_Z:
                cube.spinZ(5);
                break;
            case KeyEvent.VK_P:
                automaticSpin = !automaticSpin;
                break;
            case KeyEvent.VK_1:
                cube.changeSideLength(-2);
                break;
            case KeyEvent.VK_2:
                cube.changeSideLength(2);
                break;
            case KeyEvent.VK_D:
                cube.toggleDrawSides();
                break;
            default:
                break;
        }
        
        // TODO Auto-generated method stub

        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
