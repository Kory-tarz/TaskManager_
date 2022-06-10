package pl.cyryl.main;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StateManager {

    private final String saveFileName;
    private static Logger logger = Logger.getLogger(StateManager.class.getName());
    private Path path;

    public StateManager(String saveFileName){
        path = Paths.get(saveFileName);
        this.saveFileName = saveFileName;
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
            }else{
                Files.createFile(path);
            }
        } catch (IOException e) {
            logger.info("Error: reading saved tasks from file");
        }
        return new ArrayList<>();
    }

    public void saveOnExit(List<Saveable> elements){
        try(FileWriter fileWriter = new FileWriter(saveFileName, false)){
            for(Saveable element: elements){
                fileWriter.append(element.saveToFile());
            }
        }catch (IOException exception){
            logger.info("Error: saving tasks to file on exit");
        }
    }

}
