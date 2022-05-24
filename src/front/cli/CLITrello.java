package front.cli;

import back.controller.ControllerFlag;
import back.controller.ControllerTask;
import back.controller.ControllerTrello;
import back.controller.ControllerUser;
import back.objects.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CLITrello {

    private static CLITrello instance;
    public Trello _trello;

    private CLITrello() {
        _trello = new Trello();
    }

    private String getListTasksBySatusToPrint(Task.Status status) throws IOException {
        List<Task> tasks = _trello._tasks.stream().filter(task -> task.status == status).collect(Collectors.toList());
        String str = "";
        if(tasks.size() == 0) {
            str += "    |            Aucune tâche                                                                   |\n";
        }
        else {
            for(Task task : tasks) {
                str += String.format("    |          -  %-78s", task.name) + "|\n";
            }
        }
        return str;
    }

    private void printFrontTrello() throws IOException {

        String str = CLIUtils.getInstance().getToolBar();

        str +=  "    |    PROJET ANNUEL !                                                                        |\n" +
                "    |                                                                                           |\n" +
                "    |    Tâche(s) disponible(s) :                                                               |\n";

        str +=  "    |        A faire :                                                                          |\n" +
                getListTasksBySatusToPrint(Task.Status.TODO);
        str +=  "    |        En cours :                                                                         |\n" +
                getListTasksBySatusToPrint(Task.Status.IN_PROGRESS);
        str +=  "    |        Terminée :                                                                         |\n" +
                getListTasksBySatusToPrint(Task.Status.END);

        str += CLIUtils.getInstance().getEndPage();

        System.out.println(str);
    }

    public void actionOfTrello() throws IOException {
        _trello._users = ControllerUser.getUsers();
        _trello._tasks = ControllerTask.getTasks();
        _trello._flags = ControllerFlag.getFlags();
        printFrontTrello();
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
        }
    }

    public static CLITrello getInstance() {
        if (instance == null) {
            instance = new CLITrello();
        }
        return instance;
    }

}
