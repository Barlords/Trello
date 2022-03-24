package task;

import flag.Flag;
import home.Home;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.Main;
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
    private void goToFrameTrello(ActionEvent event) throws IOException {
        event.consume();
        Home.getInstance().start();
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
    private void refreshFrame(ActionEvent event) throws IOException {
        event.consume();
        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("task.fxml"))));
        Main.stage.show();
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
