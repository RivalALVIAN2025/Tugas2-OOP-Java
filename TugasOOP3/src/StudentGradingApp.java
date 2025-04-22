
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Superclass
class Person {
    protected String firstName;
    protected String lastName;
    protected int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

// Subclass
class Student extends Person {
    private String studentID;
    private String email;
    private String gender;
    private String department;
    private double attendance;
    private double totalScore;
    private String grade;

    public Student(String studentID, String firstName, String lastName, String email, String gender,
                   int age, String department, double attendance, double totalScore, String grade) {
        super(firstName, lastName, age);
        this.studentID = studentID;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.attendance = attendance;
        this.totalScore = totalScore;
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return getFullName() + " (" + studentID + ") - Score: " + totalScore + ", Grade: " + grade + ", Email: " + email + ", Gender: " + gender;
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();

    // Hardcoded example students
    public void addSampleStudents() {
        students.add(new Student("S0001", "Rani", "Putri", "rani@example.com", "Female", 20, "CS", 95.5, 89.7, "A"));
        students.add(new Student("S0002", "Budi", "Santoso", "budi@example.com", "Male", 21, "Math", 92.3, 72.5, "B"));
    }

    // Read students from CSV file
    public void readFromCSV(String fileName) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 15) {
                    Student s = new Student(
                            data[0], // Student_ID
                            data[1], // First_Name
                            data[2], // Last_Name
                            data[3], // Email
                            data[4], // Gender
                            Integer.parseInt(data[5]), // Age
                            data[6], // Department
                            Double.parseDouble(data[7]), // Attendance (%)
                            Double.parseDouble(data[14]), // Total_Score
                            data[15] // Grade
                    );
                    students.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public void printAllStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void printTopStudent() {
        Student top = null;
        for (Student s : students) {
            if (top == null || s.getTotalScore() > top.getTotalScore()) {
                top = s;
            }
        }
        if (top != null) {
            System.out.println("\nTop Student:");
            System.out.println(top);
        }
    }
}

public class StudentGradingApp {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        // Tambah data hardcoded
        manager.addSampleStudents();

        // Tambah data dari CSV
        manager.readFromCSV("src/Students_Grading_Dataset.csv");

        // Tampilkan semua mahasiswa
        manager.printAllStudents();

        // Tampilkan mahasiswa dengan skor tertinggi
        manager.printTopStudent();
    }
}
