package frontend.extraUi;

import stuff.Zentrale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class UserUi extends JOptionPane {
    public UserUi() {
        JFrame userUiFrame = new JFrame("User Info");
        JButton userUiBack = new JButton();
        userUiFrame.setUndecorated(true);
        userUiFrame.setSize(800, 600);

        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        userInfoPanel.setBackground(new Color(0,0,0,0));

        JLabel usernameLabel = new JLabel("Username: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel pearlcountLabel = new JLabel("Pearlcount: ");
        usernameLabel.setForeground(Color.white);
        ageLabel.setForeground(Color.white);
        pearlcountLabel.setForeground(Color.white);

        JLabel usernameLabelDisplay = new JLabel();
        JLabel ageLabelDisplay = new JLabel();
        JLabel pearlcountLabelDisplay = new JLabel();
        usernameLabelDisplay.setForeground(Color.white);
        ageLabelDisplay.setForeground(Color.white);
        pearlcountLabelDisplay.setForeground(Color.white);

        JButton logoutButton = new JButton();
        Icon logoutIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/logout.png");
        logoutButton.setIcon(logoutIcon);
        logoutButton.setOpaque(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);


        JPanel backgroundpanel = new JPanel(new GridBagLayout()) {
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
            usernameLabel.setFont(userUiFont);
            ageLabel.setFont(userUiFont);
            pearlcountLabel.setFont(userUiFont);
            usernameLabelDisplay.setFont(userUiFont);
            ageLabelDisplay.setFont(userUiFont);
            pearlcountLabelDisplay.setFont(userUiFont);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }

        Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
        userUiBack.setIcon(backIcon);
        userUiBack.setOpaque(false);
        userUiBack.setContentAreaFilled(false);
        userUiBack.setBorderPainted(false);

        usernameLabelDisplay.setText(Zentrale.getInstance().getActiveUser().getUserName());
        ageLabelDisplay.setText(Zentrale.getInstance().getActiveUser().getAge());
        pearlcountLabelDisplay.setText(Zentrale.getInstance().getActiveUser().getUser_Pearl_String());

        userInfoPanel.add(usernameLabel,new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(usernameLabelDisplay,new GridBagConstraints(2, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(ageLabel,new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(ageLabelDisplay,new GridBagConstraints(2, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(pearlcountLabel,new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(pearlcountLabelDisplay,new GridBagConstraints(2, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));


        userUiBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userUiFrame.dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userUiFrame.dispose();
                //TODO:Dispose mainFrame
                Zentrale.getInstance().setActiveUser(null);
            }
        });


        backgroundpanel.add(userInfoPanel, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(180, 0, 0, 0), 0, 0));
        backgroundpanel.add(userUiBack, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(150, 0, 0, 0), 0, 0));
        backgroundpanel.add(logoutButton,new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));


        userUiFrame.setLocationRelativeTo(null);
        userUiFrame.setContentPane(backgroundpanel);
        userUiFrame.setVisible(true);
    }
}
