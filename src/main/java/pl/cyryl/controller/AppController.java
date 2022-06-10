package pl.cyryl.controller;

import pl.cyryl.colors.ConsoleColors;
import pl.cyryl.managers.InputManager;
import pl.cyryl.managers.Manager;
import pl.cyryl.models.Task;

public class AppController {

    public AppController(){}

    public void runProgram() {
        Manager manager = new Manager();
        InputManager inputManager = new InputManager();
        String input;

        inputManager.printCommands();
        while (inputManager.isWaitingForInput()) {
            input = inputManager.readInput();
            switch (input.toLowerCase()) {
                case "remove" -> {
                    int id = inputManager.readIdToRemove();
                    try {
                        manager.removeTask(id);
                        System.out.printf(ConsoleColors.GREEN + "Task nr %s successfully removed\n", id);
                    } catch (IllegalArgumentException e) {
                        System.out.println(ConsoleColors.RED + "Incorrect task id");
                    }
                }
                case "add" -> {
                    Task newTask = inputManager.readNewTask();
                    manager.addTask(newTask);
                    System.out.printf(ConsoleColors.GREEN + "Task \"%s\" was created\n", newTask.getDescription());
                }
                case "list" -> manager.printTasks();
                case "quit" -> inputManager.close();
                default -> inputManager.printCommands();
            }
        }
        manager.close();
    }
}
