import java.io.*;

public class FileManager {
    private static final String DATA_FILE = "university.dat";

    public void save(University university) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            oos.writeObject(university);
        } catch (IOException e) {
            System.err.println("Грешка при запазване на данните: " + e.getMessage());
        }
    }

    public University load() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new University();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            return (University) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Грешка при зареждане на данните: " + e.getMessage());
            return new University();
        }
    }
}
