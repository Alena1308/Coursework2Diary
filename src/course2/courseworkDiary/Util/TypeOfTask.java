package course2.courseworkDiary.Util;

public enum TypeOfTask {
    PERSONAL("Личная"),
    WORK("Рабочая");
    private final String typeTask;

    TypeOfTask(String typeTask) {
        this.typeTask = validateStringValue(typeTask);
    }
    @Override
    public String toString() {
        return typeTask;
    }
    public String validateStringValue(String value) {
        if (value == null || value.isEmpty() || value.isBlank()) {
            return "Укажите тип задачи ЦИФРОЙ: 1 - Личная или  2 - Рабочая";
        } else {
            return value;
        }
    }

    public final String getTypeTask() {
        return typeTask;
    }
}
