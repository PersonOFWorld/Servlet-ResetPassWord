package com.hjj.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateInfo {
	public static void main(String[] args) {
		testOracle("dsd","04131117");
	}
	@SuppressWarnings("resource")
	public static boolean testOracle(String pwd,String school)
	{
	    Connection con = null;// ����һ�����ݿ�����
	    PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
	    ResultSet result = null;// ����һ�����������
	    try
	    {
	        Class.forName("oracle.jdbc.driver.OracleDriver");// ����Oracle��������
	        String url = "****";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
	        String user = "****";// �û���,ϵͳĬ�ϵ��˻���
	        String password = "****";// �㰲װʱѡ���õ�����
	        con = DriverManager.getConnection(url, user, password);// ��ȡ����
	        String sql4 = "select * from user_list where username = ?";  
	        pre = con.prepareStatement(sql4);// ʵ����Ԥ�������
	        pre.setString(1, school);
	        result=pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ��� 
	        while(result.next()){
	        	String username=result.getString("username");
	        	String sql = "update user_list set passwd = ? where username = ?";// Ԥ������䣬�������������
		        pre = con.prepareStatement(sql);// ʵ����Ԥ�������
		        pre.setString(1, pwd);
		        pre.setString(2, username);
		        int res=pre.executeUpdate();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
		        if(res == 1)
		        	return true;
		        return false;
	        }
	        return false;
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            // ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
	            // ע��رյ�˳�����ʹ�õ����ȹر�
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
		return false;
	}
}