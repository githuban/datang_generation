package com.cattsoft.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Description: <br>
 * Date: 2014-5-25 <br>
 * Copyright (c) 2014 CATTSoft <br>
 * 
 * @author zhoulei
 */
public class DBSession {
	
	private static Properties p= PropertyUtils.getConfig("jdbc.properties");

	/**
	 * @param conn
	 * @author zhoulei
	 */
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @author zhoulei
	 */
	public static Connection getConnection() {
		 Connection conn = null;  //创建用于连接数据库的Connection对象  
	        try {  
	            Class.forName(p.getProperty("driverClass"));// 加载数据驱动  
	              
	            conn = DriverManager.getConnection(p.getProperty("connectionURL"), p.getProperty("userId"), p.getProperty("password"));// 创建数据连接  
	              
	        } catch (Exception e) {  
	            System.out.println("数据库连接失败" + e.getMessage());  
	        }  
	        return conn; //返回所建立的数据库连接  
	}

}
