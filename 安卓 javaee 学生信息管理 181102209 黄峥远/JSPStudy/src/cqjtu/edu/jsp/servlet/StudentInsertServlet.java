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

import cqjtu.edu.studentadmin.entity.Student;
import cqjtu.edu.studentadmin.service.StudentOperationImp;

/**
 * 学生信息保存
 */
@WebServlet("/StudentInsertServlet")
public class StudentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentOperationImp stuOper = new StudentOperationImp();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String no=request.getParameter("no");
		String sex=request.getParameter("sex");
		String major=request.getParameter("major");
		int i=0 ;
		if(StringUtils.isNullOrEmpty(no)) {//如果有No说明是编辑，没有说明是新增
			Student s=new Student(0,name,sex,Integer.parseInt(age),major);
			i=stuOper.insertStudent(s);
		}else {
			Student s=new Student(Integer.parseInt(no),name,sex,Integer.parseInt(age),major);
			i=stuOper.updateStudent(s);
		}
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw=response.getWriter();
		pw.print(json);
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doGet(request,resp);
	}
}
