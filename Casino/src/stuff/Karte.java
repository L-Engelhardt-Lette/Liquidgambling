package stuff;

// Klasse für das Objekt Karte und die zwei Werte, welche jede Karte hat
// Kartenfarbe (Pik, Herz, Kreuz, Karo) und Kartenwert (2 - 14)

public class Karte {


    private final KartenWert wert;
    private final KartenFarbe farbe;

    public Karte(KartenFarbe farbe, KartenWert wert) {

        this.wert = wert;
        this.farbe = farbe;
    }

    // getter-Methoden für die Kartenfarbe und den Kartenwert

    public KartenWert getWert() {
        return wert;
    }

    public KartenFarbe getFarbe() {
        return farbe;
    }

    @Override
    public String toString() {
        return farbe.name() + " " + wert.name();
    }
}
