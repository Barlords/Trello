package front.cli;

import back.objects.Page;

public class CLIUtils {

    private static CLIUtils instance = null;

    private CLIUtils() {
    }

    /**
     * Retourne un header avec une toolbar sous forme de string
     * @return  le header à afficher
     */
    public String getToolBar() {
        return  "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯\\\n" +
                " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |  [X]  |\n" +
                "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n";
    }

    /**
     * Retourne un header vide sous forme de string
     * @return  le header à afficher
     */
    public String getHeader() {
        return  "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\\\n" +
                " \\_,|                                                                                           |\n";
    }

    /**
     * Retourne un pied de page sous forme de string
     * @return  le pied de page à afficher
     */
    public String getEndPage() {
        return  "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                "    |  ,-----------------------------------------------------------------------------------------,\n" +
                "    \\_/_________________________________________________________________________________________/";
    }

    /**
     * Gère les actions lié à la toolBar
     * Actualise la page si l'action provient de la toolbar
     * @param choice    action sélectionnée
     */
    public void actionToolBar(String choice) {
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
            default:
                break;
        }
    }

    /**
     * Retourne l'instance du singleton
     * Si elle n'éxiste pas, créer une instance et la retourne
     * @return instance du singleton
     */
    public static CLIUtils getInstance() {
        if(instance == null) {
            instance = new CLIUtils();
        }
        return instance;
    }

}
