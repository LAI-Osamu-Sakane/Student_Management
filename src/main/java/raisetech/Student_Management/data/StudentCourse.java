package raisetech.Student_Management.data;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
public class StudentCourse {

  @Pattern(regexp = "^\\d+$")
  @Schema(title = "コースID", description = "自動採番が設定されています。", type = "String")
//  private int courseId;
  private String courseId;

  @Pattern(regexp = "^\\d+$")
  @Schema(title = "受講生ID", description = "外部キー(studentクラス)", type = "String")
//  private int studentId;
  private String studentId;
  @NotBlank
  @Schema(title = "コース名", description = "コース名", type = "String")
  private String courseName;

  @NotBlank
  @Schema(title = "コース開始日", description = "コース開始日", type = "LocalDateTime")
  private LocalDateTime courseStartAt;

  @NotBlank
  @Schema(title = "コース終了日", description = "コース終了日", type = "LocalDateTime")
  private LocalDateTime courseEndAt;

}
