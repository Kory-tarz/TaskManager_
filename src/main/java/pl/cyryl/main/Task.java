package pl.cyryl.main;

public class Task {

    private String description;
    private String dueDate;
    private String important;

    public Task(String description, String date, String important){
        this.description = description;
        dueDate = date;
        this.important = important;
    }

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
                important;
    }
}
