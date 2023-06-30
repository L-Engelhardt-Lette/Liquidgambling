package coinflipgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CoinFlipP extends JPanel {

    private int balance = 100;
    private Random rand = new Random();

    private ImageIcon headsIcon;
    private ImageIcon tailsIcon;

    public CoinFlipP() {
        setOpaque(false); // Set the panel to be transparent
        setLayout(new GridBagLayout());

        // Load images
        headsIcon = new ImageIcon("Casino/src/frontend/img/coin/seven300.png");
        tailsIcon = new ImageIcon("Casino/src/frontend/img/coin/waterdrop300.png");

        JLabel coinLabel = new JLabel();
        coinLabel.setIcon(headsIcon);  // Set initial image

        JLabel balanceLabel = new JLabel("Balance: " + balance);
        balanceLabel.setForeground(Color.WHITE); // Set text color to white

        JTextField betField = new JTextField();
        JButton flipButton = new JButton("Flip the Coin");
        JLabel resultLabel = new JLabel();
        resultLabel.setForeground(Color.WHITE); // Set text color to white

        flipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    flipButton.setEnabled(false);  // Disable button during animation

                    // Start flip animation
                    Timer timer = new Timer(100, new ActionListener() {
                        private int count = 0;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (count < 10) {
                                coinLabel.setIcon(count % 2 == 0 ? headsIcon : tailsIcon);
                                count++;
                            } else {
                                ((Timer) e.getSource()).stop();  // Stop the animation
                                flipButton.setEnabled(true);  // Enable button after animation

                                boolean win = flipCoin();
                                if (win) {
                                    balance += bet;
                                    resultLabel.setText("You won! New balance: " + balance);
                                } else {
                                    balance -= bet;
                                    resultLabel.setText("You lost! New balance: " + balance);
                                }
                                balanceLabel.setText("Balance: " + balance);
                            }
                        }
                    });

                    timer.start();
                }
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Set the panel to be transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(coinLabel, gbc);

        gbc.gridy = 1;
        panel.add(balanceLabel, gbc);

        gbc.gridy = 2;
        panel.add(betField, gbc);

        gbc.gridy = 3;
        panel.add(flipButton, gbc);

        gbc.gridy = 4;
        panel.add(resultLabel, gbc);

        add(panel);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private boolean flipCoin() {
        return rand.nextInt(2) == 0;
    }
}
