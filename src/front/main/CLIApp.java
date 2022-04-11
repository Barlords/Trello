package front.main;

import front.home.CLIHome;

import java.util.Locale;
import java.util.Scanner;

public class CLIApp {

    private static CLIApp instance;
    public Scanner scan;

    public CLIApp() {
        scan = new Scanner(System.in);
    }

    public void launch() {
        int res = 0;
        while(res == 0) {
            printFrontApp();
            res = userDoAction();
        }
    }

    private void printFrontApp() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|                                                    |\n" +
                        "    |    Bienvenue sur le trello !                       |\n" +
                        "    |                                                    |\n" +
                        "    |    Projet disponible :                             |\n" +
                        "    |        [1] - Projet annuel                         |\n" +
                        "    |                                                    |\n" +
                        "    |                                   [Q] - Quitter    |\n" +
                        "    |                                                    |\n" +
                        "    |                                                    |\n" +
                        "    |  ,--------------------------------------------------,\n" +
                        "    \\_/__________________________________________________/");
    }

    private int userDoAction() {
        System.out.println("Choix : ");
        String choice = scan.nextLine();
        switch(choice.toLowerCase()) {
            case "1":
                CLIHome.getInstance().launch();
                return 1;
            case "q":
                return -1;
            default:
                return 0;
        }
    }

    public static CLIApp getInstance() {
        if (instance == null) {
            instance = new CLIApp();
        }
        return instance;
    }

}
