package frontend;

import Backend.Datenbank.Persistenz;
import Backend.User;
import Backend.Zentrale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static frontend.Ui.createLogoLabel;

public class LoginUi {
    public LoginUi() {


    // Erstellung von den Login Ui Elementen
    JFrame loginUi = new JFrame();
    JPanel loginMain = new JPanel(new BorderLayout());
    JLabel loginLogoLabel = createLogoLabel();
    JPanel loginCenterPanel = new JPanel(new GridBagLayout());
    JLabel loginButton = new JLabel();
    JLabel loginRegister = new JLabel();
    JPanel loginBackground = new JPanel(){
        protected void paintComponent(Graphics grphcs) {  //Gradiant für den LoginBackground
            super.paintComponent(grphcs);
            Graphics2D g2d = (Graphics2D) grphcs;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Set the gradient colors to cyan (#00d2ff) and blue (#3a7bd5)
            Color startColor = new Color(0, 210, 255);
            Color endColor = new Color(58, 123, 213);
            GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
            g2d.setPaint(gp);

            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
        loginUi.setContentPane(loginBackground);


    //Login einfabe Felder
    JTextField loginUsername = new JTextField("LEDEAdmin");
        loginUsername.setPreferredSize(new Dimension(300, 40));
        loginUsername.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            loginUsername.setText("");
        }
    });
    JTextField loginPassword = new JTextField("qwertz");
        loginPassword.setPreferredSize(new Dimension(300, 40));
        loginPassword.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            loginPassword.setText("");
        }
    });


    // Icon für den Login Button
    Icon loginicon = new ImageIcon("Casino/src/frontend/img/loginButton/login256.png");
        loginButton.setIcon(loginicon);
        loginButton.setOpaque(false);


    // Icon für den Register Button
    Icon registericon = new ImageIcon("Casino/src/frontend/img/loginButton/register128.png");
        loginRegister.setIcon(registericon);
        loginRegister.setOpaque(false);


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


    //Panel transparent machen
        loginMain.setBackground(new Color(0,0,0,0));
        loginCenterPanel.setBackground(new Color(0,0,0,0));


    // Grid-bagLayout Einstellungen und Zuweisung der Elemente zu dem Center Panel
        loginCenterPanel.add(loginLogoLabel, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginUsername, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(100, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginPassword, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginButton, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginRegister, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 0, 0, 0), 0, 0));


    // Zuweisung von Hauptelementen zu dem Login Frame
        loginMain.add(loginCenterPanel, BorderLayout.CENTER);
        loginUi.add(loginMain);
        loginUi.setVisible(true);


    // Zuweisung was der Login Button macht
        loginButton.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
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


    //Erstellung und öffnung der Register Page
        loginRegister.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            JPanel registerMain = new JPanel(new BorderLayout());
            JPanel registerPanel = new JPanel(new GridBagLayout());
            JLabel registerLogo = createLogoLabel();
            JLabel registerButton = new JLabel();
            JLabel registerBack = new JLabel();
            JPanel registerBackgroundPanel = new JPanel(){
                protected void paintComponent(Graphics grphcs) { // Gradiant Background
                    super.paintComponent(grphcs);
                    Graphics2D g2d = (Graphics2D) grphcs;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    // Set the gradient colors to cyan (#00d2ff) and blue (#3a7bd5)
                    Color startColor = new Color(0, 210, 255);
                    Color endColor = new Color(58, 123, 213);
                    GradientPaint gp = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                    g2d.setPaint(gp);

                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            };


            //Erstellung der Textfelder mit anhängender Funktion
            JTextField registerUsername = new JTextField("Username");
            registerUsername.setPreferredSize(new Dimension(300, 40));
            registerUsername.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    registerUsername.setText("");
                }
            });

            JTextField registerPassword = new JTextField("Password");
            registerPassword.setPreferredSize(new Dimension(300, 40));
            registerPassword.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    registerPassword.setText("");
                }
            });

            JTextField registerAlter = new JTextField("Alter");
            registerAlter.setPreferredSize(new Dimension(300, 40));
            registerAlter.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    registerAlter.setText("");
                }
            });


            //Zuweisung der Icons für die Buttons
            Icon registerIcon256 = new ImageIcon("Casino/src/frontend/img/loginButton/register256.png");
            registerButton.setIcon(registerIcon256);
            registerButton.setOpaque(false);

            Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
            registerBack.setIcon(backIcon);
            registerBack.setOpaque(false);


            //Zuweisung der Elemente per GridBag zu dem Register Panel
            registerPanel.add(registerLogo, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerUsername, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerPassword, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerAlter, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerButton, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerBack, new GridBagConstraints(1, 6, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));


            //Register Background und Layout settings
            registerMain.setBackground(new Color(0,0,0,0));
            registerPanel.setBackground(new Color(0,0,0,0));

            registerBackgroundPanel.add(registerPanel);
            registerMain.add(registerBackgroundPanel, BorderLayout.CENTER);


            //Wenn Register aufgerufen wird, entfernung der Ui und erstellung der neuen UI
            loginUi.remove(loginMain);
            loginUi.add(registerMain);
            loginUi.revalidate();
            loginUi.revalidate();
            loginUi.repaint();


            //Register Button action
            registerButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    try { //Check ob die angegeben daten ok sind und die Schreibung von einem neuen User in der Datenbank
                        ArrayList<User> reading = Persistenz.reading();
                        User register_user = new User(registerUsername.getText(), registerAlter.getText(), registerPassword.getText());

                        // Additional validation logic for user inputs
                        if (register_user.getUserName().isEmpty() || register_user.getPassword().isEmpty() || register_user.getAge().isEmpty()){
                            throw new IllegalArgumentException("Incomplete register Form");
                        }
                        if (!register_user.getAge().matches("\\d+")){
                            throw new IllegalArgumentException("Your age needs to be a number");
                        }

                        reading.add(register_user);
                        Persistenz.writing(reading);

                        JOptionPane.showMessageDialog(null, "Account wurde erstellt\nViel Spaß beim Spielen");
                        loginUi.remove(registerMain);
                        loginUi.add(loginMain);
                        loginUi.revalidate();
                        loginUi.revalidate();
                        loginUi.repaint();
                    } catch (Exception registerDataEX) {
                        // Handle the exception
                        registerDataEX.printStackTrace(); // Print the stack trace for debugging purposes

                        // Show an error message to the user
                        JOptionPane.showMessageDialog(null, "Registrierung fehlgeschlagen: " + registerDataEX.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


            //Backbutton zur LoginPage
            registerBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    loginUi.remove(registerMain);
                    loginUi.add(loginMain);
                    loginUi.revalidate();
                    loginUi.repaint();
                }
            });
        }
    });
    }
}
