package front.cli;

import back.controller.ControllerTask;
import back.controller.ControllerUser;
import back.objects.Page;
import back.objects.Task;
import back.objects.User;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLITask {

    private static CLITask instance;

    private CLITask() {
    }

    private void printFrontTask(Task task) throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    TACHE !                                                                                |\n" +
                "    |                                                                                           |\n" +
                String.format("    |        Nom : %70s    |", task.name) +
                String.format("    |        Description : %62s    |", task.description) +
                "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    private String getListTasksToPrint() throws IOException {
        List<Task> tasks = ControllerTask.getTasks();
        String str =    "    |    LISTE DES TACHES !                                                                     |\n" +
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
        return str;
    }

    private void printFrontTaskMenu() throws IOException {
        String str = CLIUtils.getInstance().getToolBar();

        str +=          "    |    MENU TACHE !                                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |    Que voulez vous faire :                                                                |\n" +
                        "    |        [1] - Ajouter une tâche                                                            |\n" +
                        "    |        [2] - Supprimer une tâche                                                          |\n" +
                        "    |        [3] - Modifier une tâche                                                           |\n" +
                        "    |                                                                                           |\n" +
                        "    |                                                                                           |\n";

        str += getListTasksToPrint();

        str += CLIUtils.getInstance().getEndPage();

        System.out.println(str);
    }

    private void printFrontTaskViewAll() throws IOException {
        String str = CLIUtils.getInstance().getToolBar();
        str += getListTasksToPrint();
        str += CLIUtils.getInstance().getEndPage();
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
        ControllerTask.deleteTask(choice);
        CLIApp.getInstance().actualPage = Page.TASK_MENU;
    }

    public void updateTask() throws IOException {
        printFrontTaskViewAll();

        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICATION D'UNE TACHE !");
        System.out.print("Nom de la tâche à modifier : ");
        String choice = scan.nextLine();
        Task task = ControllerTask.getTaskByName(choice);
        if(task == null) {
            System.out.println("error : l'utilisateur n'existe pas");
            return;
        }

        Task taskUp = new Task(task);
        System.out.println("MODIFICATION \n{");
        System.out.print("    Nom : ");
        choice = scan.nextLine();
        if(choice.equals("")) {
            System.out.println("erreur : champ vide");
            CLIApp.getInstance().actualPage = Page.TASK_MENU;
            return;
        }
        taskUp.name = choice;

        ControllerTask.updateTask(task.name, taskUp);
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
            case "3":
                CLIApp.getInstance().actualPage = Page.TASK_UPDATE;
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
