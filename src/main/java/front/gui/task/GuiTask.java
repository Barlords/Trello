package front.gui.task;

import javafx.fxml.FXMLLoader;
import front.gui.main.GUIApp;

import java.io.IOException;
import java.util.Objects;

public final class GuiTask {

    private static GuiTask instance = null;

    public void start() throws IOException {
        System.out.println("front.gui.task page");

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("viewAllTask.fxml"))));
        GUIApp.stage.show();
    }

    public static GuiTask getInstance() {
        if(null == instance) {
            instance = new GuiTask();
        }
        return instance;
    }

    public void viewAll() throws IOException {
        System.out.println("viewAllTask page");

        //TODO : view flags


        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("viewAllTask.fxml"))));
        GUIApp.stage.show();
    }

    public void add() throws IOException {
        System.out.println("addTask page");

        //TODO : add flag

        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("addTask.fxml"))));
        GUIApp.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("deleteTask page");

        //TODO : delete flag


        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("deleteTask.fxml"))));
        GUIApp.stage.show();
    }

}
