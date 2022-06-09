package front.cli;

import back.controller.ControllerFlag;
import back.controller.ControllerTask;
import back.controller.ControllerUser;
import back.objects.Flag;
import back.objects.Page;
import back.objects.Task;
import back.objects.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLIFlag {

    private static CLIFlag instance;

    private CLIFlag() {
    }

    private void printFrontFlag(Flag flag) throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                        "    |    TAG !                                                                                  |\n" +
                        "    |                                                                                           |\n" +
                        String.format("    |        Nom : %74s    |", flag.name) +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    private String getListFlagsToPrint() throws IOException {
        List<Flag> flags = ControllerFlag.getFlags();
        String str =    "    |    LISTE DES TAGS !                                                                     |\n" +
                "    |                                                                                           |\n";

        if(flags.size() == 0) {
            str += "    |        Aucune tag de disponible, n'hésitez pas à en créer ;)                            |";
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

        System.out.println(str);
    }

    private void printFrontFlagViewAll() throws IOException {
        String str =
                CLIUtils.getInstance().getHeader() +
                getListFlagsToPrint() +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    public void addFlag() throws IOException {
        Scanner scan = new Scanner(System.in);

        printFrontFlagViewAll();

        System.out.println("CREATION D'UN TAG !");
        System.out.print("Nom du tag : ");
        String name = scan.nextLine();

        ControllerFlag.createFlag(new Flag(name));
        CLIApp.getInstance().actualPage = Page.FLAG_MENU;
    }

    public void deleteFlag() throws IOException {
        printFrontFlagViewAll();
        System.out.println("SUPPRESSION D'UN TAG !");
        System.out.print("Nom du tag à supprimer : ");
        String choice = new Scanner(System.in).nextLine();
        ControllerFlag.deleteFlag(choice);
        CLIApp.getInstance().actualPage = Page.FLAG_MENU;
    }

    public int updateFlag() throws IOException {
        printFrontFlagViewAll();

        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICATION D'UN TAG !");
        System.out.print("Nom du tag à modifier : ");
        String choice = scan.nextLine();
        Flag flag = ControllerFlag.getFlagByName(choice);
        if(flag == null) {
            System.out.println("error : l'utilisateur n'existe pas");
            return -1;
        }

        Flag flagUp = new Flag(flag);
        System.out.println("MODIFICATION \n{");
        System.out.print("    Nom : ");
        choice = scan.nextLine();
        if(choice.equals("")) {
            System.out.println("erreur : champ vide");
            CLIApp.getInstance().actualPage = Page.FLAG_MENU;
            return -1;
        }
        flagUp.name = choice;


        ControllerFlag.updateFlag(flag.name, flagUp);
        CLIApp.getInstance().actualPage = Page.FLAG_MENU;
        return 1;
    }

    public void menuFlag() throws IOException {
        printFrontFlagMenu();
        String choice = CLIApp.getInstance().scanChoice(true);
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
            case "3":
                CLIApp.getInstance().actualPage = Page.FLAG_UPDATE;
                break;
        }
    }

    /**
     * Retourne l'instance du singleton
     * Si elle n'éxiste pas, créer une instance et la retourne
     * @return instance du singleton
     */
    public static CLIFlag getInstance() {
        if (instance == null) {
            instance = new CLIFlag();
        }
        return instance;
    }

}
