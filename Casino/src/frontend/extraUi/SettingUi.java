package frontend.extraUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class SettingUi extends JOptionPane {

    private static final int MIN_VOLUME = 0;
    private static final int MAX_VOLUME = 100;


    public SettingUi() {
        // Create the main frame for the settings UI
        JFrame settingUiFrame = new JFrame("Setting UI");
        settingUiFrame.setUndecorated(true);
        settingUiFrame.setSize(800, 600);

        // Create the volume label
        JLabel volumeLabel = new JLabel("Volume");
        volumeLabel.setForeground(Color.white);

        // Create the background panel for the settings UI
        JPanel settingUiBackground = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Paint a gradient background
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#00d2ff"),
                        getWidth(), getHeight(), Color.decode("#3a7bd5"));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Create labels for different settings
        JLabel settingUiLabel = new JLabel("SETTINGS");
        settingUiLabel.setForeground(Color.white);
        JLabel settingUiResolution = new JLabel("Resolution");
        settingUiResolution.setForeground(Color.white);

        // Create a panel for resolution settings
        JPanel settingUiResolutionPanel = new JPanel();

        // Create labels for resolution options
        JLabel settingUiFullHd = new JLabel();
        JLabel settingUiWqHd = new JLabel();

        //Create labels for Back options
        JLabel settingUiBack = new JLabel();

        // Add resolution labels to the panel
        settingUiResolutionPanel.add(settingUiFullHd);
        settingUiResolutionPanel.add(settingUiWqHd);

        // Set background color of the resolution panel to transparent
        settingUiResolutionPanel.setBackground(new Color(0,0,0,0));

        // Load icons for resolution options and back button
        Icon FullHdIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/FullHd.png");
        Icon WQHDIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/WQHD.png");
        Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");

        // Set icons for resolution labels and back button
        settingUiFullHd.setIcon(FullHdIcon);
        settingUiFullHd.setOpaque(false);

        settingUiWqHd.setIcon(WQHDIcon);
        settingUiWqHd.setOpaque(false);

        settingUiBack.setIcon(backIcon);
        settingUiBack.setOpaque(false);

        try {
            // Load a custom font for the labels
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font settingUiFont = font.deriveFont(40f);
            settingUiLabel.setFont(settingUiFont);
            volumeLabel.setFont(settingUiFont);
            settingUiResolution.setFont(settingUiFont);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }

        // Create a volume slider
        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, MIN_VOLUME, MAX_VOLUME, MAX_VOLUME);
        volumeSlider.setBackground(new Color(0,0,0,0));
        volumeSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int volume = source.getValue();
            // TODO: Adjust the loudness of the background music based on the volume value
        });

        // Add a mouse listener to the back button to close the settings UI
        settingUiBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                settingUiFrame.dispose();
            }
        });

        // Add components to the background panel using GridBagLayout
        settingUiBackground.add(settingUiResolution, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        settingUiBackground.add(settingUiResolutionPanel, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 0, 0, 0), 0, 0));
        settingUiBackground.add(volumeLabel, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        settingUiBackground.add(volumeSlider, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 0, 0, 0), 0, 0));
        settingUiBackground.add(settingUiBack, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(170, 0, 0, 0), 0, 0));

        // Configure the main frame and make it visible
        settingUiFrame.setLocationRelativeTo(null);
        settingUiFrame.setContentPane(settingUiBackground);
        settingUiFrame.setVisible(true);
    }

    private static void showCreditsDialog(JFrame parentFrame) {
        // Show a dialog box with credits information
        JOptionPane.showMessageDialog(parentFrame, "This program was created by John Doe.", "Credits", JOptionPane.PLAIN_MESSAGE, new ImageIcon());
    }
}
