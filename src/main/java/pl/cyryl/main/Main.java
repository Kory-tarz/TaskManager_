package pl.cyryl.main;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        InputManager inputManager = new InputManager();
        String input;

        inputManager.printCommands();
        while (inputManager.isWaitingForInput()){
            input = inputManager.readInput();
            switch (input.toLowerCase()){
                case "remove":
                    break;
                case "add":
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
