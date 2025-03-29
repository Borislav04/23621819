public class Main {
    public static void main(String[] args) {
        University university = new University();

        university.enrollStudent("Иван Иванов", 12345, "Информатика", "Група A");
        university.enrollStudent("Мария Петрова", 67890, "Математика", "Група B");

        Course course1 = new Course("Програмиране", "задължителна", 1);
        Course course2 = new Course("Математика", "задължителна", 1);

        university.enrollInCourse(12345, course1);
        university.enrollInCourse(67890, course2);

        university.addGrade(12345, "Програмиране", 5.5);
        university.addGrade(67890, "Математика", 6.0);

        university.changeStudentData(12345, "курс", "2");

        university.printStudentReport(12345);
    }
}
