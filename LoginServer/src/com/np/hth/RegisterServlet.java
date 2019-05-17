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
		
/*		//�жϻ�õ����ݿ������е�username���ڵ��������䣬username�ǵ�һ�����ݣ�����ֻ���жϵ�һ���ֽڷ�*����λ�ü���
		int a = a_exit.indexOf("*");
		//���a��-1���������ݿ���û�и�usernameע�ᣬ�������-1�����ظ�����ֵǰ���ַ���
		if(a != -1) {
			b_exit = a_exit.substring(0,a);
			return b_exit;
		}else {
			return null;
		}*/
		
/*		//������������о��ܲ����㣬�Ƿ����������ͨ������ֵ�Ƿ�Ϊ-1�Ϳ����жϰ��������-1�򲻴��ڣ���Ȼ���Ǵ��ڵģ�û��Ҫ��������������
		int a = a_exit.indexOf("*");
		//���a��-1���������ݿ���û�и�username�˺�ע��,b_exitΪ�棻�������-1����֤�����ݿ����Ѿ�ע������˺�
		if(a != -1) {
			b_exit = false;
			return b_exit;
		}else {
			b_exit = true;
			return b_exit;
		}*/
		
		//�����������Ҳ���У�����ֱ���ж�a_exit�Ƿ�Ϊ�ղ��ͺ�����
		//���a��-1���������ݿ���û�и�username�˺�ע��,b_exitΪ�棻�������-1����֤�����ݿ����Ѿ�ע������˺�
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