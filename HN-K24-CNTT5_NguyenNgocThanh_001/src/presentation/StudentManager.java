package presentation;

import java.util.Scanner;
import business.StudentBusiness;
import entity.Student;

public class StudentManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("""
                    ***************** QUẢN LÝ SINH VIÊN *****************
                    1. Hiển thị danh sách toàn bộ sinh viên
                    2. Thêm mới sinh viên
                    3. Cập nhật thông tin sinh viên theo mã
                    4. Xóa sinh viên theo mã sinh viên
                    5. Tìm kiếm sinh viên theo tên
                    6. Lọc danh sách sinh viên Giỏi
                    7. Sắp xếp danh sách sinh viên giảm dần theo điểm
                    8. Thoát
                    Lựa chọn của bạn:
                    """);
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    //hiển thị danh sách
                    StudentBusiness.getInstance().showAll();
                    break;
                case 2:
                    //Thêm sinh viên
                    Student newStudent = new Student();
                    newStudent.inputData(sc);
                    StudentBusiness.getInstance().addStudent(newStudent);
                    break;
                case 3:
                    //Cập nhật sinh viên
                    System.out.print("Nhập ID sinh viên cần cập nhật: ");
                    String idToUpdate = sc.nextLine().trim();
                    StudentBusiness.getInstance().updateStudent(idToUpdate, sc);
                    break;
                case 4:
                    //Xóa sinh viên
                    System.out.print("Nhập ID sinh viên cần xóa: ");
                    String idToRemove = sc.nextLine().trim();
                    StudentBusiness.getInstance().removeById(idToRemove);
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 8);
    }
}
