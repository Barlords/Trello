package front.home;

import front.main.CLIApp;

import java.util.Locale;

public class CLIHome {

    private static CLIHome instance;

    public CLIHome() {

    }

    public void launch() {
        int res = 0;
        while(res == 0) {
            printFrontHome();
            res = userDoAction();
        }
    }

    private void printFrontHome() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|  [a] - Trello  |  [z] - Equipe  |  [e] - Tâche  |  [r] - Tag  |  [t] - Aide  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    PROJET ANNUEL !                                                           |\n" +
                        "    |                                                                              |\n" +
                        "    |    Tâche(s) disponible(s) :                                                  |\n" +
                        "    |        A faire :                                                             |\n" +
                        "    |            [1] - Angular, web                                                |\n" +
                        "    |        En cours :                                                            |\n" +
                        "    |            [2] - Trello CLI                                                  |\n" +
                        "    |        Terminée :                                                            |\n" +
                        "    |            [3] - Trello GUI                                                  |\n" +
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

    public static CLIHome getInstance() {
        if (instance == null) {
            instance = new CLIHome();
        }
        return instance;
    }

}
