package GUI;

import javax.swing.JFrame;


public class Window01
{
    public void createWindow()
    {
        JFrame frame = new JFrame("Coin Flip Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Window01_Panel01 WPanel01 = new Window01_Panel01();
        frame.getContentPane().add(WPanel01);

        frame.pack();
        frame.setVisible(true);
    }
}
