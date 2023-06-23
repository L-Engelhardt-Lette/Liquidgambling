package stuff;

import Datenbank.Persistenz;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        ArrayList<User> alleUser = Persistenz.reading();
        RegisterUi regist = new RegisterUi();
        LoginUI login = new LoginUI();
        for (User user : alleUser) {
            System.out.println("User gefunden: " + user);
        }

        alleUser.add(new User(">Trööt", "55", "hurz"));



        Persistenz.writing(alleUser);


    }
}
