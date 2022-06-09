package pl.cyryl.main;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.cyryl.main.InputValidator.*;

public class Manager {

    StateManager stateManager;
    List<Task> taskList;

    public Manager(){
        stateManager = new StateManager();
        taskList = readLoadedData(stateManager.loadLinesFromSavedState());
    }

    private List<Task> readLoadedData(List<String> lines){
        return lines.stream()
                .map(line -> line.split(","))
                .filter(elements -> elements.length > 3)
                .map(elements -> new Task(elements[0], elements[1], elements[2]))
                .filter( task-> isDescriptionValid()
                            .and(isDateValid())
                            .and(isImportanceValid())
                            .apply(task) == ValidationResult.SUCCESS)
                .collect(Collectors.toList());
    }

    public void addTask(Task newTask){
        stateManager.saveTask(newTask);
        taskList.add(newTask);
    }

    public void removeTask(int i){
        if (i >= taskList.size()) throw new IllegalArgumentException("Index not found");
        taskList.remove(i);
    }

    public void listTasks(){
        IntStream.range(0, taskList.size())
                .forEach(i-> System.out.println(i + ": " + taskList.get(i).toString()));
    }

    public void exit(){
        stateManager.saveOnExit(taskList);
    }
}
