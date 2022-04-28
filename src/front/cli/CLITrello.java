package front.cli;

import back.objects.Page;

public class CLITrello {

    private static CLITrello instance;

    private CLITrello() {
    }


    private void printFrontTrello() {

        String str =
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯\\\n" +
                " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |  [X]  |\n" +
                "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                "    |    PROJET ANNUEL !                                                                        |\n" +
                "    |                                                                                           |\n" +
                "    |    Tâche(s) disponible(s) :                                                               |\n";


        System.out.print(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯\\\n" +
                        " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |  [X]  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    PROJET ANNUEL !                                                                        |\n" +
                        "    |                                                                                           |\n" +
                        "    |    Tâche(s) disponible(s) :                                                               |\n" +
                        "    |        A faire :                                                                          |\n" +
                        "    |            [1] - Angular, web                                                             |\n" +
                        "    |        En cours :                                                                         |\n" +
                        "    |            [2] - Trello CLI                                                               |\n" +
                        "    |        Terminée :                                                                         |\n" +
                        "    |            [3] - Trello GUI                                                               |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |  ,-----------------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________________/");
    }

    public void actionOfTrello() {
        printFrontTrello();
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
        }
    }

    public static CLITrello getInstance() {
        if (instance == null) {
            instance = new CLITrello();
        }
        return instance;
    }

}
