package back.objects;

import java.util.List;

public class Trello {

    private static Trello instance;

    public List<User> members;
    public List<Task> tasks;
    public List<Flag> flags;

    public static Trello getInstance() {
        if (null == instance) {
            instance = new Trello();
        }
        return instance;
    }



}


