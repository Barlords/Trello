package front.flag;

import front.main.CLIApp;

import java.util.Locale;

public class CLIFlag {

    private static CLIFlag instance;

    public CLIFlag() {

    }

    public void launch() {
        int res = 0;
        while(res == 0) {
            printFrontFlag();
            res = userDoAction();
        }
    }

    private void printFrontFlag() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|  [a] - Trello  |  [z] - Equipe  |  [e] - Tâche  |  [r] - Tag  |  [t] - Aide  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    TAG !                                                                     |\n" +
                        "    |                                                                              |\n" +
                        "    |    Que voulez vous faire :                                                   |\n" +
                        "    |        [1] - Voir les tags                                                   |\n" +
                        "    |        [2] - Ajouter un tag                                                  |\n" +
                        "    |        [3] - Supprimer un tag                                                |\n" +
                        "    |                                                                              |\n" +
                        "    |                                                             [Q] - Quitter    |\n" +
                        "    |                                                                              |\n" +
                        "    |                                                                              |\n" +
                        "    |  ,----------------------------------------------------------------------------,\n" +
                        "    \\_/____________________________________________________________________________/");
    }

    private int userDoAction() {
        System.out.println("Choix : ");
        String choice = CLIApp.getInstance().scan.nextLine();
        switch(choice.toLowerCase()) {

        }
        return 0;
    }

    public static CLIFlag getInstance() {
        if (instance == null) {
            instance = new CLIFlag();
        }
        return instance;
    }

}
