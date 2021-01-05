package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cqjtu.edu.studentadmin.entity.Student;
import cqjtu.edu.studentadmin.service.StudentOperationImp;

/**
 * Servlet implementation class StudentQuery
 */
@WebServlet("/StudentQuery")
public class StudentQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentOperationImp stuOper = new StudentOperationImp();

	/**
	 * 学生信息查询
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");//防止页面出现中文乱码，要放在PrintWriter前面
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String key = request.getParameter("key");
		JSONObject json = new JSONObject();
		List<Student> students=stuOper.searchStudent(key);
		json.put("students", students);
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doGet(request,resp);
	}

}
