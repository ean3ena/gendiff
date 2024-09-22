package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}
