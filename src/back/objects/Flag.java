package back.objects;

import com.google.gson.Gson;

public class Flag {

    int id;
    String name;
    String description;

    public Flag(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toJSON() {
        Gson g = new Gson();
        return g.toJson(this);
    }

    public void fromJSON(String json) {
        Gson g = new Gson();
        User user = g.fromJson(json, User.class);
        System.out.println(user);
    }

}
