package com.datamodeller.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataModellerGUI 
{
	Statement statement;
	ResultSet resultSet;

	public DataModellerGUI(Statement st, ResultSet rs) 
	{
		this.statement = st;
		this.resultSet = rs;
	}
	
	public void firstFact() throws SQLException
	{
		int counter = 0;
		
		int third_level_unemployed_females = 0;
		while(resultSet.next())
		{
			String student_sex = resultSet.getString("Sex");
			String education = resultSet.getString("EducationLevel");
			
			if(student_sex.equals("Female"))
			{
				counter += 1;
				System.out.println(student_sex + " -> " + education);
			}
			
			
			if(student_sex.equals("Female") && education.equals("Third level"))
			{
				third_level_unemployed_females += 1;
				System.out.println(student_sex + " -> " + education);
			}
			
		}
		
		System.out.println("Total females -> " + counter);
		
		System.out.println("Total of females unemployed with third level education -> " + third_level_unemployed_females);
		
		float percentage = 0;
		
		percentage = (((float)third_level_unemployed_females / (float)counter) * 100);
		
		System.out.println("Percentage of women unemployed with third level education -> " + percentage);
		
		resultSet.close();
		
	}
	
}
