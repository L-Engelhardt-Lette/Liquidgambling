package stuff;

public class Currency {
    private int pearl;
    private int freespin_value;

    public Currency() {
    this.pearl = 1;
    freespin_value = pearl *100;

    }
    public int getFreespin_value(){
        return freespin_value;
    }
    public int getPearl(){
        return pearl;
    }
}
