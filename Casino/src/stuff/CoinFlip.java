package stuff;

import stuff.User;

import java.util.Scanner;

import java.lang.Math;

public class CoinFlip{
    private static User k = new User("Klaus","20", "passwort");
    public static void main(String[] args)
    {
        //ersten Variablen
        double flip = Math.random();

        //scanner inizieren
        Scanner input = new Scanner(System.in);

        do {
            //Wie viel willst du Wetten
            System.out.println("How much do you wanna bet fuck off");
            int bet = input.nextInt();

            //Invalide bet
            while(bet < 0 || bet > k.getUser_Pearl())
            {
                System.out.println("Invalid bet(" + bet + "). Minimum bet = 0. Maximum bet = " + k.getUser_Pearl());
                bet = input.nextInt();
            }
            //Frage nach der Coin Seite
            System.out.println("Heads or Tails?");
            String guess = input.next();

            //Der Coin ist entweder Kopfer oder Zahl, immer Zahl außer wert geht unter 0.5
            String coin ="tails";
            if (flip < 0.5)
            {
                coin = "heads";
            }

            //Resultat für Spieler
            System.out.println("The Flip was" + coin);

            //update von Kontostand
            if(guess.equalsIgnoreCase(coin)) //erlaubt beide ergebnisse des Coins
            {
                k.plus(bet);
                System.out.println("You win" + bet);
                System.out.println("Your new Account balance" + k.getUser_Pearl());
            }
            else
            {
            k.minus(bet);
                System.out.println("You win" + bet);
                System.out.println("Your new Account balance" + k.getUser_Pearl());
            }

        }
        while(k.getUser_Pearl() > 0);
           //Tschüßßiiiiii
            System.out.println("Thank you for playing!");

        input.close();
    }
}