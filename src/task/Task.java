package task;

import home.Home;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;

import java.io.IOException;
import java.util.Objects;

public final class Task {

    private static Task instance = null;

    public void start() throws IOException {
        System.out.println("task page");

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("task.fxml"))));
        Main.stage.show();
    }

    public static Task getInstance() {
        if(null == instance) {
            instance = new Task();
        }
        return instance;
    }

}
