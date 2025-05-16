import java.io.Serializable;
import java.util.*;

/**
 * Представя студент в университетската информационна система.
 * Всеки студент има име, факултетен номер, програма, група, курс, статус,
 * списък със записани дисциплини и получени оценки.
 *
 * Този клас се използва за управление на основните действия, свързани със студентите,
 * като записване на дисциплини, добавяне на оценки, промяна на статус и други.

 */
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

    /**
     * Статус на студента.
     * ACTIVE – записан;
     * INTERRUPTED – прекъснал;
     * GRADUATED – дипломиран.
     */
    public enum Status {
        ACTIVE, INTERRUPTED, GRADUATED
    }

    /**
     * Създава нов студент с начални данни.
     *
     * @param name           Името на студента
     * @param facultyNumber  Факултетен номер
     * @param program        Име на специалността
     * @param group          Номер на групата
     */
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

    /**
     * Връща името на студента.
     * @return име
     */
    public String getName() {
        return name;
    }

    /**
     * Връща факултетния номер.
     * @return факултетен номер
     */
    public int getFacultyNumber() {
        return facultyNumber;
    }

    /**
     * Връща специалността (програмата).
     * @return програма
     */
    public String getProgram() {
        return program;
    }

    /**
     * Връща текущия курс на студента.
     * @return курс (година)
     */
    public int getYear() {
        return year;
    }

    /**
     * Връща текущия статус на студента.
     * @return статус
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Връща речник с оценките на студента.
     * @return карта с дисциплина -> оценка
     */
    public Map<String, Double> getGrades() {
        return grades;
    }

    /**
     * Връща списък с всички записани дисциплини на студента.
     * @return множестно от имена на дисциплини
     */
    public Set<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * Задава нова група на студента.
     * @param group нова група
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Задава текущ курс (година) на студента.
     * @param year курс
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Задава статус на студента.
     * @param status нов статус
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Записва студента в нова дисциплина.
     * @param course име на дисциплината
     */
    public void enrollInCourse(String course) {
        enrolledCourses.add(course);
    }

    /**
     * Добавя оценка на студента по определена дисциплина.
     * @param course име на дисциплината
     * @param grade  оценка
     */
    public void addGrade(String course, double grade) {
        grades.put(course, grade);
    }

    /**
     * Проверява дали студентът е записан по дадена дисциплина.
     * @param course име на дисциплината
     * @return true ако е записан, false в противен случай
     */
    public boolean isEnrolledIn(String course) {
        return enrolledCourses.contains(course);
    }

    /**
     * Връща текстово описание на студента.
     * @return низ с основната информация за студента
     */
    @Override
    public String toString() {
        return facultyNumber + " - " + name + ", програма: " + program + ", група: " + group +
                ", курс: " + year + ", статус: " + status;
    }
}
