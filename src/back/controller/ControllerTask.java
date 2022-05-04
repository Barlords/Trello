package back.controller;

import app.References;
import back.objects.Task;
import back.objects.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import front.gui.flag.GUIFlag;
import front.gui.task.GuiTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import front.gui.main.GUIApp;
import front.gui.user.GUIUser;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import middleware.requests.APIRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ControllerTask {

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
    public List<Task> getTasksFxml() throws IOException {
        return getTasks();
    }

    @FXML
    public int deleteTaskFxml() throws IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#name");
        return deleteTask(tf_name.getText());
    }

    @FXML
    public int createTaskFxml() throws  IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#name");
        TextField tf_description = (TextField) GUIApp.stage.getScene().lookup("#description");
        return createTask(new Task(tf_name.getText(), tf_description.getText()));
    }


    public static int createTask(Task task) throws IOException {

        HttpURLConnection con = APIRequest.Create.getConByURL(new URL (References.URL_API + "/tasks/create"));

        APIRequest.writeBodyRequest(con, task.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        return 1;
    }

    public static int deleteTask(String name) throws IOException {

        HttpURLConnection con = APIRequest.Delete.getConByURL(new URL (References.URL_API + "/tasks/delete"));

        APIRequest.writeBodyRequest(con, "{\"name\":\"" + name + "\"}");

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        return 1;
    }

    public static int updateTask(String name, Task task) throws IOException {
        HttpURLConnection con = APIRequest.Update.getConByURL(new URL (References.URL_API + "/tasks/update?name=" + name));

        APIRequest.writeBodyRequest(con, task.toJSON());

        String response = APIRequest.getResponse(con);

        System.out.println(response);

        return 1;
    }

    public static List<Task> getTasks() throws IOException {
        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/tasks/getAll"));

        String response = APIRequest.getResponse(con);

        Task[] tasks = new Gson().fromJson(response.toString(), Task[].class);

        return Arrays.asList(tasks);
    }

    public static Task getTaskByName(String name) throws IOException {

        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/tasks/getByName?name=" + name));

        String response = APIRequest.getResponse(con);

        Task task = new Gson().fromJson(response, Task.class);

        return task;
    }

    public static List<Task> getTasksByStatus(Task.Status status) throws IOException {
        HttpURLConnection con = APIRequest.Get.getConByURL(new URL (References.URL_API + "/tasks/getByStatus?status=" + status.name()));

        String response = APIRequest.getResponse(con);

        Task[] tasks = new Gson().fromJson(response.toString(), Task[].class);

        return Arrays.asList(tasks);
    }



    public int addUserToTask(String userName) {
        return 1;
    }

}
