package cqjtu.edu.studentadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cqjtu.edu.studentadmin.entity.Course;
import cqjtu.edu.studentadmin.entity.Grade;
import cqjtu.edu.studentadmin.entity.Student;

public class DatabaseOperation {
	public static Connection getConn() {
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=javaWeb;";
		String userName="sa";
		String userPwd="1273865728";
		try
		{
		    Class.forName(driverName);
		    Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		    return dbConn;
		 }
		 catch(ClassNotFoundException e)
		 {
			  e.printStackTrace();
		 } 
		 catch(SQLException e) {
			  e.printStackTrace();
		 }
		 return null;
	}
    /*
     * 用户登录验证
     */
    public boolean findUser(String name,String password){	
    	Connection conn=getConn();
		String sql="select * from user1 where username=? and password=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			ResultSet results=pst.executeQuery();
			while(results.next()) {
				return true;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    	return false;   	 
    }
    /*
     * 学生信息查询 
     */
    public List<Student> findStuInfo(String key){
		List<Student> students=new ArrayList<Student>();
		Connection conn=getConn();
		String sql = "select * from student where name like '%"+key+"%' or no like '%"+key+"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int no=results.getInt("no");
				String name=results.getString("name");
				String sex=results.getString("sex");
				int age=results.getInt("age");
				String major=results.getString("major");
				Student s=new Student(no,name,sex,age,major);
				students.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;	
	}
    /*
     * 课程信息查询 
     */
    public List<Course> findCourseInfo(String key){
		List<Course> courses=new ArrayList<Course>();
		Connection conn=getConn();
		String sql = "select * from course where name like '%"+key+"%' or no like '%"+key+"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int no=results.getInt("no");
				String name=results.getString("name");
				double score=results.getDouble("score");
				Course s=new Course(no,name,score);
				courses.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;	
	}
    /*
     * 选课信息查询 
     */
    public List<Grade> findGradeInfo(String key){
		List<Grade> grades=new ArrayList<Grade>();
		Connection conn=getConn();
		String sql = "select * from grade where sname like '%"+key+"%' or sno like '%"+key+"%' or cname like '%\"+key+\"%' or cno like '%\"+key+\"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int no=results.getInt("sno");
				String name=results.getString("sname");
				int no1=results.getInt("cno");
				String name1=results.getString("cname");
				double grade=results.getDouble("grade");
				Grade s=new Grade(no,name,no1,name1,grade);
				grades.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grades;
	}
    /*
     * 删除学生信息
     */
    public int deleteStuInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from student where no="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return i;
    }
    /*
     * 删除课程信息
     */
    public int deleteCourseInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from course where no="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return i;
    }
    /*
     * 删除学生信息
     */
    public int deleteGradeInfo(int no,int no1){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from grade where sno=? and cno=?";
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,no);
			pst.setInt(2,no1);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return i;
    }
    /*
     * 插入学生信息
     */
    public int insertStuInfo(Student s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into student(name,sex,age,major) values(?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setString(2, s.getSex());
			pst.setInt(3, s.getAge());
			pst.setString(4, s.getMajor());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			
		}
	}
    /*
     * 插入课程信息
     */
    public int insertCourseInfo(Course s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into course(name,score) values(?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setDouble(2, s.getScore());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			
		}
	}
    /*
     * 插入选课信息
     */
    public int insertGradeInfo(Grade s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into grade(sno,sname,cno,cname,grade) values(?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getNo());
			pst.setString(2, s.getName());
			pst.setInt(3, s.getNo1());
			pst.setString(4, s.getName1());
			pst.setDouble(5, s.getGrade());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			
		}
	}
    /*
     * 编辑学生信息
     */
    public int updateStudent(Student s){
		int i=0;
		Connection con=getConn();
		String sql="update student set name='"+s.getName()+"',sex='"+s.getSex()+"',age="+s.getAge()+",major='"+s.getMajor()+"'where"
				+ " no="+s.getNo();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
    /*
     * 编辑课程信息
     */
    public int updateCourse(Course s){
		int i=0;
		Connection con=getConn();
		String sql="update course set name='"+s.getName()+"',score="+s.getScore()+"where"
				+ " no="+s.getNo();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
    /*
     * 编辑课程信息
     */
    public int updateGrade(Grade s){
		int i=0;
		Connection con=getConn();
		String sql="update grade set grade="+s.getGrade()+"where sno="+s.getNo()+"and cno="+s.getNo1();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
    
}
