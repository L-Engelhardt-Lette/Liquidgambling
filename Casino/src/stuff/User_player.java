package stuff;

public class User_player extends Player {

    private User you;

    public User_player(String name) {
        super(name);
        User you = Zentrale.getInstance().getActiveUser();
    }

}