package home;

import flag.Flag;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.Main;
import task.Task;
import team.Team;

import java.io.IOException;
import java.util.Objects;

public class Controller {

    /*
    #####################
    #   Menu Trello     #
    #####################
     */

    @FXML
    private void refreshFrame(ActionEvent event) throws IOException {
        event.consume();
        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));
        Main.stage.show();
    }

    @FXML
    private void quitTrello(ActionEvent event) {
        event.consume();
        Platform.exit();
    }

    /*
    #####################
    #   Menu Team       #
    #####################
     */

    @FXML
    private void goToFrameTeam(ActionEvent event) throws IOException {
        event.consume();
        Team.getInstance().start();
    }

    /*
    #####################
    #   Menu Task       #
    #####################
     */

    @FXML
    private void goToFrameTask(ActionEvent event) throws IOException {
        event.consume();
        Task.getInstance().start();
    }

    /*
    #####################
    #   Menu Flag       #
    #####################
     */

    @FXML
    private void goToFrameFlag(ActionEvent event) throws IOException {
        event.consume();
        Flag.getInstance().start();
    }

}
