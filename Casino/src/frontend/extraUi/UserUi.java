package frontend.extraUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UserUi extends JOptionPane {
    private static final int FULL_HD_WIDTH = 1920;
    private static final int FULL_HD_HEIGHT = 1080;
    private static final int WQHD_WIDTH = 2560;
    private static final int WQHD_HEIGHT = 1440;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame userUiFrame = new JFrame("User Info");
        JButton userUiBack = new JButton();
        userUiFrame.setUndecorated(true);
        userUiFrame.setSize(800, 600);
        JLabel userInfoLabel = new JLabel();

        JPanel backgroundpanel = new JPanel() {
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

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font userUiFont = font.deriveFont(25f);
            userInfoLabel.setFont(userUiFont);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }


        Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
        userUiBack.setIcon(backIcon);
        userUiBack.setOpaque(false);
        userUiBack.setContentAreaFilled(false);
        userUiBack.setBorderPainted(false);


        String username = "JohnDoe";
        int age = 25;
        int pearlCount = 100;

        String message = "<html><b>Username:</b> " + username + "<br><b>Age:</b> " + age + "<br><b>Pearl Count:</b> " + pearlCount + "</html>";

        userInfoLabel.setText(message);
        userInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoLabel.setForeground(Color.WHITE);

        userUiBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                Window w = SwingUtilities.getWindowAncestor(userUiBack);

                if (w != null) {
                    w.setVisible(false);
                }
            }
        });

        backgroundpanel.setLayout(new GridBagLayout());

        backgroundpanel.add(userInfoLabel, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(180, 0, 0, 0), 0, 0));

        backgroundpanel.add(userUiBack, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(150, 0, 0, 0), 0, 0));


        userUiFrame.setLocationRelativeTo(null);
        userUiFrame.setContentPane(backgroundpanel);
        userUiFrame.setVisible(true);
    }

    private static Font loadCustomFont(String fontFilePath) {
        try (InputStream fontStream = UserUi.class.getResourceAsStream(fontFilePath)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return font.deriveFont(Font.PLAIN);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 12);
        }
    }
}
