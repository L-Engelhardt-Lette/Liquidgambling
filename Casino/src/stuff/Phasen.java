package stuff;

 // TODO: Definition der Phasen
 // TODO: 1. Karten verteilen, abfragen wer mitgeht
 // TODO: 2. Flop legen : 1. Karte vom Stapel weglegen und drei Karten auf den Tisch,
 //          dann so oft rumgehen bis alle den gleichen Pot haben und diesen einsammeln
 // TODO: 3. Das Gleiche wie in Phase 2, aber nur eine Karte aufdecken.
 // TODO: 4. Das Gleiche wie in Phase 2, aber nur eine Karte aufdecken,
 //          außerdem checken wer gewonnen hat und neue Runde anfangen und alle Karten einsammeln ( oder neues Deck machen)
public enum Phasen {

    PRE_FLOP,
    FLOP,
    TURN,
    RIVER;
}
