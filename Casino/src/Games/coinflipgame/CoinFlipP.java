package Games.coinflipgame;

import Backend.Zentrale;
import frontend.extraUi.SettingUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class CoinFlipP extends JPanel {

    private Random rand = new Random();
    private int displayedValue = 0;

    private ImageIcon headsIcon;
    private ImageIcon tailsIcon;

    private JButton sevenButton;
    private JButton dropButton;

    private int balance = Zentrale.getInstance().getActiveUser().getUser_Pearl();
    private int betAmount = 0;

    public CoinFlipP() {
        setOpaque(false); // Set the panel to be transparent
        setLayout(new GridBagLayout());


        JPanel betAmountPanel = new JPanel(new GridLayout(2, 3));

        JButton betAmountButton1 = new JButton("+5");
        JButton betAmountButton2 = new JButton("+10");
        JButton betAmountButton3 = new JButton("+100");
        JButton betAmountButton4 = new JButton("-5");
        JButton betAmountButton5 = new JButton("-10");
        JButton betAmountButton6 = new JButton("-100");
        JLabel betlabel = new JLabel();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String text = source.getText();
                int i = Integer.parseInt(text);
                betAmount += i;
                betlabel.setText("Im Pot: " + betAmount);
            }
        };


        betAmountButton1.addActionListener(listener);
        betAmountButton2.addActionListener(listener);
        betAmountButton3.addActionListener(listener);
        betAmountButton4.addActionListener(listener);
        betAmountButton5.addActionListener(listener);
        betAmountButton6.addActionListener(listener);

        betAmountPanel.add(betAmountButton1);
        betAmountPanel.add(betAmountButton2);
        betAmountPanel.add(betAmountButton3);
        betAmountPanel.add(betAmountButton4);
        betAmountPanel.add(betAmountButton5);
        betAmountPanel.add(betAmountButton6);
        // Load images
        headsIcon = new ImageIcon("Casino/src/frontend/img/coin/seven300.png");
        tailsIcon = new ImageIcon("Casino/src/frontend/img/coin/waterdrop300.png");

        JLabel coinLabel = new JLabel();

        coinLabel.setBackground(new Color(0, 0, 0, 0));
        coinLabel.setIcon(headsIcon);  // Set initial image

        JPanel coinSelectPanel = new JPanel(new GridLayout(1, 2));




        JLabel resultLabel = new JLabel();
        JLabel coinFlipBet = new JLabel();
        sevenButton = createButton("Seven", Color.RED);
        dropButton = createButton("Drop", Color.BLACK);

        sevenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random =  new Random();
                boolean sd =  random.nextBoolean();
                String betText = betlabel.getText();
                if (betText.isEmpty()) {
                    System.out.println("Please enter a bet amount.");
                    return;
                }
                int bet;
                try {
                    bet = betAmount;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid bet amount!");
                    return;
                }

                if (bet > balance) {
                    System.out.println("Insufficient balance!");
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
                                    Zentrale.getInstance().getActiveUser().plus(bet);
                                   System.out.println("You won! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                } else {
                                    Zentrale.getInstance().getActiveUser().minus(bet);
                                   System.out.println("You lost! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                }
                            }
                        }
                    });

                    timer.start();
                }



                sevenButton.setBackground(Color.WHITE);
                sevenButton.setForeground(Color.black);
                dropButton.setBackground(Color.BLACK);
                dropButton.setForeground(Color.WHITE);
            }
        });

        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String betText = betlabel.getText();
                if (betText.isEmpty()) {
                    System.out.println("Please enter a bet amount.");
                    return;
                }
                int bet;
                try {
                    bet = betAmount;
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid bet amount!");
                    return;
                }

                if (bet > balance) {
                    System.out.println("Insufficient balance!");
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
                                    Zentrale.getInstance().getActiveUser().plus(bet);
                                    System.out.println("You won! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                } else {
                                    Zentrale.getInstance().getActiveUser().minus(bet);
                                    System.out.println("You lost! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                }
                            }
                        }
                    });

                    timer.start();
                }

                sevenButton.setBackground(Color.RED);
                dropButton.setBackground(Color.WHITE);
                dropButton.setForeground(Color.black);
            }
        });

        resultLabel.setForeground(Color.WHITE); // Set text color to white


        coinSelectPanel.add(sevenButton);
        coinSelectPanel.add(dropButton);




        Icon betIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/Bet.png");
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
        coinFlipPanel.add(coinSelectPanel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0); // Add spacing between components
        coinFlipPanel.add(betAmountPanel, gbc);

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
