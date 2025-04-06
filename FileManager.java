import java.io.*;

public class FileManager {

    public static void saveUniversityToFile(String fileName, University university) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(university);
            System.out.println("Данните са запазени успешно във файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Грешка при запис: " + e.getMessage());
        }
    }

    public static University loadUniversityFromFile(String fileName) {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Файлът не съществува. Създава се нов университет.");
            return new University();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            University university = (University) in.readObject();
            System.out.println("Данните са заредени успешно от файл: " + fileName);
            return university;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Грешка при зареждане на файл: " + e.getMessage());
            return null;
        }
    }
}
