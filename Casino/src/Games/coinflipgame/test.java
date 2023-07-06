package Games.coinflipgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class test extends JPanel {

    private Random rand = new Random();
    private int displayedValue = 0;

    private ImageIcon headsIcon;
    private ImageIcon tailsIcon;

    private JButton sevenButton;
    private JButton dropButton;

    public test() {
        setOpaque(false); // Set the panel to be transparent
        setLayout(new GridBagLayout());

        // Load images
        headsIcon = new ImageIcon("Casino/src/frontend/img/coin/seven300.png");
        tailsIcon = new ImageIcon("Casino/src/frontend/img/coin/waterdrop300.png");

        JLabel coinLabel = new JLabel();
        coinLabel.setBackground(new Color(0, 0, 0, 0));
        coinLabel.setIcon(headsIcon);  // Set initial image

        JPanel coinSelectPanel = new JPanel(new GridLayout(1, 2));

        sevenButton = createButton("Seven", Color.RED);
        dropButton = createButton("Drop", Color.BLACK);

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

        coinSelectPanel.add(sevenButton);
        coinSelectPanel.add(dropButton);

        JPanel betAmountPanel = new JPanel(new GridLayout(2, 3));

        JButton betAmountButton1 = new JButton();
        JButton betAmountButton2 = new JButton();
        JButton betAmountButton3 = new JButton();
        JButton betAmountButton4 = new JButton();
        JButton betAmountButton5 = new JButton();
        JButton betAmountButton6 = new JButton();

        betAmountPanel.add(betAmountButton1);
        betAmountPanel.add(betAmountButton2);
        betAmountPanel.add(betAmountButton3);
        betAmountPanel.add(betAmountButton4);
        betAmountPanel.add(betAmountButton5);
        betAmountPanel.add(betAmountButton6);

        JButton coinFlipBet = new JButton();
        Icon betIcon = new ImageIcon("Casino/src/frontend/img/extraUiButtons/Bet.png");
        coinFlipBet.setIcon(betIcon);
        coinFlipBet.setOpaque(false);
        coinFlipBet.setContentAreaFilled(false);
        coinFlipBet.setBorderPainted(false);

        JButton coinFlipBack = new JButton();
        Icon backIcon = new ImageIcon("Casino/src/frontend/img/loginButton/back200.png");
        coinFlipBack.setIcon(backIcon);
        coinFlipBack.setOpaque(false);
        coinFlipBack.setContentAreaFilled(false);
        coinFlipBack.setBorderPainted(false);

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

        gbc.gridy = 4;
        coinFlipPanel.add(coinFlipBack, gbc);

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
