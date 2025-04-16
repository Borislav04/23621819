import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    public enum StudentStatus {
        ACTIVE, INTERRUPTED, GRADUATED
    }

    private String name;
    private String facultyNumber;
    private String program;
    private String group;
    private int year;
    private StudentStatus status;
    private Map<Course, Double> grades;

    public Student(String name, String facultyNumber, String program, String group) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        this.program = program;
        this.group = group;
        this.year = 1;
        this.status = StudentStatus.ACTIVE;
        this.grades = new HashMap<>();
    }

    public void interrupt() {
        if (this.status != StudentStatus.GRADUATED) {
            this.status = StudentStatus.INTERRUPTED;
        }
    }

    public void resume() {
        if (this.status == StudentStatus.INTERRUPTED) {
            this.status = StudentStatus.ACTIVE;
        }
    }

    public void graduate() {
        this.status = StudentStatus.GRADUATED;
    }

    public void addGrade(Course course, double grade) {
        if (status == StudentStatus.ACTIVE) {
            grades.put(course, grade);
        }
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) return 0.0;

        double sum = grades.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return sum / grades.size();
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public StudentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("Студент: %s (%s), %s, %s, %d курс, Статус: %s, Среден успех: %.2f",
                name, facultyNumber, program, group, year, status, calculateAverageGrade());
    }
}
