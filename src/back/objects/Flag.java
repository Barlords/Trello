package back.objects;

import com.google.gson.Gson;

public class Flag {

    private int id;
    public String name;

    public Flag(String name) {
        this.name = name;
    }

    public Flag(Flag flag) {
        this.id = flag.id;
        this.name = flag.name;
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
