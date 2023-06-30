package stuff;

import java.util.ArrayList;

public class Main {



    public static void main(String[] args) {

//        ArrayList<User> alleUser = Persistenz.reading();
//        //RegisterUi regist = new RegisterUi();
//        LoginUI login = new LoginUI();
//        for (User user : alleUser) {
//            System.out.println("User gefunden: " + user);
//        }
//
//        Persistenz.writing(alleUser);
//
//        ArrayList<Karte> alleKarten = new ArrayList<>();
//
//        for (KartenFarbe farbe : KartenFarbe.values()) {
//            for (KartenWert wert : KartenWert.values()) {
//                Karte meineKarte = new Karte(farbe, wert);
//                alleKarten.add(meineKarte);
//
//            }
//        }
//
//
//        Collections.shuffle(alleKarten);
//
//        Karte obersteKarte = alleKarten.remove(0);
//        Karte obersteKarte2 = alleKarten.remove(0);
//        System.out.println("Gezogen: " + obersteKarte);
//        System.out.println("Gezogen: " + obersteKarte2);


        Dealer dealer = new Dealer();
        dealer.Made_C_deck();
        dealer.spielerHinzufuegen(new Player("Ich"));
        dealer.spielerHinzufuegen(new Player("Du"));
        dealer.spielerHinzufuegen(new Player("Müllers Kuh"));
        dealer.spielerHinzufuegen(new Player("Ene"));
        dealer.spielerHinzufuegen(new Player("Mene Mu"));

        dealer.sit_down();
        dealer.deal();


        ArrayList<Karte> kartes = new ArrayList<>();
        kartes.add(new Karte(KartenFarbe.KARO, KartenWert.NEUN));
        kartes.add(new Karte(KartenFarbe.KARO, KartenWert.NEUN));
        kartes.add(new Karte(KartenFarbe.KARO, KartenWert.NEUN));
        kartes.add(new Karte(KartenFarbe.KARO, KartenWert.NEUN));
        kartes.add(new Karte(KartenFarbe.HERZ, KartenWert.NEUN));

        boolean b = Regelwerk.istFlush(kartes);

        System.out.println("Flush: " + b);

    }





}
