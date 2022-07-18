package front.gui.trello;

import javafx.fxml.FXMLLoader;
import front.gui.main.GUIApp;

import java.io.IOException;
import java.util.Objects;

public final class GUITrello {

    private static GUITrello instance = null;

    public void start() throws IOException {
        System.out.println("front.home page");
        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("trello.fxml"))));
        GUIApp.stage.show();
    }

    public static GUITrello getInstance() {
        if(null == instance) {
            instance = new GUITrello();
        }
        return instance;
    }

}
