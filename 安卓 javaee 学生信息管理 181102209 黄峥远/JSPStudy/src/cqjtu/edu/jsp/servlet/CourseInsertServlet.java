package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;

import cqjtu.edu.studentadmin.entity.Course;
import cqjtu.edu.studentadmin.service.CourseOperationImp;

/**
 * 课程信息保存
 */
@WebServlet("/CourseInsertServlet")
public class CourseInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CourseOperationImp stuOper = new CourseOperationImp();   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String score=request.getParameter("score");
		int i=0 ;
		if(StringUtils.isNullOrEmpty(no)) {///如果有No说明是编辑，没有说明是新增
			Course s=new Course(0,name,Double.parseDouble(score));
			i=stuOper.insertCourse(s);
		}else {
			Course s=new Course(Integer.parseInt(no),name,Double.parseDouble(score));
			i=stuOper.updateCourse(s);
		}
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw=response.getWriter();
		pw.print(json);
		pw.close();
	}

}
