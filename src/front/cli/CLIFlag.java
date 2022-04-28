package front.cli;

import back.objects.Page;

public class CLIFlag {

    private static CLIFlag instance;

    private CLIFlag() {
    }


    private void printFrontFlag() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯\\\n" +
                        " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |  [X]  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    MENU TAG !                                                                             |\n" +
                        "    |                                                                                           |\n" +
                        "    |    Que voulez vous faire :                                                                |\n" +
                        "    |        [1] - Voir les tags                                                                |\n" +
                        "    |        [2] - Ajouter un tag                                                               |\n" +
                        "    |        [3] - Supprimer un tag                                                             |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |  ,-----------------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________________/"
        );
    }

    public void actionOfFlag() {
        printFrontFlag();
        String choice = CLIApp.getInstance().scanChoice();
        switch(choice) {
            case "a":
                CLIApp.getInstance().actualPage = Page.TRELLO;
                break;
            case "z":
                CLIApp.getInstance().actualPage = Page.USER_MENU;
                break;
            case "e":
                CLIApp.getInstance().actualPage = Page.TASK_MENU;
                break;
            case "r":
                CLIApp.getInstance().actualPage = Page.FLAG_MENU;
                break;
            case "t":
                CLIApp.getInstance().actualPage = Page.HELP;
                break;
            case "x" :
                CLIApp.getInstance().actualPage = Page.QUIT;
                break;
            case "1":
                CLIApp.getInstance().actualPage = Page.FLAG_VIEW;
                break;
            case "2":
                CLIApp.getInstance().actualPage = Page.FLAG_ADD;
                break;
            case "3":
                CLIApp.getInstance().actualPage = Page.FLAG_DELETE;
                break;
        }
    }

    public static CLIFlag getInstance() {
        if (instance == null) {
            instance = new CLIFlag();
        }
        return instance;
    }

}
