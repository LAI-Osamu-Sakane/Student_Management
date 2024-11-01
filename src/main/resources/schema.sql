CREATE TABLE IF NOT EXISTS students (
student_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
kana_name VARCHAR(50) NOT NULL,
nickname VARCHAR(50),
email VARCHAR(50) NOT NULL,
area VARCHAR(50),
age int,
sex VARCHAR(10),
remark text,
is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses(
course_id INT PRIMARY KEY AUTO_INCREMENT,
student_id INT NOT NULL,
course_name VARCHAR(50) NOT NULL,
course_start_at TIMESTAMP,
course_end_at TIMESTAMP
);