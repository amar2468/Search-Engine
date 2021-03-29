/***********************************
*Search Engine: This is the search engine class which allows the user to enter the word they wish to find and the file processing
*         class is called within this class so that the file can be opened and the word can be searched.
*Author: Amar Plakalo
*Date:29/03/2021
***********************************/


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
			
			FileProcessing firstDocument = new FileProcessing("ap_docs2.txt","simpleTextFile.txt");
			
			firstDocument.openFile();
			
			String wordTyped = searchForWords.getText();
			
	        
			firstDocument.readFile(wordTyped);
		
			firstDocument.reverseMap();
			
		
		}

	}
	
	
}
