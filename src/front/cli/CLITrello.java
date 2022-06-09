package front.cli;

import back.controller.ControllerFlag;
import back.controller.ControllerTask;
import back.controller.ControllerUser;
import back.objects.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CLITrello {

    private static CLITrello instance;
    public Trello _trello;

    private CLITrello() {
        _trello = new Trello();
    }

    private String getListTasksBySatusToPrint(Task.Status status) throws IOException {
        List<Task> tasks = Trello.getInstance()._tasks.stream().filter(task -> task.status == status).collect(Collectors.toList());
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

    private void screenTrello() throws IOException {

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

    public void actionTrello() throws IOException {
        Trello.getInstance()._users = ControllerUser.getUsers();
        Trello.getInstance()._tasks = ControllerTask.getTasks();
        Trello.getInstance()._flags = ControllerFlag.getFlags();
        screenTrello();
        String choice = CLIApp.getInstance().scanChoice(true);
        CLIUtils.getInstance().actionToolBar(choice);
    }

    /**
     * Retourne l'instance du singleton
     * Si elle n'éxiste pas, créer une instance et la retourne
     * @return instance du singleton
     */
    public static CLITrello getInstance() {
        if (instance == null) {
            instance = new CLITrello();
        }
        return instance;
    }

}
