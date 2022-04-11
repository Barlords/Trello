package front.flag;

import javafx.fxml.FXMLLoader;
import front.main.GUIApp;

import java.io.IOException;
import java.util.Objects;

public final class FrontFlag {

    private static FrontFlag instance = null;
    public static FrontFlag getInstance() {
        if(null == instance) {
            instance = new FrontFlag();
        }
        return instance;
    }

    public void viewAll() throws IOException {
        System.out.println("viewAllFlag page");

        //TODO : view flags

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllFlag.fxml"))));
        GUIApp.stage.show();
    }

    public void add() throws IOException {
        System.out.println("addFlag page");

        //TODO : add flag

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addFlag.fxml"))));
        GUIApp.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("deleteFlag page");

        //TODO : delete flag

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteFlag.fxml"))));
        GUIApp.stage.show();
    }





}
