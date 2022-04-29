package front.cli;

import back.objects.Page;
import back.objects.User;
import back.controller.ControllerUser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLIUser {

    private static CLIUser instance;

    private CLIUser() {
    }

    private String getListUsersToPrint() throws IOException {
        List<User> users = ControllerUser.getUsers();
        String str =    "    |    LISTE DES UTILISATEURS !                                                               |\n" +
                        "    |                                                                                           |\n";
        if(users.size() == 0) {
            str += "    |        Aucun utilisateur dans le projet, je suis tout seul :(                             |\n";
        }
        else {
            for(User user : users) {
                str += String.format("    |      -  %-82s", user.pseudo) + "|\n";
            }
        }
        return str;
    }

    private void printFrontUserMenu() throws IOException {

        String str = CLIUtils.getInstance().getToolBar() +
                        "    |    MENU UTILISATEUR !                                                                     |\n" +
                        "    |                                                                                           |\n" +
                        "    |    Que voulez vous faire :                                                                |\n" +
                        "    |        [1] - Ajouter un utilisateur                                                       |\n" +
                        "    |        [2] - Supprimer un utilisateur                                                     |\n" +
                        "    |        [3] - Modifier un utilisateur                                                      |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n";
        str += getListUsersToPrint();
        str += CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    private void printFrontUserViewAll() throws IOException {
        String str = CLIUtils.getInstance().getToolBar();
        str += getListUsersToPrint();
        str += CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    public void addUser() throws IOException {
        Scanner scan = new Scanner(System.in);

        printFrontUserViewAll();

        System.out.println("CREATION D'UN UTILISATEUR !");
        System.out.print("Nom de l'utilisateur : ");
        String choice = scan.nextLine();

        ControllerUser.createUser(new User(choice));
        CLIApp.getInstance().actualPage = Page.USER_MENU;
    }

    public void deleteUser() throws IOException {
        printFrontUserViewAll();
        System.out.println("SUPPRESSION D'UN UTILISATEUR !");
        System.out.print("Nom de l'utilisateur à supprimer : ");
        String choice = new Scanner(System.in).nextLine();
        ControllerUser.deleteUser(choice);
        CLIApp.getInstance().actualPage = Page.USER_MENU;
    }

    /*
    public void actionOfUserViewAll() throws IOException {
        printFrontUserViewAll();
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
            case "x":
                CLIApp.getInstance().actualPage = Page.QUIT;
                break;
        }
    }
*/

    public void menuUser() throws IOException {
        printFrontUserMenu();
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
                CLIApp.getInstance().actualPage = Page.USER_ADD;
                break;
            case "2":
                CLIApp.getInstance().actualPage = Page.USER_DELETE;
                break;
        }
    }

    public static CLIUser getInstance() {
        if (instance == null) {
            instance = new CLIUser();
        }
        return instance;
    }

}
