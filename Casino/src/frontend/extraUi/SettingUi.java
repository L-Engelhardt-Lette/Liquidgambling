package frontend.extraUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingUi extends JOptionPane {

    private static final int MIN_VOLUME = 0;
    private static final int MAX_VOLUME = 100;


    public SettingUi() {
        JFrame startFrame = new JFrame("Setting UI");
        startFrame.setUndecorated(true);
        startFrame.setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu resolutionMenu = new JMenu("Resolution");

        JMenu creditsMenu = new JMenu("Credits");
        JMenuItem creditsItem = new JMenuItem("View Credits");
        creditsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCreditsDialog(startFrame);
            }
        });
        creditsMenu.add(creditsItem);

        menuBar.add(resolutionMenu);
        menuBar.add(creditsMenu);
        startFrame.setJMenuBar(menuBar);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#00d2ff"),
                        getWidth(), getHeight(), Color.decode("#3a7bd5"));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JLabel volumeLabel = new JLabel("Volume");
        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, MIN_VOLUME, MAX_VOLUME, MAX_VOLUME);
        volumeSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int volume = source.getValue();
            // TODO: Adjust the loudness of the background music based on the volume value
        });

        panel.add(volumeLabel);
        panel.add(volumeSlider);

        startFrame.setLocationRelativeTo(null);
        startFrame.setContentPane(panel);
        startFrame.setVisible(true);
    }

    private static void showCreditsDialog(JFrame parentFrame) {
        JOptionPane.showMessageDialog(parentFrame, "This program was created by John Doe.", "Credits", JOptionPane.PLAIN_MESSAGE, new ImageIcon());

    }
}
