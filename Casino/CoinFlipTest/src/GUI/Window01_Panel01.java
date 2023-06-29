package GUI;

import Main.Coin;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window01_Panel01 extends JPanel
{
    private JButton button01;
    private JLabel label01; // resulatat
    private Coin coin01;
    private double flipCounter, headsCounter, tailsCounter;
    private JLabel label02; // anzahlt geworfen
    private JLabel label03; // prozentual deine mom

    // Constructor von diese panel
    public Window01_Panel01()
    {
        flipCounter = 0;
        headsCounter = 0;
        tailsCounter = 0;

        coin01 = new Coin();

        button01 = new JButton("Flip once");
        button01.addActionListener(new ButtonListener());

        label01 = new JLabel("Result : None");

        label02 = new JLabel("Number of Flipped : 0");

        label03 = new JLabel("Heads : 0 % \t Tails : 0 %");

        add(button01);
        add(label01);
        add(label02);
        add(label03);

        setBackground(Color.white);
        setPreferredSize(new Dimension(200, 80));
    }

    // wen drücken, Class COin ausführen
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            flipCounter++;

            coin01.flip();
            String result = coin01.getResult();

            headsTailsCounter(result);

            label01.setText("Result : " + result);

            label02.setText("Number of Flipped : " + (int)flipCounter);

            label03.setText("Heads : " + returnHeadsPercentage() + "% \t Tails : " + returnTailsPercentage() + " %");

        }
    }

    private void headsTailsCounter(String result)
    {
        if(result.compareToIgnoreCase("Heads") == 0)
        {
            headsCounter++;
        }
        else if(result.compareToIgnoreCase("Tails") == 0)
        {
            tailsCounter++;
        }
    }

    private double returnHeadsPercentage()
    {
        double headsResult = Math.round((headsCounter / flipCounter) * 100);
        return headsResult;
    }

    private double returnTailsPercentage()
    {
        double tailsResult = Math.round((tailsCounter / flipCounter) * 100);
        return tailsResult;
    }
}
