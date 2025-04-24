import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class University implements Serializable {
    private List<Student> students;

    public University() {
        this.students = new ArrayList<>();
    }

    public void enrollStudent(String name, int fn, String program, String group) {
        if (findStudentByFacultyNumber(fn).isPresent()) {
            System.out.println("Студент с този факултетен номер вече съществува.");
            return;
        }
        students.add(new Student(name, fn, program, group));
        System.out.println("Студентът е записан успешно.");
    }

    public void advanceStudent(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            if (student.getStatus() != Student.Status.ACTIVE) {
                System.out.println("Само активни студенти могат да преминават в следващ курс.");
                return;
            }
            student.setYear(student.getYear() + 1);
            System.out.println("Студентът е преминал в курс " + student.getYear() + ".");
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void changeStudentProgram(int fn, String newProgram) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            student.setStatus(Student.Status.ACTIVE); // автоматично активиране
            student.setYear(1); // връща в първи курс
            student.getEnrolledCourses().clear();
            student.getGrades().clear();
            student.setGroup("new"); // или запази старата група
            student.setStatus(Student.Status.ACTIVE);
            student.setYear(1);
            student.enrollInCourse(newProgram);
            System.out.println("Програмата е сменена успешно.");
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void graduateStudent(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            student.setStatus(Student.Status.GRADUATED);
            System.out.println("Студентът е завършил.");
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void interruptStudent(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            student.setStatus(Student.Status.INTERRUPTED);
            System.out.println("Обучението е прекъснато.");
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void resumeStudent(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            if (student.getStatus() != Student.Status.INTERRUPTED) {
                System.out.println("Студентът не е прекъснал.");
                return;
            }
            student.setStatus(Student.Status.ACTIVE);
            System.out.println("Студентът е възстановен.");
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void enrollInCourse(int fn, String course) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            student.enrollInCourse(course);
            System.out.println("Записан е по дисциплината.");
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void addGrade(int fn, String course, double grade) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            if (!student.isEnrolledIn(course)) {
                System.out.println("Студентът не е записан по тази дисциплина.");
                return;
            }
            student.addGrade(course, grade);
            System.out.println("Оценката е добавена.");
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void printStudentReport(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            System.out.println(student);
            student.getGrades().forEach((course, grade) ->
                    System.out.println(" - " + course + ": " + grade));
        }, () -> System.out.println("Няма такъв студент."));
    }

    public void printAllStudents(String program, int year) {
        List<Student> filtered = students.stream()
                .filter(s -> (program == null || s.getProgram().equalsIgnoreCase(program)) &&
                        (year == 0 || s.getYear() == year))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("Няма намерени студенти.");
            return;
        }

        filtered.forEach(System.out::println);
    }

    public void generateProtocol(String course) {
        List<Student> list = students.stream()
                .filter(s -> s.isEnrolledIn(course))
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            System.out.println("Няма записани студенти по тази дисциплина.");
            return;
        }

        System.out.println("Протокол за дисциплина: " + course);
        list.forEach(s -> System.out.println(s.getFacultyNumber() + " - " + s.getName()));
    }

    public Optional<Student> findStudentByFacultyNumber(int fn) {
        return students.stream().filter(s -> s.getFacultyNumber() == fn).findFirst();
    }

    public List<Student> getStudents() {
        return students;
    }
}
