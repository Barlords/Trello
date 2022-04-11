package front.home;

import front.flag.FrontFlag;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import front.main.GUIApp;
import front.task.FrontTask;
import front.team.FrontTeam;

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
        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml"))));
        GUIApp.stage.show();
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
    private void viewAllMembers(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTeam("viewAll");
    }
    @FXML
    private void addMember(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTeam("add");
    }
    @FXML
    private void deleteMember(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTeam("delete");
    }

    private void goToFrameTeam(String action) throws IOException {
        switch (action) {
            case "viewAll" :
                FrontTeam.getInstance().viewAll();
                break;
            case "add" :
                FrontTeam.getInstance().add();
                break;
            case "delete" :
                FrontTeam.getInstance().delete();
                break;
            default:
                System.out.println("ERROR : goToFrameTask switch action");
                break;
        }
    }

    /*
    #####################
    #   Menu Task       #
    #####################
     */
    @FXML
    private void viewAllTasks(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTask("viewAll");
    }
    @FXML
    private void addTask(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTask("add");
    }
    @FXML
    private void deleteTask(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTask("delete");
    }


    private void goToFrameTask(String action) throws IOException {
        switch (action) {
            case "viewAll" :
                FrontTask.getInstance().viewAll();
                break;
            case "add" :
                FrontTask.getInstance().add();
                break;
            case "delete" :
                FrontTask.getInstance().delete();
                break;
            default:
                System.out.println("ERROR : goToFrameTask switch action");
                break;
        }
    }


    /*
    #####################
    #   Menu Flag       #
    #####################
     */

    @FXML
    private void viewAllFlags(ActionEvent event) throws IOException {
        event.consume();
        goToFrameFlag("viewAll");
    }
    @FXML
    private void addFlag(ActionEvent event) throws IOException {
        event.consume();
        goToFrameFlag("add");
    }
    @FXML
    private void deleteFlag(ActionEvent event) throws IOException {
        event.consume();
        goToFrameFlag("delete");
    }

    private void goToFrameFlag(String action) throws IOException {
        switch (action) {
            case "viewAll" :
                FrontFlag.getInstance().viewAll();
                break;
            case "add" :
                FrontFlag.getInstance().add();
                break;
            case "delete" :
                FrontFlag.getInstance().delete();
                break;
            default:
                System.out.println("ERROR : goToFrameFlag switch action");
                break;
        }

    }

}
