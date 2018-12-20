package DaGame;

import DaGame.GamePanel;

import javax.swing.*;

import java.awt.*;


public class Window extends JFrame {



    public Window(){
        setTitle("Detective Game -to be named");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280,720));
        setIgnoreRepaint(false);
        setBackground(Color.blue);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
