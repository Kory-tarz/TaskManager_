package pl.cyryl.main;

import pl.cyryl.colors.ConsoleColors;

import java.util.Scanner;

public class InputManager {

    private boolean quitting;
    private final Scanner scanner;

    public InputManager(){
        quitting = false;
        scanner = new Scanner(System.in);
    }

    public boolean isWaitingForInput(){
        return !quitting;
    }

    public void printCommands(){
        System.out.println(ConsoleColors.GREEN_UNDERLINED + "Select an option: ");
        System.out.println(ConsoleColors.YELLOW_BOLD + "add");
        System.out.println(ConsoleColors.RED_BOLD + "remove");
        System.out.println(ConsoleColors.WHITE_BOLD + "list");
        System.out.println(ConsoleColors.BLUE + "quit");
    }

    public String readInput(){
        if(quitting)
            throw new RuntimeException("Trying to read input after quiting");

        String input = scanner.nextLine().trim();
        if(input.equalsIgnoreCase("quit")) {
            quitting = true;
            scanner.close();
        }
        return input;
    }
}
