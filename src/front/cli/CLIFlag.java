package front.cli;

import back.controller.ControllerFlag;
import back.objects.Flag;
import back.objects.Page;
import back.objects.Trello;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLIFlag {

    private static CLIFlag instance;

    private CLIFlag() {
    }

    // GET LIST
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

    // SCREENS
    public void screenFlag(Flag flag) {
        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    TAG !                                                                                  |\n" +
                String.format("    |        Nom : %74s    |\n", "'"+flag.name+"'") +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Affecter le tag a une tâche                                                  |\n" +
                "    |        [2] - Retirer le tag d'une tâche                                                   |\n" +
                "    |        [3] - Modifier le tag                                                              |\n" +
                "    |        [4] - Supprimer le tag                                                             |\n" +
                "    |        [Q] - Retour                                                                       |\n" +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    public void screenFlagMenu() throws IOException {
        String str =
                CLIUtils.getInstance().getToolBar() +
                "    |    MENU TAG !                                                                             |\n" +
                "    |                                                                                           |\n" +
                "    |    Que voulez vous faire :                                                                |\n" +
                "    |        [1] - Ajouter un tag                                                               |\n" +
                "    |        [2] - Consulter un tag                                                             |\n" +
                "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                getListFlagsToPrint() +
                CLIUtils.getInstance().getEndPage();

        System.out.println(str);
    }

    public void screenListFlag() throws IOException {
        String str =
                CLIUtils.getInstance().getHeader() +
                getListFlagsToPrint() +
                CLIUtils.getInstance().getEndPage();
        System.out.println(str);
    }

    // ACTION FLAG MENU
    public void actionMenuFlag() throws IOException {
        Trello.getInstance()._flags = ControllerFlag.getFlags();
        screenFlagMenu();
        String choice = CLIApp.getInstance().scanChoice(true);
        if (!CLIUtils.getInstance().actionToolBar(choice)) {
            switch (choice) {
                case "1":
                    addFlag();
                    break;
                case "2":
                    consultFlag();
                    break;
                default:
                    System.out.println("Merci de renseigner un choix valide");
                    break;
            }
        }
    }

    public void addFlag() throws IOException {
        screenListFlag();

        System.out.print(
                "CREATION D'UN TAG !\n" +
                        "{\n" +
                        "    Nom : "
        );
        String name = CLIApp.getInstance().scanNextLine(false);
        System.out.println("}");

        ControllerFlag.createFlag(new Flag(name));
        CLIApp.getInstance().actualPage = Page.FLAG_MENU;
    }

    private void consultFlag() throws IOException {
        screenListFlag();

        System.out.println("CONSULTATION D'UN TAG !");
        System.out.print("Nom du tag à consulter : ");
        String name = CLIApp.getInstance().scanNextLine(false);
        Flag flag = ControllerFlag.getFlagByName(name);
        if(flag == null) {
            System.out.println("Utilisateur non trouvé");
            CLIApp.getInstance().actualPage = Page.FLAG_MENU;
            return;
        }
        Trello.getInstance().currentFlag = flag;
        CLIApp.getInstance().actualPage = Page.FLAG;
    }

    // ACTIONS FLAG
    public void actionFlag() throws IOException {
        screenFlag(Trello.getInstance().currentFlag);
        String choice = CLIApp.getInstance().scanChoice(true);
        if (!CLIUtils.getInstance().actionToolBar(choice)) {
            switch (choice) {
                case "1":
                    assignFlagToTask();
                    CLIApp.getInstance().actualPage = Page.FLAG;
                    break;
                case "2":
                    unassignFlagToTask();
                    CLIApp.getInstance().actualPage = Page.FLAG;
                    break;
                case "3":
                    updateFlag();
                    CLIApp.getInstance().actualPage = Page.FLAG;
                    break;
                case "4":
                    deleteFlag();
                    break;
                case "q":
                    CLIApp.getInstance().actualPage = Page.FLAG_MENU;
                    break;
                default:
                    System.out.println("Merci de renseigner un choix valide");
                    break;
            }
        }
    }

    private void assignFlagToTask() throws IOException {
        CLITask.getInstance().screenListTask();
        System.out.print(
                "AJOUTER LE FLAG A UNE TACHE\n" +
                "Nom de la tâche : "
        );
        String taskName = CLIApp.getInstance().scanNextLine(false);
        ControllerFlag.assignFlagToTask(Trello.getInstance().currentFlag.name, taskName);
    }

    private void unassignFlagToTask() throws IOException {
        System.out.print(
                "RETIRER LE FLAG D'UNE TACHE\n" +
                "Nom de la tâche : "
        );
        String taskName = new Scanner(System.in).nextLine();
        ControllerFlag.unassignFlagToTask(Trello.getInstance().currentFlag.name, taskName);
    }

    public void updateFlag() throws IOException {
        Flag flagUp = new Flag(Trello.getInstance().currentFlag);

        System.out.print(
                "MODIFICATION D'UN TAG\n" +
                "Laisser le champ vide si vous ne voulez pas apporter de modification\n" +
                "{\n" +
                "    Nom : "
        );
        String name = CLIApp.getInstance().scanNextLine(false);
        if(!name.equals("")) {
            flagUp.name = name;
        }
        System.out.println("}");

        ControllerFlag.updateFlag(Trello.getInstance().currentTask.name, flagUp);
    }

    public void deleteFlag() throws IOException {
        System.out.println(
                "SUPPRESSION DU TAG\n" +
                        "Etes-vous sûr de vouloir supprimer ce tag ?\n" +
                        "  [y] - Oui\n" +
                        "  [n] - Non"
        );
        switch(CLIApp.getInstance().scanChoice(true)) {
            case "y":
                ControllerFlag.deleteFlag(Trello.getInstance().currentFlag.name);
                CLIApp.getInstance().actualPage = Page.FLAG_MENU;
                break;
            case "n":
                CLIApp.getInstance().actualPage = Page.FLAG;
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
    public static CLIFlag getInstance() {
        if (instance == null) {
            instance = new CLIFlag();
        }
        return instance;
    }

}
