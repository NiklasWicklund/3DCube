
import javax.swing.*;

public class CubeWindow extends JFrame{

    public CubeWindow(){

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,100,450,450);

        //Display the window
        //Makes sure the window is placed in the center of the screen
        setLocationRelativeTo(null);
        getContentPane().add(new CubePanel());
        //Shows the window
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CubeWindow::new);
    }
}