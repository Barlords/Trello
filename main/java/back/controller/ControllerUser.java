package back.controller;

import app.References;
import back.objects.Task;
import com.google.gson.Gson;
import front.gui.flag.GUIFlag;
import front.gui.user.GUIUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import front.gui.main.GUIApp;
import back.objects.User;
import front.gui.task.GuiTask;
import javafx.scene.control.TextField;
import middleware.requests.APIRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ControllerUser {

    /*
    #####################
    #   Menu Trello     #
    #####################
     */

    @FXML
    private void homeTrelloMenu(ActionEvent event) throws IOException {
        event.consume();
        GUIApp.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("trello.fxml"))));
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
    public List<User> getUsersFxml() throws IOException {
        return getUsers();
    }

    @FXML
    public void deleteUserFxml() throws IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#pseudo");
        deleteUser(tf_name.getText());
    }

    @FXML
    public void createUserFxml() throws  IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#pseudo");
        createUser(new User(tf_name.getText()));
    }


    // REQUEST
    public static void createUser(User user) throws IOException {

        HttpURLConnection con = APIRequest.Create.getConByURL(new URL (References.URL_API + "/users/create"));

        APIRequest.writeBodyRequest(con, user.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static void deleteUser(String pseudo) throws IOException {

        HttpURLConnection con = APIRequest.Delete.getConByURL(new URL (References.URL_API + "/users/delete"));

        APIRequest.writeBodyRequest(con, "{\"pseudo\":\"" + pseudo + "\"}");

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static void updateUser(String pseudo, User userUp) throws IOException {
        HttpURLConnection con = APIRequest.Update.getConByURL(new URL (References.URL_API + "/users/update?pseudo=" + pseudo));

        APIRequest.writeBodyRequest(con, userUp.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static List<User> getUsers() throws IOException {

        System.out.println(References.URL_API + "/users/getAll");

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/users/getAll"));

        String response = APIRequest.getResponse(con);

        User[] users = new Gson().fromJson(response, User[].class);

        return Arrays.asList(users);
    }

    public static User getUserByPseudo(String pseudo) throws IOException {

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/users/getByPseudo?pseudo=" + pseudo));

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        User user = new Gson().fromJson(response, User.class);

        return user;
    }

    public static List<Task> getTasksAssignToUser(String pseudo) throws IOException {

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/getTasksAssignToUser?pseudo=" + pseudo));

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        Task[] tasks = new Gson().fromJson(response, Task[].class);

        return Arrays.asList(tasks);
    }

    public static void assignUserToTask(String pseudo, String taskName) throws IOException {
        HttpURLConnection con = APIRequest.Create.getConByURL(new URL (References.URL_API + "/assignUserToTask"));

        APIRequest.writeBodyRequest(con, String.format(
                "{" +
                        "\"pseudo\": \"%s\", " +
                        "\"taskName\": \"%s\"" +
                "}",
                pseudo, taskName));

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }

    public static void unassignUserToTask(String pseudo, String taskName) throws IOException {
        HttpURLConnection con = APIRequest.Delete.getConByURL(new URL (References.URL_API + "/unassignUserToTask"));

        APIRequest.writeBodyRequest(con, String.format(
                "{" +
                        "\"pseudo\": \"%s\", " +
                        "\"taskName\": \"%s\"" +
                        "}",
                pseudo, taskName));

        String response = APIRequest.getResponse(con);

        System.out.println(response);
    }


}
