package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cqjtu.edu.studentadmin.service.GradeOperationImp;

/**
 * Servlet implementation class GradeDelServlet
 */
@WebServlet("/GradeDelServlet")
public class GradeDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GradeOperationImp stuOper = new GradeOperationImp();   
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String no = request.getParameter("no");
		String no1= request.getParameter("no1");
		int i=stuOper.deleteGrade(Integer.parseInt(no),Integer.parseInt(no1));
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();
	}

}
