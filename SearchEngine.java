package com.javaapp.test;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchEngine extends JFrame implements ActionListener
{
	JTextField searchForWords;
	JButton searchButton;
	JPanel panel;
	
	SearchEngine(String titleOfApp)
	{
		super(titleOfApp);
		setSize(250,300);
		panel = new JPanel();
		
		searchButton = new JButton("Search");
		
		searchButton.addActionListener(this);
		
		searchForWords = new JTextField();
		searchForWords.setColumns(14);
		searchForWords.addActionListener(this);
		
		
		
		add(panel);

		
		panel.add(searchForWords);
		panel.add(searchButton);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent eventDetected)
	{
		if (eventDetected.getSource() == searchButton)
		{
			FileProcessing firstDocument = new FileProcessing("ap_docs2.txt");
			
			firstDocument.openFile();
			
			String wordTyped = searchForWords.getText();
			firstDocument.readFile(wordTyped);
		}

	}
	
	
}
