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
import java.io.File;
import java.io.IOException;
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

        JLabel betlabel = new JLabel("Im Pot: " + betAmount);
        betlabel.setForeground(Color.white);
        JLabel resultLabel = new JLabel("RESULT");
        resultLabel.setForeground(Color.white);

        //Font für die anderen texte die kleiner sein sollen
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Casino/src/frontend/font/Watermelon.ttf"));
            Font smallfont = font.deriveFont(40f);
            resultLabel.setFont(smallfont);
            betlabel.setFont(smallfont);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
        }

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

                                if (gewonnen&& sevenWasClicked) {
                                    coinLabel.setIcon(tailsIcon);
                                    Zentrale.getInstance().getActiveUser().plus(bet);
                                    resultLabel.setText("You won! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                    revalidate();
                                    revalidate();
                                    repaint();
                                }
                                if (gewonnen&& !sevenWasClicked) {
                                    coinLabel.setIcon(headsIcon);
                                    Zentrale.getInstance().getActiveUser().plus(bet);
                                    resultLabel.setText("You won! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                    revalidate();
                                    revalidate();
                                    repaint();
                                }
                                 else {
                                    Zentrale.getInstance().getActiveUser().minus(bet);
                                    resultLabel.setText("You lost! New balance: " + Zentrale.getInstance().getActiveUser().getUser_Pearl());
                                    revalidate();
                                    revalidate();
                                    repaint();
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


        coinFlipPanel.add(coinLabel, new GridBagConstraints(1, 1, 2, 2, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        coinFlipPanel.add(betlabel, new GridBagConstraints(1, 3, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        coinFlipPanel.add(coinSelectPanel, new GridBagConstraints(1, 4, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        coinFlipPanel.add(betAmountPanel, new GridBagConstraints(1, 5, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
        coinFlipPanel.add(resultLabel, new GridBagConstraints(1, 6, 1, 1, 1, 0f, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));


        add(coinFlipPanel);
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
