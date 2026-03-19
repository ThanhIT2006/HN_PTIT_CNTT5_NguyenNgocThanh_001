package business;

import entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentBusiness {
    private List<Student> students = new ArrayList<>();

    private static final StudentBusiness INSTANCE = new StudentBusiness();

    private StudentBusiness() {
    }

    public static StudentBusiness getInstance() {
        return INSTANCE;
    }

    //Hiển thị danh sách sinh viên
    public void showAll() {
        if (students.isEmpty()) {
            System.err.println("Danh sách sinh viên rỗng!");
            return;
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-15s %-30s %-5s %-5s%n", "Student ID", "Name", "Age", "GPA");
        System.out.println("-----------------------------------------------------------------");
        students.stream().forEach(s -> System.out.printf("%-15s %-30s %-5d %-5.2f%n",
                s.getStudentId() == null ? "" : s.getStudentId(),
                s.getStudentName() == null ? "" : s.getStudentName(),
                s.getAge(),
                s.getGpa()));
        System.out.println("-----------------------------------------------------------------");
    }

    //Thêm sinh viên
    public boolean addStudent(Student student) {
        if (student == null || student.getStudentId() == null) {
            System.err.println("Dữ liệu sinh viên không hợp lệ!");
            return false;
        }
        Optional<Student> existing = students.stream()
                .filter(s -> s.getStudentId().equalsIgnoreCase(student.getStudentId()))
                .findAny();
        if (existing.isPresent()) {
            System.err.printf("ID '%s' đã tồn tại. Không thể thêm sinh viên trùng mã.%n", student.getStudentId());
            return false;
        }
        students.add(student);
        System.out.println("Thêm sinh viên thành công.");
        return true;
    }
    //Tìm theo id
    public Optional<Student> findById(String studentId) {
        if (studentId == null) return Optional.empty();
        return students.stream()
                .filter(s -> s.getStudentId() != null && s.getStudentId().equalsIgnoreCase(studentId))
                .findFirst();
    }
    //Cập nhật thông tin
    public boolean updateStudent(String studentId, Scanner scanner) {
        Optional<Student> opt = findById(studentId);
        if (!opt.isPresent()) {
            System.err.printf("Không tìm thấy sinh viên với ID '%s'.%n", studentId);
            return false;
        }
        Student s = opt.get();
        if (scanner == null) scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Chọn thông tin muốn cập nhật:");
            System.out.println("1. Tên");
            System.out.println("2. Tuổi");
            System.out.println("3. GPA");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Nhập tên mới: ");
                    String name = scanner.nextLine().trim();
                    if (!name.isEmpty()) {
                        s.setStudentName(name);
                        System.out.println("Cập nhật tên thành công.");
                    } else {
                        System.err.println("Tên không được để trống.");
                    }
                    break;
                case "2":
                    System.out.print("Nhập tuổi mới (18-100): ");
                    String ageStr = scanner.nextLine().trim();
                    try {
                        int age = Integer.parseInt(ageStr);
                        if (age >= 18 && age <= 100) {
                            s.setAge(age);
                            System.out.println("Cập nhật tuổi thành công.");
                        } else {
                            System.err.println("Tuổi phải từ 18 đến 100.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Giá trị tuổi không hợp lệ.");
                    }
                    break;
                case "3":
                    System.out.print("Nhập GPA mới (0.0 - 10.0): ");
                    String gpaStr = scanner.nextLine().trim();
                    try {
                        double gpa = Double.parseDouble(gpaStr);
                        if (gpa >= 0.0 && gpa <= 10.0) {
                            s.setGpa(gpa);
                            System.out.println("Cập nhật GPA thành công.");
                        } else {
                            System.err.println("GPA phải nằm trong khoảng 0.0 - 10.0.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Giá trị GPA không hợp lệ.");
                    }
                    break;
                case "4":
                    System.out.println("Thoát cập nhật.");
                    return true;
                default:
                    System.err.println("Lựa chọn không hợp lệ.");
            }
        }
    }
    //Xóa theo id
    public boolean removeById(String studentId) {
        if (studentId == null) {
            System.err.println("ID không hợp lệ.");
            return false;
        }
        boolean removed = students.removeIf(s -> s.getStudentId() != null && s.getStudentId().equalsIgnoreCase(studentId));
        if (!removed) {
            System.err.printf("Không tìm thấy sinh viên với ID '%s'. Xóa thất bại.%n", studentId);
        } else {
            System.out.println("Xóa sinh viên thành công.");
        }
        return removed;
    }
}
