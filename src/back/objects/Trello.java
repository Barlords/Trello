package back.objects;

import java.util.List;

public class Trello {

    private static Trello instance;

    public User currentUser;
    public Task currentTask;
    public Flag currentFlag;

    public static Trello getInstance() {
        if (null == instance) {
            instance = new Trello();
        }
        return instance;
    }



}


