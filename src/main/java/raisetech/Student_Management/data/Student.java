package raisetech.Student_Management.data;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter
public class Student {

  @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
  @Schema(title = "受講生ID", description = "自動採番が設定されています。", type = "String")
//  private int studentId;
  private String studentId;

  @NotBlank
  @Schema(title = "受講生名", description = "フルネーム　苗字と名前の間は半角スペースを入れます。", type = "String")
  private String name;

  @NotBlank
  @Schema(title = "受講生名(かな）", description = "フルネーム　かな入力、苗字と名前の間は半角スペースを入れます。", type = "String")
  private String kanaName;

  @Schema(title = "ニックネーム", description = "あだ名", type = "String")
  private String nickname;

  @NotBlank
  @Email
  @Schema(title = "E-Mail", description = "E-Mailアドレス", type = "String")
  private String email;

  @Schema(title = "地域", description = "地域", type = "String")
  private String area;

  @Schema(title = "年齢", description = "年齢(数値)", type = "int")
  private int age;

  @Schema(title = "性別", description = "性別", type = "String")
  private String sex;

  @Schema(title = "備考", description = "備考欄", type = "String")
  private String remark;

  @Schema(title = "キャンセルフラグ", description = "キャンセルフラグ、TrueかFalse", type = "boolean")
  private boolean isDeleted;


}
