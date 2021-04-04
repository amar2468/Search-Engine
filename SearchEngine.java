/***********************************
*Search Engine: This is the search engine class which allows the user to enter the word they wish to find and the file processing
*         class is called within this class so that the file can be opened and the word can be searched.
*Author: Amar Plakalo
*Date:04/04/2021
***********************************/


package com.javaapp.test;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		setSize(400,400);
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
	
	public String toString()
	{
		String messageToUser = "Welcome to the Search Engine app. Search a word and the program will tell you which "
				+ "documents contain that word and how many times the word occurs in the documents";
		
		return messageToUser;
							
	}
	public void actionPerformed(ActionEvent eventDetected)
	{
		if (eventDetected.getSource() == searchButton)
		{
			String [] documents = {"ap_docs2.txt","simpleTextFile.txt"};
			FileProcessing documentsToRead = new FileProcessing(documents);
			
			documentsToRead.openFile();
			
			
			String wordsTyped = searchForWords.getText();
			
			
			String [] words = wordsTyped.split(" ");
			
			System.out.println(Arrays.toString(words));
			
			
			
			
			
			Map<File, Integer> documentsToProcess = new HashMap<File, Integer>();
			
			Map<File, Integer> Results = new HashMap<File, Integer>();
			
	        
			Results = documentsToRead.readFile(words);
		
			
			documentsToProcess.putAll(Results);
			
			

			// The entries inside the hash map will be viewed as an entrySet(). Entry set is needed when I need
			// both key and value from the data. In this case, I want to have the file name and the number of 
	        	// times that the word occurred in that file	
			Set<Entry<File, Integer>> setContainingFilesWithOccurences = documentsToProcess.entrySet();
			 
			// An ArrayList is created because when using the Collections.sort method, the first parameter must be a list
			// so I had to make an array list out of the key,value.
			 
		    	List<Entry<File, Integer>> listContainingFilesWithOccurences = new ArrayList<Entry<File, Integer>>(setContainingFilesWithOccurences);
		    	Collections.sort(listContainingFilesWithOccurences, new Comparator<Map.Entry<File, Integer>>()

		    	{
		    		public int compare(Map.Entry<File, Integer> firstOccurence, Map.Entry<File, Integer> secondOccurence)
		        	{	
		    			return (secondOccurence.getValue()).compareTo(firstOccurence.getValue());
		        	}

		    	{	
		    		public int compare(Map.Entry<File, Integer> firstOccurence, Map.Entry<File, Integer> secondOccurence)
		        	{
		    			return (secondOccurence.getValue()).compareTo(firstOccurence.getValue());
		     		}

		    	} 
		    	);
		     
			JOptionPane.showMessageDialog(this, listContainingFilesWithOccurences);
			
			
			
			
			
		
		}

	}
	
	
}
