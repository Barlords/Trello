package team;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.Main;

import java.io.IOException;
import java.util.Objects;

public final class Team {

    private static Team instance = null;

    public void start() throws IOException {
        System.out.println("team page");

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("team.fxml"))));
        Main.stage.show();
    }

    public static Team getInstance() {
        if(null == instance) {
            instance = new Team();
        }
        return instance;
    }

}
