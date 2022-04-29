package front.cli;

public class CLIUtils {

    private static CLIUtils instance = null;

    private CLIUtils() {
    }

    public String getToolBar() {
        return  "\n /¯\\¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯¯¯¯¯¯¯¯¯T¯¯¯¯¯¯\\\n" +
                " \\_,|  [A] - Trello  |  [Z] - Utilisateur  |  [E] - Tâche  |  [R] - Tag  |  [T] - Aide  |  [X]  |\n" +
                "    |¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n";
    }

    public String getEndPage() {
        return  "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                "    |                                                                                           |\n" +
                "    |  ,-----------------------------------------------------------------------------------------,\n" +
                "    \\_/_________________________________________________________________________________________/";
    }

    public static CLIUtils getInstance() {
        if(instance == null) {
            instance = new CLIUtils();
        }
        return instance;
    }

}
