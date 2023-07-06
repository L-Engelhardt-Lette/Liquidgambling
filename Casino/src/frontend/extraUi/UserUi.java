package frontend.extraUi;

import Backend.Zentrale;
import frontend.Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class UserUi extends JOptionPane {

    public UserUi(){

        //Erstellung der Elemente
        JFrame userUiFrame = new JFrame("User Info");
        userUiFrame.setUndecorated(true);
        userUiFrame.setSize(800,600);

        JPanel backgroundpanel = new JPanel(new GridBagLayout()){
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


        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        userInfoPanel.setBackground(new Color(0,0,0,0));

        JLabel userInfoNameLabel = new JLabel("Username: ");
        userInfoNameLabel.setForeground(Color.white);
        JLabel userInfoNameLabelDisplay = new JLabel();
        userInfoNameLabelDisplay.setForeground(Color.white);
        JLabel userInfoAgeLabel = new JLabel("Age: ");
        userInfoAgeLabel.setForeground(Color.white);
        JLabel userInfoAgeLabelDisplay = new JLabel();
        userInfoAgeLabelDisplay.setForeground(Color.white);
        JLabel userInfoPearlLabel = new JLabel("Pearl-count: ");
        userInfoPearlLabel.setForeground(Color.white);
        JLabel userInfoPearlLabelDisplay = new JLabel();
        userInfoPearlLabelDisplay.setForeground(Color.white);


        JButton userUiBack = new JButton();
        JButton userUiLogout = new JButton();


        //Font erstellung
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font userUiFont = font.deriveFont(25f);
            userInfoPanel.setFont(userUiFont);
            userInfoNameLabel.setFont(userUiFont);
            userInfoNameLabelDisplay.setFont(userUiFont);
            userInfoAgeLabel.setFont(userUiFont);
            userInfoAgeLabelDisplay.setFont(userUiFont);
            userInfoPearlLabel.setFont(userUiFont);
            userInfoPearlLabelDisplay.setFont(userUiFont);
        }catch (FontFormatException | IOException a){
            a.printStackTrace();
        }

        Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
        userUiBack.setIcon(backIcon);
        userUiBack.setOpaque(false);
        userUiBack.setContentAreaFilled(false);
        userUiBack.setBorderPainted(false);

        Icon logoutIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/logout.png");
        userUiLogout.setIcon(logoutIcon);
        userUiLogout.setOpaque(false);
        userUiLogout.setContentAreaFilled(false);
        userUiLogout.setBorderPainted(false);

        userInfoPanel.add(userInfoNameLabel, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(userInfoNameLabelDisplay, new GridBagConstraints(2, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));
        userInfoPanel.add(userInfoAgeLabel, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(userInfoAgeLabelDisplay, new GridBagConstraints(2, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));
        userInfoPanel.add(userInfoPearlLabel, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(userInfoPearlLabelDisplay, new GridBagConstraints(2, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));



        backgroundpanel.add(userInfoPanel, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(150, 0, 0, 0), 0, 0));
        backgroundpanel.add(userUiBack, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(150, 0, 0, 0), 0, 0));
        backgroundpanel.add(userUiLogout, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));


        userUiFrame.setLocationRelativeTo(null);
        userUiFrame.setContentPane(backgroundpanel);
        userUiFrame.setVisible(true);


        userUiBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userUiFrame.dispose();
            }
        });
        userUiLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Zentrale.getInstance().setActiveUser(null);
                userUiFrame.dispose();
            }
        });
    }
}
