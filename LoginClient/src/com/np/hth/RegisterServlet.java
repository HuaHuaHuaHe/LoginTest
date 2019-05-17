package com.np.hth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		resp.addIntHeader("ycy", 101);
		PrintWriter out = resp.getWriter();
		System.out.println(req.getRequestURI());
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)req.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp,json;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
			}
			br.close();
		json = sb.toString();
		Gson gson = new Gson();
		User user = gson.fromJson(json, User.class);
		String uname = user.getUname();
		String pwd = user.getPwd();
		boolean u_exit = false;
		u_exit = exit(uname,u_exit);
		if(u_exit) {
			insert(uname, pwd);
			resp.getWriter().write("success");
		}else {
			resp.getWriter().write("failure");
		}
	}

	private boolean exit(String uname,boolean b_exit) {
		DBHandler db_exit = new DBHandler();
		String sql_select = "select *from user where username=?";
		String a_exit = db_exit.query(sql_select, new String[] {uname});
		
/*		//判断获得的数据库数据中的username所在的索引区间，username是第一行数据，所有只用判断第一个分节符*所在位置即可
		int a = a_exit.indexOf("*");
		//如果a是-1，表明数据库中没有该username注册，如果不是-1；返回该索引值前的字符串
		if(a != -1) {
			b_exit = a_exit.substring(0,a);
			return b_exit;
		}else {
			return null;
		}*/
		
/*		//上面这个方法感觉很不方便，是否可以这样，通过索引值是否为-1就可以判断啊，如果是-1则不存在，不然就是存在的，没必要进行其他操作。
		int a = a_exit.indexOf("*");
		//如果a是-1，表明数据库中没有该username账号注册,b_exit为真；如果不是-1，则证明数据库中已经注册过该账号
		if(a != -1) {
			b_exit = false;
			return b_exit;
		}else {
			b_exit = true;
			return b_exit;
		}*/
		
		//上面这个方法也不行，我们直接判断a_exit是否为空不就好了吗
		//如果a是-1，表明数据库中没有该username账号注册,b_exit为真；如果不是-1，则证明数据库中已经注册过该账号
		if(a_exit.isEmpty()) {
			b_exit = true;
			return b_exit;
		}else {
			b_exit = false;
			return b_exit;
		}
	}
	
	private void insert(String uname, String pwd) {
		DBHandler db_insert = new DBHandler();
		String sql = "insert into user(username,pwd) values (?,?)";
		db_insert.insert(sql, new String[] {uname, pwd});
	}

}