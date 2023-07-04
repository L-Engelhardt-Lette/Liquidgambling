package frontend.extraUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SettingUi extends JOptionPane {

    private static final int MIN_VOLUME = 0;
    private static final int MAX_VOLUME = 100;


    public SettingUi() {
        JFrame settingUiFrame = new JFrame("Setting UI");
        settingUiFrame.setUndecorated(true);
        settingUiFrame.setSize(800, 600);
        JLabel volumeLabel = new JLabel("Volume");
        JPanel settingUiBackground = new JPanel(new GridBagLayout()){
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



        JLabel settingUiLabel = new JLabel();
        JLabel settingUiResolution = new JLabel("Resolution");
        JPanel settingUiResolutionPanel = new JPanel();
        JButton settingUiFullHd = new JButton();
        JButton settingUiWqHd = new JButton();
        JButton settingUiBack = new JButton();

        settingUiResolutionPanel.add(settingUiFullHd);
        settingUiResolutionPanel.add(settingUiWqHd);

        settingUiResolutionPanel.setBackground(new Color(0,0,0,0));

        Icon FullHdIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/FullHd.png");
        Icon WQHDIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/WQHD.png");
        Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");

        settingUiFullHd.setIcon(FullHdIcon);
        settingUiFullHd.setOpaque(false);
        settingUiFullHd.setContentAreaFilled(false);
        settingUiFullHd.setBorderPainted(false);

        settingUiWqHd.setIcon(WQHDIcon);
        settingUiWqHd.setOpaque(false);
        settingUiWqHd.setContentAreaFilled(false);
        settingUiWqHd.setBorderPainted(false);

        settingUiBack.setIcon(backIcon);
        settingUiBack.setOpaque(false);
        settingUiBack.setContentAreaFilled(false);
        settingUiBack.setBorderPainted(false);

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font settingUiFont = font.deriveFont(40f);
            settingUiLabel.setFont(settingUiFont);
            volumeLabel.setFont(settingUiFont);
            settingUiResolution.setFont(settingUiFont);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }


        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, MIN_VOLUME, MAX_VOLUME, MAX_VOLUME);
        volumeSlider.setBackground(new Color(0,0,0,0));
        volumeSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            int volume = source.getValue();
            // TODO: Adjust the loudness of the background music based on the volume value
        });

        settingUiBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingUiFrame.dispose();
            }
        });


        settingUiBackground.add(settingUiResolution, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        settingUiBackground.add(settingUiResolutionPanel, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 0, 0, 0), 0, 0));
        settingUiBackground.add(volumeLabel, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));
        settingUiBackground.add(volumeSlider, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 0, 0, 0), 0, 0));
        settingUiBackground.add(settingUiBack, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(170, 0, 0, 0), 0, 0));

        settingUiFrame.setLocationRelativeTo(null);
        settingUiFrame.setContentPane(settingUiBackground);
        settingUiFrame.setVisible(true);



    }

    private static void showCreditsDialog(JFrame parentFrame) {
        JOptionPane.showMessageDialog(parentFrame, "This program was created by John Doe.", "Credits", JOptionPane.PLAIN_MESSAGE, new ImageIcon());

    }
}
