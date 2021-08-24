
import javax.swing.*;
import java.awt.*;

public class CubeWindow extends JFrame{

    public CubeWindow(){

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension size = new Dimension(450,450);
        setSize(size);

        //Display the window
        //Makes sure the window is placed in the center of the screen
        setLocationRelativeTo(null);
        getContentPane().add(new CubePanel(size));
        //Shows the window
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CubeWindow::new);
    }
}