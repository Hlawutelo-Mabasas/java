package vut;

public class Student {
    private String studentNumber;
    private String name;
    private String email;
    private String pin;

    public Student(String studentNumber, String name, String email, String pin) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.email = email;
        this.pin = pin;
    }

    public String getStudentNumber() { return studentNumber; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPin() { return pin; }

    public String toFileString() {
        return studentNumber + "," + name + "," + email + "," + pin;
    }

    public static Student fromFileString(String data) {
        String[] parts = data.split(",");
        return new Student(parts[0], parts[1], parts[2], parts[3]);
    }

    @Override
    public String toString() {
        return "Student Number: " + studentNumber + "\nName: " + name + "\nEmail: " + email;
    }
}

