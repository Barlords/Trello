package front.team;

import back.objects.Flag;
import back.objects.User;
import javafx.fxml.FXMLLoader;
import front.main.Main;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class FrontTeam {

    private static FrontTeam instance = null;

    public void start() throws IOException {
        System.out.println("front.team page");

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllTeam.fxml"))));
        Main.stage.show();
    }

    public static FrontTeam getInstance() {
        if(null == instance) {
            instance = new FrontTeam();
        }
        return instance;
    }

    public void viewAll() throws IOException {
        System.out.println("viewAllFlag page");

        //TODO : view users

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("viewAllTeam.fxml"))));
        Main.stage.show();
    }

    public void add() throws IOException {
        System.out.println("viewAllFlag page");

        //TODO : add user



        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addMember.fxml"))));
        Main.stage.show();
    }

    public void delete() throws IOException {
        System.out.println("viewAllFlag page");


        //TODO : delete user

        Main.stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("deleteMember.fxml"))));
        Main.stage.show();
    }


}
