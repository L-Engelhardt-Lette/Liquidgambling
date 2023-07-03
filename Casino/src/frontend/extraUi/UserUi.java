package frontend.extraUi;

import javax.swing.*;
import java.awt.*;
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
        JFrame startFrame = new JFrame("User Info");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(FULL_HD_WIDTH, FULL_HD_HEIGHT);

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

        // Load the custom font
        Font customFont = loadCustomFont("/frontend/font/Watermelon.ttf");

        String username = "JohnDoe";
        int age = 25;
        int pearlCount = 100;

        String message = "<html><b>Username:</b> " + username + "<br><b>Age:</b> " + age + "<br><b>Pearl Count:</b> " + pearlCount + "</html>";

        JLabel userInfoLabel = new JLabel(message);
        userInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userInfoLabel.setFont(customFont.deriveFont(Font.BOLD, 18));
        userInfoLabel.setForeground(Color.WHITE);

        panel.setLayout(new BorderLayout());
        panel.add(userInfoLabel, BorderLayout.CENTER);

        startFrame.setContentPane(panel);
        startFrame.setVisible(true);
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
