import java.util.Scanner;
/**
 * Главен клас {@code Main}, който реализира потребителски интерфейс чрез
 * команден ред за взаимодействие със студентската информационна система.
 * Позволява извършване на операции като записване на студенти, промяна на
 * данни, добавяне на оценки, справки и експортиране на данни.
 * <p>
 * Работи в непрекъснат цикъл, като приема команди от потребителя и извиква
 * съответните методи от класа {@code University}.
 */
public class Main {
    /**
     * Главна входна точка на програмата.
     *  Поддържани команди:
     *  <pre>
     *  enroll &lt;име&gt; &lt;фн&gt; &lt;специалност&gt; &lt;група&gt; - Записва нов студент
     *  advance &lt;фн&gt; - Премества студент в следващ курс
     *  change &lt;фн&gt; &lt;program|group|year&gt; &lt;стойност&gt; - Променя данни за студент
     *  graduate &lt;фн&gt; - Маркира студент като завършил
     *  interrupt &lt;фн&gt; - Прекъсва обучението на студент
     *  resume &lt;фн&gt; - Възстановява студент
     *  enrollin &lt;фн&gt; &lt;дисциплина&gt; - Записва студент в дисциплина
     *  addgrade &lt;фн&gt; &lt;дисциплина&gt; &lt;оценка&gt; - Добавя оценка
     *  print &lt;фн&gt; - Извежда информация за студент
     *  printall [специалност] [курс] - Списък на студентите
     *  protocol &lt;дисциплина&gt; - Генерира протокол за дисциплина
     *  report &lt;фн&gt; - Академична справка
     *  export [файл] - Експортира данни в текстов файл
     *  courses &lt;специалност&gt; - Показва всички дисциплини по специалността
     *  help - Показва този помощен текст
     *  exit - Запазва и излиза от системата
     *  </pre>
     *
     *  */
    public static void main(String[] args) {
        University university;
        FileManager fileManager = new FileManager();

        university = fileManager.loadUniversity();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printHelp();
            System.out.print("> ");
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                String[] tokens = input.split("\\s+");

                if (tokens.length == 0 || tokens[0].isEmpty()) continue;

                String command = tokens[0].toLowerCase();

                switch (command) {
                    case "enroll":
                        handleEnroll(tokens, university);
                        break;
                    case "advance":
                        handleAdvance(tokens, university);
                        break;
                    case "change":
                        handleChange(tokens, university);
                        break;
                    case "graduate":
                        handleGraduate(tokens, university);
                        break;
                    case "interrupt":
                        handleInterrupt(tokens, university);
                        break;
                    case "resume":
                        handleResume(tokens, university);
                        break;
                    case "enrollin":
                        handleEnrollIn(tokens, university);
                        break;
                    case "addgrade":
                        handleAddGrade(tokens, university);
                        break;
                    case "print":
                        handlePrint(tokens, university);
                        break;
                    case "printall":
                        handlePrintAll(tokens, university);
                        break;
                    case "protocol":
                        handleProtocol(tokens, university);
                        break;
                    case "report":
                        handleReport(tokens, university);
                        break;
                    case "export":
                        handleExport(tokens, university, fileManager);
                        break;
                    case "courses":
                        handleCourses(tokens, university);
                        break;
                    case "help":
                        printHelp();
                        break;
                    case "exit":
                        fileManager.saveUniversity(university);
                        System.out.println("Довиждане!");
                        System.exit(0);
                    default:
                        System.out.println("Неизвестна команда! Напишете 'help' за списък с команди.");
                }
            } catch (Exception e) {
                System.out.println("Грешка: " + e.getMessage());
            }
        }
    }
    /**
     * Обработва командата за показване на дисциплините по специалност.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleCourses(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: courses <специалност>");
            return;
        }
        String program = parts[1];
        int year = 1;
        university.printAllCourses(program, year);
    }
    /**
     * Обработва командата за записване на нов студент.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleEnroll(String[] parts, University university) {
        if (parts.length < 5) {
            System.out.println("Използване: enroll <име> <фн> <специалност> <група>");
            return;
        }
        String name = parts[1];
        int fn = Integer.parseInt(parts[2]);
        String program = parts[3];
        String group = parts[4];
        university.enrollStudent(name, fn, program, group);
    }
    /**
     * Обработва командата за преминаване на студент в следващ курс.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleAdvance(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: advance <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.advanceStudent(fn);
    }
    /**
     * Обработва командата за промяна на програма, група или курс на студент.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleChange(String[] parts, University university) {
        if (parts.length < 4) {
            System.out.println("Използване: change <фн> <program|group|year> <стойност>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        String option = parts[2];
        String value = parts[3];

        switch (option) {
            case "program":
                university.changeStudentProgram(fn, value);
                break;
            case "group":
                university.findStudentByFacultyNumber(fn).ifPresent(s -> s.setGroup(value));
                break;
            case "year":
                int year = Integer.parseInt(value);
                university.findStudentByFacultyNumber(fn).ifPresent(s -> s.setYear(year));
                break;
            default:
                System.out.println("Невалидна опция за промяна!");
        }
    }
    /**
     * Обработва командата за дипломиране на студент.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleGraduate(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: graduate <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.graduateStudent(fn);
    }
    /**
     * Обработва командата за прекъсване на обучението на студент.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleInterrupt(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: interrupt <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.interruptStudent(fn);
    }
    /**
     * Обработва командата за възстановяване на студент след прекъсване.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleResume(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: resume <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.resumeStudent(fn);
    }
    /**
     * Обработва командата за записване на студент в дадена дисциплина.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleEnrollIn(String[] parts, University university) {
        if (parts.length < 3) {
            System.out.println("Използване: enrollin <фн> <дисциплина>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        String courseName = parts[2];
        university.enrollInCourse(fn, courseName);
    }
    /**
     * Обработва командата за добавяне на оценка на студент по дисциплина.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleAddGrade(String[] parts, University university) {
        if (parts.length < 4) {
            System.out.println("Използване: addgrade <фн> <дисциплина> <оценка>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        String courseName = parts[2];
        double grade = Double.parseDouble(parts[3]);
        university.addGrade(fn, courseName, grade);
    }
    /**
     * Обработва командата за извеждане на информация за конкретен студент.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handlePrint(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: print <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.printStudentReport(fn);
    }
    /**
     * Обработва командата за извеждане на списък на всички студенти
     * по специалност и/или курс.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handlePrintAll(String[] parts, University university) {
        if (parts.length == 1) {
            university.printAllStudents(null, 0);
        } else if (parts.length == 2) {
            university.printAllStudents(parts[1], 0);
        } else {
            university.printAllStudents(parts[1], Integer.parseInt(parts[2]));
        }
    }
    /**
     * Обработва командата за генериране на протокол по дисциплина.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleProtocol(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: protocol <дисциплина>");
            return;
        }
        String courseName = parts[1];
        university.generateProtocol(courseName, 1, courseName);
    }
    /**
     * Обработва командата за генериране на академична справка на студент.
     *
     * @param parts      масив с параметри на командата
     * @param university обектът, който управлява студентските данни
     */
    private static void handleReport(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: report <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.printStudentReport(fn);
    }

    private static void handleExport(String[] parts, University university, FileManager fileManager) {
        String filename = parts.length > 1 ? parts[1] : "students_report.txt";
        fileManager.exportToTextFile(filename, university);
    }
    /**
     * Извежда списък с всички налични команди и тяхното описание.
     */
    private static void printHelp() {
        System.out.println("\nДостъпни команди:");
        System.out.println("enroll <име> <фн> <специалност> <група> - Записва нов студент");
        System.out.println("advance <фн> - Премества студент в следващ курс");
        System.out.println("change <фн> <program|group|year> <стойност> - Променя данни за студент");
        System.out.println("graduate <фн> - Маркира студент като завършил");
        System.out.println("interrupt <фн> - Прекъсва обучението на студент");
        System.out.println("resume <фн> - Възстановява студент");
        System.out.println("enrollin <фн> <дисциплина> - Записва студент в дисциплина");
        System.out.println("addgrade <фн> <дисциплина> <оценка> - Добавя оценка");
        System.out.println("print <фн> - Извежда информация за студент");
        System.out.println("printall [специалност] [курс] - Списък на студентите");
        System.out.println("protocol <дисциплина> - Генерира протокол за дисциплина");
        System.out.println("report <фн> - Академична справка");
        System.out.println("export [файл] - Експортира данни в текстов файл");
        System.out.println("courses <специалност> - Показва всички дисциплини по специалността");
        System.out.println("help - Показва този помощен текст");
        System.out.println("exit - Запазва и излиза от системата\n");
    }
}
