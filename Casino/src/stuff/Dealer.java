package stuff;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

    ArrayList<Player> allPlayer = new ArrayList<>();
    ArrayList<Karte> alleKarten = new ArrayList<>();

    public boolean Made_C_deck() {
        for (KartenFarbe farbe : KartenFarbe.values()) {
            for (KartenWert wert : KartenWert.values()) {
                Karte meineKarte = new Karte(farbe, wert);
                alleKarten.add(meineKarte);
            }
        }
        Collections.shuffle(alleKarten);

/*        Karte obersteKarte = alleKarten.remove(0);
        Karte obersteKarte2 = alleKarten.remove(0);
        System.out.println("Gezogen: " + obersteKarte);
        System.out.println("Gezogen: " + obersteKarte2);*/
        if (alleKarten.size() == 52) {
            System.out.println("alle Karten sind vorhanden");
        }
        System.out.println("Nicht genug Karten!");
        return false;
    }


    public void spilerHiufugen(Player p) {
        allPlayer.add(p);
    }

    public boolean sit_down() {
        if (allPlayer.size() == 5) {
            System.out.println("alle Spieler sind da");
            return true;
        }
        System.out.println("nicht genug Spieler");
        return false;
    }

    public boolean deal() {

        for (Player player : allPlayer) {
            Karte obersteKarte = alleKarten.remove(0);
            player.setKarten(obersteKarte);
        }
        for (Player player : allPlayer) {
            player.setKarten2(alleKarten.remove(0));
        }


        for (Player player : allPlayer) {
            System.out.println(player + " hat: " + player.getKarte1() + " und " + player.getKarte_2());
        }
        return false;
    }
}
