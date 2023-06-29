package Main;

import java.util.Random;


public class Coin
{
    // 0 == Heads, 1 == Tails
    private int coinSide;
    // Coin construction
    public Coin()
    {
        coinSide = 0;
    }
    // flip the coin
    public void flip()
    {
        Random flipCoin = new Random();
        coinSide = flipCoin.nextInt(2);
    }
    // ergebniss ausgeben
    public String getResult()
    {
        if(coinSide == 0)
        {
            return "Heads";
        }
        else if(coinSide == 1)
        {
            return "Tails";
        }
        else
        {
            return "Was hier los, das gibts nicht " + coinSide;
        }
    }
}