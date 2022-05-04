package back.objects;

import com.google.gson.Gson;

public class User {

    public int id;
    public String pseudo;

    public User(String pseudo) {
        this.pseudo = pseudo;
    }

    public User(User user) {
        this.id = user.id;
        this.pseudo = user.pseudo;
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
