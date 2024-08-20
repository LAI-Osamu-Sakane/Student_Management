package raisetech.Student_Management.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentsCourses;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;
}
