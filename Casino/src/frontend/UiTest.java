package frontend;

import Datenbank.Persistenz;
import stuff.User;
import stuff.Zentrale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UiTest {
    public static void main(String[] args) {

        // Erstellung von den Login Ui Elementen
        JFrame loginUi = new JFrame();
        JPanel loginBackground = new JPanel();
        JPanel loginMain = new JPanel(new BorderLayout());
        JPanel loginLogoPanel = new JPanel();
        JPanel loginCenterPanel = new JPanel(new GridBagLayout());
        JButton loginButton = new JButton();
        JButton loginRegister = new JButton();


        JTextField loginUsername = new JTextField("LEDEAdmin");
        loginUsername.setPreferredSize(new Dimension(200, 30));
        loginUsername.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginUsername.setText("");
            }
        });
        JTextField loginPassword = new JTextField("qwertz");
        loginPassword.setPreferredSize(new Dimension(200, 30));
        loginPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginPassword.setText("");
            }
        });

        // TODO: LOGO hinzufügen

        // Icon für den Login Button
        Icon loginicon = new ImageIcon("Casino/src/frontend/img/loginButton/login256.png");
        loginButton.setIcon(loginicon);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);

        // Icon für den Register Button
        Icon registericon = new ImageIcon("Casino/src/frontend/img/loginButton/register128.png");
        loginRegister.setIcon(registericon);
        loginRegister.setOpaque(false);
        loginRegister.setContentAreaFilled(false);
        loginRegister.setBorderPainted(false);

        // Vorgaben für das Login Fenster
        loginUi.setSize(800, 600);
        loginUi.setResizable(false);
        loginUi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginUi.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = loginUi.getSize();
        int windowX = Math.max(0, (screenSize.width - windowSize.width) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);
        loginUi.setLocation(windowX, windowY);

        // Grid-bagLayout Einstellungen und Zuweisung der Elemente zu dem Center Panel
        loginCenterPanel.add(loginUsername, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginPassword, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginButton, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginRegister, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        // Zuweisung von Hauptelementen zu dem Login Frame
        loginMain.add(loginCenterPanel, BorderLayout.CENTER);
        loginBackground.add(loginMain);
        loginUi.add(loginBackground);
        loginUi.setVisible(true);

        // Zuweisung was der Login Button macht
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> reading = Persistenz.reading();
                boolean userLoginCorrect = false;
                for (User user : reading) {
                    if (user.getUserName().equals(loginUsername.getText()) && user.getPassword().equals(loginPassword.getText())) {
                        Zentrale.getInstance().setActiveUser(user);
                        userLoginCorrect = true;
                    }
                }
                if (userLoginCorrect) {
                    System.out.println("USER LOGGED IN");
                    loginUi.dispose();
                    new Ui();
                } else {
                    JOptionPane.showMessageDialog(null, "Falscher Username oder Passwort");
                    System.out.println("USER LOGIN ERROR");
                }
            }
        });

        loginRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel registerMain = new JPanel(new BorderLayout());
                JPanel registerPanel = new JPanel(new GridBagLayout());
                JPanel registerBackgroundPanel = new JPanel();
                JPanel registerLogo = new JPanel();
                JButton registerButton = new JButton();
                JButton registerBack = new JButton();

                JTextField registerUsername = new JTextField("Username");
                registerUsername.setPreferredSize(new Dimension(400, 50));
                registerUsername.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        registerUsername.setText("");
                    }
                });
                JTextField registerPassword = new JTextField("Password");
                registerPassword.setPreferredSize(new Dimension(400, 50));
                registerPassword.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        registerPassword.setText("");
                    }
                });
                JTextField registerAlter = new JTextField("Alter");
                registerAlter.setPreferredSize(new Dimension(400, 50));
                registerAlter.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        registerAlter.setText("");
                    }
                });

                Icon registerIcon256 = new ImageIcon("Casino/src/frontend/img/loginButton/register256.png");
                registerButton.setIcon(registerIcon256);
                registerButton.setOpaque(false);
                registerButton.setContentAreaFilled(false);
                registerButton.setBorderPainted(false);

                Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
                registerBack.setIcon(backIcon);
                registerBack.setOpaque(false);
                registerBack.setContentAreaFilled(false);
                registerBack.setBorderPainted(false);

                registerPanel.add(registerUsername, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerPassword, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerAlter, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerButton, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerBack, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

                registerBackgroundPanel.add(registerPanel);
                registerMain.add(registerBackgroundPanel, BorderLayout.CENTER);

                loginBackground.remove(loginMain);
                loginBackground.add(registerMain);
                loginUi.revalidate();
                loginUi.repaint();

                registerButton.addActionListener(new ActionListener() {
                    @Override //TODO:Add ErrorCode für falsche Regestrierung
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<User> reading = Persistenz.reading();
                        User register_user = new User(registerUsername.getText(), registerAlter.getText(), registerPassword.getText());
                        reading.add(register_user);
                        Persistenz.writing(reading);
                        JOptionPane.showMessageDialog(null, "Account wurde erstellt\nViel Spaß beim Spielen");
                        loginBackground.remove(registerMain);
                        loginBackground.add(loginMain);
                        loginUi.revalidate();
                        loginUi.repaint();
                    }
                });

                registerBack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loginBackground.remove(registerMain);
                        loginBackground.add(loginMain);
                        loginUi.revalidate();
                        loginUi.repaint();
                    }
                });
            }
        });
    }
}
