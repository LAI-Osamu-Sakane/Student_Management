package raisetech.Student_Management.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.Student_Management.domain.StudentDetail;
import raisetech.Student_Management.exception.TestException;
import raisetech.Student_Management.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */
@Validated
@RestController
public class StudentController {

  /**
   * 受講生詳細検索です。
   * IDに紐づく任意の受講生の情報を取得します。
   *
   * @param studentId　受講生ID
   * @return 受講生詳細
   */
  @Operation(summary = "受講生詳細検索", description = "受講生詳細を検索します。")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK",
          content = @Content(
              schema = @Schema(implementation = StudentDetail.class))),
  })
  @GetMapping("/student/{studentId}")
//  public StudentDetail getStudent(@PathVariable int studentId) {
  public StudentDetail getStudent(@PathVariable String studentId) {
    return service.searchStudent(studentId);
  }

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生詳細一覧(全件)
   */
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {//throws TestException {
    return service.searchStudentList(); //エラーを出すためいったんコメントアウト
//    throw new TestException("現在は今APIは利用できません。URLは「studentList」ではなく「students」を利用してください。");
  }

  /**
   * 受講生詳細の登録を行います。
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生詳細登録", description = "受講生詳細を登録します。")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。   * キャンセルフラグの更新もここで行います。(論理削除)
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生詳細更新", description = "受講生詳細を更新します。")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  /**
   * 例外処理クラスの動作確認を行います。
   *
   * @return
   * @throws TestException
   */
  @Operation(summary = "例外処理確認用", description = "例外処理確認をします。")
  @GetMapping("/studentLists")
  public List<StudentDetail> getStudentLists() throws TestException {
    throw new TestException("現在は今APIは利用できません。URLは「studentList」ではなく「students」を利用してください。");
  }
//  映像時に作成
//  @ExceptionHandler(TestException.class)
//  public ResponseEntity<String> handleTestException(TestException ex) {
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//  }
}
