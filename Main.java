import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        University university = new University();
        String fileName = "student_data.dat";

        addTestData(university);
        saveUniversityData(university, fileName);
        University loadedUniversity = loadUniversityData(fileName);
        if (loadedUniversity != null) {
            displayUniversityData(loadedUniversity);
        }
    }

    private static void addTestData(University university) {
        university.enrollStudent("Иван Иванов", 12345, "Информатика", "Група A");
        university.enrollStudent("Мария Петрова", 67890, "Математика", "Група B");

        Course programming = new Course("Програмиране", "задължителна", 1);
        Course math = new Course("Дискретна Математика", "задължителна", 1);

        university.enrollInCourse(12345, programming);
        university.enrollInCourse(67890, math);

        university.addGrade(12345, "Програмиране", 5.5);
        university.addGrade(67890, "Дискретна Математика", 6.0);

        university.changeStudentData(12345, "курс", "2");
    }

    private static void saveUniversityData(University university, String fileName) {
        try {
            FileManager.saveUniversityToFile(fileName, university);
        } catch (Exception e) {
            System.err.println("Грешка при запис: " + e.getMessage());
        }
    }

    private static University loadUniversityData(String fileName) {
        try {
            return FileManager.loadUniversityFromFile(fileName);
        } catch (Exception e) {
            System.err.println("Грешка при зареждане: " + e.getMessage());
            return null;
        }
    }

    private static void displayUniversityData(University university) {
        System.out.println("\n=== Студентски справки ===");
        university.printStudentReport(12345);
        university.printStudentReport(67890);

        System.out.println("\n=== Статистика ===");
        System.out.println("Общ брой студенти: " + university.getStudents().size());
    }
}
