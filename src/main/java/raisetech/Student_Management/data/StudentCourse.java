package raisetech.Student_Management.data;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  @NotBlank
  @Pattern(regexp = "^\\d+$")
  private int courseId;

  @NotBlank
  @Pattern(regexp = "^\\d+$")
  private int studentId;

  @NotBlank
  private String courseName;

  @NotBlank
  private LocalDateTime courseStartAt;

  @NotBlank
  private LocalDateTime courseEndAt;

}
