package com.hjj.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Decoder.BASE64Encoder;

@WebServlet(name = "exampleServlet", urlPatterns = "/setCode")
public class SendPng extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//������Ŀ�ļ����·��
		String path=request.getServletContext().getRealPath("/image/vcode.png");
		String png = GetImageStr(path);
		PrintWriter out = response.getWriter();
		out.println(png);
		out.flush();
	}
	public static String GetImageStr(String imgFilePath) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
		byte[] data = null;
		// ��ȡͼƬ�ֽ�����
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ���ֽ�����Base64����
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// ����Base64��������ֽ������ַ���
	}
	/**
	 * ��ȡ�ܵ��е�������
	 */
	public byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userAccout=(String) session.getAttribute("userNumber");
		String newPwd=request.getParameter("password");
		boolean result = UpdateInfo.testOracle(newPwd, userAccout);
		if(result==true){
			request.setAttribute("result", "�����޸ĳɹ���");
			request.setAttribute("page", "success");
			request.getRequestDispatcher("/dao.jsp").forward(request,response);
			return;
		}
		if(result==false)
			request.setAttribute("result", "�����޸�ʧ�ܣ�");
			request.setAttribute("page", "schoolnum.jsp");
			request.getRequestDispatcher("/dao.jsp").forward(request,response);
			return;
	}
}
