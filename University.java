import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class University implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Student> students;

    public University() {
        this.students = new ArrayList<>();
    }

    public void enrollStudent(String name, int facultyNumber, String program, String group) {
        Student student = new Student(name, facultyNumber, 1, program, group);
        students.add(student);
        System.out.println("Студентът " + name + " е записан успешно.");
    }

    public Student findStudentByFacultyNumber(int facultyNumber) {
        for (Student student : students) {
            if (student.facultyNumber == facultyNumber) {
                return student;
            }
        }
        return null;
    }

    public void enrollInCourse(int facultyNumber, Course course) {
        Student student = findStudentByFacultyNumber(facultyNumber);
        if (student != null) {
            student.addCourse(course);
            System.out.println("Дисциплината " + course.name + " е записана за студент " + student.name);
        } else {
            System.out.println("Студент с факултетен номер " + facultyNumber + " не е намерен.");
        }
    }

    public void addGrade(int facultyNumber, String courseName, double grade) {
        Student student = findStudentByFacultyNumber(facultyNumber);
        if (student != null) {
            for (Course course : student.courses) {
                if (course.name.equals(courseName)) {
                    course.setGrade(grade);
                    System.out.println("Оценка " + grade + " добавена по " + courseName);
                    return;
                }
            }
            System.out.println("Дисциплината " + courseName + " не е намерена.");
        } else {
            System.out.println("Студент с факултетен номер " + facultyNumber + " не е намерен.");
        }
    }

    public void changeStudentData(int facultyNumber, String option, String value) {
        Student student = findStudentByFacultyNumber(facultyNumber);
        if (student != null) {
            switch (option) {
                case "курс":
                    student.year = Integer.parseInt(value);
                    System.out.println("Курсът на студент " + student.name + " е променен на " + value);
                    break;
                case "специалност":
                    student.program = value;
                    System.out.println("Специалността на студент " + student.name + " е променена на " + value);
                    break;
                case "група":
                    student.group = value;
                    System.out.println("Групата на студент " + student.name + " е променена на " + value);
                    break;
                default:
                    System.out.println("Невалидна опция за промяна.");
            }
        } else {
            System.out.println("Студент с факултетен номер " + facultyNumber + " не е намерен.");
        }
    }

    public void printStudentReport(int facultyNumber) {
        Student student = findStudentByFacultyNumber(facultyNumber);
        if (student != null) {
            System.out.println("Информация за студент:");
            System.out.println("Име: " + student.name);
            System.out.println("Факултетен номер: " + student.facultyNumber);
            System.out.println("Специалност: " + student.program);
            System.out.println("Група: " + student.group);
            System.out.println("Дисциплини:");
            for (Course course : student.courses) {
                System.out.println("  - " + course.name + " (" + course.type + ", курс " + course.year + "): " + (course.grade > 0 ? course.grade : "няма оценка"));
            }
        } else {
            System.out.println("Студент с факултетен номер " + facultyNumber + " не е намерен.");
        }
    }
    public List<Student> getStudents() {
        return students;
    }
}
