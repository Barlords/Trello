package main;

import home.Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import task.Task;

import java.util.Objects;


public class Main extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Main.stage.getIcons().add(new Image("books.png"));
        Main.stage.setTitle("Trello Projet Annuel");
        Main.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")))));

        Home.getInstance().start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
