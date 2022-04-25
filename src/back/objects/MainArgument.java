package back.objects;

import app.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainArgument {

    public static List<MainArgument> arguments = new ArrayList<>();

    public String name;
    public List<String> options;
    public String description;

    public MainArgument(String name, List<String> options, String description) {
        this.name = name;
        this.options = options;
        this.description = description;
    }

    public static MainArgument getArg(String name) {
        for(MainArgument arg : arguments) {
            if(arg.name.equals(name))
                return arg;
        }
        return null;
    }

    public static void initArgs() {
        MainArgument.arguments.add(new MainArgument(
                "--help",
                new ArrayList<>(),
                "Affiche cette aide"));
        MainArgument.arguments.add(new MainArgument(
                "-interface",
                new ArrayList<>(Arrays.asList("cli", "gui")),
                "Définit l'interface à utiliser\n" +
                          "                   option = [cli | gui]"));
    }

    public static int useArgs() {
        int res = 0;
        initArgs();
        while(Main.indexMainArg < Main.args.length && res != -1) {
            MainArgument argument = getArg(Main.args[Main.indexMainArg]);
            if(argument == null){
                System.out.println("Argument \"" + Main.args[Main.indexMainArg] + "\" inconnue");
                return -1;
            }
            else {
                res = useArg(argument);
            }
            Main.indexMainArg++;
        }
        return res;
    }

    public static int useArg(MainArgument arg) {
        switch (arg.name) {
            case "--help":
                useArgHelp();
                return -1;
            case "-interface":
                useArgInterface();
                break;
            default:
                System.out.println("Argument \"" + arg.name + "\" na pas d'effet");
                return -1;
        }
        return 0;
    }

    public static void useArgHelp() {
        Main.indexMainArg++;
        StringBuilder str = new StringBuilder("Option pour l'executable \"Trello.jar\" :\n");
        for(MainArgument arg : arguments) {
            str.append("    ").append(String.format("%-15s", arg.name)).append(arg.description).append("\n");
        }
        str.append("\n");
        System.out.println(str);
    }

    public static void useArgInterface() {
        Main.indexMainArg++;
        switch(Main.args[Main.indexMainArg]) {
            case "cli":
                Main.ihm = "cli";
                break;
            case "gui":
                Main.ihm = "gui";
                break;
        }
    }

}
