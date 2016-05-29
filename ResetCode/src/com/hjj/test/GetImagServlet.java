package com.hjj.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;

@WebServlet(name = "myFirstServlet", urlPatterns = "/resetCode")
public class GetImagServlet extends HttpServlet {
	// 学校教务处登陆页面
	private final String LOGIN_URL = "****";
	// 验证码页面
	private final String CAPTCHA_URL = "****";
	// 保存会话cookie
	public static String cookieValue = null;
	// 模拟登录设置服务器代理
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String schoolnum = request.getParameter("schoolnum");
		String path=request.getServletContext().getRealPath("/image/vcode.png");
		Dao dao = new Dao();
		cookieValue = dao.getPng(LOGIN_URL, CAPTCHA_URL, USER_AGENT,path);
		HttpSession session=request.getSession();
		session.setAttribute("modify", "");
		session.setAttribute("userCookie", cookieValue);
		session.setAttribute("userAccout", schoolnum);
		session.setAttribute("userNumber", schoolnum);
		request.setAttribute("page", "validate.jsp");
		request.getRequestDispatcher("/dao.jsp").forward(request,response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ClientProtocolException, IOException, ServletException {
		HttpSession session=request.getSession();
		String userAccout=request.getParameter("schoolnum");
		String userCookie=(String)session.getAttribute("userCookie");
		
		Dao dao = new Dao();
		boolean info = dao.postForm(userAccout, request.getParameter("password"), request.getParameter("validate"),
				userCookie, USER_AGENT);
		session.setAttribute("userCookie", null);
		session.setAttribute("userAccout", "false");
		if(info==true){
			request.setAttribute("result", "信息验证成功，请修改密码！");
			request.setAttribute("page", "modify.jsp");
			request.getRequestDispatcher("/dao.jsp").forward(request,response);
			return;
		}else{
			request.setAttribute("result", "信息验证失败，请重新确认信息！");
			request.setAttribute("page", "resetCode");
			request.getRequestDispatcher("/dao.jsp").forward(request,response);
			return;
		}
	}
}
