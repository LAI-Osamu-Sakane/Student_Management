package raisetech.Student_Management;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

//  課題用
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentCourses();
}
