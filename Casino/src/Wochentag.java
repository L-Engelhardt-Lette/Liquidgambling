public enum Wochentag {

    MONTAG(1),
    DIENSTAG(2),
    MITTWOH(3),
    DO(4),
    FREITAG(5),
    SAMSTAG(6),
    SONNTAG(7);


    private int position;

    Wochentag(int position) {
        this.position = position;
    }

    public boolean istWochenende(){

        if (this == Wochentag.SAMSTAG || this == Wochentag.SONNTAG){
            return true;
        }
        else{
            return false;
        }

    }

    public int getPosition() {
        return position;
    }
}
