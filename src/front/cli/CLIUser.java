package front.cli;

import back.objects.Page;
import back.objects.Task;
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

    // GET LIST
    private String getListUsersToPrint() {
        List<User> users = Trello.getInstance()._users;
        String str =    "    |    LISTE DES UTILISATEURS !                                                               |\n" +
                        "    |                                                                                           |\n";
        if(users.size() == 0) {
            str += "    |        Aucun utilisateur pour ce projet, pas très attirant votre truc :(                   |\n";
        }
        else {
            for(User user : users) {
                str += String.format("    |      -  %-78s    |\n", "'" + user.pseudo + "'");
            }
        }
        return str;
    }

    private String getListTasksAssignedToUserToPrint() throws IOException {
        List<Task> tasks = ControllerUser.getTasksAssignToUser(Trello.getInstance().currentUser.pseudo);
        String str =    "    |    TACHES A REALISER                                                                      |\n";
        if(tasks.size() == 0) {
            str += "    |        Aucune tâche, profite ;)                                                           |\n";
        }
        else {
            for(Task task : tasks) {
                str += String.format("    |        -  %-76s    |\n", "'" + task.name + "'");
            }
        }
        return str;
    }

    // SCREENS
    public void screenUser(User user) throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    UTILISATEUR                                                                            |\n" +
                String.format("    |        Pseudo : %-70s    |\n", "'" + user.pseudo + "'") +
                "    |                                                                                           |\n" +
                getListTasksAssignedToUserToPrint() +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Affecter l'utilisateur à une tâche                                           |\n" +
                "    |        [2] - Retirer l'utilisateur d'une tâche                                            |\n" +
                "    |        [3] - Modifier l'utilisateur                                                       |\n" +
                "    |        [4] - Supprimer l'utilisateur                                                      |\n" +
                "    |        [Q] - Retour                                                                       |\n" +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    public void screenUserMenu() {
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

    public void screenListUser() {
        String str =
                CLIUtils.getInstance().getHeader() +
                getListUsersToPrint() +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    // ACTIONS USER MENU
    public void actionMenuUser() throws IOException {
        Trello.getInstance()._users = ControllerUser.getUsers();
        screenUserMenu();
        String choice = CLIApp.getInstance().scanChoice(true);
        if (!CLIUtils.getInstance().actionToolBar(choice)) {
            switch (choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    consultUser();
                    break;
                default:
                    System.out.println("Merci de renseigner un choix valide");
                    break;
            }
        }
    }

    private void addUser() throws IOException {
        screenListUser();

        System.out.print(
                "CREATION D'UN UTILISATEUR !\n" +
                "{\n" +
                "    Pseudo : "
        );
        String pseudo = CLIApp.getInstance().scanNextLine(false);
        System.out.println("}");

        ControllerUser.createUser(new User(pseudo));
        CLIApp.getInstance().actualPage = Page.USER_MENU;
    }

    private void consultUser() throws IOException {
        screenListUser();

        System.out.println("CONSULTATION D'UN UTILISATEUR !");
        System.out.print("Nom de l'utilisateur à consulter : ");
        String pseudo = CLIApp.getInstance().scanNextLine(false);
        User user = ControllerUser.getUserByPseudo(pseudo);
        if(user == null) {
            System.out.println("Utilisateur non trouvé");
            CLIApp.getInstance().actualPage = Page.USER_MENU;
            return;
        }
        Trello.getInstance().currentUser = user;
        CLIApp.getInstance().actualPage = Page.USER;
    }

    // ACTIONS USER
    public void actionUser() throws IOException {
        screenUser(Trello.getInstance().currentUser);
        String choice = CLIApp.getInstance().scanChoice(true);
        if (!CLIUtils.getInstance().actionToolBar(choice)) {
            switch (choice) {
                case "1":
                    assignUserToTask();
                    CLIApp.getInstance().actualPage = Page.USER;
                    break;
                case "2":
                    unassignUserToTask();
                    CLIApp.getInstance().actualPage = Page.USER;
                    break;
                case "3":
                    updateUser();
                    CLIApp.getInstance().actualPage = Page.USER;
                    break;
                case "4":
                    deleteUser();
                    break;
                case "q":
                    CLIApp.getInstance().actualPage = Page.USER_MENU;
                    break;
                default:
                    System.out.println("Merci de renseigner un choix valide");
                    break;
            }
        }
    }

    private void assignUserToTask() throws IOException {
        CLITask.getInstance().screenListTask();
        System.out.print(
                "SE JOINDRE A UNE TACHE\n" +
                "Nom de la tâche : "
        );
        String taskName = CLIApp.getInstance().scanNextLine(false);
        ControllerUser.assignUserToTask(Trello.getInstance().currentUser.pseudo, taskName);
    }

    private void unassignUserToTask() throws IOException {
        System.out.print(
                "SE RETIRER D'UNE TACHE\n" +
                "Nom de la tâche : "
        );
        String taskName = new Scanner(System.in).nextLine();
        ControllerUser.unassignUserToTask(Trello.getInstance().currentUser.pseudo, taskName);
    }

    private void updateUser() throws IOException {
        User userUp = new User(Trello.getInstance().currentUser);

        System.out.print(
                "MODIFICATION D'UN UTILISATEUR\n" +
                        "Laisser le champ vide si vous ne voulez pas apporter de modification\n" +
                        "{\n" +
                        "    Pseudo : "
        );
        String pseudo = CLIApp.getInstance().scanNextLine(false);
        if(!pseudo.equals("")) {
            userUp.pseudo = pseudo;
        }
        System.out.println("}");

        ControllerUser.updateUser(Trello.getInstance().currentUser.pseudo, userUp);
    }

    private void deleteUser() throws IOException {
        System.out.println(
                "SUPPRESSION DE L'UTILISATEUR\n" +
                        "Etes-vous sûr de vouloir supprimer cet utilisateur ?\n" +
                        "  [y] - Oui\n" +
                        "  [n] - Non"
        );
        switch(CLIApp.getInstance().scanChoice(true)) {
            case "y":
                ControllerUser.deleteUser(Trello.getInstance().currentUser.pseudo);
                CLIApp.getInstance().actualPage = Page.USER_MENU;
                break;
            case "n":
                CLIApp.getInstance().actualPage = Page.USER;
                break;
            default:
                System.out.println("Merci de renseigner un choix valide");
                break;
        }
    }


    /**
     * Retourne l'instance du singleton
     * Si elle n'éxiste pas, créer une instance et la retourne
     * @return instance du singleton
     */
    public static CLIUser getInstance() {
        if (instance == null) {
            instance = new CLIUser();
        }
        return instance;
    }

}
