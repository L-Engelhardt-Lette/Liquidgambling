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

//Die beste Ui die es nur geben kann

public class Ui {

    private final JFrame startFrame;

    //Main Ui Part
    public Ui() {

        //Erstellung der Elemente
        startFrame = new JFrame();
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        startFrame.setUndecorated(true);
        JLabel startNameLogo = new JLabel();
        JLabel startSetting = new JLabel();
        JLabel startCoinflip = new JLabel();
        JLabel startPoker = new JLabel();
        JLabel startSlots = new JLabel();
        JLabel startCoinflipLable = new JLabel();
        JLabel startPokerLable = new JLabel();
        JLabel startSlotsLable = new JLabel();

    
        JLabel startPearlCount = new JLabel() { // Aktualisirung des Pearlcounts
            @Override
            public String getText() {
                return Zentrale.getInstance().getActiveUser().getUser_Pearl_String();
            }
        };
        JLabel startPearlLogo = new JLabel();
        JButton startUser = new JButton();

        // ActionListener um die Setings zu öffnen
        startSetting.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                SettingUi settingUi = new SettingUi();
                settingUi.setVisible(true);
            }
        });

        // ActionListener um die UUserUi zu öffnen
        startUser.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                UserUi userUi = new UserUi(Ui.this);
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


        //Erstellung des Background Img für die Ui
        JPanelWithBackground backgroundPanel = new JPanelWithBackground("Casino/src/frontend/img/Background/bgImg.jpg");
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


        //Setting für die Obere Anzeige
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

        Icon slotsicon = new ImageIcon("Casino/src/frontend/img/gameButtons/slots512.png");
        startSlots.setIcon(slotsicon);
        startSlots.setOpaque(false);;

        Icon coinflipicon = new ImageIcon("Casino/src/frontend/img/gameButtons/coinflip512.png");
        startCoinflip.setIcon(coinflipicon);
        startCoinflip.setOpaque(false);


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
        startNameLogo.setIcon(logoIcon);
        startNameLogo.setOpaque(false);


        //Pearl Icon
        Icon pearlIcon = new ImageIcon("Casino/src/frontend/img/Logo/pearl64px.png");
        startPearlLogo.setIcon(pearlIcon);
        startPearlLogo.setHorizontalAlignment(SwingConstants.CENTER);


        //Setting Button
        Icon settingIcon = new ImageIcon("Casino/src/frontend/img/Logo/setting64px.png");
        startSetting.setIcon(settingIcon);
        startSetting.setOpaque(false);


        //User Button
        Icon userIcon = new ImageIcon("Casino/src/frontend/img/Logo/user64px.png");
        startUser.setIcon(userIcon);
        startUser.setOpaque(false);
        startUser.setContentAreaFilled(false);
        startUser.setBorderPainted(false);

        //TODO: Die Standard größe erhöhen.
        //Anzeige für die Pearl Balance
        startPearlCount.setText(Zentrale.getInstance().getActiveUser().getUser_Pearl_String());
        //startPearlCount.setPreferredSize();
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

        //Start des Poker games
        startPoker.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
        JPanel pokerPanel = new JPanel();

        //Backbutton
                                            JLabel backButton = new JLabel();
                                            Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
                                            backButton.setIcon(backIcon);
                                            backButton.setOpaque(false);
                                            JPanel backPanel = new JPanel(new GridBagLayout());
                                            backPanel.setBackground(new Color(0,0,0,0));
                                            backPanel.add(backButton);
                                            backButton.addMouseListener(new MouseAdapter() {
                                                @Override
                                                public void mousePressed(MouseEvent e) {
                                                    startFrame.remove(pokerPanel);
                                                    startFrame.add(startMainPanel, BorderLayout.CENTER);
                                                    startFrame.revalidate();
                                                    startFrame.revalidate();
                                                    startFrame.repaint();
                                                }
                                            });


                                            pokerPanel.add(backPanel, BorderLayout.SOUTH);

                                            startFrame.remove(startMainPanel);
                                            startFrame.add(pokerPanel, BorderLayout.CENTER);
                                            startFrame.revalidate();
                                            startFrame.revalidate();

                                        }
                                    });


        //Start des Coinflipgames
        startCoinflip.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                CoinFlipP coinflipPanel = new CoinFlipP();
                JPanel coinflipMainPanel = new JPanel();

                //Backbutton
                JLabel backButton = new JLabel();
                Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
                backButton.setIcon(backIcon);
                backButton.setOpaque(false);
                JPanel backPanel = new JPanel(new GridBagLayout());
                backPanel.setBackground(new Color(0,0,0,0));
                backPanel.add(backButton);
                backButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
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
                coinflipMainPanel.add(backPanel, BorderLayout.SOUTH);
                startFrame.remove(startMainPanel);
                startFrame.add(coinflipMainPanel, BorderLayout.CENTER);
                startFrame.revalidate();
                startFrame.repaint();
            }
        });


        //Start des Slotgames
        startSlots.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                //Backbutton
                JLabel backButton = new JLabel();
                Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
                backButton.setIcon(backIcon);
                backButton.setOpaque(false);
                JPanel backPanel = new JPanel(new GridBagLayout());
                backPanel.setBackground(new Color(0,0,0,0));
                backPanel.add(backButton);
                backButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        startFrame.remove(startSlots);
                        startFrame.add(startMainPanel, BorderLayout.CENTER);
                        startFrame.revalidate();
                        startFrame.revalidate();
                        startFrame.repaint();
                    }
                });
                startSlots.add(backPanel, BorderLayout.SOUTH);

            }
        });

    }


    //Methode um den Pearlcount zu updaten
    public void update() {

        startPearlCount.repaint();

    }



    //Erstellung des Beackground über ein BufferedImg
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


    //Erstellung des Logo vom Casino
    public static JLabel createLogoLabel() {
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

    public void unsichtbarMachen(){
        startFrame.setVisible(false);
    }
}
