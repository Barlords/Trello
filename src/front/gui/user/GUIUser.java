package front.gui.user;

import javafx.fxml.FXMLLoader;
import front.gui.main.GUIApp;

import java.io.IOException;
import java.util.Objects;

public final class GUIUser {

    private static GUIUser instance = null;

    public void start() throws IOException {
        System.out.println("front.team page");

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllUser.fxml"))));
        GUIApp.stage.show();
    }

    public static GUIUser getInstance() {
        if(null == instance) {
            instance = new GUIUser();
        }
        return instance;
    }

    public void viewAll() throws IOException {
        System.out.println("viewAllFlag page");

        //TODO : view users

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllUser.fxml"))));
        GUIApp.stage.show();
    }

    public void add() throws IOException {
        System.out.println("viewAllFlag page");

        //TODO : add user

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addUser.fxml"))));
        GUIApp.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("viewAllFlag page");


        //TODO : delete user

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteUser.fxml"))));
        GUIApp.stage.show();
    }


}
