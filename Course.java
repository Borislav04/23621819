import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;


    private String name;
    private String type;
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
    public Course(Course other) {
        this.name = other.name;
        this.type = other.type;
        this.year = other.year;
        this.credits = other.credits;
        this.grade = other.grade;
    }

    public String getName() {
        return name; 
    }
    public String getType() {
        return type; 
    }
    public int getYear() {
        return year; 
    }
    public int getCredits() {
        return credits;
    }
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 2.0 && grade <= 6.0) {
            this.grade = grade;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d курс) - %s, Кредити: %d",
                name, type, year, grade > 0 ? "Оценка: " + grade : "Невзет", credits);
    }
}
