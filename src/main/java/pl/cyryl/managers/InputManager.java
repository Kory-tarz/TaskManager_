package pl.cyryl.managers;

import pl.cyryl.colors.ConsoleColors;
import pl.cyryl.enums.ValidationResult;
import pl.cyryl.models.Task;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static pl.cyryl.interfaces.InputValidator.*;

public class InputManager {

    private boolean quitting;
    private final Scanner scanner;

    public InputManager(){
        quitting = false;
        scanner = new Scanner(System.in);
    }

    public Task readNewTask() {
        Task newTask = new Task();
        String message = ConsoleColors.YELLOW + "Enter task description: ";
        readAndValidate(newTask, message, Task.setDescription, isDescriptionValid());
        message = ConsoleColors.YELLOW + "Enter date in format YYYY-MM-DD:";
        readAndValidate(newTask, message, Task.setDueDate, isDateValid());
        message = ConsoleColors.YELLOW + "Is this task important? Enter true/false: ";
        readAndValidate(newTask, message, Task.setImportant, isImportanceValid());
        return newTask;
    }

    private void readAndValidate(Task currentTask, String messageForUser, BiConsumer<Task, String> set, Function<Task, ValidationResult> validation){
        String input;
        ValidationResult result;
        do{
            System.out.println(messageForUser);
            input = scanner.nextLine();
            set.accept(currentTask, input);
            result = validation.apply(currentTask);
            if (result != ValidationResult.SUCCESS) {
                System.out.println(ConsoleColors.RED_BOLD + "Error: " + result);
            }
        }while (result != ValidationResult.SUCCESS);
    }

    public int readIdToRemove() {
        System.out.println(ConsoleColors.RED + "Enter id to remove a task: ");
        while (!scanner.hasNextInt()){
            scanner.nextLine();
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public boolean isWaitingForInput(){
        return !quitting;
    }

    public void printCommands(){
        System.out.println(ConsoleColors.GREEN_UNDERLINED + "Select an option:");
        System.out.println(ConsoleColors.YELLOW+ "add");
        System.out.println(ConsoleColors.RED + "remove");
        System.out.println(ConsoleColors.BLUE + "list");
        System.out.println(ConsoleColors.PURPLE + "quit");
    }

    public String readInput(){
        if(quitting)
            throw new RuntimeException("Trying to read input after quiting");

        String input = scanner.nextLine().trim();
        return input;
    }

    public void close(){
        scanner.close();
        quitting = true;
    }
}
