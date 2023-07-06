package Games.Poker;


import java.util.Arrays;
import java.util.List;

public enum Phasen {

    PRE_FLOP,
    FLOP,
    TURN,
    RIVER;


    public Phasen getNaechstePhase(){
//        if (this == PRE_FLOP){
//            return FLOP;
//        }
        //TODO....

        List<Phasen> phasens = Arrays.asList(Phasen.values());
        int index = phasens.indexOf(this);
        return index < phasens.size()-1? phasens.get(index+1): phasens.get(0);
    }




}
