package front.cli;

import back.objects.Page;

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
                    actionOfApp();
                    break;
                case TRELLO:
                    CLITrello.getInstance().actionOfTrello();
                    break;
                case USER:
                    break;
                case USER_MENU:
                    CLIUser.getInstance().menuUser();
                    break;
                case USER_ADD:
                    CLIUser.getInstance().addUser();
                    break;
                case USER_DELETE:
                    CLIUser.getInstance().deleteUser();
                    break;
                case USER_UPDATE:
                    CLIUser.getInstance().updateUser();
                    break;
                case TASK:
                    break;
                case TASK_MENU:
                    CLITask.getInstance().menuTask();
                    break;
                case TASK_ADD:
                    CLITask.getInstance().addTask();
                    break;
                case TASK_DELETE:
                    CLITask.getInstance().deleteTask();
                    break;
                case TASK_UPDATE:
                    CLITask.getInstance().updateTask();
                    break;
                case TASK_USERS:
                    CLITask.getInstance().usersOnTask();
                    break;
                case TASK_FLAGS:
                    CLITask.getInstance().flagsOnTask();
                    break;
                case FLAG:
                    break;
                case FLAG_MENU:
                    CLIFlag.getInstance().menuFlag();
                    break;
                case FLAG_ADD:
                    CLIFlag.getInstance().addFlag();
                    break;
                case FLAG_DELETE:
                    CLIFlag.getInstance().deleteFlag();
                    break;
                case FLAG_UPDATE:
                    CLIFlag.getInstance().updateFlag();
                    break;
                case HELP:
                    break;
                case QUIT:
                    System.out.println("Hey! Il reste du taff ne part pas :P");
                    return;
                default:
                    throw new Exception("Page indéterminée");
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

    public void actionOfApp() {
        printFrontApp();
        String choice = scanChoice();
        try {
            int value = Integer.parseInt(choice);
            CLIApp.getInstance().actualPage = Page.TRELLO;
        } catch (NumberFormatException e) {
            switch(choice) {
                case "x":
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

    public String scanChoice() {
        System.out.print("Choix : ");
        return scan.nextLine().toLowerCase();
    }

    public static CLIApp getInstance() {
        if (instance == null) {
            instance = new CLIApp();
        }
        return instance;
    }

}
