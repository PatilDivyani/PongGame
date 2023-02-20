import javax.swing.*;
import java.awt.*;

public class GameFrame {
    JFrame frame;
    Panel panel;

    GameFrame(){
        frame = new JFrame("Pong Game");
       // frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        panel = new Panel();
       // System.out.println(Color.black);
        frame.setBackground(Color.GREEN);
        //panel.getContentPane().setBackground(Color.black);
        frame.add(panel);
       // panel.setBounds(0,0,1000,555);


        //frame.setSize(1010,560);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.isResizable();
    }
}
