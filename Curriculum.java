import java.io.Serializable;
import java.util.*;
/**
 * Клас Curriculum представя учебния план за различни специалности и курсове.
 * Всеки учебен план съдържа списък от дисциплини за определен курс от дадена специалност.
 * Данните се съхраняват в структуриран вид чрез вложени речници.
 *
 * Пример:
 *  - "SI" → { 1 → [Програмиране, Математика1], 2 → [ООП, Алгоритми, ...] }
 *  - "Cyber" → { 1 → [...] }
 *
 * Този клас поддържа операции по извличане на дисциплини за дадена програма и курс,
 * проверка за наличие на дисциплина и получаване на целия план по специалност.
 */
public class Curriculum implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Map<Integer, List<Course>>> programPlans;
    /**
     * Конструктор, който инициализира предварително зададени учебни планове
     * за специалностите "SI" и "Cyber".
     */
    public Curriculum() {
        programPlans = new HashMap<>();

        // Примерна програма "SI" (Софтуерни и интернет технологии)
        Map<Integer, List<Course>> siCourses = new HashMap<>();
        siCourses.put(1, List.of(
                new Course("Програмиране", "задължителна", 1),
                new Course("Математика1", "задължителна", 1)
        ));
        siCourses.put(2, List.of(
                new Course("ООП", "задължителна", 2),
                new Course("Алгоритми", "задължителна", 2),
                new Course("Бази данни", "избираема", 2, 3)
        ));
        programPlans.put("SI", siCourses);

        // Примерна програма "Cyber" (Киберсигурност)
        Map<Integer, List<Course>> cyberCourses = new HashMap<>();
        cyberCourses.put(1, List.of(
                new Course("Математика", "задължителна", 1),
                new Course("Въведение в киберсигурността", "задължителна", 1)
        ));
        programPlans.put("Cyber", cyberCourses);
    }
    /**
     * Връща списък с дисциплини за дадена специалност и курс.
     *
     * @param program специалност (напр. "SI" или "Cyber")
     * @param year курс (1, 2, ... )
     * @return списък с дисциплини или празен списък, ако няма намерени
     */
    public List<Course> getCoursesFor(String program, int year) {
        return programPlans.getOrDefault(program, new HashMap<>())
                           .getOrDefault(year, Collections.emptyList());
    }
    /**
     * Проверява дали дадена дисциплина съществува в учебния план
     * за посочената специалност и курс.
     *
     * @param program специалност
     * @param year курс
     * @param courseName име на дисциплината
     * @return true ако дисциплината съществува, иначе false
     */
    public boolean courseExistsFor(String program, int year, String courseName) {
        return getCoursesFor(program, year).stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(courseName));
    }
    /**
     * Връща пълния учебен план (всички курсове и дисциплини) за дадена специалност.
     *
     * @param program специалност
     * @return map с ключ курс (int) и стойност списък от дисциплини
     */
    public Map<Integer, List<Course>> getCourseMapForProgram(String program) {
        return programPlans.getOrDefault(program, new HashMap<>());
    }
}
