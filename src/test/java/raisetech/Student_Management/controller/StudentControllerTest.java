package raisetech.Student_Management.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.awt.MediaTracker;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.Student_Management.data.Student;
import raisetech.Student_Management.domain.StudentDetail;
import raisetech.Student_Management.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {

    Mockito.when(service.searchStudentList()).thenReturn(List.of(new StudentDetail()));

    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk());
//        .andExpect(content().json("[{\"student\":null,\"studentCourseList\":null}]"));

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の検索が実行できて空で返ってくること() throws Exception {

    String studentId = "999";
    mockMvc.perform(get("/student/{studentId}", studentId))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(studentId);
  }

  @Test
  void 受講生詳細の受講生で入力チェックに以上が発生しないこと() {

    Student student = new Student();
    student.setStudentId("1");
    student.setName("名前");
    student.setKanaName("なまえ");
    student.setNickname("ニックネーム");
    student.setEmail("aaa.bb@zzz.com");
    student.setArea("関西");
    student.setSex("男");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

//    Assertions.assertEquals(0, violations.size());
    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の検索が実行できて空で帰ってくること() throws Exception {
    String studentId  = "999";
    mockMvc.perform(MockMvcRequestBuilders.get("/student/{studentId}", studentId))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(studentId);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いた時に入力チェックに掛かること() {

    Student student = new Student();
    student.setStudentId("てすとです");
    student.setName("名前");
    student.setKanaName("なまえ");
    student.setNickname("ニックネーム");
    student.setEmail("aaa.bb@zzz.com");
    student.setArea("関西");
    student.setSex("男");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

//    Assertions.assertEquals(1, violations.size());
    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");

  }

  @Test
  void 受講生詳細の登録が実行できてからでかえってくること() throws Exception {

//    リクエストデータは適切に口実して入力チェックの検証も兼ねている
//    本来であれば帰りは登録されたデータが入るが、モック化すると意味がないため、レスポンスは作らない
    mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(
            """
              {
                "student": {
                "name": "田中 太郎",
                "kanaName": "たなか たろう",
                "nickname": "てすと",
                "email": "mmm.nn@zzz.com",
                "area": "関東",
                "age": 45,
                "sex": "男",
                "remark": ""
              },
                "studentsCourseList": [
                  {
                  "courseName": "Java Standard"
                  }
                ]
              }
    """
        ))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の更新が実行できてからでかえってくること() throws Exception {

//    リクエストデータは適切に口実して入力チェックの検証も兼ねている
//    本来であれば帰りは登録されたデータが入るが、モック化すると意味がないため、レスポンスは作らない
    mockMvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(
            """
              {
                "student": {
                "name": "鈴木 祐輔",
                "kanaName": "鈴木 祐輔",
                "nickname": "ゆうすけ",
                "email": "mmm.nn@zzz.com",
                "area": "関東",
                "age": 45,
                "sex": "男",
                "remark": "",
                "deleted": false
              },
                "studentsCourseList": [
                  {
                  "studentId": "123452",
                  "courseName": "Java Standard",
                  "startingDate": "2024-05-11T13:52:41",
                  "scheduledEndDate": "2025-05-11T13:52:41"
                  }
                ]
              }
    """
        ))
        .andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any());
  }

  @Test
  void 受講生詳細の例外APIが実行できてステータスが400で帰ってくること() throws Exception {
    mockMvc.perform(get("/studentLists"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("現在は今APIは利用できません。URLは「studentList」ではなく「students」を利用してください。"));
  }

}