package raisetech.Student_Management.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentsCourses;
import raisetech.Student_Management.domain.StudentDetail;

/**
 * 受講生詳細を受講生や受講生コース情報、もしくはその逆の変換を行うコンバーターです。
 */
@Component
public class StudentConverter {

  /**
   * 受講生に紐づく受講生コース情報をマッピングする。
   * 受講生コース情報は受講生に対して複数存在するのでループを回して受講生詳細情報を組み立てる。
   *
   * @param students 受講生一覧
   * @param studentsCourses 受講生コース情報のリスト
   * @return 受講生詳細情報のリスト
   */
  public static List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentsCourses> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);
      List<StudentsCourses> convertStudentCourses = studentsCourses.stream()
          .filter(studentsCourse -> student.getStudentId() == studentsCourse.getStudentId())
          .collect(Collectors.toList());
      studentDetail.setStudentsCourses(convertStudentCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }
}
