package stuff;

import java.util.ArrayList;

public class Regelwerk {





    public static boolean istFlush(ArrayList<Karte> alleKarten){


        Karte karte = alleKarten.get(0);
        KartenFarbe farbe = karte.getFarbe();

        for (Karte karte1 : alleKarten) {
            if (karte1.getFarbe() != farbe){
                return false;
            }
        }
        return true;
    }




}
