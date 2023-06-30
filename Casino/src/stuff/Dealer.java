package stuff;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Dealer {

    // TODO : Den Pot an den Gewinner verteilen
    // TODO : Eine Methode, welche bestimmt, welcher Spieler gerade an der Reihe ist.
    // TODO : Update-Methode, wen jeder fertig ist beginnt eine neue Phase mit Enum( Flop, River, usw.).
    // TODO : Checken der Aktion des Players
    // TODO : Komplett Runde fertig.
    // TODO : Karten wegnehmen Methode nach der Runde oder nach jedem Fold.
    // TODO : Phase Karten legen.

    ArrayList<Player> allPlayer = new ArrayList<>();
    ArrayList<Karte> alleKarten = new ArrayList<>();
    private int pot;
    private int position;
    private Phasen phasen;


    // Methode zum Kartendeck erstellen.
    public boolean Made_C_deck() {
        for (KartenFarbe farbe : KartenFarbe.values()) {
            for (KartenWert wert : KartenWert.values()) {
                Karte meineKarte = new Karte(farbe, wert);
                alleKarten.add(meineKarte);
            }
        }
        Collections.shuffle(alleKarten);


        if (alleKarten.size() == 52) {
            System.out.println("alle Karten sind vorhanden");
        }
        System.out.println("Nicht genug Karten!");
        return false;
    }

    // Methode zum hinzufügen der einzelnen Spieler
    public void spielerHinzufuegen(Player p) {
        int your_move;
        allPlayer.add(p);
        p.setYour_move(p.isYour_move() + 1);
        your_move = p.isYour_move();
    }

    public boolean sit_down() {
        if (allPlayer.size() == 5) {
            System.out.println("alle Spieler sind da");
            return true;
        }
        System.out.println("nicht genug Spieler");
        return false;
    }

    // Methode zum austeilen der Karten an die Spieler
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

    public void setPhasen(Phasen phasen){
        this.phasen = phasen;
    }

    public Phasen getPhasen(){
        return phasen;
    }

    // Methode für das Entfernen der Handkarten eines Spielers, falls dieser folden will
    public void fold(Player player){

        player.setKarten(null);
        player.setKarten2(null);

    }

    // Methode, welche checkt, ob der Pot aller Spieler der Gleiche ist
    private boolean checkIfPotsEqual(){
        int firstPot = allPlayer.get(0).getPot();

        for (Player player : allPlayer) {
            int pot = player.getPot();
            if (firstPot != pot){
                return false;
            }
        }
        return true;
    }

    public void dealer_Pot(){

        // erstmal checken ob alle gleich viel haben
        if (checkIfPotsEqual() == false){
            return;
        }

        // alle gleich viel: taschen leeren und uns geben:
        for (Player player : allPlayer) {
            this.pot += player.emptyPot();
        }

    }

    // getter-Methode für den Pot des Dealers
    public int getPot() {
        return pot;
    }

    // Methode zum kicken der Spieler, wenn diese verloren haben
    public void kick_player(){

        HashSet<Player> toBeRemoved = new HashSet<>();
        for (Player player : allPlayer) {
            if (player.getChips() <= 0){
                toBeRemoved.add(player);
            }
        }
        allPlayer.removeAll(toBeRemoved);
    }

    public void next_Player(){

    }

}
