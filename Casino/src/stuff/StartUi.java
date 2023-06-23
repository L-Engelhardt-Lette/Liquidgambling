/*/*import javax.swing.*;
import java.awt.*;

public class StartUi {
    class Animatedbackground{
            JFrame frame = new JFrame("Simple UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

            // Create a JLabel and set its text
            JLabel label = new JLabel("Top Left");

            // Create a JButton and set its text
            JButton button = new JButton("Top Right");

            // Set the layout of the frame to BorderLayout
        frame.setLayout(new BorderLayout());

            // Add the label to the top-left corner
        frame.add(label, BorderLayout.NORTH);

            // Add the button to the top-right corner
        frame.add(button, BorderLayout.NORTH);

            // Display the JFrame
        frame.setVisible(true);

        }
    }




}*/
