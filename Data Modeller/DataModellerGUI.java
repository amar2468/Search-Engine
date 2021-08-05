/***********************************
*DataModellerGUI: This class has the GUI elements which are interactive i.e. you can click on them. There are 
*four buttons to click which will present a certain fact from the dataset I used which was - 25-64 yr olds 
*who are unemployed.
*Author: Amar Plakalo
*Date:05/08/2021
*/

package com.datamodeller.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
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
	ResultSet resultSet2;
	ResultSet resultSet3;
	JButton b1,b2,b3,b4;
	


	public DataModellerGUI(Statement st, ResultSet rs,ResultSet rs_female,ResultSet rs_male,ResultSet rs_male_2) 
	{
		this.statement = st;
		this.resultSet = rs;
		this.resultSet1 = rs_female;
		this.resultSet2 = rs_male;
		this.resultSet3 = rs_male_2;
		
		setSize(1500,500);
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER,20,35);
		FlowLayout flow2 = new FlowLayout(FlowLayout.CENTER,20,10);
		BorderLayout borlay3 = new BorderLayout();
		
		
		ImageIcon myPicture = new ImageIcon("unnamed.jpg");
		Image img = myPicture.getImage();
		Image modifiedImage = img.getScaledInstance(1600, 600, DO_NOTHING_ON_CLOSE);
		myPicture = new ImageIcon(modifiedImage);
		
		// This label will hold the picture. The picture will be placed in the lowest panel.
		
		JLabel picLabel = new JLabel((myPicture));
	
		b1 = new JButton("First Fact");
		b2 = new JButton("Second Fact");
		b3 = new JButton("Third Fact");
		b4 = new JButton("Fourth Fact");
		
		b1.setBackground(Color.orange);
		b2.setBackground(Color.orange);
		b3.setBackground(Color.orange);
		b4.setBackground(Color.orange);
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		p1.setLayout(flow);
		p1.setBackground(Color.RED);
		
		p2.setLayout(flow2);
		p2.setBackground(Color.RED);
		
		p3.setLayout(borlay3);
		p3.setBackground(Color.RED);
		
		JLabel label = new JLabel("Find out about interesting facts from the following database - Unemploy"
				+ "ment rate for people from the ages of 25-64");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		add(p1,"Center");
		add(p2,"North");
		add(p3,"South");
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p2.add(label);
		p3.add(picLabel);
		
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
		String output = String.format("%.2f", percentage);
		JOptionPane.showMessageDialog(this, "Percentage of women unemployed with third level education in 2011 -> " + output);
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
		
		String output = String.format("%.2f", percentage);
		JOptionPane.showMessageDialog(this, "Percentage of women unemployed with third level education in 2020 -> " + output);
	}
	
	
	public void thirdFact() throws SQLException
	{
		int sum_new_1 = 0;
		
		
		int third_level_unemployed_males_1 = 0;
		
	
		while(resultSet2.next())
		{
			String student_sex = resultSet2.getString("Sex");
			String quarter2 = resultSet2.getString("quarter");
			String education = resultSet2.getString("EducationLevel");
			int totalMales2011 = resultSet2.getInt("Value");
			
			if(student_sex.equals("Male") && quarter2.equals("2011Q2"))
			{
				sum_new_1 += totalMales2011;
				System.out.println(student_sex + " -> " + education);
			}
			
			
			if(student_sex.equals("Male") && education.equals("Third level") && quarter2.equals("2011Q2"))
			{
				third_level_unemployed_males_1 += totalMales2011;
				System.out.println(student_sex + " -> " + education);
			}
			
		}
		
		System.out.println("Total males in 2011 -> " + sum_new_1);
		
		System.out.println("Total of men unemployed with third level education in 2011 -> " + third_level_unemployed_males_1);
		
		float percentage = 0;
		
		percentage = (((float)third_level_unemployed_males_1 / (float)sum_new_1) * 100);
		
		System.out.println("Percentage of men unemployed with third level education in 2011 -> " + percentage);
		
		String output = String.format("%.2f", percentage);
		JOptionPane.showMessageDialog(this, "Percentage of men unemployed with third level education in 2011 -> " + output);
	}
	
	
	public void fourthFact() throws SQLException
	{
		int sum_new_2 = 0;
		
		
		int third_level_unemployed_males_2 = 0;
		
	
		while(resultSet3.next())
		{
			String student_sex = resultSet3.getString("Sex");
			String quarter2 = resultSet3.getString("quarter");
			String education = resultSet3.getString("EducationLevel");
			int totalMales2020 = resultSet3.getInt("Value");
			
			if(student_sex.equals("Male") && quarter2.equals("2020Q2"))
			{
				sum_new_2 += totalMales2020;
				System.out.println(student_sex + " -> " + education);
			}
			
			
			if(student_sex.equals("Male") && education.equals("Third level") && quarter2.equals("2020Q2"))
			{
				third_level_unemployed_males_2 += totalMales2020;
				System.out.println(student_sex + " -> " + education);
			}
			
		}
		
		System.out.println("Total males in 2020 -> " + sum_new_2);
		
		System.out.println("Total of men unemployed with third level education in 2020 -> " + third_level_unemployed_males_2);
		
		float percentage = 0;
		
		percentage = (((float)third_level_unemployed_males_2 / (float)sum_new_2) * 100);
		
		System.out.println("Percentage of men unemployed with third level education in 2020 -> " + percentage);
		
		String output = String.format("%.2f", percentage);
		JOptionPane.showMessageDialog(this, "Percentage of men unemployed with third level education in 2020 -> " + output);
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
		
		else if(eventDetected.getSource() == b3)
		{
			try 
			{
				thirdFact();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		else if(eventDetected.getSource() == b4)
		{
			try
			{
				fourthFact();
			}
			catch(SQLException e)
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
