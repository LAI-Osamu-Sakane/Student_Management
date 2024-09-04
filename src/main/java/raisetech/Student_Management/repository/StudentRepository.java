package raisetech.Student_Management.repository;


import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentsCourses;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

//  課題用
  @Select("SELECT * FROM students WHERE student_id = #{studentId}")
  Student searchStudent(int studentId);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentCoursesList();

//  課題用
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentCourse(int studentId);

//  課題用
  @Insert("INSERT INTO students (name, kana_name, nickname, email, area, age, sex, remark, is_deleted) "
      + "VALUES (#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")
  @Options(useGeneratedKeys = true,keyProperty = "studentId")
  void registerStudent(Student student);

//  課題用
  @Insert("INSERT INTO students_courses (student_id, course_name, course_start_at, course_end_at) "
      + "VALUES (#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  @Options(useGeneratedKeys = true,keyProperty = "courseId")
  void registerStudentCourse(StudentsCourses studentsCourse);

//  課題用
  @Update("UPDATE students SET name = #{name}, kana_name = #{kanaName}, nickname = #{nickname}, "
      + "email = #{email}, area = #{area}, age = #{age}, sex = #{sex}, remark = #{remark}, is_deleted = #{isDeleted} WHERE student_id = #{studentId}")
  void updateStudent(Student student);

//  課題用
  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE course_id = #{courseId}")
  void updateStudentCourses(StudentsCourses studentCourse);
}
