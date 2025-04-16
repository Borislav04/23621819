import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        University university = new University();
        FileManager fileManager = new FileManager();

        University loadedUniversity = fileManager.load();
        if (loadedUniversity != null) {
            university = loadedUniversity;
        }
        university.setFileManager(fileManager);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Информационна система за студенти");
        System.out.println("Достъпни команди: enroll, print, interrupt, resume, exit");

        while (true) {
            System.out.print("> ");
            String commandLine = scanner.nextLine();
            String[] parts = commandLine.split(" ", 2);
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "enroll":
                        handleEnrollCommand(parts[1], university);
                        break;
                    case "print":
                        handlePrintCommand(parts[1], university);
                        break;
                    case "interrupt":
                        university.interruptStudent(parts[1]);
                        System.out.println("Статусът на студента е променен на 'Прекъснал'");
                        break;
                    case "exit":
                        System.out.println("Затваряне на системата...");
                        return;
                    default:
                        System.out.println("Неизвестна команда");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Грешен формат на командата");
            }
        }
    }

    private static void handleEnrollCommand(String args, University university) {
        String[] enrollArgs = args.split(" ");
        if (enrollArgs.length < 4) {
            System.out.println("Грешен формат. Използвайте: enroll <fn> <program> <group> <name>");
            return;
        }

        String facultyNumber = enrollArgs[0];
        String program = enrollArgs[1];
        String group = enrollArgs[2];
        String name = enrollArgs[3];

        Student student = new Student(name, facultyNumber, program, group);
        university.enrollStudent(student);
        System.out.println("Студентът е записан успешно!");
    }

    private static void handlePrintCommand(String facultyNumber, University university) {
        university.getStudent(facultyNumber).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Студент с факултетен номер " + facultyNumber + " не е намерен")
        );
    }
}
