package raisetech.Student_Management.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.Student_Management.controller.converter.StudentConverter;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentCourse;
import raisetech.Student_Management.domain.StudentDetail;
import raisetech.Student_Management.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検_全件索でリポジトリとコンバーターの処理が適切に呼び出せていること() {
    //Mock化（スタブ）

    // 事前準備
    //　@BeforeEachのbeforeメソッドに作成
//    StudentService sut = new StudentService(repository, converter);
//    List<StudentDetail> expected = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    // 実行
//    List<StudentDetail> actual = sut.searchStudentList();
    sut.searchStudentList();

    // 検証
//    Assertions.assertEquals(expected, actual);

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);

    // 後処理
  }

  @Test
  void 受講生詳細検索_リポジトリの処理が適切に呼び出せていること() {

    String studentId = "999";
    Student student = new Student();
    student.setStudentId(studentId);
    List<StudentCourse> stucentCourseList = new ArrayList<>();
    StudentDetail expected = new StudentDetail(student, new ArrayList<>());

    when(repository.searchStudent(studentId)).thenReturn(student);
    when(repository.searchStudentCourse(student.getStudentId())).thenReturn(stucentCourseList);

    StudentDetail actual = sut.searchStudent(studentId);

    Assertions.assertEquals(expected.getStudent().getStudentId(), actual.getStudent().getStudentId());
    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentCourse(studentId);
  }

  @Test
  void 受講生登録_リポジトリの処理が適切に呼び出せていること() {

    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse> studentCourseList = List.of(studentCourse);
    StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

    sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生更新_レポジトリの処理が適切に呼び出せていること() {
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse> studentCourseList = List.of(studentCourse);
    StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(1)).updateStudentCourse(studentCourse);
  }

  @Test
  void 受講生詳細登録_初期化処理が行われること() {

    String studentId = "999";
    Student student = new Student();
    student.setStudentId(studentId);
    StudentCourse studentCourse = new StudentCourse();

    sut.initStudentsCourse(studentCourse, student);

    Assertions.assertEquals(studentId, student.getStudentId());
    Assertions.assertEquals(LocalDateTime.now().getHour(), studentCourse.getCourseStartAt().getHour());
    Assertions.assertEquals(LocalDateTime.now().plusYears(1).getYear(), studentCourse.getCourseEndAt().getYear());
  }
}