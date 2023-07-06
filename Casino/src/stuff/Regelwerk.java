package stuff;

// TODO: Definition der Phasen
// TODO: 1. Karten verteilen, abfragen wer mitgeht
// TODO: 2. Flop legen : 1. Karte vom Stapel weglegen und drei Karten auf den Tisch,
//          dann so oft rumgehen bis alle den gleichen Pot haben und diesen einsammeln
// TODO: 3. Das Gleiche wie in Phase 2, aber nur eine Karte aufdecken.
// TODO: 4. Das Gleiche wie in Phase 2, aber nur eine Karte aufdecken,
//          außerdem checken wer gewonnen hat und neue Runde anfangen und alle Karten einsammeln ( oder neues Deck machen)

import java.util.ArrayList;

public class Regelwerk {

    private Phasen phasen;

    public void update_Phasen(Dealer dealer) {
        if (dealer.getPhasen() == Phasen.PRE_FLOP) {
            if (dealer.sit_down() == true) {
                dealer.Made_C_deck();
                dealer.deal();
                dealer.setPhasen();
            }
            if (dealer.getPhasen() == Phasen.FLOP) {
                dealer.lay_Flop();
                dealer.setPhasen();
            }
            if (dealer.getPhasen() == Phasen.TURN) {
                dealer.lay_TnR();
                dealer.setPhasen();
            }
            if (dealer.getPhasen() == Phasen.RIVER) {
                dealer.lay_TnR();
                dealer.setPhasen();
            } else {
                System.out.printf("KEINE AKTIVE PHASE!!");
            }
        }
    }


    public static boolean istFlush(ArrayList<Karte> alleKarten) {

        Karte karte = alleKarten.get(0);
        KartenFarbe farbe = karte.getFarbe();

        for (Karte karte1 : alleKarten) {
            if (karte1.getFarbe() != farbe) {
                return false;
            }
        }
        return true;
    }

    public static boolean istPaar(ArrayList<Karte> alleKarten) {

        Karte karte = alleKarten.get(0);
        KartenWert wert = karte.getWert();

        for (Karte karte1 : alleKarten) {
            if (karte1.getWert() == wert) {
                return true;
            }
        }
        return false;
    }

    public static boolean istStraße(ArrayList<Karte> alleKarten){
        for (Karte karte : alleKarten) {
            karte.getWert();
            for (Karte karte1 : alleKarten) {
                if (karte1.getWert() == karte.getWert() += 1 && karte1.getWert() == karte.getWert() += 2 &&)
            }
        }
    }
}
