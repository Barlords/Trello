package front.cli;

import back.controller.ControllerFlag;
import back.controller.ControllerTask;
import back.controller.ControllerUser;
import back.objects.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLITask {

    private static CLITask instance;

    private CLITask() {
    }

    // GET LIST
    private String getListTasksToPrint() throws IOException {
        List<Task> tasks = Trello.getInstance()._tasks;
        String str =    "    |    LISTE DES TACHES !                                                                     |\n" +
                        "    |                                                                                           |\n";

        if(tasks.size() == 0) {
            str += "    |        Aucune tâche dans le projet, enfin du temp libre :D                              |";
        }
        else {
            for(Task task : tasks) {
                str += String.format("    |      -  %-76s    |\n", "'"+task.name+"'");
                if(task.description.length() > 74) {
                    str += String.format("    |             %-71s...    |\n", task.description.substring(0, 71));
                }
                else {
                    str += String.format("    |             %-74s    |\n", task.description);
                }
            }
        }
        return str;
    }

    private String getListUsersAssignedToTaskToPrint() throws IOException {
        List<User> users = ControllerTask.getUsersAssignToTask(Trello.getInstance().currentTask.name);
        String str =    "    |    UTILISATEURS ASSIGNE A LA TÂCHE                                                                      |\n";
        if(users.size() == 0) {
            str += "    |        Aucun utilisateur.                                                                               |\n";
        }
        else {
            for(User user : users) {
                str += String.format("    |        -  %-76s    |\n", "'" + user.pseudo + "'");
            }
        }
        return str;
    }

    private String getListFlagsAssignedToTaskToPrint() throws IOException {
        List<Flag> flags = ControllerTask.getFlagsAssignToTask(Trello.getInstance().currentTask.name);
        String str =    "    |    TAG ATTRIBUE A LA TÂCHE                                                                      |\n";
        if(flags.size() == 0) {
            str += "    |        Aucun tag.                                                                                       |\n";
        }
        else {
            for(Flag flag : flags) {
                str += String.format("    |        -  %-76s    |\n", "'" + flag.name + "'");
            }
        }
        return str;
    }

    // SCREEN
    public void screenTask(Task task) throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    TACHE !                                                                                |\n" +
                String.format("    |        Nom : %-70s    |\n", task.name) +
                String.format("    |        Description : %-62s    |\n", task.description) +
                getListUsersAssignedToTaskToPrint() +
                getListFlagsAssignedToTaskToPrint() +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Affecter un utilisateur à cette tâche                                        |\n" +
                "    |        [2] - Retirer un utilisateur de cette tâche                                        |\n" +
                "    |        [3] - Ajouter un tag à cette tâche                                                 |\n" +
                "    |        [4] - Retirer un tag de cette tâche                                                |\n" +
                "    |        [5] - Modifier la tâche                                                            |\n" +
                "    |        [6] - Supprimer la tâche                                                           |\n" +
                "    |        [Q] - Retour                                                                       |\n" +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    public void screenTaskMenu() throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    MENU TACHE !                                                                           |\n" +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Ajouter une tâche                                                            |\n" +
                "    |        [2] - Consulter une tâche                                                          |\n" +
                "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                getListTasksToPrint() +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    public void screenListTask() throws IOException {
        String str =
                CLIUtils.getInstance().getHeader() +
                getListTasksToPrint() +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    // ACTIONS TASK MENU
    public void actionMenuTask() throws IOException {
        Trello.getInstance()._tasks = ControllerTask.getTasks();
        screenTaskMenu();
        String choice = CLIApp.getInstance().scanChoice(true);
        CLIUtils.getInstance().actionToolBar(choice);
        switch (choice) {
            case "1":
                addTask();
                break;
            case "2":
                consultTask();
                break;
            default:
                System.out.println("Merci de renseigner un choix valide");
                break;
        }
    }

    private void addTask() throws IOException {
        screenListTask();

        System.out.print(
                "CREATION D'UNE TACHE !\n" +
                "{\n" +
                "    Nom : "
        );
        String name = CLIApp.getInstance().scanNextLine(false);
        System.out.print(
                "    Description : "
        );
        String description = CLIApp.getInstance().scanNextLine(false);
        System.out.println("}");

        ControllerTask.createTask(new Task(name, description));
        CLIApp.getInstance().actualPage = Page.TASK_MENU;
    }

    private void consultTask() throws IOException {
        screenListTask();

        System.out.println("CONSULTATION D'UNE TACHE !");
        System.out.print("Nom de la tâche à consulter : ");
        String choice = CLIApp.getInstance().scanNextLine(false);
        Task task = ControllerTask.getTaskByName(choice);
        if(task == null) {
            System.out.println("Tâche non trouvée");
            CLIApp.getInstance().actualPage = Page.TASK_MENU;
            return;
        }
        Trello.getInstance().currentTask = task;
        CLIApp.getInstance().actualPage = Page.TASK;
    }

    // ACTIONS TASK
    public void actionTask() throws IOException {
        screenTask(Trello.getInstance().currentTask);
        String choice = CLIApp.getInstance().scanChoice(true);
        CLIUtils.getInstance().actionToolBar(choice);
        switch(choice) {
            case "1":
                assignUserToTask();
                break;
            case "2":
                unassignUserToTask();
                break;
            case "3":
                assignFlagToTask();
                break;
            case "4":
                unassignFlagToTask();
                break;
            case "5":
                updateTask();
                break;
            case "6":
                deleteTask();
                break;
            case "q":
                CLIApp.getInstance().actualPage = Page.TASK_MENU;
                break;
            default:
                System.out.println("Merci de renseigner un choix valide");
                break;
        }
    }

    private void updateTask() throws IOException {
        Task taskUp = new Task(Trello.getInstance().currentTask);

        System.out.print(
                "MODIFICATION D'UNE TACHE\n" +
                "Laisser le champ vide si vous ne voulez pas apporter de modification\n" +
                "{\n" +
                "    Nom : "
        );
        String name = CLIApp.getInstance().scanNextLine(false);
        if(!name.equals("")) {
            taskUp.name = name;
        }
        System.out.print(
                "    Description : "
        );
        String description = CLIApp.getInstance().scanNextLine(false);
        if(!description.equals("")) {
            taskUp.description = description;
        }
        System.out.println("}");

        ControllerTask.updateTask(Trello.getInstance().currentTask.name, taskUp);
    }

    private void deleteTask() throws IOException {
        System.out.println(
                "SUPPRESSION D'UNE TACHE\n" +
                        "Etes-vous sûr de vouloir supprimer cette tâche ?\n" +
                        "  [y] - Oui\n" +
                        "  [n] - Non"
        );
        switch (CLIApp.getInstance().scanChoice(true)) {
            case "y":
                ControllerTask.deleteTask(Trello.getInstance().currentTask.name);
                CLIApp.getInstance().actualPage = Page.TASK_MENU;
                break;
            case "n":
                CLIApp.getInstance().actualPage = Page.TASK;
                break;
            default:
                System.out.println("Merci de renseigner un choix valide");
                break;
        }
    }

    private void assignUserToTask() throws IOException {
        CLIUser.getInstance().screenListUser();
        System.out.print(
                "AFFECTER UN UTILISATEUR A LA TACHE\n" +
                "Nom de l'utilisateur : "
        );
        String pseudo = CLIApp.getInstance().scanNextLine(false);
        ControllerUser.assignUserToTask(pseudo, Trello.getInstance().currentTask.name);
    }

    private void unassignUserToTask() throws IOException {
        System.out.print(
                "RERIRER UN UTILISATEUR DE LA TACHE\n" +
                "Nom de l'utilisateur : "
        );
        String pseudo = CLIApp.getInstance().scanNextLine(false);
        ControllerUser.unassignUserToTask(pseudo, Trello.getInstance().currentTask.name);
    }

    private void assignFlagToTask() throws IOException {
        CLIFlag.getInstance().screenListFlag();
        System.out.print(
                "AFFECTER UN TAG A LA TACHE\n" +
                "Nom du tag : "
        );
        String name = CLIApp.getInstance().scanNextLine(false);
        ControllerFlag.assignFlagToTask(name, Trello.getInstance().currentTask.name);
    }

    private void unassignFlagToTask() throws IOException {
        System.out.print(
                "RERIRER UN TAG DE LA TACHE\n" +
                "Nom du tag : "
        );
        String name = CLIApp.getInstance().scanNextLine(false);
        ControllerFlag.unassignFlagToTask(name, Trello.getInstance().currentTask.name);
    }

    /**
     * Retourne l'instance du singleton
     * Si elle n'éxiste pas, créer une instance et la retourne
     * @return instance du singleton
     */
    public static CLITask getInstance() {
        if (instance == null) {
            instance = new CLITask();
        }
        return instance;
    }

}
