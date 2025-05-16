import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Основен клас за управление на университетската информационна система.
 * Съдържа списък със студенти и учебен план.
 */
public class University implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Student> students;
    private Curriculum curriculum = new Curriculum();

    /**
     * Конструктор по подразбиране.
     */
    public University() {
        this.students = new ArrayList<>();
    }
    /**
     * Записва нов студент.
     *
     * @param name   името на студента
     * @param fn     факултетен номер
     * @param program специалност
     * @param group  група
     */
    public void enrollStudent(String name, int fn, String program, String group) {
        if (findStudentByFacultyNumber(fn).isPresent()) {
            System.out.println("Студент с този факултетен номер вече съществува.");
            return;
        }
        students.add(new Student(name, fn, program, group));
        System.out.println("Студентът е записан успешно.");
    }
    /**
     * Повишава студент в следващ курс.
     *
     * @param fn факултетен номер
     */
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
    /**
     * Сменя специалността на студент.
     *
     * @param fn         факултетен номер
     * @param newProgram нова специалност
     */
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
    /**
     * Дипломира студент, ако всички изпити са положени.
     *
     * @param fn факултетен номер
     */
    public void graduateStudent(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            if (student.getStatus() != Student.Status.ACTIVE) {
                System.out.println("Само активни студенти могат да се дипломират.");
                return;
            }

            boolean allPassed = true;

            for (String course : student.getEnrolledCourses()) {
                Double grade = student.getGrades().get(course);
                if (grade == null || grade < 3.0) {
                    allPassed = false;
                    System.out.println("Невзет или неположен изпит по: " + course);
                }
            }

            if (allPassed) {
                student.setStatus(Student.Status.GRADUATED);
                System.out.println("Студентът е успешно дипломиран.");
            } else {
                System.out.println("Студентът НЕ може да се дипломира.");
            }

        }, () -> System.out.println("Няма такъв студент."));
    }

    /**
     * Прекъсва обучението на студент.
     *
     * @param fn факултетен номер
     */
    public void interruptStudent(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            student.setStatus(Student.Status.INTERRUPTED);
            System.out.println("Обучението е прекъснато.");
        }, () -> System.out.println("Няма такъв студент."));
    }
    /**
     * Възстановява студент след прекъсване.
     *
     * @param fn факултетен номер
     */
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
    /**
     * Записва студент по дадена дисциплина.
     *
     * @param fn         факултетен номер
     * @param courseName име на дисциплината
     */
    public void enrollInCourse(int fn, String courseName) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            if (student.getStatus() != Student.Status.ACTIVE) {
                System.out.println("Студентът не е активен.");
                return;
            }

            if (!curriculum.courseExistsFor(student.getProgram(), student.getYear(), courseName)) {
                System.out.println("Тази дисциплина не е част от учебния план на специалността и курса.");
                return;
            }

            student.enrollInCourse(courseName);
            System.out.println("Записан е по дисциплината.");
        }, () -> System.out.println("Няма такъв студент."));
    }

    /**
     * Добавя оценка по дисциплина за студент.
     *
     * @param fn     факултетен номер
     * @param course име на дисциплина
     * @param grade  оценка
     */
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
    /**
     * Извежда академичен отчет за студент.
     *
     * @param fn факултетен номер
     */
    public void printStudentReport(int fn) {
        findStudentByFacultyNumber(fn).ifPresentOrElse(student -> {
            System.out.println("\n" + student);

            List<String> passed = new ArrayList<>();
            List<String> failedOrMissing = new ArrayList<>();
            double total = 0;
            int count = 0;

            for (String course : student.getEnrolledCourses()) {
                Double grade = student.getGrades().get(course);

                if (grade != null && grade >= 3.0) {
                    passed.add(course + " - " + grade);
                    total += grade;
                } else {
                    failedOrMissing.add(course + " - " + (grade == null ? "Няма оценка" : grade));
                    total += 2.0;
                }

                count++;
            }

            System.out.println("\n Взети изпити:");
            passed.forEach(c -> System.out.println("  - " + c));

            System.out.println("\n Невзети/неположени изпити:");
            failedOrMissing.forEach(c -> System.out.println("  - " + c));

            double average = count > 0 ? total / count : 0;
            System.out.printf("\n Среден успех: %.2f%n\n", average);

        }, () -> System.out.println("Няма такъв студент."));
    }

    /**
     * Извежда списък на всички студенти.
     */
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
    /**
     * Генерира протокол по дисциплина за дадена група и курс.
     *
     * @param course име на дисциплината
     * @param year   курс
     * @param group  име на групата
     */
    public void generateProtocol(String course,int year,String group ) {
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

    /**
     * Извежда всички дисциплини за дадена специалност и курс от учебния план.
     *
     * @param program специалност
     * @param year    курс
     */
    public void printAllCourses(String program, int year) {
        Map<Integer, List<Course>> allCourses = curriculum.getCourseMapForProgram(program);

        if (allCourses.isEmpty()) {
            System.out.println("Няма намерени дисциплини за тази специалност.");
            return;
        }

        allCourses.keySet().stream().sorted().forEach(y -> {
            System.out.println("Курс " + y + ":");
            allCourses.get(y).forEach(c ->
                    System.out.println(" - " + c.getName() + " (" + c.getType() + ")")
            );
        });
    }

    /**
     * Намира студент по факултетен номер.
     *
     * @param fn факултетен номер
     * @return Optional със студент, ако бъде намерен
     */
    public Optional<Student> findStudentByFacultyNumber(int fn) {
        return students.stream()
                .filter(s -> s.getFacultyNumber() == fn)
                .findFirst();
    }
    /**
     * Връща списък с всички записани студенти.
     *
     * @return списък със студенти
     */
    public List<Student> getStudents() {
        return students;
    }
}
