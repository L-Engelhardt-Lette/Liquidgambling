package stuff;
// Klasse für die beiden Währungen des Users.
// Pearl = Geldwährung, welche auch durch die games verdient wird.
// Freespin = 1 mal ohne Pearl zu bezahlen spielen.
public class Currency {
    private int pearl;
    private int freespin_value;

    public Currency() {
        this.pearl = 1;
        freespin_value = pearl *100;

        // getter-Methoden für die beiden Währungen.
    }
    public int getFreespin_value(){
        return freespin_value;
    }
    public int getPearl(){
        return pearl;
    }
}
