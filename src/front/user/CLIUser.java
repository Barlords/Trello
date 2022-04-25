package front.user;

import front.main.CLIApp;

public class CLIUser {

    private static CLIUser instance;

    private CLIUser() {
    }

    public void printFrontUser() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|  [a] - Trello  |  [z] - Utilisateur  |  [e] - Tâche  |  [r] - Tag  |  [t] - Aide  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    UTILISATEUR !                                                                       |\n" +
                        "    |                                                                                   |\n" +
                        "    |    Que voulez vous faire :                                                        |\n" +
                        "    |        [1] - Voir l'équipe                                                        |\n" +
                        "    |        [2] - Ajouter un membre à l'équipe                                         |\n" +
                        "    |        [3] - Supprimer un membre de l'équipe                                      |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                  [Q] - Quitter    |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                                   |\n" +
                        "    |  ,---------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________/");
    }

    public void actionOfUser(String choice) {
        switch(choice) {

        }
    }

    public static CLIUser getInstance() {
        if (instance == null) {
            instance = new CLIUser();
        }
        return instance;
    }

}
