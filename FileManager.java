import java.io.*;

public class FileManager {
    private static final String DATA_FILE = "university.dat";

    public void saveUniversity(University university) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            oos.writeObject(university);
            System.out.println("Данните са запазени успешно във файл: " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Грешка при запис на данните: " + e.getMessage());
        }
    }

    public University loadUniversity() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("Не е намерен файл с данни. Създава се нова база данни.");
            return new University();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            University university = (University) ois.readObject();
            System.out.println("Данните са заредени успешно от файл: " + DATA_FILE);
            return university;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Грешка при зареждане на данните: " + e.getMessage());
            return new University();
        }
    }

    public void exportToTextFile(String filename, University university) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== Списък на студентите ===");
            university.getStudents().forEach(student -> {
                writer.println(student);
                student.getEnrolledCourses().forEach(course ->
                        writer.println("  - " + course));
            });
            System.out.println("Данните са експортирани в " + filename);
        } catch (IOException e) {
            System.err.println("Грешка при експорт: " + e.getMessage());
        }
    }
}
