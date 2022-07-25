package pl.cyryl.task;

import pl.cyryl.interfaces.Savable;

import java.util.function.BiConsumer;

public class Task implements Savable {

    private String description;
    private String dueDate;
    private String important;

    public Task(){
        description = "";
        dueDate = "";
        important = "";
    }

    public Task(String description, String date, String important){
        setDescription(description);
        dueDate = date;
        this.important = important;
    }

    private void setDescription(String description) {
        this.description = description.replaceAll(",", "");
    }

    private void setImportant(String important) {
        this.important = important;
    }

    private void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public static BiConsumer<Task, String> setDescription = Task::setDescription;

    public static BiConsumer<Task, String> setDueDate = Task::setDueDate;

    public static BiConsumer<Task, String> setImportant = Task::setImportant;

    public String getDueDate() {
        return dueDate;
    }

    public String getImportant() {
        return important;
    }

    public String getDescription() {
        return description;
    }

    public String saveToFile(){
        return description + ", " + dueDate + ", " + important + System.lineSeparator();
    }

    @Override
    public String toString() {
        return  description + " " +
                dueDate + " " +
                (important.equalsIgnoreCase("true") ? "important" : "not important");
    }
}
