package Backend;
/* Zentrale hält den Aktiven User fest der sich einloggt und für alle Classes die ihn aufrufen müssen.
*  Der Konstruktor wird erst aufgerufen wenn kein anderer User Aktiv ist um nicht mehrere Aktive User zu haben */
public class Zentrale {
    private static Zentrale INSTANCE; //Erstellt eine Instance der Zentrale
    private User activeUser; //Der Aktive User

    private Zentrale(){}
    // Singleton pattern
    public static Zentrale getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Zentrale();
        }
        return INSTANCE;
    }

//Überschreib Methode die den eingeloggten User übergibt
    public void setActiveUser(User user) {
        this.activeUser = user;
    }
//Return Methode die den aktiven User zurück gibt
    public User getActiveUser() {
        return activeUser;
    }
}
