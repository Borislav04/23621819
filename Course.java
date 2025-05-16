import java.io.Serializable;
/**
 * Представлява учебен курс в учебния план на студент.
 * Всеки курс има име, тип (задължителен или избираем), курс (година), кредити и оценка.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String type;
    private int year;
    private int credits;
    private double grade;

    /**
     * Конструктор за курс без зададени кредити.
     * Ако курсът е "избираема", автоматично се задават 3 кредита, иначе 0.
     *
     * @param name името на курса
     * @param type типът на курса ("задължителна" или "избираема")
     * @param year курсът (годината), в която се води
     */
    public Course(String name, String type, int year) {
        this(name, type, year, type.equals("избираема") ? 3 : 0);
    }

    /**
     * Конструктор за курс с явно зададени кредити.
     *
     * @param name името на курса
     * @param type типът на курса
     * @param year курсът (годината), в която се води
     * @param credits броят кредити за курса
     */
    public Course(String name, String type, int year, int credits) {
        this.name = name;
        this.type = type;
        this.year = year;
        this.credits = credits;
        this.grade = 0.0;
    }

    /**
     * Връща името на курса.
     *
     * @return името на курса
     */
    public String getName() {
        return name;
    }

    /**
     * Връща типа на курса.
     *
     * @return "задължителна" или "избираема"
     */
    public String getType() {
        return type;
    }

    /**
     * Връща текстово представяне на курса.
     *
     * @return низ с информация за курса, включително име, тип, курс, кредити и оценка
     */
    @Override
    public String toString() {
        return String.format("%s (%s, %d курс) - %s, Кредити: %d",
                name, type, year, grade > 0 ? "Оценка: " + grade : "Невзет", credits);
    }
}
