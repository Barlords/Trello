package back.objects;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class Task {

    public enum Status {
        TODO,
        IN_PROGRESS,
        END;
    }

    public int id;
    public String name;
    public String description;
    public Status status;
    public List<Flag> flags;
    public List<User> members;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = Status.TODO;
    }

    public Task(Task task) {
        this.name = task.name;
        this.description = task.description;
        this.status = task.status;
        this.flags = task.flags;
        this.members = task.members;
    }

    public String toJSON() {
        Gson g = new Gson();
        return g.toJson(this);
    }

    public static User fromJSON(String json) {
        Gson g = new Gson();
        User user = g.fromJson(json, User.class);
        System.out.println(user);
        return user;
    }

}
