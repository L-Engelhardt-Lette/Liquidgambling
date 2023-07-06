package frontend;


import Backend.Datenbank.Persistenz;
import Games.coinflipgame.CoinFlipP;
import frontend.extraUi.SettingUi;
import frontend.extraUi.UserUi;
import Backend.User;
import Backend.Zentrale;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Marek weiß gar nichts über java frontend

public class Ui {

    private final JLabel startPearlCount;

    public static void main(String[] args) {

        // Erstellung von den Login Ui Elementen
        JFrame loginUi = new JFrame();
        JPanel loginMain = new JPanel(new BorderLayout());
        JLabel loginLogoLabel = createLogoLabel();
        JPanel loginCenterPanel = new JPanel(new GridBagLayout());
        JLabel loginButton = new JLabel();
        JButton loginRegister = new JButton();
        JPanel loginBackground = new JPanel(){
            protected void paintComponent(Graphics grphcs) {
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
//        loginButton.setContentAreaFilled(false);
//        loginButton.setBorderPainted(false);

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
                        JOptionPane.showMessageDialog(null,"Done");
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
                JLabel registerLogo = createLogoLabel();
                JButton registerButton = new JButton();
                JButton registerBack = new JButton();
                JPanel registerBackgroundPanel = new JPanel(){
                    protected void paintComponent(Graphics grphcs) {
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

                registerPanel.add(registerLogo, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerUsername, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerPassword, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerAlter, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerButton, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
                registerPanel.add(registerBack, new GridBagConstraints(1, 6, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

                registerMain.setBackground(new Color(0,0,0,0));
                registerPanel.setBackground(new Color(0,0,0,0));

                registerBackgroundPanel.add(registerPanel);
                registerMain.add(registerBackgroundPanel, BorderLayout.CENTER);

                loginUi.remove(loginMain);
                loginUi.add(registerMain);
                loginUi.revalidate();
                loginUi.revalidate();
                loginUi.repaint();

                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
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


                registerBack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loginUi.remove(registerMain);
                        loginUi.add(loginMain);
                        loginUi.revalidate();
                        loginUi.repaint();
                    }
                });
            }

        });
    }


    public Ui() {

        JFrame startFrame = new JFrame();
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
        startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        startFrame.setUndecorated(true);
        JButton startNameLogo = new JButton();
        JButton startSetting = new JButton();
        JButton startCoinflip = new JButton();
        JButton startPoker = new JButton();
        JButton startSlots = new JButton();
        JLabel startCoinflipLable = new JLabel();
        JLabel startPokerLable = new JLabel();
        JLabel startSlotsLable = new JLabel();
        startPearlCount = new JLabel() {
            @Override
            public String getText() {
                return Zentrale.getInstance().getActiveUser().getUser_Pearl_String();
            }
        };
        JLabel startPearlLogo = new JLabel();
        JButton startUser = new JButton();


        // ActionListener
        startSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingUi settingUi = new SettingUi();
                settingUi.setVisible(true);
            }
        });
        startUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserUi userUi = new UserUi();
                userUi.setVisible(true);
            }
        });


        // Sucht die Fonts für das Programm aus BIG FONT
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font mainfont = font.deriveFont(70f);
            startNameLogo.setFont(mainfont);

        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }
        //Font für die anderen texte die mittelgroß sein sollen
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font mediumfont = font.deriveFont(55f);
            startCoinflipLable.setFont(mediumfont);
            startPokerLable.setFont(mediumfont);
            startSlotsLable.setFont(mediumfont);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }
        //Font für die anderen texte die kleiner sein sollen
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font smallfont = font.deriveFont(40f);
            startPearlCount.setFont(smallfont);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }

        JPanelWithBackground backgroundPanel = new JPanelWithBackground("Casino/src/frontend/img/Background/mcbg.jpg");
        backgroundPanel.setLayout(new BorderLayout());
        startFrame.setContentPane(backgroundPanel);


        //Panel Setup
        JPanel startTopPanel = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(255, 255, 255, 160), 0, getHeight(), new Color(255, 255, 255, 0));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        startTopPanel.setOpaque(false);
        JPanel startMainPanel = new JPanel(new GridBagLayout());
        startMainPanel.setOpaque(false);
        JPanel startLogoNamePanel = new JPanel(new GridLayout(1, 2));
        startLogoNamePanel.setOpaque(false);
        JPanel startTopRightPanel = new JPanel(new GridBagLayout());
        startTopRightPanel.setOpaque(false);

        //Icons für die Game buttons
        Icon pokericon = new ImageIcon("Casino/src/frontend/img/gameButtons/poker512.png");
        startPoker.setIcon(pokericon);
        startPoker.setOpaque(false);
        startPoker.setContentAreaFilled(false);
        startPoker.setBorderPainted(false);

        Icon slotsicon = new ImageIcon("Casino/src/frontend/img/gameButtons/slots512.png");
        startSlots.setIcon(slotsicon);
        startSlots.setOpaque(false);
        startSlots.setContentAreaFilled(false);
        startSlots.setBorderPainted(false);

        Icon coinflipicon = new ImageIcon("Casino/src/frontend/img/gameButtons/coinflip512.png");
        startCoinflip.setIcon(coinflipicon);
        startCoinflip.setOpaque(false);
        startCoinflip.setContentAreaFilled(false);
        startCoinflip.setBorderPainted(false);

        //Die Buttons die zum game führen
        JPanel startPokerPanel = new JPanel(new GridLayout(2, 1));
        startPokerPanel.add(startPoker);
        startPokerPanel.add(startPokerLable);
        startPokerPanel.setOpaque(false);
        JPanel startSlotsPanel = new JPanel(new GridLayout(2, 1));
        startSlotsPanel.add(startSlots);
        startSlotsPanel.add(startSlotsLable);
        startSlotsPanel.setOpaque(false);
        JPanel startCoinflipPanel = new JPanel(new GridLayout(2, 1));
        startCoinflipPanel.add(startCoinflip);
        startCoinflipPanel.add(startCoinflipLable);
        startCoinflipPanel.setOpaque(false);

        //Gridbaglayout
        startMainPanel.add(startPokerPanel, new GridBagConstraints(1, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(300, 200, 0, -200), 0, 0));
        startMainPanel.add(startSlotsPanel, new GridBagConstraints(2, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(300, -200, 0, -200), 0, 0));
        startMainPanel.add(startCoinflipPanel, new GridBagConstraints(3, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(300, -200, 0, 200), 0, 0));

        //Home Button
        Icon logoIcon = new ImageIcon("Casino/src/frontend/img/Logo/LogoLable.png");
        //startNameLogo.addActionListener(clickToGoHome); //TODO: Home Button Actionlistener adden
        startNameLogo.setIcon(logoIcon);
        startNameLogo.setOpaque(false);
        startNameLogo.setContentAreaFilled(false);
        startNameLogo.setBorderPainted(false);

        //Pearl Icon
        Icon pearlIcon = new ImageIcon("Casino/src/frontend/img/Logo/pearl64px.png");
        startPearlLogo.setIcon(pearlIcon);
        startPearlLogo.setHorizontalAlignment(SwingConstants.CENTER);

        //Setting Button
        Icon settingIcon = new ImageIcon("Casino/src/frontend/img/Logo/setting64px.png");
        startSetting.setIcon(settingIcon);
        startSetting.setOpaque(false);
        startSetting.setContentAreaFilled(false);
        startSetting.setBorderPainted(false);

        //User Button
        Icon userIcon = new ImageIcon("Casino/src/frontend/img/Logo/user64px.png");
        startUser.setIcon(userIcon);
        startUser.setOpaque(false);
        startUser.setContentAreaFilled(false);
        startUser.setBorderPainted(false);

        //Anzeige für die Pearl Balance
        startPearlCount.setForeground(Color.WHITE);
        startPearlCount.setHorizontalAlignment(SwingConstants.RIGHT);
        startPearlCount.setAlignmentY(Component.CENTER_ALIGNMENT);
        startPearlCount.setOpaque(false);


        //Zuweisung von Items zu dem LogoNamePanel
        startLogoNamePanel.add(startNameLogo);

        //Zuweisung von items zu dem TopRightPanel wie settings etc
        startTopRightPanel.add(startPearlCount, new GridBagConstraints(1, 1, 2, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        startTopRightPanel.add(startPearlLogo, new GridBagConstraints(3, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        startTopRightPanel.add(startSetting, new GridBagConstraints(4, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        startTopRightPanel.add(startUser, new GridBagConstraints(5, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));

        //Zuweisung der PanelGroups zum Programm Header (TopPanel)
        startTopPanel.add(startTopRightPanel, BorderLayout.EAST);
        startTopPanel.add(startLogoNamePanel, BorderLayout.WEST);
        //Zuweisung der panel zu frame
        startFrame.add(startTopPanel, BorderLayout.NORTH);
        startFrame.add(startMainPanel, BorderLayout.CENTER);


        //Start Frame Stuff
        startFrame.setVisible(true);


        startPoker.addActionListener(e -> {
            JPanel pokerPanel = new JPanel();
            JButton back = new JButton("BACK");
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startFrame.remove(pokerPanel);
                    startFrame.add(startMainPanel, BorderLayout.CENTER);
                    startFrame.revalidate();
                    startFrame.revalidate();
                    startFrame.repaint();
                }
            });
            pokerPanel.add(back);
            startFrame.remove(startMainPanel);
            startFrame.add(pokerPanel, BorderLayout.CENTER);
            startFrame.revalidate();
            startFrame.revalidate();
        });
        startCoinflip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CoinFlipP coinflipPanel = new CoinFlipP();
                JButton back = new JButton("BACK");
                JPanel coinflipMainPanel = new JPanel();
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startFrame.remove(coinflipMainPanel);
                        startFrame.add(startMainPanel, BorderLayout.CENTER);
                        startFrame.revalidate();
                        startFrame.revalidate();
                        startFrame.repaint();
                    }
                });
                coinflipPanel.setBackground(new Color(0,0,0,0));
                coinflipMainPanel.setBackground(new Color(0,0,0,0));
                coinflipMainPanel.setLayout(new BorderLayout());
                coinflipMainPanel.add(coinflipPanel, BorderLayout.CENTER);
                coinflipMainPanel.add(back, BorderLayout.SOUTH);
                startFrame.remove(startMainPanel);
                startFrame.add(coinflipMainPanel, BorderLayout.CENTER);
                startFrame.revalidate();
                startFrame.repaint();
            }
        });
        startSlots.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

    }

    public void update() {
        startPearlCount.repaint();

    }

    public static class JPanelWithBackground extends JPanel {
        private BufferedImage backgroundImage;


        public JPanelWithBackground(String fileName) {
            //super(new BorderLayout());
            File imageFile = new File(fileName);
            if (!imageFile.exists()) {

            }

            try {
                backgroundImage = ImageIO.read(imageFile);
            } catch (IOException e) {
                System.out.println("ERROR Bild wurde nicht geladen / gefunden");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private static JLabel createLogoLabel() {
        // Load the logo image from a local file
        BufferedImage logoImage = null;
        try {
            File imageFile = new File("Casino/src/frontend/img/Logo/LogoLable.png"); // Replace with the file path of your logo image
            logoImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new JLabel with the logo image
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));

        return logoLabel;
    }
}
