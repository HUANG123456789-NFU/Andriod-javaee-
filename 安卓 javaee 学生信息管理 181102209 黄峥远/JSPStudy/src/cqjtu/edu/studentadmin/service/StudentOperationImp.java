package cqjtu.edu.studentadmin.service;

import java.util.List;

import cqjtu.edu.studentadmin.dao.DatabaseOperation;
import cqjtu.edu.studentadmin.entity.Student;

public class StudentOperationImp implements StudentOperation {
	DatabaseOperation dbOp = new DatabaseOperation();

	@Override
	public List<Student> searchStudent(String key) {
		// TODO Auto-generated method stub
		return dbOp.findStuInfo(key);
	}

	@Override
	public int deleteStudent(int no) {
		int i = dbOp.deleteStuInfo(no);
		return i;
	}

	@Override
	public int insertStudent(Student s) {
		int i = dbOp.insertStuInfo(s);
		return i;
	}

	@Override
	public int updateStudent(Student s) {
		int i=dbOp.updateStudent(s);
		return i;
	}

}
