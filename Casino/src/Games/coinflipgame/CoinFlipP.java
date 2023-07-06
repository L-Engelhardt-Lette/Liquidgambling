package Games.coinflipgame;

import Backend.Zentrale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CoinFlipP extends JPanel {

    private int balance = Zentrale.getInstance().getActiveUser().getUser_Pearl();
    private Random rand = new Random();
    private ImageIcon headsIcon;
    private ImageIcon tailsIcon;

    private JButton sevenButton;
    private JButton dropButton;

    public CoinFlipP() {
        setOpaque(false); // Set the panel to be transparent
        setLayout(new GridBagLayout());

        // Load images
        headsIcon = new ImageIcon("Casino/src/frontend/img/coin/seven300.png");
        tailsIcon = new ImageIcon("Casino/src/frontend/img/coin/waterdrop300.png");

        JLabel coinLabel = new JLabel();
        coinLabel.setBackground(new Color(0, 0, 0, 0));
        coinLabel.setIcon(headsIcon);  // Set initial image

        JLabel balanceLabel = new JLabel("Balance: " + balance);
        balanceLabel.setForeground(Color.WHITE); // Set text color to white

        JTextField betField = new JTextField();
        JButton flipButton = new JButton("Flip the Coin");
        JLabel resultLabel = new JLabel();
        resultLabel.setForeground(Color.WHITE); // Set text color to white

        JButton waterdropButton = createButton("Waterdrop", Color.BLACK);
        sevenButton = createButton("Seven", Color.RED);

        JLabel betAmountLabel = new JLabel("Bet Amount: ");
        betAmountLabel.setForeground(Color.WHITE); // Set text color to white

        JPanel betButtonsPanel = createBetButtonsPanel();
        JButton resetButton = createIconButton("reset.png");
        JButton backButton = createIconButton("back.png");

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
                                    Zentrale.getInstance().getActiveUser().plus(bet);
                                    resultLabel.setText("You won! New balance: " + balance);
                                } else {
                                    balance -= bet;
                                    Zentrale.getInstance().getActiveUser().minus(bet);
                                    resultLabel.setText("You lost! New balance: " + balance                                }
                                balanceLabel.setText("Balance: " + balance);
                                ui.update();
                            }

                        }

                    });

                    timer.start();
                }
            }
        });

        sevenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sevenButton.setBackground(Color.WHITE);
                sevenButton.setForeground(Color.BLACK);
                dropButton.setBackground(Color.BLACK);
                dropButton.setForeground(Color.WHITE);
            }
        });

        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sevenButton.setBackground(Color.RED);
                dropButton.setBackground(Color.WHITE);
                dropButton.setForeground(Color.BLACK);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false); // Set the panel to be transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

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

        gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(waterdropButton, gbc);

        gbc.gridx = 1;
        panel.add(sevenButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(betAmountLabel, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(betButtonsPanel, gbc);

        gbc.gridy = 8;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(resetButton, gbc);

        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(backButton, gbc);

        add(panel);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private boolean flipCoin() {
        return rand.nextInt(2) == 0;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(darkerColor(color), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement action for the button
            }
        });
        return button;
    }

    private JPanel createBetButtonsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add 4x2 buttons
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                JButton button = createButton("", Color.BLUE);
                gbc.gridx = j;
                gbc.gridy = i;
                panel.add(button, gbc);
            }
        }

        return panel;
    }

    private JButtoncreateIconButton(String iconName) {
        ImageIcon icon = new ImageIcon("Casino/src/frontend/img/" + iconName);
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement action for the button
            }
        });
        return button;
    }

    private Color darkerColor(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float brightness = hsb[2];
        return Color.getHSBColor(hsb[0], hsb[1], brightness * 0.8f);
    }
}
