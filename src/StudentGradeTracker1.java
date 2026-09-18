import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> marks;

    Student(String name) {
        this.name = name;
        this.marks = new ArrayList<>();
    }

    void addMarks(int mark) {
        marks.add(mark);
    }

    double getAverage() {
        int total = 0;

        for (int mark : marks) {
            total += mark;
        }

        return (double) total / marks.size();
    }

    int getHighest() {
        int highest = marks.get(0);

        for (int mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }

        return highest;
    }

    int getLowest() {
        int lowest = marks.get(0);

        for (int mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }

        return lowest;
    }

    String getGrade() {
        double average = getAverage();

        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    void displayReport() {
        System.out.println("\n--------------------------------");
        System.out.println("Student Name : " + name);
        System.out.println("Marks        : " + marks);
        System.out.printf("Average      : %.2f%n", getAverage());
        System.out.println("Highest Mark : " + getHighest());
        System.out.println("Lowest Mark  : " + getLowest());
        System.out.println("Grade        : " + getGrade());
        System.out.println("--------------------------------");
    }
}

public class StudentGradeTracker1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=================================");
        System.out.println("     STUDENT GRADE TRACKER");
        System.out.println("=================================");

        System.out.print("Enter number of students: ");
        int numberOfStudents = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= numberOfStudents; i++) {

            System.out.println("\nStudent " + i);

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            Student student = new Student(name);

            System.out.print("Enter number of subjects: ");
            int numberOfSubjects = sc.nextInt();

            for (int j = 1; j <= numberOfSubjects; j++) {

                int mark;

                while (true) {
                    System.out.print("Enter marks for Subject " + j + " (0-100): ");
                    mark = sc.nextInt();

                    if (mark >= 0 && mark <= 100) {
                        break;
                    }

                    System.out.println("Invalid marks! Please enter between 0 and 100.");
                }

                student.addMarks(mark);
            }

            sc.nextLine();
            students.add(student);
        }

        System.out.println("\n\n=================================");
        System.out.println("        SUMMARY REPORT");
        System.out.println("=================================");

        for (Student student : students) {
            student.displayReport();
        }

        System.out.println("\nTotal Students: " + students.size());

        sc.close();
    }
}
