package back.controller;

import app.References;
import back.objects.Flag;
import back.objects.Task;
import com.google.gson.Gson;
import front.gui.flag.GUIFlag;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import front.gui.main.GUIApp;
import front.gui.task.GuiTask;
import front.gui.user.GUIUser;
import javafx.scene.control.TextField;
import middleware.requests.APIRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ControllerFlag {

    /*
    #####################
    #   Menu Trello     #
    #####################
     */

    @FXML
    private void homeTrelloMenu(ActionEvent event) throws IOException {
        event.consume();
        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("trello.fxml"))));
        GUIApp.stage.show();
    }

    @FXML
    private void quitTrelloMenu(ActionEvent event) {
        event.consume();
        Platform.exit();
    }

    /*
    #####################
    #   Menu Team       #
    #####################
     */
    @FXML
    private void viewAllMembersMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTeam("viewAll");
    }
    @FXML
    private void addMemberMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTeam("add");
    }
    @FXML
    private void deleteMemberMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTeam("delete");
    }

    private void goToFrameTeam(String action) throws IOException {
        switch (action) {
            case "viewAll" :
                GUIUser.getInstance().viewAll();
                break;
            case "add" :
                GUIUser.getInstance().add();
                break;
            case "delete" :
                GUIUser.getInstance().delete();
                break;
            default:
                System.out.println("ERROR : goToFrameTeam switch action");
                break;
        }
    }

    /*
    #####################
    #   Menu Task       #
    #####################
     */
    @FXML
    private void viewAllTasksMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTask("viewAll");
    }
    @FXML
    private void addTaskMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTask("add");
    }
    @FXML
    private void deleteTaskMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameTask("delete");
    }


    private void goToFrameTask(String action) throws IOException {
        switch (action) {
            case "viewAll" :
                GuiTask.getInstance().viewAll();
                break;
            case "add" :
                GuiTask.getInstance().add();
                break;
            case "delete" :
                GuiTask.getInstance().delete();
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
    private void viewAllFlagsMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameFlag("viewAll");
    }
    @FXML
    private void addFlagMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameFlag("add");
    }
    @FXML
    private void deleteFlagMenu(ActionEvent event) throws IOException {
        event.consume();
        goToFrameFlag("delete");
    }

    private void goToFrameFlag(String action) throws IOException {
        switch (action) {
            case "viewAll" :
                GUIFlag.getInstance().viewAll();
                break;
            case "add" :
                GUIFlag.getInstance().add();
                break;
            case "delete" :
                GUIFlag.getInstance().delete();
                break;
            default:
                System.out.println("ERROR : goToFrameFlag switch action");
                break;
        }

    }

    /*
    #####################
    #   Buttons         #
    #####################
     */
    @FXML
    public List<Flag> getFlagsFxml() throws IOException {
        return getFlags();
    }

    @FXML
    public void deleteFlagFxml() throws IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#name");
        deleteFlag(tf_name.getText());
    }

    @FXML
    public void createFlagFxml() throws  IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#name");
        createFlag(new Flag(tf_name.getText()));
    }


    // REQUEST
    public static void createFlag(Flag flag) throws IOException {

        HttpURLConnection con = APIRequest.Create.getConByURL(new URL (References.URL_API + "/flags/create"));

        APIRequest.writeBodyRequest(con, flag.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static void deleteFlag(String name) throws IOException {

        HttpURLConnection con = APIRequest.Delete.getConByURL(new URL (References.URL_API + "/flags/delete"));

        APIRequest.writeBodyRequest(con, "{\"name\":\"" + name + "\"}");

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static void updateFlag(String name, Flag flagUp) throws IOException {
        HttpURLConnection con = APIRequest.Update.getConByURL(new URL (References.URL_API + "/flags/update?name=" + name));

        APIRequest.writeBodyRequest(con, flagUp.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static List<Flag> getFlags() throws IOException {
        System.out.println(References.URL_API + "/flags/getAll");

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/flags/getAll"));

        String response = APIRequest.getResponse(con);

        Flag[] flags = new Gson().fromJson(response, Flag[].class);

        return Arrays.asList(flags);
    }

    public static Flag getFlagByName(String name) throws IOException {

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/flags/getByName?name=" + name));

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        Flag flag = new Gson().fromJson(response, Flag.class);

        return flag;
    }

    public static List<Task> getTasksByFlag(String name) throws IOException {

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/getTasksByFlag?name=" + name));

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        Task[] tasks = new Gson().fromJson(response, Task[].class);

        return Arrays.asList(tasks);
    }

    public static void assignFlagToTask(String name, String taskName) throws IOException {
        HttpURLConnection con = APIRequest.Create.getConByURL(new URL (References.URL_API + "/assignFlagToTask"));

        APIRequest.writeBodyRequest(con, String.format(
                "{" +
                        "\"flagName\": \"%s\", " +
                        "\"taskName\": \"%s\"" +
                        "}",
                name, taskName));

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static void unassignFlagToTask(String name, String taskName) throws IOException {
        HttpURLConnection con = APIRequest.Delete.getConByURL(new URL (References.URL_API + "/unassignFlagToTask"));

        APIRequest.writeBodyRequest(con, String.format(
                "{" +
                        "\"flagName\": \"%s\", " +
                        "\"taskName\": \"%s\"" +
                        "}",
                name, taskName));

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }
}
