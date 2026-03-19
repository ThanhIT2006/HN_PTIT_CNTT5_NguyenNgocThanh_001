package entity;

import java.util.Scanner;

public class Student {
    private String studentId;
    private String studentName;
    private int age;
    private double gpa;

    public Student(int age, double gpa, String studentId, String studentName) {
        this.age = age;
        this.gpa = gpa;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public void inputData(Scanner scanner) {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        String input;

        do {
            System.out.print("Nhập Id sinh viên: ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Id sinh viên không được để trống, nhập lại");
            }
        } while (input.isEmpty());
        this.studentId = input;

        do {
            System.out.print("Nhập tên sinh viên: ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Tên sinh viên không được để trống, nhập lại");
            }
        } while (input.isEmpty());
        this.studentName = input;

        while (true) {
            System.out.print("Nhập tuổi: ");
            input = scanner.nextLine().trim();
            try {
                int a = Integer.parseInt(input);
                if (a >= 18 && a <= 100) {
                    this.age = a;
                    break;
                } else {
                    System.out.println("Tuổi phải từ 18-100, nhập lại");
                }
            } catch (NumberFormatException e) {
                System.out.println("không được để trống, nhập lại");
            }
        }

        while (true) {
            System.out.print("Nhập GPA (0.0-10.0)");
            input = scanner.nextLine().trim();
            try {
                double g = Double.parseDouble(input);
                if (g >= 0.0 && g <= 10.0) {
                    this.gpa = g;
                    break;
                } else {
                    System.out.println("GPA cần giữa 0.0 - 10.0, nhập lại");
                }
            } catch (NumberFormatException e) {
                System.out.println("Không được để trống, nhập lại");
            }
        }
    }


    public void displayData() {
        System.out.println("----- Thông Tin Sinh Viên -----");
        System.out.printf("ID    : %s%n", this.studentId == null ? "" : this.studentId);
        System.out.printf("Name  : %s%n", this.studentName == null ? "" : this.studentName);
        System.out.printf("Age   : %d%n", this.age);
        System.out.printf("GPA   : %.2f%n", this.gpa);
        System.out.println("-------------------------------");
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gpa=" + gpa +
                '}';
    }


}
