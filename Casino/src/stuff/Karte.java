package stuff;

public class Karte {


    private final KartenWert wert;
    private final KartenFarbe farbe;

    public Karte(KartenFarbe farbe, KartenWert wert) {


        this.wert = wert;
        this.farbe = farbe;
    }

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
