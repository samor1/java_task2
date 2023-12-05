import java.util.*;

class Event {
    private String title;
    private String description;

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

public class Organizer {

    private Map<Integer, Map<Integer, Map<Integer, List<Event>>>> events;

    public Organizer() {
        events = new HashMap<>();
    }

    public void addEvent(int year, int month, int day, Event event) {
        // Получаем год
        Map<Integer, Map<Integer, List<Event>>> yearMap = events.getOrDefault(year, new HashMap<>());

        // Получаем месяц
        Map<Integer, List<Event>> monthMap = yearMap.getOrDefault(month, new HashMap<>());

        // Получаем день
        List<Event> dayList = monthMap.getOrDefault(day, new ArrayList<>());

        // Добавляем событие в список
        dayList.add(event);

        // Обновляем списки
        monthMap.put(day, dayList);
        yearMap.put(month, monthMap);
        events.put(year, yearMap);
    }

    public void removeEvent(int year, int month, int day, Event event) {
        // Получаем год
        Map<Integer, Map<Integer, List<Event>>> yearMap = events.get(year);
        if (yearMap == null) {
            return;
        }

        // Получаем месяц
        Map<Integer, List<Event>> monthMap = yearMap.get(month);
        if (monthMap == null) {
            return;
        }

        // Получаем день
        List<Event> dayList = monthMap.get(day);
        if (dayList == null) {
            return;
        }

        // Удаляем событие из списка
        dayList.remove(event);

        // Если список пуст, удаляем его
        if (dayList.isEmpty()) {
            monthMap.remove(day);
        }

        // Если список для месяца пуст, удаляем его
        if (monthMap.isEmpty()) {
            yearMap.remove(month);
        }

        // Если список для года пуст, удаляем его
        if (yearMap.isEmpty()) {
            events.remove(year);
        }
    }

    public List<Event> getEvents(int year, int month, int day) {
        // Получаем год
        Map<Integer, Map<Integer, List<Event>>> yearMap = events.get(year);
        if (yearMap == null) {
            return Collections.emptyList();
        }

        // Получаем месяц
        Map<Integer, List<Event>> monthMap = yearMap.get(month);
        if (monthMap == null) {
            return Collections.emptyList();
        }

        // Получаем день
        List<Event> dayList = monthMap.get(day);
        if (dayList == null) {
            return Collections.emptyList();
        }

        // Возвращаем список событий
        return dayList;
    }

    public void editEvent(int year, int month, int day, Event oldEvent, Event newEvent) {
        // Получаем год
        Map<Integer, Map<Integer, List<Event>>> yearMap = events.get(year);
        if (yearMap == null) {
            return;
        }

        // Получаем месяц
        Map<Integer, List<Event>> monthMap = yearMap.get(month);
        if (monthMap == null) {
            return;
        }

        // Получаем день
        List<Event> dayList = monthMap.get(day);
        if (dayList == null) {
            return;
        }

        // Проверяем, содержит ли список старое событие
        if (dayList.contains(oldEvent)) {
            // Индекс старого события в списке
            int index = dayList.indexOf(oldEvent);

            // Заменяем старое событие на новое событие
            dayList.set(index, newEvent);
        }
    }
    public static void main(String[] args) {
        Organizer organizer = new Organizer();

        // Добавляем события
        organizer.addEvent(2023, 12, 25, new Event("День рождения", "Встреча с друзьями"));
        organizer.addEvent(2023, 12, 31, new Event("Новый год", "Вечеринка"));

        // Получаем события
        List<Event> events = organizer.getEvents(2023, 12, 25);
        for (Event event : events) {
            System.out.println(event.getTitle() + ": " + event.getDescription());
        }

        // Редактируем событие
        Event eventToEdit = events.get(0);
        Event newEvent = new Event("День рождения", "Встреча с семьей");
        organizer.editEvent(2023, 12, 25, eventToEdit, newEvent);

        // Получаем отредактированное событие
        events = organizer.getEvents(2023, 12, 25);
        for (Event event : events) {
            System.out.println(event.getTitle() + ": " + event.getDescription());
        }
    }

}
