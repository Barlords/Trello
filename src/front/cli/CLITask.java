package front.cli;

import back.controller.ControllerTask;
import back.controller.ControllerUser;
import back.objects.Page;
import back.objects.Task;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLITask {

    private static CLITask instance;

    private CLITask() {
    }

    private void printFrontTaskMenu() throws IOException {
        List<Task> tasks = ControllerTask.getTasks();
        String str =
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯\\\n" +
                        " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |  [X]  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    MENU TACHE !                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |    Que voulez vous faire :                                                                |\n" +
                        "    |        [1] - Ajouter une tâche                                                            |\n" +
                        "    |        [2] - Supprimer une tâche                                                          |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |    LISTE DES TACHES !                                                                     |\n" +
                        "    |                                                                                           |\n";

        if(tasks.size() == 0) {
            str += "    |        Aucune tâche dans le projet, enfin du temp libre :D                              |";
        }
        else {
            for(Task task : tasks) {
                str += String.format("    |      -  %-78s    |\n", task.name);
                if(task.description.length() > 74) {
                    str += String.format("    |             %-71s...    |\n", task.description.substring(0, 71));
                }
                else {
                    str += String.format("    |             %-74s    |\n", task.description);
                }
            }
        }

        str +=
                "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |  ,-----------------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________________/";

        System.out.println(str);
    }

    private void printFrontTaskViewAll() throws IOException {
        List<Task> tasks = ControllerTask.getTasks();
        String str =
                "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯\\\n" +
                        " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |  [X]  |\n" +
                        "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                        "    |    LISTE DES TACHES !                                                                     |\n" +
                        "    |                                                                                           |\n";

        if(tasks.size() == 0) {
            str += "    |        Aucune tâche dans le projet, enfin du temp libre :D                              |";
        }
        else {
            for(Task task : tasks) {
                str += String.format("    |      -  %-78s    |\n", task.name);
                if(task.description.length() > 74) {
                    str += String.format("    |             %-71s...    |\n", task.description.substring(0, 71));
                }
            }
        }

        str +=
                "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |  ,-----------------------------------------------------------------------------------------,\n" +
                        "    \\_/_________________________________________________________________________________________/";

        System.out.println(str);
    }

    public void addTask() throws IOException {
        Scanner scan = new Scanner(System.in);

        printFrontTaskViewAll();

        System.out.println("CREATION D'UNE TACHE !");
        System.out.print("Nom de la tâche : ");
        String name = scan.nextLine();
        System.out.print("Description de la tâche : ");
        String description = scan.nextLine();

        ControllerTask.createTask(new Task(name, description));
        CLIApp.getInstance().actualPage = Page.TASK_MENU;
    }

    public void deleteTask() throws IOException {
        printFrontTaskViewAll();
        System.out.println("SUPPRESSION D'UNE TACHE !");
        System.out.print("Nom de la tâche à supprimer : ");
        String choice = new Scanner(System.in).nextLine();
        ControllerUser.deleteUser(choice);
        CLIApp.getInstance().actualPage = Page.TASK_MENU;
    }

    public void menuTask() throws IOException {
        printFrontTaskMenu();
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
                CLIApp.getInstance().actualPage = Page.TASK_ADD;
                break;
            case "2":
                CLIApp.getInstance().actualPage = Page.TASK_DELETE;
                break;
        }
    }

    public static CLITask getInstance() {
        if (instance == null) {
            instance = new CLITask();
        }
        return instance;
    }

}
