package front.flag;

import front.main.CLIApp;

import java.util.Locale;

public class CLIFlag {

    private static CLIFlag instance;

    private CLIFlag() {
    }


    public void printFrontFlag() {
        System.out.println(
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                        " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    TAG !                                                                          |\n" +
                        "    |                                                                                   |\n" +
                        "    |    Que voulez vous faire :                                                        |\n" +
                        "    |        [1] - Voir les tags                                                        |\n" +
                        "    |        [2] - Ajouter un tag                                                       |\n" +
                        "    |        [3] - Supprimer un tag                                                     |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                  [Q] - Quitter    |\n" +
                        "    |                                                                                   |\n" +
                        "    |                                                                                   |\n" +
                        "    |  ,---------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________/");
    }

    public void actionOfFlag(String choice) {
        switch(choice) {
            case "a":
                break;
            case "z":
                break;
            case "e":
                break;
            case "r":
                break;
            case "t":
                break;
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "q":
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
