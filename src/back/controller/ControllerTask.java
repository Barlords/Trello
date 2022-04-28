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


    public static int createTask(Task task) throws IOException {

        try {
            URL url = new URL (References.URL_API + "/tasks/create");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String requestJson = task.toJSON();

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = requestJson.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                System.out.println(con.getResponseCode());
                System.out.println(con.getResponseMessage());
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response);
            }
            return 1;
        }
        catch(Exception e) {
            System.out.println("error : " + e);
            return 0;
        }

    }

    public static List<Task> getTasks() throws IOException {
        URL url = new URL (References.URL_API + "/tasks/getAll");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println(response);

            Task[] tasks = new Gson().fromJson(response.toString(), Task[].class);
            return Arrays.asList(tasks);
        }
    }

    public static int updateTask() throws IOException {
        URL url = new URL (References.URL_API + "/tasks/update");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        //TODO : update
        Task t =  new Task("tres urgent", "encore plus qu'avant");

        String requestJson = t.toJSON();

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = requestJson.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }

        return 1;
    }

    public static int deleteTask(String name) throws IOException {

        URL url = new URL (References.URL_API + "/tasks/delete");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String requestJson = "{\"name\":\"" + name + "\"}";

        System.out.println(requestJson);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = requestJson.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }

        return 1;
    }

    @FXML
    public List<Task> getTasksFxml() throws IOException {
        return getTasks();
    }

    @FXML
    public int deleteTaskFxml() throws IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#pseudo");
        return deleteTask(tf_name.getText());
    }

    @FXML
    public int createTaskFxml() throws  IOException {
        TextField tf_name = (TextField) GUIApp.stage.getScene().lookup("#name");
        TextField tf_description = (TextField) GUIApp.stage.getScene().lookup("#description");
        return createTask(new Task(tf_name.getText(), tf_description.getText()));
    }

    public int addUserToTask(String userName) {





        return 1;
    }

}
