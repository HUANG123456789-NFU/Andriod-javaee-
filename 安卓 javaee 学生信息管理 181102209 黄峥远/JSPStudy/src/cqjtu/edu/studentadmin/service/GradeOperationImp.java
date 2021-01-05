package cqjtu.edu.studentadmin.service;

import java.util.List;

import cqjtu.edu.studentadmin.dao.DatabaseOperation;
import cqjtu.edu.studentadmin.entity.Grade;

public class GradeOperationImp implements GradeOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Grade> searchGrade(String key) {
		return dbOp.findGradeInfo(key);
	}

	@Override
	public int deleteGrade(int no, int no1) {
		// TODO 自动生成的方法存根
		int i = dbOp.deleteGradeInfo(no, no1);
		return i;
	}

	@Override
	public int insertGrade(Grade s) {
		// TODO 自动生成的方法存根
		int i= dbOp.insertGradeInfo(s);
		return i;
	}

	@Override
	public int updateGrade(Grade s) {
		// TODO 自动生成的方法存根
		int i=dbOp.updateGrade(s);
		return i;
	}

}
