package front.home;

import javafx.fxml.FXMLLoader;
import front.main.Main;

import java.io.IOException;
import java.util.Objects;

public final class FrontHome {

    private static FrontHome instance = null;

    public void start() throws IOException {
        System.out.println("front.home page");
        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));
        Main.stage.show();
    }

    public static FrontHome getInstance() {
        if(null == instance) {
            instance = new FrontHome();
        }
        return instance;
    }

}
