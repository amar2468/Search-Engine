/***********************************
*Control: In this class, I created a connection with the database which I used and created some statements which
*I would use in the other class
*Author: Amar Plakalo
*Date:05/08/2021
*/

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
		
		Statement st1 = conn.createStatement();
		
		Statement st2 = conn.createStatement();
		
		Statement st3 = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * from UnemploymentLevels");
		
		ResultSet rs_female = st1.executeQuery("SELECT * from UnemploymentLevels");
		
		ResultSet rs_male = st2.executeQuery("SELECT * from UnemploymentLevels");
		
		ResultSet rs_male_2 = st3.executeQuery("SELECT * from UnemploymentLevels");
		
		DataModellerGUI obj = new DataModellerGUI(st,rs,rs_female,rs_male,rs_male_2);
		
		System.out.println(obj);
		
	}
	
	
}

