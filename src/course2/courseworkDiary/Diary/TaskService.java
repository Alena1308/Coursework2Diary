package course2.courseworkDiary.Diary;

import course2.courseworkDiary.Util.Repeatability;
import course2.courseworkDiary.Util.TypeOfTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskService {
    private static Map<Integer, Task> toDoList= new LinkedHashMap<>();

    public static void addNewTaskInToDoList(Scanner scanner) {
        System.out.println("Введите название задачи: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.println("Введите год в формате *УУУУ*:");
        int year = scanner.nextInt();
        System.out.println("Введите месяц в формате *ММ*:");
        int month = scanner.nextInt();
        System.out.println("Введите день в формате *DD*:");
        int day = scanner.nextInt();
        System.out.println("Введите час в формате *hh*:");
        int hour = scanner.nextInt();
        System.out.println("Введите минуты в формате *mm*:");
        int minute = scanner.nextInt();
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
        validateDate(dateTime.toLocalDate());
        System.out.println("Введите тип задачи. Укажите цифру: ");
        System.out.println("1 - личная, \n" +
                           "2 - рабочая");
        scanner.nextLine();
        TypeOfTask type = getTypeFromScanner(scanner);
        System.out.println("Введите тип повторяемости задачи. Укажите цифру: ");
        System.out.print("1 - однократная, \n" +
                         "2 - ежедневная, \n" +
                         "3 - еженедельная, \n" +
                         "4 - ежемесячная, \n" +
                         "5 - ежегодная \n");
        Repeatability reply = getReplyFromScanner(scanner);
        Task task = new Task(name, description, type, reply, dateTime);
        toDoList.put(task.getId(), task);
        System.out.println(toDoList);
    }
    public static TypeOfTask getTypeFromScanner (Scanner scanner) {
        String type = scanner.nextLine();
        if (type.equals("1")) {
            return TypeOfTask.PERSONAL;
        }
        if (type.equals("2")) {
            return TypeOfTask.WORK;
        } else {
            throw new IllegalArgumentException("Неверно заполнено поле ТИП ЗАДАЧИ");
        }
    }
    public static Repeatability getReplyFromScanner (Scanner scanner) {
        String reply = scanner.nextLine();
        if (reply.equals("1")) {
            return Repeatability.ONETIME;
        }
        if (reply.equals("2")) {
            return Repeatability.DAILY;
        }
        if (reply.equals("3")) {
            return Repeatability.WEEKLY;
        }
        if (reply.equals("4")) {
            return Repeatability.MONTHLY;
        }
        if (reply.equals("5")) {
            return Repeatability.ANNUAL;
        } else {
            throw new IllegalArgumentException("Неверно заполнено поле ТИП ПОВТОРЯЕМОСТИ ЗАДАЧИ");
        }
    }
    public static void removeTaskFromToDoList (Scanner scanner){
        System.out.println("Введите id задачи, которую хотите удалить из списка задач:");
        Integer id = scanner.nextInt();
        for(Iterator<Integer> iterator = toDoList.keySet().iterator(); iterator.hasNext(); ) {
            Integer key = iterator.next();
            if(key == id) {
                iterator.remove();
            }
        }
        System.out.println(toDoList);
    }
    public static void getTasksForTheDay (Scanner scanner) {
        System.out.println("Введите дату для отображения списка дел в формате *dd/MM/yyyy*:");
        scanner.nextLine();
        String newDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate desiredDate = LocalDate.parse(newDate, formatter);
        validateDate(desiredDate);
        LocalDateTime desiredDateTime = desiredDate.atStartOfDay();
        for (Map.Entry<Integer, Task> entry : toDoList.entrySet()) {
            Task task = entry.getValue();
            setDateTimeForRepeatability(task, desiredDateTime);
            LocalDate taskDate = task.getDateTimeOfTask().toLocalDate();
            if (taskDate.isEqual(desiredDate)){
                System.out.println(task);
            }
        }
    }
    public static LocalDate validateDate (LocalDate date){
        if (date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now())){
            return date;
        } else {
            throw new IllegalArgumentException("Введенная дата раньше сегодняшней даты. Некорректно.");
        }
    }
    public static void setDateTimeForRepeatability(Task task, LocalDateTime dateTime){
        while (task.getRepeatability().equals(Repeatability.DAILY) &&
                task.getDateTimeOfTask().isBefore(dateTime)){
            task.setDate(task.getDateTimeOfTask().plusDays(1L));
        }
        while (task.getRepeatability().equals(Repeatability.WEEKLY) &&
                task.getDateTimeOfTask().isBefore(dateTime)){
            task.setDate(task.getDateTimeOfTask().plusWeeks(1l));
        }
        while (task.getRepeatability().equals(Repeatability.MONTHLY) &&
                task.getDateTimeOfTask().isBefore(dateTime)){
            task.setDate(task.getDateTimeOfTask().plusMonths(1l));
        }
        if (task.getRepeatability().equals(Repeatability.ANNUAL) &&
                task.getDateTimeOfTask().isBefore(dateTime)){
            task.setDate(task.getDateTimeOfTask().plusYears(1l));
        }
    }
}
