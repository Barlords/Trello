package front.task;

import back.objects.Task;
import back.objects.Trello;
import back.objects.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import front.flag.FrontFlag;
import front.home.FrontHome;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import front.main.Main;
import front.team.FrontTeam;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
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


    public int createTask() throws IOException {

        try {
            TextField tf_name = (TextField) Main.stage.getScene().lookup("#name");
            TextArea ta_description = (TextArea) Main.stage.getScene().lookup("#description");

            URL url = new URL ("http://localhost:3000/tasks");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String requestJson = new Task(tf_name.getText(), ta_description.getText()).toJSON();

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
        catch(Exception e) {
            System.out.println("error : " + e);
            return 0;
        }

    }

    public int getTasks() throws IOException {
        URL url = new URL ("http://localhost:3000/tasks");
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

            List<Task> tasks = new Gson().fromJson(response.toString(), new TypeToken<List<Task>>() {}.getType());
            System.out.println(tasks);
        }
        return 1;
    }

    public int updateTask() throws IOException {
        URL url = new URL ("http://localhost:3000/tasks");
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

    public int deleteTask() throws IOException {
        TextField tf_name = (TextField) Main.stage.getScene().lookup("#name");
        String name = tf_name.getText().replace(" ", "%20");

        URL url = new URL ("http://localhost:3000/tasks?name="+name);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

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

    public int addUserToTask(String userName) {





        return 1;
    }

}
