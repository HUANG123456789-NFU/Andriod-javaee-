package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cqjtu.edu.studentadmin.service.StudentOperationImp;

/**
 * Servlet implementation class StudentDelServlet
 */
@WebServlet("/StudentDelServlet")
public class StudentDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentOperationImp stuOper = new StudentOperationImp();   
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		//
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String no = request.getParameter("no");
		int i=stuOper.deleteStudent(Integer.parseInt(no));
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doGet(request,resp);
	}

	
}
