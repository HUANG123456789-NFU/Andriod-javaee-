package cqjtu.edu.studentadmin.service;

import java.util.List;
import cqjtu.edu.studentadmin.entity.Course;

public interface CourseOperation {
	public abstract List<Course> searchCourse(String key);
	public abstract int  deleteCourse(int no);
	public abstract int  insertCourse(Course s);
	public abstract int  updateCourse(Course s);
}
