package raisetech.Student_Management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "Enami Kouji";
	private String age = "37";
//課題用データ
	private Map<String, String> students = new HashMap<>(Map.of("Enami Kouji", "37", "Kouji Enami", "27"));

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

//	最初に作成し、動作確認たあとは削除
//	@GetMapping("/name")
//	public String getName() {
//		return name;
//	}

	@GetMapping("/studentInfo")
	public String getStudentInfo() {
		return name + " " + age + "歳";
	}

//	課題用
	@GetMapping("/student")
	public List<String> getStudents() {
		List<String> studentList = new ArrayList<>();
		if(CollectionUtils.isEmpty(students)) {
			studentList.add("studentsは空です。");
		} else {
			for(String name : students.keySet()) {
				String data = name + " " + students.get(name) + "歳";
				studentList.add(data);
			}
		}
		return studentList;
	}

//	課題用
	@PostMapping("/student")
	public void setStudents(String name, String age) {

		if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(age))  {
			this.students.put(name, age);
		}
	}

//	最初に作成し、動作確認たあとは削除
//	@PostMapping("/name")
//	public void setName(String name) {
//		this.name = name;
//	}

	@PostMapping("/studentInfo")
	public void setStudentInfo(String name, String age) {
		this.name = name;
		this.age = age;
	}

	@PostMapping("/name")
	public void UpdateStudentName(String name) {
		this.name = name;
	}
}
