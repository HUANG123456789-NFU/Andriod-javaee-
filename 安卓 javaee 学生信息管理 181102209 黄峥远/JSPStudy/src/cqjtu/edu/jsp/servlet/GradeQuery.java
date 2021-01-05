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

import cqjtu.edu.studentadmin.entity.Grade;
import cqjtu.edu.studentadmin.service.GradeOperationImp;

/**
 * Servlet implementation class GradeQuery
 */
@WebServlet("/GradeQuery")
public class GradeQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GradeOperationImp stuOper = new GradeOperationImp();

	/**
	 * 学生信息查询
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key = request.getParameter("key");
		JSONObject json = new JSONObject();
		List<Grade> grades=stuOper.searchGrade(key);
		json.put("grades",grades);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8"); //防止页面出现中文乱码，要放在PrintWriter前面
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.close();		
	}

}
