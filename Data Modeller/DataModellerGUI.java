package com.datamodeller.test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DataModellerGUI extends JFrame implements ActionListener
{
	Statement statement;
	ResultSet resultSet;
	ResultSet resultSet1;
	JButton b1,b2;
	


	public DataModellerGUI(Statement st, ResultSet rs,ResultSet rs_female) 
	{
		this.statement = st;
		this.resultSet = rs;
		this.resultSet1 = rs_female;
		
		setSize(1500,500);
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER,20,30);
		FlowLayout flow2 = new FlowLayout(FlowLayout.CENTER,20,20);
		
		b1 = new JButton("First Fact");
		b2 = new JButton("Second Fact");
		
		b1.setBackground(Color.orange);
		b2.setBackground(Color.orange);
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.setLayout(flow);
		p1.setBackground(Color.MAGENTA);
		
		p2.setLayout(flow2);
		p2.setBackground(Color.MAGENTA);
		
		JLabel label = new JLabel("Find out about interesting facts from the following database - Unemploy"
				+ "ment rate for people from the ages of 25-64");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		add(p1,"Center");
		add(p2,"North");
		
		p1.add(b1);
		p1.add(b2);
		p2.add(label);
		
		setVisible(true);
	}
	
	public void firstFact() throws SQLException
	{	
		int sum = 0;
		
		int third_level_unemployed_females = 0;
		
	
		while(resultSet.next())
		{
			String student_sex = resultSet.getString("Sex");
			int totalFemales = resultSet.getInt("Value");
			String education = resultSet.getString("EducationLevel");
			String quarter1 = resultSet.getString("quarter");
			
			if(student_sex.equals("Female") && quarter1.equals("2011Q2"))
			{
				sum += totalFemales;
				System.out.println(student_sex + " -> " + education);
			}
			
			
			if(student_sex.equals("Female") && education.equals("Third level") && quarter1.equals("2011Q2"))
			{
				third_level_unemployed_females += totalFemales;
				System.out.println(student_sex + " -> " + education);
			}
			
		}
		
		System.out.println("Total females in 2011 -> " + sum);
		
		System.out.println("Total of females unemployed with third level education in 2011 -> " + third_level_unemployed_females);
		
		float percentage = 0;
		
		percentage = (((float)third_level_unemployed_females / (float)sum) * 100);
		
		System.out.println("Percentage of women unemployed with third level education in 2011 -> " + percentage);
		
		JOptionPane.showMessageDialog(this, "Percentage of women unemployed with third level education in 2011 -> " + percentage);
	}
	
	public void secondFact() throws SQLException
	{
		int sum_new = 0;
		
		
		int third_level_unemployed_females_2 = 0;
		
	
		while(resultSet1.next())
		{
			String student_sex = resultSet1.getString("Sex");
			String quarter2 = resultSet1.getString("quarter");
			String education = resultSet1.getString("EducationLevel");
			int totalFemales2020 = resultSet1.getInt("Value");
			
			if(student_sex.equals("Female") && quarter2.equals("2020Q2"))
			{
				sum_new += totalFemales2020;
				System.out.println(student_sex + " -> " + education);
			}
			
			
			if(student_sex.equals("Female") && education.equals("Third level") && quarter2.equals("2020Q2"))
			{
				third_level_unemployed_females_2 += totalFemales2020;
				System.out.println(student_sex + " -> " + education);
			}
			
		}
		
		System.out.println("Total females in 2020 -> " + sum_new);
		
		System.out.println("Total of females unemployed with third level education in 2020 -> " + third_level_unemployed_females_2);
		
		float percentage = 0;
		
		percentage = (((float)third_level_unemployed_females_2 / (float)sum_new) * 100);
		
		System.out.println("Percentage of women unemployed with third level education in 2020 -> " + percentage);
		
		
		JOptionPane.showMessageDialog(this, "Percentage of women unemployed with third level education in 2020 -> " + percentage);
	}
	
	public void actionPerformed(ActionEvent eventDetected)
	{
		if(eventDetected.getSource() == b1)
		{
			try 
			{
				firstFact();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		
		else if(eventDetected.getSource() == b2)
		{
			try 
			{
				secondFact();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public String toString()
	{
		String sentenceReturned = "";
		
		sentenceReturned = "Welcome to the Data Modelling App! Enjoy exploring the facts!!!";
		
		return sentenceReturned;
	}
	
}
