import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        University university = new University();
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

    private static void handleAdvance(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: advance <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.advanceStudent(fn);
    }

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

    private static void handleGraduate(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: graduate <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.graduateStudent(fn);
    }

    private static void handleInterrupt(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: interrupt <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.interruptStudent(fn);
    }

    private static void handleResume(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: resume <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.resumeStudent(fn);
    }

    private static void handleEnrollIn(String[] parts, University university) {
        if (parts.length < 3) {
            System.out.println("Използване: enrollin <фн> <дисциплина>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        String courseName = parts[2];
        university.enrollInCourse(fn, courseName);
    }

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

    private static void handlePrint(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: print <фн>");
            return;
        }
        int fn = Integer.parseInt(parts[1]);
        university.printStudentReport(fn);
    }

    private static void handlePrintAll(String[] parts, University university) {
        if (parts.length == 1) {
            university.printAllStudents(null, 0);
        } else if (parts.length == 2) {
            university.printAllStudents(parts[1], 0);
        } else {
            university.printAllStudents(parts[1], Integer.parseInt(parts[2]));
        }
    }

    private static void handleProtocol(String[] parts, University university) {
        if (parts.length < 2) {
            System.out.println("Използване: protocol <дисциплина>");
            return;
        }
        String courseName = parts[1];
        university.generateProtocol(courseName);
    }

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
        System.out.println("help - Показва този помощен текст");
        System.out.println("exit - Запазва и излиза от системата\n");
    }
}
