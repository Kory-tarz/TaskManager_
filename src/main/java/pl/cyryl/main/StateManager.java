package pl.cyryl.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class StateManager {

    private final String saveFileName = "tasks.csv";
    private static Logger logger = Logger.getLogger(StateManager.class.getName());
    private Path path;

    public StateManager(){
        path = Paths.get(saveFileName);
    }

    public void saveTask(Task task){
        try (FileWriter fileWriter = new FileWriter(saveFileName, true)) {
            fileWriter.append(task.saveToFile());
        }catch (IOException exception){
            logger.info("Error: saving task: " + task.toString());
        }
    }

    public List<String> loadLinesFromSavedState(){
        try {
            if (Files.exists(path)) {
                return Files.readAllLines(path);
            }
        } catch (IOException e) {
            logger.info("Error: reading saved tasks from file");
        }
        return new ArrayList<>();
    }


    public void saveOnExit(List<Task> taskList){
        try(FileWriter fileWriter = new FileWriter(saveFileName, false)){
            for(Task task: taskList){
                fileWriter.append(task.saveToFile());
            }
        }catch (IOException exception){
            logger.info("Error: saving tasks to file on exit");
        }
    }

}
