package raisetech.Student_Management.data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private int studentId;
  private String name;
  private String kanaName;
  private String nickname;
  private String email;
  private String area;
  private int age;
  private String sex;
  private String remark;
  private boolean isDeleted;


}
