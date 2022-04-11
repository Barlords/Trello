package front.team;

import front.main.CLIApp;

import java.util.Locale;

public class CLITeam {

    private static CLITeam instance;

    public CLITeam() {

    }

    public void launch() {
        int res = 0;
        while(res == 0) {
            printFrontTeam();
            res = userDoAction();
        }
    }

    private void printFrontTeam() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|  [a] - Trello  |  [z] - Equipe  |  [e] - Tâche  |  [r] - Tag  |  [t] - Aide  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    EQUIPE !                                                                  |\n" +
                        "    |                                                                              |\n" +
                        "    |    Que voulez vous faire :                                                   |\n" +
                        "    |        [1] - Voir l'équipe                                                   |\n" +
                        "    |        [2] - Ajouter un membre à l'équipe                                    |\n" +
                        "    |        [3] - Supprimer un membre de l'équipe                                 |\n" +
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

    public static CLITeam getInstance() {
        if (instance == null) {
            instance = new CLITeam();
        }
        return instance;
    }

}
