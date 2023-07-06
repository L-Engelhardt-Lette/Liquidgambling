package Games.Poker;

// Enumeration für alle verschiedenen Kartenwerte (2 - 14)

public enum KartenWert {

    ZWEI(2),
    DREI(3),
    VIER(4),
    FUENF(5),
    SECHS(6),
    SIEBEN(7),
    ACHT(8),
    NEUN(9),
    ZEHN(10),
    ELF(11),
    ZWOELF(12),
    DREIZEHN(13),
    VIERZEHN(14);

    private int value;

    KartenWert(int value){
        this.value = value;
    }

    // getter-Methode für den Kartenwert, der jeder Karte zugewiesen wird

    public int getValue() {
        return value;
    }
}
