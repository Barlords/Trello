package front.task;

import front.main.CLIApp;

import java.util.Locale;

public class CLITask {

    private static CLITask instance;

    public CLITask() {

    }

    public void printFrontTask() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|  [a] - Trello  |  [z] - Utilisateur  |  [e] - Tâche  |  [r] - Tag  |  [t] - Aide  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    TACHE !                                                                        |\n" +
                        "    |                                                                                   |\n" +
                        "    |    Que voulez vous faire :                                                        |\n" +
                        "    |        [1] - Voir les tâches                                                      |\n" +
                        "    |        [2] - Ajouter une tâche                                                    |\n" +
                        "    |        [3] - Supprimer une tâche                                                  |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                  [Q] - Quitter    |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                                   |\n" +
                        "    |  ,---------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________/");
    }

    private void actionOfTask(String choice) {
        switch(choice) {

        }
    }

    public static CLITask getInstance() {
        if (instance == null) {
            instance = new CLITask();
        }
        return instance;
    }

}
