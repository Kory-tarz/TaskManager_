package pl.cyryl.interfaces;

import org.apache.commons.lang3.StringUtils;
import pl.cyryl.task.Task;
import pl.cyryl.enums.ValidationResult;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

import static pl.cyryl.enums.ValidationResult.*;

@FunctionalInterface
public interface InputValidator extends Function<Task, ValidationResult> {

    static InputValidator isDescriptionValid(){
        return task -> StringUtils.isBlank(task.getDescription()) ? EMPTY_DESCRIPTION : SUCCESS;
    }

    static InputValidator isDateValid() {
        return task -> {
            try {
                LocalDate date = LocalDate.parse(task.getDueDate());
                return date.compareTo(LocalDate.now()) >= 0 ? SUCCESS : DATE_IN_THE_PAST;
            } catch (DateTimeParseException e) {
                return INVALID_DATE_FORMAT;
            }
        };
    }

    static InputValidator isImportanceValid(){
        return task ->
            task.getImportant().equalsIgnoreCase("true") || task.getImportant().equalsIgnoreCase("false") ?
                    SUCCESS : INVALID_IMPORTANCE;
    }

    default InputValidator and (InputValidator other){
        return task -> {
            ValidationResult result = this.apply(task);
            return result.equals(SUCCESS) ? other.apply(task) : result;
        };
    }
}
