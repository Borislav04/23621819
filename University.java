import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class University implements Serializable {
    private List<Student> students;
    private List<Course> courses;
    private transient FileManager fileManager;

    public University() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void enrollStudent(Student student) {
        students.add(student);
        saveData();
    }

    public Optional<Student> getStudent(String facultyNumber) {
        return students.stream()
                .filter(s -> s.getFacultyNumber().equals(facultyNumber))
                .findFirst();
    }

    public void interruptStudent(String facultyNumber) {
        getStudent(facultyNumber).ifPresent(Student::interrupt);
        saveData();
    }

    private void saveData() {
        if (fileManager != null) {
            fileManager.save(this);
        }
    }
    
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
