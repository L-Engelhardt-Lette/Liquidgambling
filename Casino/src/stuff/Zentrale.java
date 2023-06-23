package stuff;

public class Zentrale {

    private static Zentrale INSTANCE;
    private User activeUser;


    private Zentrale(){

    }

    // Singleton pattern
    public static Zentrale getInstance(){

        if (INSTANCE == null){
            INSTANCE = new Zentrale();
        }
        return INSTANCE;
    }


    public void setActiveUser(User user) {
        this.activeUser = user;
    }

    public User getActiveUser() {
        return activeUser;
    }
}
