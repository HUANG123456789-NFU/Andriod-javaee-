package cqjtu.edu.studentadmin.service;

import java.util.List;

import cqjtu.edu.studentadmin.entity.Student;

public interface StudentOperation {
	public abstract List<Student> searchStudent(String key);
	public abstract int  deleteStudent(int no);
	public abstract int  insertStudent(Student s);
	public abstract int  updateStudent(Student s);
}
