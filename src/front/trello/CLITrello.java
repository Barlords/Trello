package front.trello;

import back.objects.Page;
import front.main.CLIApp;

public class CLITrello {

    private static CLITrello instance;

    private CLITrello() {
    }


    public void printFrontHome() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    PROJET ANNUEL !                                                                |\n" +
                        "    |                                                                                   |\n" +
                        "    |    Tâche(s) disponible(s) :                                                       |\n" +
                        "    |        A faire :                                                                  |\n" +
                        "    |            [1] - Angular, web                                                     |\n" +
                        "    |        En cours :                                                                 |\n" +
                        "    |            [2] - Trello CLI                                                       |\n" +
                        "    |        Terminée :                                                                 |\n" +
                        "    |            [3] - Trello GUI                                                       |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                  [Q] - Quitter    |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                                   |\n" +
                        "    |  ,---------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________/");
    }

    public void actionOfTrello(String choice) {
        switch(choice) {
            case "a":
                CLIApp.getInstance().actualPage = Page.TRELLO;
        }
    }

    public static CLITrello getInstance() {
        if (instance == null) {
            instance = new CLITrello();
        }
        return instance;
    }

}
