package stuff;

import Datenbank.Persistenz;

import java.io.*;
import java.util.ArrayList;

public class User {
    private Currency value = new Currency();
    private String userName;
    private String password;
    private String age;
    private int freeSpin;
    private int user_Pearl;



    public User(String userName, String age, String password) {

        this.userName = userName;
        this.age = age;
        this.password = password;
        user_Pearl = value.getPearl() * 1000;
        freeSpin = value.getFreespin_value() * 10;


    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    public int getFreeSpin() {
        return freeSpin;
    }

    public int getUser_Pearl() {
        return user_Pearl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void plus(long pearl) {
        user_Pearl += pearl;
    }

    public boolean minus(long pearl) {
        if (user_Pearl < 0) {
            return false;
        }
        user_Pearl -= pearl;
        return true;
    }


    @Override
    public String toString() {
        return getUserName() + ", " + getPassword();
    }


}

