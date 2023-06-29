package stuff;

public class Player {

    private final String name;
    public Karte karte;
    public Karte karte_2;

    public Player(String name) {
        karte = null;
        karte_2 = null;
        this.name = name;
    }

    public void setKarten(Karte karte) {
        this.karte = karte;
    }

    public void setKarten2(Karte karte) {
        this.karte_2 = karte;

    }



    public Karte getKarte1() {
        return karte;
    }

    public Karte getKarte_2() {
        return karte_2;
    }

    @Override
    public String toString() {
        return name;

    }
}
