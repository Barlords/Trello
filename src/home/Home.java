package home;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Main;
import task.Task;

import java.io.IOException;
import java.util.Objects;

public final class Home {

    private static Home instance = null;

    public void start() throws IOException {
        System.out.println("home page");
        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));
        Main.stage.show();
    }

    public static Home getInstance() {
        if(null == instance) {
            instance = new Home();
        }
        return instance;
    }

}
