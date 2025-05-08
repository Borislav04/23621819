import java.io.Serializable;

public class Course implements Serializable {

    private String name;
    private String type; // "задължителна" или "избираема"
    private int year;
    private int credits;
    private double grade;

    public Course(String name, String type, int year) {
        this(name, type, year, type.equals("избираема") ? 3 : 0);
    }

    public Course(String name, String type, int year, int credits) {
        this.name = name;
        this.type = type;
        this.year = year;
        this.credits = credits;
        this.grade = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d курс) - %s, Кредити: %d",
                name, type, year, grade > 0 ? "Оценка: " + grade : "Невзет", credits);
    }
}
