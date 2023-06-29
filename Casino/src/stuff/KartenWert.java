package stuff;

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

    public int getValue() {
        return value;
    }
}
