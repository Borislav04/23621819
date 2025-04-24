import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int facultyNumber;
    private String program;
    private String group;
    private int year;
    private Status status;
    private Map<String, Double> grades;
    private Set<String> enrolledCourses;

    public enum Status {
        ACTIVE, INTERRUPTED, GRADUATED
    }

    public Student(String name, int facultyNumber, String program, String group) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        this.program = program;
        this.group = group;
        this.year = 1;
        this.status = Status.ACTIVE;
        this.grades = new HashMap<>();
        this.enrolledCourses = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

    public String getProgram() {
        return program;
    }

    public String getGroup() {
        return group;
    }

    public int getYear() {
        return year;
    }

    public Status getStatus() {
        return status;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public Set<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void enrollInCourse(String course) {
        enrolledCourses.add(course);
    }

    public void addGrade(String course, double grade) {
        grades.put(course, grade);
    }

    public boolean isEnrolledIn(String course) {
        return enrolledCourses.contains(course);
    }

    @Override
    public String toString() {
        return facultyNumber + " - " + name + ", програма: " + program + ", група: " + group +
                ", курс: " + year + ", статус: " + status;
    }
}
