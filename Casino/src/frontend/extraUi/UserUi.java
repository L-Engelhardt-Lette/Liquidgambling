package frontend.extraUi;

import Backend.Datenbank.Persistenz;
import Backend.User;
import Backend.Zentrale;
import frontend.LoginUi;
import frontend.Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserUi extends JOptionPane {

    public UserUi(Ui ui){

        // Erstellung der UI-Elemente
        JFrame userUiFrame = new JFrame("User Info");
        userUiFrame.setUndecorated(true);
        userUiFrame.setSize(800,600);

        // Hintergrundpanel mit Gradienten-Hintergrund
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

        // UI-Elemente für Benutzerinformationen
        JLabel userInfoNameLabel = new JLabel("Username: ");
        userInfoNameLabel.setForeground(Color.white);
        JLabel userInfoNameLabelDisplay = new JLabel(Zentrale.getInstance().getActiveUser().getUserName());
        userInfoNameLabelDisplay.setForeground(Color.white);
        JLabel userInfoAgeLabel = new JLabel("Age: ");
        userInfoAgeLabel.setForeground(Color.white);
        JLabel userInfoAgeLabelDisplay = new JLabel(Zentrale.getInstance().getActiveUser().getAge());
        userInfoAgeLabelDisplay.setForeground(Color.white);
        JLabel userInfoPearlLabel = new JLabel("Pearl-count: ");
        userInfoPearlLabel.setForeground(Color.white);
        JLabel userInfoPearlLabelDisplay = new JLabel(Zentrale.getInstance().getActiveUser().getUser_Pearl_String());
        userInfoPearlLabelDisplay.setForeground(Color.white);

        JButton userUiBack = new JButton();
        JButton userUiLogout = new JButton();

        // Schriftart erstellen
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
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }

        // Hintergrundbilder für Buttons
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

        // UI-Elemente zum Benutzerinformationspanel hinzufügen
        userInfoPanel.add(userInfoNameLabel, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(userInfoNameLabelDisplay, new GridBagConstraints(2, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));
        userInfoPanel.add(userInfoAgeLabel, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(userInfoAgeLabelDisplay, new GridBagConstraints(2, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));
        userInfoPanel.add(userInfoPearlLabel, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        userInfoPanel.add(userInfoPearlLabelDisplay, new GridBagConstraints(2, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));

        // UI-Elemente zum Hintergrundpanel hinzufügen
        backgroundpanel.add(userInfoPanel, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(150, 0, 0, 0), 0, 0));
        backgroundpanel.add(userUiBack, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(150, 0, 0, 0), 0, 0));
        backgroundpanel.add(userUiLogout, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        // Fenstereigenschaften festlegen und sichtbar machen
        userUiFrame.setLocationRelativeTo(null);
        userUiFrame.setContentPane(backgroundpanel);
        userUiFrame.setVisible(true);

        // Aktionen für Button-Klicks festlegen
        userUiBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userUiFrame.dispose();
            }
        });

        userUiLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> reading = Persistenz.reading();
                User activeUser = Zentrale.getInstance().getActiveUser();

                // Aktualisieren der Benutzerdaten in der Persistenz
                for (User user : reading) {
                    if (user.getUserName().equals(activeUser.getUserName()) && user.getPassword().equals(activeUser.getPassword())){
                        user.setUser_Pearl(activeUser.getUser_Pearl());
                    }
                }
                Persistenz.writing(reading);

                // Aktiven Benutzer löschen und das Fenster schließen
                Zentrale.getInstance().setActiveUser(null);


                // Anmeldeseite anzeigen
                userUiFrame.dispose();
                ui.unsichtbarMachen();
                LoginUi loginUi = new LoginUi();

            }
        });

    }
}
