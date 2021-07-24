package com.datamodeller.test;

import java.sql.*;
import java.sql.DriverManager;

public class Control 
{
	public static void main(String [] args) throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","person","123");
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * from UnemploymentLevels");
		
		DataModellerGUI obj = new DataModellerGUI(st,rs);
		
		obj.firstFact();
		
		conn.close();
	}
	
	
}
