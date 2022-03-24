package flag;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;
import task.Task;

import java.io.IOException;
import java.util.Objects;

public final class Flag {

    private static Flag instance = null;

    public void start() throws IOException {
        System.out.println("flag page");

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("flag.fxml"))));
        Main.stage.show();
    }

    public static Flag getInstance() {
        if(null == instance) {
            instance = new Flag();
        }
        return instance;
    }

}
