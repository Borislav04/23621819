import java.io.Serializable;

public class Course implements Serializable {
    public enum CourseType {
        MANDATORY, ELECTIVE
    }

    private String name;
    private CourseType type;
    private int credits; // Само за избираеми дисциплини

    public Course(String name, CourseType type) {
        this(name, type, type == CourseType.ELECTIVE ? 3 : 0);
    }

    public Course(String name, CourseType type, int credits) {
        this.name = name;
        this.type = type;
        this.credits = type == CourseType.ELECTIVE ? credits : 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public CourseType getType() {
        return type;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, type);
    }
}
