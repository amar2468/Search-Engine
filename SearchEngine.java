package com.javaapp.test;



import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchEngine extends JFrame
{
	JTextField searchForWords;
	JButton searchButton;
	JPanel panel;
	
	SearchEngine(String titleOfApp)
	{
		super(titleOfApp);
		setSize(250,300);
		
		JTextField searchForWords = new JTextField();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8,1));
		JButton searchButton = new JButton("Search");
		add(panel);
		panel.add(searchForWords);
		panel.add(searchButton);
		setVisible(true);
		
	}
	
	
}
