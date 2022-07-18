package app;

import front.cli.CLIApp;
import front.gui.main.GUIApp;

import java.util.Objects;

public class Main {
    
    public static void main(String[] args) throws Exception {
        if(!Objects.equals(args[0], "cli") && !Objects.equals(args[0], "gui") && !Objects.equals(args[0], "help")) {
            return;
        }
        switch(args[0]) {
            case "cli":
                System.out.println("Lancement de l'application en CLI");
                CLIApp.getInstance().launch();
                break;
            case "gui":
                System.out.println("Lancement de l'application en GUI");
                GUIApp app = new GUIApp();
                app.launch();
                break;
            case "help":
                System.out.println("Bienvenue dans l'aide.\n" +
                        "Pour lancer le programme, il faut que vous utilisiez soit l'argument cli soit gui.");
            default:
                System.out.println("Erreur dans main -> switch(ihm) : ihm inconnue");
                break;
        }
    }

}
