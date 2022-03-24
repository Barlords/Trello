package flag;

import home.Home;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private void refreshFrame(ActionEvent event) throws IOException {
        event.consume();
        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("flag.fxml"))));
        Main.stage.show();
    }

}
