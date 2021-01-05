package cqjtu.edu.studentadmin.service;

import java.util.List;

import cqjtu.edu.studentadmin.entity.Grade;

public interface GradeOperation {
	public abstract List<Grade> searchGrade(String key);
	public abstract int  deleteGrade(int no,int no1);
	public abstract int  insertGrade(Grade s);
	public abstract int  updateGrade(Grade s);
}
