package raisetech.Student_Management.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentCourse;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {

    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生1件の検索が実行でき内容が正しいこと() {

    int studentId = 2;
    Student expected = new Student();

    expected.setStudentId(Integer.toString(studentId));
    expected.setName("渡真利 大吾郎");
    expected.setKanaName("とまり だいごろう");
    expected.setNickname("だいごろう");
    expected.setEmail("ccc.dd@zzz.com");
    expected.setArea("九州");
    expected.setAge(52);
    expected.setSex("男");
    expected.setRemark("なし");
    expected.setDeleted(false);

    Student actual = sut.searchStudent(Integer.toString(studentId));

    assertEquals(expected.getStudentId(), actual.getStudentId());
    assertEquals(expected.getName(), actual.getName());
    assertEquals(expected.getKanaName(), actual.getKanaName());
    assertEquals(expected.getNickname(), actual.getNickname());
    assertEquals(expected.getEmail(), actual.getEmail());
    assertEquals(expected.getArea(), actual.getArea());
    assertEquals(expected.getAge(), actual.getAge());
    assertEquals(expected.getSex(), actual.getSex());
    assertEquals(expected.getRemark(), actual.getRemark());
    assertEquals(expected.isDeleted(), actual.isDeleted());
  }

  @Test
  void 受講生コース情報の全件検索が行えること() {

    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(8);
  }

  @Test
  void 受講生のIDに紐づくコース情報の検索が実行でき内容が正しいこと() {

    int studentId = 2;
    StudentCourse expected = new StudentCourse();
    expected.setStudentId(Integer.toString(studentId));;
    expected.setCourseName("Python Basic");
    expected.setCourseStartAt(LocalDateTime.of(2024,1,23,0,0,0));
    expected.setCourseEndAt(LocalDateTime.of(2024,2,22,0,0,0));

    List<StudentCourse> actual = sut.searchStudentCourse(Integer.toString(studentId));

    assertThat(actual.size()).isEqualTo(1);
    assertEquals(expected.getStudentId(), actual.get(0).getStudentId());
    assertEquals(expected.getCourseName(), actual.get(0).getCourseName());
    assertEquals(expected.getCourseStartAt(), actual.get(0).getCourseStartAt());
    assertEquals(expected.getCourseEndAt(), actual.get(0).getCourseEndAt());


  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("確認 専用");
    student.setKanaName("かくにん せんよう");
    student.setNickname("かくにん");
    student.setEmail("abc.def@ggg.com");
    student.setArea("関西");
    student.setAge(55);
    student.setSex("男");
    student.setRemark("とりあえず");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生コース情報の登録が行えること() {
    int studentId = 2;
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId(Integer.toString(studentId));
    studentCourse.setCourseName("Java Basic");
    studentCourse.setCourseStartAt(LocalDateTime.of(2024,2,23,0,0,0));
    studentCourse.setCourseEndAt(LocalDateTime.of(2024,3,22,0,0,0));

    sut.registerStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentCourse(Integer.toString(studentId));

    assertThat(actual.size()).isEqualTo(2);
    assertEquals(studentCourse.getStudentId(), actual.get(1).getStudentId());
    assertEquals(studentCourse.getCourseName(), actual.get(1).getCourseName());
    assertEquals(studentCourse.getCourseStartAt(), actual.get(1).getCourseStartAt());
    assertEquals(studentCourse.getCourseEndAt(), actual.get(1).getCourseEndAt());
  }

  @Test
  void 受講生の更新が行え内容が正しいこと() {
    int studentId = 2;
    Student student = new Student();
    student.setStudentId(Integer.toString(studentId));
    student.setName("渡真利 大吾郎");
    student.setKanaName("とまり だいごろう");
    student.setNickname("だいごろう");
    student.setEmail("ccc.dd@zzz.com");
    student.setArea("東京");
    student.setAge(50);
    student.setSex("男");
    student.setRemark("とりあえず");
    student.setDeleted(false);

    sut.updateStudent(student);

    Student actual = sut.searchStudent(Integer.toString(studentId));

    assertEquals(student.getStudentId(), actual.getStudentId());
    assertEquals(student.getName(), actual.getName());
    assertEquals(student.getKanaName(), actual.getKanaName());
    assertEquals(student.getNickname(), actual.getNickname());
    assertEquals(student.getEmail(), actual.getEmail());
    assertEquals(student.getArea(), actual.getArea());
    assertEquals(student.getAge(), actual.getAge());
    assertEquals(student.getSex(), actual.getSex());
    assertEquals(student.getRemark(), actual.getRemark());
    assertEquals(student.isDeleted(), actual.isDeleted());
  }

  @Test
  void 受講生コース情報の更新が行え内容が正しいこと() {
    int studentId = 2;
    int courseId = 2;
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId(Integer.toString(courseId));
    studentCourse.setStudentId(Integer.toString(studentId));
    studentCourse.setCourseName("Java Basic");

    sut.updateStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentCourse(Integer.toString(studentId));

    assertThat(actual.size()).isEqualTo(1);
    assertEquals(studentCourse.getStudentId(), actual.get(0).getStudentId());
    assertEquals(studentCourse.getCourseName(), actual.get(0).getCourseName());
  }
}