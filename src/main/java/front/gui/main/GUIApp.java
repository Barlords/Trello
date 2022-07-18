package front.gui.main;

import front.gui.trello.GUITrello;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class GUIApp extends Application {


    public GUIApp() {
    }

    public void launch() {
        Application.launch();
    }

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        GUIApp.stage = stage;
        GUIApp.stage.setResizable(false);
        GUIApp.stage.getIcons().add(new Image("books.png"));
        GUIApp.stage.setTitle("Trello Projet Annuel");
        GUIApp.stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("guiapp.fxml")))));

        GUITrello.getInstance().start();
    }

}
