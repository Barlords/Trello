package front.cli;

import back.controller.ControllerTrello;
import back.objects.Page;
import back.objects.Trello;
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
        List<User> users = Trello.getInstance()._users;
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

    private void printFrontUser(User user) throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    UTILISATEUR !                                                                          |\n" +
                "    |                                                                                           |\n" +
                String.format("    |        Pseudo : %-70s    |\n", user.pseudo) +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Modifier l'utilisateur                                                       |\n" +
                "    |        [2] - Supprimer l' utilisateur                                                     |\n" +
                "    |        [Q] - Retour                                                                       |\n" +
                "    |                                                                                           |\n" +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    private void printFrontUserMenu() throws IOException {

        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    MENU UTILISATEUR !                                                                     |\n" +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Ajouter un utilisateur                                                       |\n" +
                "    |        [2] - Consulter un utilisateur                                                     |\n" +
                "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                getListUsersToPrint() +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    private void printFrontUserViewAll() throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                getListUsersToPrint() +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    public void consultUser() throws IOException {
        printFrontUserViewAll();

        System.out.println("CONSULTATION D'UN UTILISATEUR !");
        System.out.print("Nom de l'utilisateur à consulter: ");
        String choice = new Scanner(System.in).nextLine();
        User user = ControllerUser.getUserByPseudo(choice);
        if(user == null) {
            System.out.println("Utilisateur non trouvé");
            CLIApp.getInstance().actualPage = Page.USER_MENU;
            return;
        }
        Trello.getInstance().currentUser = user;
        actionUser(user);

    }

    public void addUser() throws IOException {
        printFrontUserViewAll();

        System.out.println("CREATION D'UN UTILISATEUR !");
        System.out.print("Nom de l'utilisateur à créer: ");
        String choice = new Scanner(System.in).nextLine();

        ControllerUser.createUser(new User(choice));
        CLIApp.getInstance().actualPage = Page.USER_MENU;
    }

    public void updateUser() throws IOException {
        printFrontUserViewAll();

        Scanner scan = new Scanner(System.in);

        User userUp = new User(Trello.getInstance().currentUser);
        System.out.println("MODIFICATION\n" +
                "Laisser le champ vide si vous ne voulez pas apporter de modification\n" +
                "{");

        System.out.print("    Pseudo : ");
        String choice = scan.nextLine();
        if(!choice.equals("")) {
            userUp.pseudo = choice;
        }
        System.out.println("}");
        ControllerUser.updateUser(Trello.getInstance().currentUser.pseudo, userUp);

    }

    public void actionUser(User user) throws IOException {
        printFrontUser(user);
        String choice = new Scanner(System.in).nextLine();
        switch(choice) {
            case "1":
                updateUser();
                CLIApp.getInstance().actualPage = Page.USER;
                break;
            case "2":
                ControllerUser.deleteUser(Trello.getInstance().currentUser.pseudo);
                CLIApp.getInstance().actualPage = Page.USER_MENU;
                break;
            case "q":
                CLIApp.getInstance().actualPage = Page.USER_MENU;
                break;
        }
    }

    public void actionMenuUser() throws IOException {
        Trello.getInstance()._users = ControllerUser.getUsers();
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
                CLIApp.getInstance().actualPage = Page.USER;
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
