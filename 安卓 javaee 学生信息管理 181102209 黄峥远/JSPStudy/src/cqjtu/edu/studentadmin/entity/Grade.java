package cqjtu.edu.studentadmin.entity;

public class Grade {
	private Student student;
	private Course course;
	private int no;
	private String name;
	private int no1;
	private String name1;
	private double grade;
	public Grade(){}
	public Grade(int no,String name,int no1,String name1,double grade){
		this.no=no;
		this.setName(name);
		this.no1=no1;
		this.name1=name1;
		this.grade=grade;
		
	}
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getNo1() {
		return no1;
	}
	public void setNo1(int no1) {
		this.no1 = no1;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}

}
