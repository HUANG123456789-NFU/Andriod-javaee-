package cqjtu.edu.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cqjtu.edu.studentadmin.service.LoginService;



public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
    LoginService loginService=new LoginService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		//
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username=request.getParameter("userName");
		String password=request.getParameter("password");
		JSONObject json = new JSONObject(); 
		if(loginService.loginCheck(username, password)) {
			json.put("code", 1);
		}else {
			json.put("code", 0);
		}
		PrintWriter pw=response.getWriter();
		pw.print(json);
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		doGet(request,resp);
	}
}
