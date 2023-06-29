public enum Card {

    K2(2),
    K3(3),
    K4(4),
    K5(5),
    K6(6),
    K7(7),
    K8(8),
    K9(9),
    K10(10),
    K11(11),
    K12(12),
    K13(13),
    K14(14),
    C2(2),
    C3(3),
    C4(4),
    C5(5),
    C6(6),
    C7(7),
    C8(8),
    C9(9),
    C10(10),
    C11(11),
    C12(12),
    C13(13),
    C14(14),
    H2(2),
    H3(3),
    H4(4),
    H5(5),
    H6(6),
    H7(7),
    H8(8),
    H9(9),
    H10(10),
    H11(11),
    H12(12),
    H13(13),
    H14(14),
    P2(2),
    P3(3),
    P4(4),
    P5(5),
    P6(6),
    P7(7),
    P8(8),
    P9(9),
    P10(10),
    P11(11),
    P12(12),
    P13(13),
    P14(14);


    private int value;

    Card(int value) {
        this.value = value;
    }

//    public int handvalue(int value2){
//        if (this == Card.K14  ||  this == Card.C14  || this == Card.H14  || this == Card.P14){
//            value2 = 14;
//            return value2;
//        }
//
//
//
//    }



    public int getValue() {
        return value;
    }
}
