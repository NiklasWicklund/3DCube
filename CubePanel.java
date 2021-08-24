import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CubePanel extends JPanel implements KeyListener {

    private Cube cube;
    private Timer timer;
    private boolean automaticSpin = false;
    public CubePanel (){
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //This panel now listens for key inputs.
        setFocusable(true);
        addKeyListener(this);

        //Create our cube object
        cube = new Cube();
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
