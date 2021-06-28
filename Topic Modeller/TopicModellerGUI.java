package com.topicmodeller.test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TopicModellerGUI extends JFrame implements ActionListener
{
	JButton button1;
	
	JPanel panel1,panel2;
	
	JLabel titleLabel;
	
	String stringToBePrinted = "";
	
	TopicModellerGUI(String title)
	{
		super(title);
		
		setSize(400,340);
		
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.pink);
		
		FlowLayout flay = new FlowLayout();
		panel2 =  new JPanel();
		panel2.setLayout(flay);
		panel2.setBackground(Color.cyan);
		
		
		button1 = new JButton("Check if same topic");
		button1.setBackground(Color.green);
		button1.addActionListener(this);
		
		
		titleLabel = new JLabel("Enjoy using the Topic Modeller App!!");
		titleLabel.setBackground(Color.darkGray);
	
		
		add(panel1,"North");
		add(panel2,"Center");
		
		panel1.add(titleLabel);
		
		panel2.add(button1);
		
		setVisible(true);
		
	}
	
	public void initialiseVariableWithResultString(String resultFromCheck)
	{
		stringToBePrinted = resultFromCheck;
	}
	
	public void actionPerformed(ActionEvent eventDetected)
	{
		if(eventDetected.getSource() == button1)
		{
			JOptionPane.showMessageDialog(button1, stringToBePrinted);
			
		}
	}
	
	public String toString()
	{
		String stringresult;
		
		stringresult = "Enjoy using the Topic Modeller App!!";

		return stringresult;
	}

}

