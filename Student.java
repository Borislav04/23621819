public class Student {
    public String name;
    public int facultyNumber;
    public int year;
    public String program;
    public String group;
    public String status;

    public Student(String name, int facultyNumber, int year, String program, String group) {
        this.name = name;
        this.facultyNumber = facultyNumber;
        this.year = year;
        this.program = program;
        this.group = group;
        this.status = "записан";
    }
}
