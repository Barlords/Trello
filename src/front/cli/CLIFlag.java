package front.cli;

import back.controller.ControllerFlag;
import back.controller.ControllerTask;
import back.objects.Flag;
import back.objects.Page;
import back.objects.Task;

import java.io.IOException;
import java.util.List;

public class CLIFlag {

    private static CLIFlag instance;

    private CLIFlag() {
    }

    private String getListFlagsToPrint() throws IOException {
        List<Flag> flags = ControllerFlag.getFlags();
        String str =    "    |    LISTE DES TACHES !                                                                     |\n" +
                "    |                                                                                           |\n";

        if(flags.size() == 0) {
            str += "    |        Aucune tâche dans le projet, enfin du temp libre :D                              |";
        }
        else {
            for(Flag flag : flags) {
                str += String.format("    |      -  %-78s    |\n", flag.name);
            }
        }
        return str;
    }

    private void printFrontFlagMenu() throws IOException {
        String str = CLIUtils.getInstance().getToolBar();
        str +=  "    |    MENU TAG !                                                                             |\n" +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Ajouter un tag                                                               |\n" +
                "    |        [2] - Supprimer un tag                                                             |\n" +
                "    |        [3] - Modifier un tag                                                              |\n" +
                "    |                                                                                           |\n" +
                "    |                                                                                           |\n";
        str += getListFlagsToPrint();

        str += CLIUtils.getInstance().getEndPage();

    }

    public void actionOfFlag() throws IOException {
        printFrontFlagMenu();
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
                CLIApp.getInstance().actualPage = Page.FLAG_ADD;
                break;
            case "2":
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
