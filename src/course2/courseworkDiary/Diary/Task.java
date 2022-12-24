package course2.courseworkDiary.Diary;

import course2.courseworkDiary.Util.Repeatability;
import course2.courseworkDiary.Util.TypeOfTask;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private int id;
    private static int count = 1;
    private  String nameOfTask;
    private  String descriptionOfTask;
    private TypeOfTask typeOfTask;
    private Repeatability repeatability;
    private LocalDateTime date;

    public Task(String nameOfTask,
                String descriptionOfTask,
                TypeOfTask typeOfTask,
                Repeatability repeatability, LocalDateTime date) {
        this.id = count++;
        this.nameOfTask = validateStringValue(nameOfTask);
        this.descriptionOfTask = validateStringValue(descriptionOfTask);
        this.typeOfTask = typeOfTask;
        this.repeatability = repeatability;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && nameOfTask.equals(task.nameOfTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfTask);
    }

    @Override
    public String toString() {
        return "Задача № " +
                id + "\n" +
                "Название: " + nameOfTask + "\n" +
                "Описание: " + descriptionOfTask + "\n" +
                "Повторяемость: " + repeatability + "\n" +
                "Тип: " + typeOfTask + "\n" +
                "Дата: " + date + "\n" +
                "*******************************************";
    }

    public String validateStringValue(String value) {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException("Неверно заполненно поле");
        } else {
            return value;
        }
    }

    public int getId() {
        return id;
    }
    public String getNameOfTask() {
        return nameOfTask;
    }
    public void setNameOfTask(String nameOfTask) {
        this.nameOfTask = validateStringValue(nameOfTask);
    }
    public String getDescriptionOfTask() {
        return descriptionOfTask;
    }
    public void setDescriptionOfTask(String descriptionOfTask) {
        this.descriptionOfTask = validateStringValue(descriptionOfTask);
    }
    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }
    public void setTypeOfTask(TypeOfTask typeOfTask) {
        this.typeOfTask = typeOfTask;
    }
    public LocalDateTime getDateTimeOfTask() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Repeatability getRepeatability() {
        return repeatability;
    }
    public void setRepeatability(Repeatability repeatability) {
        this.repeatability = repeatability;
    }
}
