import java.io.*;
/**
 * Класът {@code FileManager} отговаря за запазване, зареждане и експортиране
 * на данните за университета във файлове. Той използва Java сериализация за
 * работа с обекти от тип {@code University}, както и текстов файлов изход за
 * експортиране в четим формат.
 *
 * Данните се съхраняват в двоичен файл с име {@code university.dat}.
 * При несъвместимост на сериализацията (например при промяна на класите),
 * автоматично се създава нов празен обект {@code University}.
 */
public class FileManager {
    private static final String DATA_FILE = "university.dat";
    /**
     * Записва обект от тип {@code University} във файл чрез сериализация.
     *
     * @param university обектът, който ще бъде записан
     */
    public void saveUniversity(University university) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            oos.writeObject(university);
            System.out.println("Данните са запазени успешно във файл: " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Грешка при запис на данните: " + e.getMessage());
        }
    }
    /**
     * Зарежда обект {@code University} от сериализиран файл.
     * Ако файлът не съществува или структурата е несъвместима,
     * връща нов празен обект {@code University}.
     *
     * @return зареден обект {@code University}, или нов ако възникне грешка
     */
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
        } catch (InvalidClassException e) {
                System.err.println("Несъвместимост на класовете при зареждане (вероятно промяна в класа). Създава се нова база.");
                return new University();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Грешка при зареждане на данните: " + e.getMessage());
            return new University();
        }
    }
    /**
     * Експортира списъка на всички студенти и техните записани дисциплини
     * в текстов файл с посочено име.
     *
     * @param filename име на текстовия файл, в който ще се запише
     * @param university обект {@code University}, от който се извличат данните
     */
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
