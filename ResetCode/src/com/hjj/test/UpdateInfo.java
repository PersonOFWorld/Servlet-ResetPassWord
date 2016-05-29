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
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    try
	    {
	        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
	        String url = "****";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
	        String user = "****";// 用户名,系统默认的账户名
	        String password = "****";// 你安装时选设置的密码
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        String sql4 = "select * from user_list where username = ?";  
	        pre = con.prepareStatement(sql4);// 实例化预编译语句
	        pre.setString(1, school);
	        result=pre.executeQuery();// 执行查询，注意括号中不需要再加参数 
	        while(result.next()){
	        	String username=result.getString("username");
	        	String sql = "update user_list set passwd = ? where username = ?";// 预编译语句，“？”代表参数
		        pre = con.prepareStatement(sql);// 实例化预编译语句
		        pre.setString(1, pwd);
		        pre.setString(2, username);
		        int res=pre.executeUpdate();// 执行查询，注意括号中不需要再加参数
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
	            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
	            // 注意关闭的顺序，最后使用的最先关闭
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