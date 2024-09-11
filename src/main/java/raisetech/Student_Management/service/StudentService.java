package raisetech.Student_Management.service;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.Student_Management.controller.converter.StudentConverter;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentsCourses;
import raisetech.Student_Management.domain.StudentDetail;
import raisetech.Student_Management.repository.StudentRepository;

/**
 * 受講生所法を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return　受講生一覧(全件）
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentsCourses> studentsCoursesList = repository.searchStudentCoursesList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  public List<StudentsCourses> searchStudentsCourseList() {
    return repository.searchStudentCoursesList();
  }

  /**
   * 受講生検索です。
   * IDに紐づく受講生情報を取得したあと、その受講生に紐づく、受講生コース情報を取得して設定します。
   *
   * @param studentId 受講生ID
   * @return 受講生
   */
  public StudentDetail searchStudent(int studentId) {
    Student student = repository.searchStudent(studentId);
//    List<StudentsCourses> studentsCourses = repository.searchStudentCourse(Integer.toString(student.getStudentId()));
    List<StudentsCourses> studentsCourses = repository.searchStudentCourse(student.getStudentId());
    return new StudentDetail(student, studentsCourses);
  }


  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());

    for(StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      studentsCourses.setStudentId(studentDetail.getStudent().getStudentId());
      studentsCourses.setCourseStartAt(LocalDateTime.now());
      studentsCourses.setCourseEndAt(LocalDateTime.now().plusYears(1));
      repository.registerStudentCourse(studentsCourses);
    }
    return studentDetail;
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for(StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      repository.updateStudentCourses(studentsCourse);
    }
  }
}
