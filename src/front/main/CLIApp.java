package front.main;

import back.objects.Page;
import front.trello.CLITrello;
import front.user.CLIUser;

import java.net.Inet4Address;
import java.util.Scanner;

public class CLIApp {

    private static CLIApp instance;
    public Scanner scan;
    public Page actualPage;

    private CLIApp() {
        scan = new Scanner(System.in);
    }

    public void launch() throws Exception {
        actualPage = Page.APP;
        while(true) {
            switch(actualPage) {
                case APP:
                    printFrontApp();
                    actionOfApp(scanChoice());
                    break;
                case TRELLO:
                    CLITrello.getInstance().printFrontHome();
                    CLITrello.getInstance().actionOfTrello(scanChoice());
                    break;
                case USER:
                    CLIUser.getInstance().printFrontUser();
                    CLIUser.getInstance().actionOfUser(scanChoice());
                    break;
                case USER_MENU:
                    break;
                case USER_VIEW:
                    break;
                case USER_ADD:
                    break;
                case USER_DELETE:
                    break;
                case TASK:
                    break;
                case TASK_MENU:
                    break;
                case TASK_VIEW:
                    break;
                case TASK_ADD:
                    break;
                case TASK_DELETE:
                    break;
                case FLAG:
                    break;
                case FLAG_MENU:
                    break;
                case FLAG_VIEW:
                    break;
                case FLAG_ADD:
                    break;
                case FLAG_DELETE:
                    break;
                case QUIT:
                    System.out.println("Hey! Il reste du taff ne part pas :P");
                    return;
                default:
                    throw new Exception("CLIApp -> launch -> switch(choice) -> default");
            }
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

    public void actionOfApp(String choice) {
        try {
            int value = Integer.parseInt(choice);
            CLIApp.getInstance().actualPage = Page.TRELLO;
        } catch (NumberFormatException e) {
            switch(choice) {
                case "q":
                    CLIApp.getInstance().actualPage = Page.QUIT;
                    break;
                default:
                    System.out.println(choice + " n'est pas un choix valide");
            }
        }
    }

    public void checkBDDTrello(int id) {
        if(id == 1) {
            CLIApp.getInstance().actualPage = Page.TRELLO;
        }
    }

    private String scanChoice() {
        System.out.println("Choix : ");
        return scan.nextLine().toLowerCase();
    }

    public static CLIApp getInstance() {
        if (instance == null) {
            instance = new CLIApp();
        }
        return instance;
    }

}
