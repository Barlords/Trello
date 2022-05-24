package back.objects;

import java.util.ArrayList;
import java.util.List;

public class Trello {

    private static Trello instance;

    public List<User> _users = new ArrayList<>();
    public List<Task> _tasks = new ArrayList<>();
    public List<Flag> _flags = new ArrayList<>();

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


