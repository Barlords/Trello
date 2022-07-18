package front.gui.flag;

import javafx.fxml.FXMLLoader;
import front.gui.main.GUIApp;

import java.io.IOException;
import java.util.Objects;

public final class GUIFlag {

    private static GUIFlag instance = null;
    public static GUIFlag getInstance() {
        if(null == instance) {
            instance = new GUIFlag();
        }
        return instance;
    }

    public void viewAll() throws IOException {
        System.out.println("viewAllFlag page");

        //TODO : view flags

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("viewAllFlag.fxml"))));
        GUIApp.stage.show();
    }

    public void add() throws IOException {
        System.out.println("addFlag page");

        //TODO : add flag

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("addFlag.fxml"))));
        GUIApp.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("deleteFlag page");

        //TODO : delete flag

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("deleteFlag.fxml"))));
        GUIApp.stage.show();
    }





}
