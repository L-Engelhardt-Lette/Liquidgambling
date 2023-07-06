package Backend;

/* Die User Class beinhaltet alle Eigenschaften und Methoden um die Info über die User in dem Programm zu verarbeiten
 * zur verfügung zu stellen.
 * Die Class vergibt den Benutzername, Passwort, Alter, wie viele Freispiele er hat und wie viele Perlen(Währung) er besitzt. */

public class User {
    private Currency value = new Currency(); //Währung aktivieren
    private String userName; //Benutzername
    private String password; //Passwort
    private String age; //Alter
    private int freeSpin; //Freispiele
    private int user_Pearl; //Guthaben

    public User(String userName, String age, String password) {
        this.userName = userName;
        this.age = age;
        this.password = password;
        user_Pearl = value.getPearl() * 1000; //Guthabenhöhe wird gesetzt
        freeSpin = value.getFreespin_value() -90 ; //Freispiele anzahl wird gesetzt
    }
    //Return Methode für den Benutzernamen des User
    public String getUserName() {
        return userName;
    }
    //Return Methode für das Passwort des User
    public String getPassword() {
        return password;
    }
    //Return Methode für das Alter des User
    public String getAge() {
        return age;
    }
    //Return Methode für die Freispiele des User
    public int getFreeSpin() {
        return freeSpin;
    }
    //Return Methode für das Guthaben des User
    public int getUser_Pearl() {
        return user_Pearl;
    }

    public void setUser_Pearl(int user_Pearl) {
        this.user_Pearl = user_Pearl;
    }

    //#
    public String getUser_Pearl_String(){
        String User_Pearl_String = Integer.toString(user_Pearl);
        return User_Pearl_String;
    }
    //Überschreib Methode für das Passwort des User
    public void setPassword(String password) {
        this.password = password;
    }
    //Überschreib Methode für den Benutzernamen des User
    public void setUserName(String userName) {
        this.userName = userName;
    }
    //Berechnungs Methode um gewinne auf das Guthaben des User zu addieren
    public void plus(long pearl) {
        user_Pearl += pearl;
    }
    //Berechnungs Methode um verluste des Guthaben des User ab zu ziehen
    public boolean minus(long pearl) {
        if (user_Pearl < 0) {
            return false;
        }
        user_Pearl -= pearl;
        return true;
    }
    //Return Methode
    @Override
    public String toString() {
        return getUserName() + ", " + getPassword();
    }
}

