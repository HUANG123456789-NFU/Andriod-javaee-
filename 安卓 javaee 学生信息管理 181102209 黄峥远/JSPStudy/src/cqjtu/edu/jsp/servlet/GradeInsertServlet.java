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

import cqjtu.edu.studentadmin.entity.Grade;
import cqjtu.edu.studentadmin.service.GradeOperationImp;


/**
 * Servlet implementation class GradeInsertServlet
 */
@WebServlet("/GradeInsertServlet")
public class GradeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GradeOperationImp stuOper = new GradeOperationImp();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String no1=request.getParameter("no1");
		String name1=request.getParameter("name1");
		String grade=request.getParameter("grade");
		int i=0 ;
		if(StringUtils.isNullOrEmpty(no)) {///如果有No说明是编辑，没有说明是新增
			Grade s=new Grade(Integer.parseInt(no),name,Integer.parseInt(no1),name1,Double.parseDouble(grade));
			i=stuOper.insertGrade(s);
		}else {
			Grade s=new Grade(Integer.parseInt(no),name,Integer.parseInt(no1),name1,Double.parseDouble(grade));
			i=stuOper.updateGrade(s);
		}
		JSONObject json = new JSONObject();
		json.put("code", i);
		PrintWriter pw=response.getWriter();
		pw.print(json);
		pw.close();
	}

}
