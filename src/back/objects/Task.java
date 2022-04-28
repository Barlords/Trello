package back.objects;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class Task {

    public String name;
    public String description;
    public List<Flag> flags;
    public List<User> members;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int addMember(User member) throws IOException {

        members.add(member);
        return 1;
    }

    public int deleteMember(String name) {
        return 0;
    }

    public int addFlag(Flag flag) {
        flags.add(flag);
        return 1;
    }

    public int deleteFlag(String name) {
        return 0;
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
