public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Иван Иванов", 12345, 1, "Информатика", "Група A");
        Student student2 = new Student("Мария Петрова", 67890, 1, "Математика", "Група B");

        Course course1 = new Course("Програмиране", "задължителна", 1);
        Course course2 = new Course("Математика", "задължителна", 1);

        student1.addCourse(course1);
        student2.addCourse(course2);

        System.out.println("Информация за студентите:");
        printStudentInfo(student1);
        printStudentInfo(student2);
    }

    public static void printStudentInfo(Student student) {
        System.out.println("Студент: " + student.name);
        System.out.println("Факултетен номер: " + student.facultyNumber);
        System.out.println("Специалност: " + student.program);
        System.out.println("Група: " + student.group);
        System.out.println("Дисциплини:");
        for (Course course : student.courses) {
            System.out.println("  - " + course.name + " (" + course.type + ", курс " + course.year + ")");
        }
        System.out.println();
    }
}
