public class Course {
    public String name;
    public String type;
    public int year;
    public double grade;

    public Course(String name, String type, int year) {
        this.name = name;
        this.type = type;
        this.year = year;
        this.grade = 0.0;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
