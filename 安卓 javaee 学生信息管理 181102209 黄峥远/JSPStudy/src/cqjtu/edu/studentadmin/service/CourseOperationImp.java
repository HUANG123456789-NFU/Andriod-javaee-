package cqjtu.edu.studentadmin.service;

import java.util.List;

import cqjtu.edu.studentadmin.dao.DatabaseOperation;
import cqjtu.edu.studentadmin.entity.Course;

public class CourseOperationImp implements CourseOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Course> searchCourse(String key) {
		// TODO 自动生成的方法存根
		return dbOp.findCourseInfo(key);
	}
	@Override
	public int deleteCourse(int no) {
		// TODO 自动生成的方法存根
		int i = dbOp.deleteCourseInfo(no);
		return i;
	}

	@Override
	public int insertCourse(Course s) {
		// TODO 自动生成的方法存根
		int i= dbOp.insertCourseInfo(s);
		return i;
	}

	@Override
	public int updateCourse(Course s) {
		// TODO 自动生成的方法存根
		int i=dbOp.updateCourse(s);
		return i;
	}

}
