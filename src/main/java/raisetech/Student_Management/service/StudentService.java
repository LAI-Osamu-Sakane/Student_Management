package raisetech.Student_Management.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.data.StudentsCourses;
import raisetech.Student_Management.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentsCourses> searchStudentsCourseList() {
        return repository.searchStudentCourses();
  }

//  課題用
//  public List<Student> searchStudentList() {
//    //30代抽出
//    List<Student> studentList = repository.search().stream()
//        .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
//        .collect(Collectors.toList());
//    return studentList;
//  }

//  課題用
//  public List<StudentsCourses> searchStudentsCourseList() {
//    //Javaコース抽出
//    List<StudentsCourses> studentsCoursesList = repository.searchStudentCourses().stream()
//        .filter(studentsCourses -> studentsCourses.getCourseName().contains("Java"))
//        .collect(Collectors.toList());
//
//    return studentsCoursesList;
//  }
}
