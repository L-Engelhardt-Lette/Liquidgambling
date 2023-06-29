package frontend;

import Datenbank.Persistenz;
import stuff.User;
import stuff.Zentrale;

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

// Marek weiß was über java frontend

public class Ui {

    public static void main(String[] args) {

        //Erstellung von den Login Ui Elementen
        JFrame loginUi = new JFrame();
        JPanel loginBackground = new JPanel();
        JPanel loginMain = new JPanel(new BorderLayout());
        JButton loginButton = new JButton();
        JButton loginRegister = new JButton();
        JButton loginNameLogo = new JButton();
        JPanel loginCenterPanel = new JPanel(new GridBagLayout());


        //Erstellung der eingabe Flächen und die funktion der text-fields selber TODO: Text in Textfield ändern
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


        /*
        TODO: Hintergrund für den Login Bildschirm
        Image loginBackgroundImg = Toolkit.getDefaultToolkit().createImage("Casino/src/frontend/img/Background/mcbg.jpg");
        loginBackground.drawImage(loginBackgroundImg,0,0,null);
        */

        //Zuweisung was der Login Button macht
        loginButton.addActionListener(e -> {
            ArrayList<User> reading = Persistenz.reading();
            for (User user : reading) {
                if (user.getUserName().equals(loginUsername.getText()) && user.getPassword().equals(loginPassword.getText())) {
                    Zentrale.getInstance().setActiveUser(user);
                    break;
                }
                //TODO: Error message wenn der user nicht richtig ist
            }

            System.out.println("Login war erfolgreich? " + Zentrale.getInstance().getActiveUser() != null);
            loginUi.dispose();
            new Ui();
        });


        //Background für die Login-Page TODO:Background muss noch richtig Implimentiert werden
        JPanelWithBackground backgroundPanel = new JPanelWithBackground("Casino/src/frontend/img/Background/mcbg.jpg");
        backgroundPanel.setLayout(new BorderLayout());
        loginUi.setContentPane(backgroundPanel);


        //Font für die Login-Page
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font loginfont = font.deriveFont(60f);
            loginNameLogo.setFont(loginfont);

        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }


        // Name des Projekts
        loginNameLogo.setText("Liquid Gambling"); //TODO:Vielleicht useless
        loginNameLogo.setForeground(Color.WHITE);
        loginNameLogo.setOpaque(false);

        //Icon für den Home Button
        Icon logoIcon = new ImageIcon("Casino/src/frontend/img/Logo/Logo.png");
        loginNameLogo.setIcon(logoIcon);
        loginNameLogo.setOpaque(false);
        loginNameLogo.setContentAreaFilled(false);
        loginNameLogo.setBorderPainted(false);

        //Icon für den Login Button
        Icon loginicon = new ImageIcon("Casino/src/frontend/img/loginButton/login256.png");
        loginButton.setIcon(loginicon);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);

        //Icon für den Register Button
        Icon registericon = new ImageIcon("Casino/src/frontend/img/loginButton/register128.png");
        loginRegister.setIcon(registericon);
        loginRegister.setOpaque(false);
        loginRegister.setContentAreaFilled(false);
        loginRegister.setBorderPainted(false);


        //Vorgaben für das Login Fenster
        loginUi.setSize(800, 600);
        loginUi.setResizable(false);
        loginUi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginUi.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = loginUi.getSize();
        int windowX = Math.max(0, (screenSize.width - windowSize.width) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);
        loginUi.setLocation(windowX, windowY);

        //Grid-bagLayout Einstellungen und zuweisung der Elemente zu dem Center Panel
        loginCenterPanel.add(loginNameLogo, new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginUsername, new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginPassword, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginButton, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        loginCenterPanel.add(loginRegister, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        //Zuweisung von Hauptelementen zu dem Login Frame
        loginMain.add(loginCenterPanel,BorderLayout.CENTER);
        loginBackground.add(loginMain);
        loginUi.add(loginBackground);
        loginUi.setVisible(true);


        //Erstellung der Action, wenn ein user sich Registrieren will
        loginRegister.addActionListener(e -> {
            JPanel registerPanel = new JPanel(new GridBagLayout());
            //TODO:Logo bei dem Register Page einfügen
            //JPanel registerLogo = new JPanel(new ImageIcon("Casino/src/frontend/img/Logo/LogoLable.png"));

            //Textfeld für Alter/Username/Password mit anweisung, wenn clicked ist
            JTextField registerAlter = new JTextField("Alter");
            registerAlter.setPreferredSize(new Dimension(400,50));
            registerAlter.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    registerAlter.setText("");
                }
            });
            JTextField registerUsername = new JTextField("Username");
            registerUsername.setPreferredSize(new Dimension(400,50));
            registerUsername.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    registerUsername.setText("");
                }
            });
            JTextField registerPassword = new JTextField("Password");
            registerPassword.setPreferredSize(new Dimension(400,50));
            registerPassword.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    registerPassword.setText("");
                }
            });

            //Erstellung der Buttons für die Register Page
            JButton registerButton = new JButton();
            JButton registerBack = new JButton();

            //Zuweisung der funktion für den Back Button auf der Register Page
            registerBack.addActionListener(e1 -> {
                loginUi.remove(registerPanel);
                loginUi.add(loginMain,BorderLayout.CENTER);
                loginUi.revalidate();
                loginUi.revalidate();
                loginUi.repaint();
            });

            //registerPanel.add(registerLogo);
            registerPanel.add(registerUsername,new GridBagConstraints(1, 1, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerPassword,new GridBagConstraints(1, 2, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerAlter,new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            registerPanel.add(registerButton,new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            Icon registerIcon256 = new ImageIcon("Casino/src/frontend/img/loginButton/register256.png");
            registerButton.setIcon(registerIcon256);
            registerButton.setOpaque(false);
            registerButton.setContentAreaFilled(false);
            registerButton.setBorderPainted(false);
            registerPanel.add(registerBack,new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
            Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
            registerBack.setIcon(backIcon);
            registerBack.setOpaque(false);
            registerBack.setContentAreaFilled(false);
            registerBack.setBorderPainted(false);


            registerButton.addActionListener(e12 -> {
                ArrayList<User> reading = Persistenz.reading();
                User register_user = new User(registerUsername.getText(), registerAlter.getText(), registerPassword.getText());
                reading.add(register_user);
                Persistenz.writing(reading);

                loginUi.remove(registerPanel);
                loginUi.add(loginMain,BorderLayout.CENTER);
                loginUi.revalidate();
                loginUi.revalidate();
                loginUi.repaint();

            });

            loginUi.remove(loginMain);
            loginUi.add(registerPanel, BorderLayout.CENTER);
            loginUi.revalidate();
            loginUi.revalidate();


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
        JLabel startPearlCount = new JLabel();
        JLabel startPearlLogo = new JLabel();
        JButton startUser = new JButton();


        // ActionListener
        ActionListener clickToGoHome = e -> {

        };
        ActionListener openSettingUi = e -> {

        };
        ActionListener openUserUi = e -> {

        };


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

        /*
        Anpassung der Größe vom Programm an den Bildschirm
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        startFrame.setSize(screenWidth, screenHeight);
        */

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


        // Name des Projekts
        startNameLogo.setText("Liquid Gambling");
        startNameLogo.setForeground(Color.WHITE);
        startNameLogo.setOpaque(false);

        //Home Button
        Icon logoIcon = new ImageIcon("Casino/src/frontend/img/Logo/Logo.png");
        startNameLogo.addActionListener(clickToGoHome);
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
        startSetting.addActionListener(openSettingUi);
        startSetting.setIcon(settingIcon);
        startSetting.setOpaque(false);
        startSetting.setContentAreaFilled(false);
        startSetting.setBorderPainted(false);

        //User Button
        Icon userIcon = new ImageIcon("Casino/src/frontend/img/Logo/user64px.png");
        startUser.addActionListener(openUserUi);
        startUser.setIcon(userIcon);
        startUser.setOpaque(false);
        startUser.setContentAreaFilled(false);
        startUser.setBorderPainted(false);

        //JPanel startAdminPanel = new JPanel();           Stuff für das Background
        //startFrame.add(startAdminPanel); Stuff für den Background


        //Anzeige für die Pearl Balance
        startPearlCount.setText(Zentrale.getInstance().getActiveUser().getUser_Pearl_String()); //TODO: Nicht in Klammern stehen lassen
        startPearlCount.setForeground(Color.WHITE);
        startPearlCount.setHorizontalAlignment(SwingConstants.RIGHT);
        startPearlCount.setAlignmentY(Component.CENTER_ALIGNMENT);
        startPearlCount.setOpaque(false);


        //Zuweisung von Items zu dem LogoNamePanel
        startLogoNamePanel.add(startNameLogo);

        //Zuweisung von items zu dem TopRightPanel wie settings etc
        startTopRightPanel.add(startPearlCount, new GridBagConstraints(1, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));
        startTopRightPanel.add(startPearlLogo, new GridBagConstraints(2, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        startTopRightPanel.add(startSetting, new GridBagConstraints(3, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        startTopRightPanel.add(startUser, new GridBagConstraints(4, 1, 1, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

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

    }


    //TODO:Background einfügen und ihn sichtbar machen ohne das der andere stuff weg ist
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

            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
