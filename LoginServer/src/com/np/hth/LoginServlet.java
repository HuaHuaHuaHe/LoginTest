package com.np.hth;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();
	//	String name = req.getParameter("username");
	//	String psd = req.getParameter("password");
	String name = "wandou";
	String psd = "123456";
		DBHandler dbh=new DBHandler();
		boolean b=dbh.checkUser("select * from user where user_name=? and user_psd=?",new String[]{name,psd});
		out.print(b);

//		out.println("<html><head>");
//		out.println("<title>First Servlet Hello</title>");
//		out.println("</head><body>");
//		out.println("Hello!Servlet!");
//		out.println("<h2>The time right now is : " + new Date() + "</h2>");
//		out.println("</body></html>");
//		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		DBHandler db = new DBHandler();
		boolean flag1 = db.insert("insert into user(uname,pwd) values(?,?)",new String[] {uname,pwd});
	}

}
