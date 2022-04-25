package front.trello;

import javafx.fxml.FXMLLoader;
import front.main.GUIApp;

import java.io.IOException;
import java.util.Objects;

public final class FrontTrello {

    private static FrontTrello instance = null;

    public void start() throws IOException {
        System.out.println("front.home page");
        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("trello.fxml"))));
        GUIApp.stage.show();
    }

    public static FrontTrello getInstance() {
        if(null == instance) {
            instance = new FrontTrello();
        }
        return instance;
    }

}
