package app;

import front.main.CLIApp;
import front.main.GUIApp;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) throws Exception {
        if(args.length == 1) {
            if (args[0].equals("gui")) {
                GUIApp app = new GUIApp();
                app.launch();
            } else if (args[0].equals("cli")) {
                CLIApp.getInstance().launch();
            } else {
                System.out.println("Argument incorrect :\n" +
                        "   Autorisé : cli, gui\n" +
                        "   Trouvé : " + args[0]);
            }
        }
        else {
            System.out.println("Nombre d'argument(s) incorrect :\n" +
                    "   Attendu 1\n" +
                    "   Trouvé " + args.length);
        }
    }

}
