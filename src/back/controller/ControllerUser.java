package back.controller;

import app.References;
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
import java.nio.charset.StandardCharsets;
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
    public int deleteUserFxml() throws IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#pseudo");
        return deleteUser(tf_name.getText());
    }

    @FXML
    public int createUserFxml() throws  IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#pseudo");
        return createUser(new User(tf_name.getText()));
    }


    // REQUEST
    public static int createUser(User user) throws IOException {

        HttpURLConnection con = APIRequest.Create.getConByURL(new URL (References.URL_API + "/users/create"));

        APIRequest.writeBodyRequest(con, user.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        return 1;
    }

    public static int deleteUser(String pseudo) throws IOException {

        HttpURLConnection con = APIRequest.Delete.getConByURL(new URL (References.URL_API + "/users/delete"));

        APIRequest.writeBodyRequest(con, "{\"pseudo\":\"" + pseudo + "\"}");

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        return 1;
    }

    public static int updateUser(String pseudo) throws IOException {
        HttpURLConnection con = APIRequest.Update.getConByURL(new URL (References.URL_API + "/users/update"));

        // TEST
        User u =  new User("the best");
        u.id = 16;
        u.oldPseudo = u.pseudo;
        u.pseudo = "the old best";
        //

        APIRequest.writeBodyRequest(con, u.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        return 1;
    }

    public static List<User> getUsers() throws IOException {

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/users/getAll"));

        String response = APIRequest.getResponse(con);

        User[] users = new Gson().fromJson(response, User[].class);

        return Arrays.asList(users);
    }

    public static User getUsersByPseudo(String pseudo) throws IOException {

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/users/getByPseudo?pseudo=" + pseudo));

        String response = APIRequest.getResponse(con);

        User user = new Gson().fromJson(response, User.class);

        return user;
    }




}
