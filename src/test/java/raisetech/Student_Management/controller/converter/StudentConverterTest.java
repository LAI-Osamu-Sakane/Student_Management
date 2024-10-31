package raisetech.Student_Management.controller.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentCourse;
import raisetech.Student_Management.domain.StudentDetail;
import raisetech.Student_Management.service.StudentService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentConverterTest {

  private StudentConverter sut;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡して受講生詳細のリストを作成できること() {
    Student student = createStudent();
    LocalDateTime localDateTime = LocalDateTime.now();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("98765");
    studentCourse.setStudentId(student.getStudentId());
    studentCourse.setCourseName("Java Standard");
    studentCourse.setCourseStartAt(localDateTime);
    studentCourse.setCourseEndAt(localDateTime.plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡したときに紐づかない受講生コース情報は除外されること() {
    Student student = createStudent();
    LocalDateTime localDateTime = LocalDateTime.now();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("98766");
    studentCourse.setStudentId("12346");
    studentCourse.setCourseName("Python Basic");
    studentCourse.setCourseStartAt(localDateTime);
    studentCourse.setCourseEndAt(localDateTime.plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEmpty();
  }

  private static Student createStudent() {
    Student student = new Student();
    student.setStudentId("123456");
    student.setName("確認 専用");
    student.setKanaName("かくにん せんよう");
    student.setNickname("かくにん");
    student.setEmail("abc.def@ggg.com");
    student.setArea("関西");
    student.setAge(55);
    student.setSex("男");
    student.setRemark("とりあえず");
    student.setDeleted(false);

    return student;
  }
}