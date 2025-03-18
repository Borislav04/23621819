import java.util.ArrayList;
import java.util.List;

public class University {
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
}
