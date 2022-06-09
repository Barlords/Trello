package front.cli;

import back.objects.Page;

import java.util.Scanner;

public class CLIApp {

    private static CLIApp instance;
    private final Scanner scanner;
    public Page actualPage;

    private CLIApp() {
        scanner = new Scanner(System.in);
    }


    public void launch() throws Exception {
        actualPage = Page.APP;
        while(true) {
            switch(actualPage) {
                case APP:
                    actionAppMenu();
                    break;
                case TRELLO:
                    CLITrello.getInstance().actionTrello();
                    break;
                case USER:
                    CLIUser.getInstance().actionUser();
                    break;
                case USER_MENU:
                    CLIUser.getInstance().actionMenuUser();
                    break;
                case TASK:
                    CLITask.getInstance().actionTask();
                    break;
                case TASK_MENU:
                    CLITask.getInstance().actionMenuTask();
                    break;
                case FLAG:
                    break;
                case FLAG_MENU:
                    CLIFlag.getInstance().menuFlag();
                    break;
                case HELP:
                    break;
                case QUIT:
                    System.out.println("Hey! Il reste du taff ne partez pas :P");
                    return;
                default:
                    throw new Exception("Page indéterminée");
            }
        }
    }

    private void screenAppMenu() {
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

    /**
     * Gère les actions lié au screen de l'application
     * Actualise la page en fonction de l'action
     */
    public void actionAppMenu() {
        screenAppMenu();
        String choice = scanChoice(true);
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

    public String scanChoice(boolean toLowerCase) {
        System.out.print("Choix : ");
        return scanNextLine(toLowerCase);
    }

    public String scanNextLine(boolean toLowerCase) {
        if(toLowerCase)  {
            return scanner.nextLine().toLowerCase();
        }
        return scanner.nextLine();
    }

    /**
     * Retourne l'instance du singleton
     * Si elle n'éxiste pas, créer une instance et la retourne
     * @return instance du singleton
     */
    public static CLIApp getInstance() {
        if (instance == null) {
            instance = new CLIApp();
        }
        return instance;
    }

}
