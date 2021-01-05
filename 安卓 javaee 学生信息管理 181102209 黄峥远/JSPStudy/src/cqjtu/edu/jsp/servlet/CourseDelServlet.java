package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cqjtu.edu.studentadmin.service.CourseOperationImp;

/**
 * Servlet implementation class CourseDelServlet
 */
@WebServlet("/CourseDelServlet")
public class CourseDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CourseOperationImp stuOper = new CourseOperationImp();      
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no = request.getParameter("no");
		int i=stuOper.deleteCourse(Integer.parseInt(no));
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();
	}
}
