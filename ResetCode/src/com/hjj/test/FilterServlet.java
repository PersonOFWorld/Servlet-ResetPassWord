package com.hjj.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(urlPatterns={"/validate.jsp","/modify.jsp"})
public class FilterServlet implements Filter {

	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//ת����ʵ�����������Ӧ����
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session=req.getSession();
		if(((String)session.getAttribute("modify")).equals("OK")){
			System.out.println("��ת�޸�����");
			chain.doFilter(request, response);
			//req.getRequestDispatcher("/modify.jsp").forward(req,res);
		}
			
		if(session.getAttribute("userAccout")==null){
			req.getRequestDispatcher("/resetCode").forward(req,res);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
