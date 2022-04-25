package app;

import back.objects.MainArgument;
import front.main.CLIApp;
import front.main.GUIApp;

public class Main {

    public static String ihm = "";
    public static String[] args;
    public static int indexMainArg = 0;


    public static void main(String[] args) throws Exception {
        Main.args = args;
        int res = MainArgument.useArgs();
        if(res == -1) {
            return;
        }
        switch(ihm) {
            case "cli":
                CLIApp.getInstance().launch();
                break;
            case "gui":
                GUIApp app = new GUIApp();
                app.launch();
                break;
            default:
                System.out.println("Erreur dans main -> switch(ihm) : ihm inconnue");
                break;
        }
    }

}
