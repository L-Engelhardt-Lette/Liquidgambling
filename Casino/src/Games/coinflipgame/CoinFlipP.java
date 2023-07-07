// Package declaration
//TODO: ARTIFACTS FIXEN BITTE FRONTEND MEISTER.
package Games.coinflipgame;

// Import statements
import Backend.Zentrale;
import frontend.extraUi.SettingUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

// Class declaration
public class CoinFlipP extends JPanel {

    // Instance variables
    private Random rand = new Random();
    private int displayedValue = 0;

    private ImageIcon headsIcon;
    private ImageIcon tailsIcon;

    private JButton sevenButton;
    private JButton dropButton;

    private int balance = Zentrale.getInstance().getActiveUser().getUser_Pearl();
    private int betAmount = 0;

    // Constructor
    public CoinFlipP() {
        setOpaque(false); // Set the panel to be transparent
        setLayout(new GridBagLayout());

        // Create the bet amount panel
        JPanel betAmountPanel = new JPanel(new GridLayout(2, 3));

        // Create the bet amount buttons
        JButton betAmountButton1 = new JButton("+5");
        JButton betAmountButton2 = new JButton("+10");
        JButton betAmountButton3 = new JButton("+100");
        JButton betAmountButton4 = new JButton("-5");
        JButton betAmountButton5 = new JButton("-10");
        JButton betAmountButton6 = new JButton("-100");

        //TODO: betlabel hat funktionen und soll den Bet amount zeigen können aber ist noch null optisch eingestellt
        JLabel betlabel = new JLabel();

        // ActionListener for bet amount buttons
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

        // Add the ActionListener to the bet amount buttons
        betAmountButton1.addActionListener(listener);
        betAmountButton2.addActionListener(listener);
        betAmountButton3.addActionListener(listener);
        betAmountButton4.addActionListener(listener);
        betAmountButton5.addActionListener(listener);
        betAmountButton6.addActionListener(listener);

        // Add the bet amount buttons to the panel
        betAmountPanel.add(betAmountButton1);
        betAmountPanel.add(betAmountButton2);
        betAmountPanel.add(betAmountButton3);
        betAmountPanel.add(betAmountButton4);
        betAmountPanel.add(betAmountButton5);
        betAmountPanel.add(betAmountButton6);

        // Load images for coin icons
        tailsIcon = new ImageIcon("Casino/src/frontend/img/coin/seven300.png");
        headsIcon = new ImageIcon("Casino/src/frontend/img/coin/waterdrop300.png");

        JLabel coinLabel = new JLabel();

        coinLabel.setBackground(new Color(0, 0, 0, 0));
        coinLabel.setIcon(headsIcon);  // Set initial image

        JPanel coinSelectPanel = new JPanel(new GridLayout(1, 2));

        //TODO: resultLabel wird noch nicht verwendet und muss inplementiert werden.
        JLabel resultLabel = new JLabel();
        JLabel coinFlipBet = new JLabel();
        sevenButton = createButton("Seven", Color.RED);
        dropButton = createButton("Drop", Color.BLACK);

        // ActionListener for the "Seven" button
        ActionListener betListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();
                boolean sevenWasClicked = source.equals(sevenButton);
                Random random =  new Random();
                boolean r = random.nextBoolean();
                boolean gewonnen = r == sevenWasClicked;
                //TODO: Beide unteren S.o.p müssen im finalen Release entfernt werden.
                System.out.println(r);
                System.out.println(gewonnen);
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
                    return;
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

                               //TODO: Icon muss rictiges ergebnis wiederspiegeln
                                if (gewonnen&& sevenWasClicked) {
                                    coinLabel.setIcon(tailsIcon);
                                    Zentrale.getInstance().getActiveUser().plus(bet);
                                    //TODO: S.o.p muss durch Resultlabel umgestellt werden
                                    System.out.println("You won! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                }
                                if (gewonnen&& !sevenWasClicked) {
                                    coinLabel.setIcon(headsIcon);
                                    Zentrale.getInstance().getActiveUser().plus(bet);
                                    //TODO: S.o.p muss durch Resultlabel umgestellt werden
                                    System.out.println("You won! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                }
                                 else {
                                    Zentrale.getInstance().getActiveUser().minus(bet);
                                    //TODO: S.o.p muss durch Resultlabel umgestellt werden
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
        };

        sevenButton.addActionListener(betListener);
        dropButton.addActionListener(betListener);


        resultLabel.setForeground(Color.WHITE); // Set text color to white


        coinSelectPanel.add(sevenButton);
        coinSelectPanel.add(dropButton);




        Icon betIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/Bet.png");
        coinFlipBet.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

            }
        });


        // Create the coin flip panel

        JPanel coinFlipPanel = new JPanel(new GridBagLayout());
        coinFlipPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // Add some vertical spacing between components

        coinFlipPanel.add(coinLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0); //Add spacing between components
        coinFlipPanel.add(coinSelectPanel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0); // Add spacing between components
        coinFlipPanel.add(betAmountPanel, gbc);

        gbc.gridy = 3;
        coinFlipPanel.add(coinFlipBet, gbc);

        // Add coinFlipPanel to the main panel
        add(coinFlipPanel);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    // Utility method to create a customized button
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

    // Method to simulate coin flip
    private boolean flipCoin() {
        return rand.nextInt(2) == 0;
    }
}
