import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public int facultyNumber;
    public int year;
    public String program;
    public String group;
    public String status;
    public List<Course> courses;

    public Student(String name, int facultyNumber, int year, String program, String group) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        this.year = year;
        this.program = program;
        this.group = group;
        this.status = "записан";
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
    public double calculateAverageGrade() {
        if (courses.isEmpty()) return 0.0;
        double sum = 0.0;
        int count = 0;
        for (Course course : courses) {
            if (course.getGrade() > 0) {
                sum += course.getGrade();
                count++;
            }
        }
        return count > 0 ? sum / count : 0.0;
    }
    public String getName() { return this.name; }
    public int getFacultyNumber() { return this.facultyNumber; }
    public List<Course> getCourses() { return this.courses; }
}
