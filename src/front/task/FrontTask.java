package front.task;

import back.objects.Flag;
import javafx.fxml.FXMLLoader;
import front.main.Main;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class FrontTask {

    private static FrontTask instance = null;

    public void start() throws IOException {
        System.out.println("front.task page");

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllTask.fxml"))));
        Main.stage.show();
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


        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllTask.fxml"))));
        Main.stage.show();
    }

    public void add() throws IOException {
        System.out.println("addTask page");

        //TODO : add flag

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addTask.fxml"))));
        Main.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("deleteTask page");

        //TODO : delete flag


        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteTask.fxml"))));
        Main.stage.show();
    }

}
