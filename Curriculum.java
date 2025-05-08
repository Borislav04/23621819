import java.io.Serializable;
import java.util.*;

public class Curriculum implements Serializable {

    // Пример: "SI" → { 1 → [Course1, Course2], 2 → [...], ... }
    private Map<String, Map<Integer, List<Course>>> programPlans;

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

    public List<Course> getCoursesFor(String program, int year) {
        return programPlans.getOrDefault(program, new HashMap<>())
                .getOrDefault(year, Collections.emptyList());
    }

    public boolean courseExistsFor(String program, int year, String courseName) {
        return getCoursesFor(program, year).stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(courseName));
    }

    public Map<Integer, List<Course>> getCourseMapForProgram(String program) {
        return programPlans.getOrDefault(program, new HashMap<>());
    }
}
