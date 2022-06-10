package pl.cyryl.main;

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
                case "remove":
                    int id = inputManager.readIdToRemove();
                    try {
                        manager.removeTask(id);
                        System.out.printf("Task nr %s successfully removed\n", id);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Incorrect task id");
                    }
                    break;
                case "add":
                    Task newTask = inputManager.readNewTask();
                    manager.addTask(newTask);
                    break;
                case "list":
                    manager.printTasks();
                    break;
                case "quit":
                    break;
                default:
                    inputManager.printCommands();
            }
        }
        manager.exit();
    }
}
