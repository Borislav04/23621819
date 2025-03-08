public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[100];
        int studentCount = 0;

        students[studentCount] = new Student("Иван Иванов", 12345, 1, "Информатика", "Група A");
        studentCount++;
        System.out.println("Студентът Иван Иванов е записан успешно.");

        students[studentCount] = new Student("Мария Петрова", 67890, 1, "Математика", "Група B");
        studentCount++;
        System.out.println("Студентката Мария Петрова е записана успешно.");

        int searchFacultyNumber = 12345;
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].facultyNumber == searchFacultyNumber) {
                System.out.println("Намерен студент: " + students[i].name);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Студент с факултетен номер " + searchFacultyNumber + " не е намерен.");
        }
    }
}
