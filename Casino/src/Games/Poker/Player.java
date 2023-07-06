package Games.Poker;

public class Player {

    //TODO: Check, Raise, Fold ( Karten wegnehmen, oder boolean ) -- Methoden hinzufügen.
    //TODO: Einen eigenen Geldstapel und einen eigenen Stapel wo das gesetzte Geld dieser Runde draufkommt, was der Dealer abliest.
    //TODO: Evtl. getter und setter Methoden der einzelnen Sachen, sodass der Dealer die Aktionen lesen kann.

    private final String name;
    public Karte karte;
    public Karte karte_2;
    private int chips;
    private int pot;
    private int your_move;


    public Player(String name) {
        karte = null;
        karte_2 = null;
        this.name = name;
        chips = 1000;
        pot = 0;
        your_move = 0;
    }

    public void setYour_move (int your_move){
        this.your_move = your_move;
    }

    public int isYour_move() {
        return your_move;
    }

    // Methoden für das setzen der Karten
    public void setKarten(Karte karte) {
        this.karte = karte;
    }

    public void setKarten2(Karte karte) {
        this.karte_2 = karte;
    }

    // Der Player bietet Chips, welche von seinen Chips abgezogen werden und dem eigenen Pot hinzugefügt
    public int bet_chips(int chips){
        if (chips <= this.chips) {
            this.chips -= chips;
            System.out.println(chips + "Chips wurden gesetzt");
            return pot += chips;
        }
        else{
            return pot;
        }
    }

    // getter-Methode für den Pot des einzelnen Spielers
    public int getPot() {
        return pot;
    }

    // getter-Methode für die Chips des Spielers
    public int getChips(){
        return chips;
    }

    // getter-Methode für die erste Handkarte
    public Karte getKarte1() {
        return karte;
    }

    // getter-Methode für die zweite Handkarte
    public Karte getKarte_2() {
        return karte_2;
    }

    // Methode, die den Pot des Spielers leert
    public int emptyPot(){

        int temp = pot;
        pot = 0;
        return temp;
    }



    @Override
    public String toString() {
        return name;
    }
}