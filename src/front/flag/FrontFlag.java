package front.flag;

import back.objects.Flag;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXMLLoader;
import front.main.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public final class FrontFlag {

    private static FrontFlag instance = null;
    public static FrontFlag getInstance() {
        if(null == instance) {
            instance = new FrontFlag();
        }
        return instance;
    }

    public void viewAll() throws IOException {
        System.out.println("viewAllFlag page");

        //TODO : view flags

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllFlag.fxml"))));
        Main.stage.show();
    }

    public void add() throws IOException {
        System.out.println("addFlag page");

        //TODO : add flag

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addFlag.fxml"))));
        Main.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("deleteFlag page");

        //TODO : delete flag

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteFlag.fxml"))));
        Main.stage.show();
    }





}
