package Games.coinflipgame;

import frontend.extraUi.SettingUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CoinFlipP extends JPanel {

    private Random rand = new Random();
    private int displayedValue = 0;

    private ImageIcon headsIcon;
    private ImageIcon tailsIcon;

    private JButton sevenButton;
    private JButton dropButton;

    private int balance;
    private int betAmount = 0;

    public CoinFlipP() {
        setOpaque(false); // Set the panel to be transparent
        setLayout(new GridBagLayout());

        // Load images
        headsIcon = new ImageIcon("Casino/src/frontend/img/coin/seven300.png");
        tailsIcon = new ImageIcon("Casino/src/frontend/img/coin/waterdrop300.png");

        //Bet Buttons
        sevenButton = createButton("Seven", Color.RED);
        dropButton = createButton("Drop", Color.BLACK);

        //Erstelung der Elemente
        JLabel coinLabel = new JLabel();
        JLabel betAmountLabel = new JLabel();
        JPanel coinSelectPanel = new JPanel(new GridLayout(1, 2));
        JLabel betField = new JLabel();
        JLabel resultLabel = new JLabel();

        // Sucht die Fonts für das Programm
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font mainfont = font.deriveFont(50f);
            coinLabel.setFont(mainfont);
            betAmountLabel.setFont(mainfont);

        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }

        //Coin Label Settings
        coinLabel.setBackground(new Color(0, 0, 0, 0));
        coinLabel.setIcon(headsIcon);  // Set initial image






        sevenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sevenButton.setBackground(Color.WHITE);
                sevenButton.setForeground(Color.black);
                dropButton.setBackground(Color.BLACK);
                dropButton.setForeground(Color.WHITE);
            }
        });

        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sevenButton.setBackground(Color.RED);
                dropButton.setBackground(Color.WHITE);
                dropButton.setForeground(Color.black);
            }
        });


        resultLabel.setForeground(Color.WHITE); // Set text color to white


        coinSelectPanel.add(sevenButton);
        coinSelectPanel.add(dropButton);

        JPanel betAmountButtonsPanel = new JPanel(new GridLayout(2, 3));

        JButton betAmountButton1 = new JButton("+5");
        JButton betAmountButton2 = new JButton("+10");
        JButton betAmountButton3 = new JButton("+100");
        JButton betAmountButton4 = new JButton("-5");
        JButton betAmountButton5 = new JButton("-10");
        JButton betAmountButton6 = new JButton("-100");


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String text = source.getText();
                int i = Integer.parseInt(text);
                betAmount += i;
                betAmountLabel.setText("Im Pot: " + betAmount);
            }
        };


        betAmountButton1.addActionListener(listener);
        betAmountButton2.addActionListener(listener);
        betAmountButton3.addActionListener(listener);
        betAmountButton4.addActionListener(listener);
        betAmountButton5.addActionListener(listener);
        betAmountButton6.addActionListener(listener);

        betAmountButtonsPanel.add(betAmountButton1);
        betAmountButtonsPanel.add(betAmountButton2);
        betAmountButtonsPanel.add(betAmountButton3);
        betAmountButtonsPanel.add(betAmountButton4);
        betAmountButtonsPanel.add(betAmountButton5);
        betAmountButtonsPanel.add(betAmountButton6);

        JLabel coinFlipBet = new JLabel();
        Icon betIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/Bet.png");
        coinFlipBet.setIcon(betIcon);
        coinFlipBet.setOpaque(false);
        coinFlipBet.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                String betText = betField.getText();
                if (betText.isEmpty()) {
                    resultLabel.setText("Please enter a bet amount.");
                    return;
                }
                int bet;
                try {
                    bet = Integer.parseInt(betText);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid bet amount!");
                    return;
                }

                if (bet > balance) {
                    resultLabel.setText("Insufficient balance!");
                } else {
                    coinFlipBet.setEnabled(false);  // Disable button during animation

                    // Start flip animation
                    Timer timer = new Timer(100, new ActionListener() {
                        private int count = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (count < 10) {
                                coinLabel.setIcon(count % 2 == 0 ? headsIcon : tailsIcon);
                                count++;
                                repaint(); // Trigger repaint
                            } else {
                                ((Timer) e.getSource()).stop();  // Stop the animation
                                coinFlipBet.setEnabled(true);  // Enable button after animation

                                boolean win = flipCoin();
                                if (win) {
                                    balance += bet;
                                    resultLabel.setText("You won! New balance: " + balance);
                                } else {
                                    balance -= bet;
                                    resultLabel.setText("You lost! New balance: " + balance);
                                }
                            }
                        }
                    });

                    timer.start();
                }
            }
        });


        coinFlipBet.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

            }
        });

        JPanel coinFlipPanel = new JPanel(new GridBagLayout());
        coinFlipPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // Add some vertical spacing between components

        coinFlipPanel.add(coinLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0); // Add spacing between components
        coinFlipPanel.add(betAmountLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0); // Add spacing between components
        coinFlipPanel.add(betAmountButtonsPanel, gbc);

        gbc.gridy = 3;
        coinFlipPanel.add(coinFlipBet, gbc);



        add(coinFlipPanel);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setFocusPainted(false);
        button.setFont(button.getFont().deriveFont(Font.BOLD));
        return button;
    }

    private boolean flipCoin() {
        return rand.nextInt(2) == 0;
    }
}
