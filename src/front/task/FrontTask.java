package front.task;

import javafx.fxml.FXMLLoader;
import front.main.GUIApp;

import java.io.IOException;
import java.util.Objects;

public final class FrontTask {

    private static FrontTask instance = null;

    public void start() throws IOException {
        System.out.println("front.task page");

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllTask.fxml"))));
        GUIApp.stage.show();
    }

    public static FrontTask getInstance() {
        if(null == instance) {
            instance = new FrontTask();
        }
        return instance;
    }

    public void viewAll() throws IOException {
        System.out.println("viewAllTask page");

        //TODO : view flags


        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllTask.fxml"))));
        GUIApp.stage.show();
    }

    public void add() throws IOException {
        System.out.println("addTask page");

        //TODO : add flag

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addTask.fxml"))));
        GUIApp.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("deleteTask page");

        //TODO : delete flag


        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteTask.fxml"))));
        GUIApp.stage.show();
    }

}
