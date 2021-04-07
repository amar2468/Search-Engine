/***********************************
*Search Engine: This is the search engine class which allows the user to enter the word they wish to find and the file processing
*         class is called within this class so that the file can be opened and the word can be searched.
*Author: Amar Plakalo
*Date:07/04/2021
***********************************/


package com.javaapp.test;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;


@SuppressWarnings("serial")
public class SearchEngine extends JFrame implements ActionListener
{
	JTextField searchForWords;
	JButton searchButton,chooseFileToRead,showFiles;
	JLabel title;
	JPanel panel;
	ArrayList<String> documents = new ArrayList<String>();
	
	SearchEngine(String titleOfApp)
	{
		super(titleOfApp);
		setSize(470,300);
		panel = new JPanel();
		panel.setBackground(Color.red);
		
		panel.setLayout(new GridLayout(1,4));
		
		FlowLayout flayout = new FlowLayout();
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(flayout);
		panel2.setBackground(Color.gray);
		
		title = new JLabel("WELCOME TO THE SEARCH ENGINE! HAVE FUN!!!");
		
		searchButton = new JButton("Search");
		showFiles = new JButton("Show Files");
		chooseFileToRead = new JButton("Choose File");
		
		searchButton.addActionListener(this);
		chooseFileToRead.addActionListener(this);
		showFiles.addActionListener(this);
		
		searchForWords = new JTextField();
		searchForWords.setColumns(14);
		searchForWords.addActionListener(this);
		
		
		
		add(panel,"North");
		add(panel2,"West");

		panel.add(title);
		panel2.add(searchForWords);
		panel2.add(searchButton);
		panel2.add(chooseFileToRead);
		panel2.add(showFiles);
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
			if(documents.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "No files have been chosen. Please select files!");
			}
			else
			{
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
 
			    	ArrayList<Entry<File, Integer>> listContainingFilesWithOccurences = new ArrayList<Entry<File, Integer>>(setContainingFilesWithOccurences);
			    	Collections.sort(listContainingFilesWithOccurences, new Comparator<Map.Entry<File, Integer>>()
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
		
		
		else if(eventDetected.getSource() == chooseFileToRead)
		{
			int checker = 0;
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
			  
			int retVal = j.showSaveDialog(null);
			
			
			if(retVal == JFileChooser.APPROVE_OPTION)
			{
				if(documents.isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Successfully chose the file name " + j.getSelectedFile().getName());
					documents.add(j.getSelectedFile().getName());
					System.out.println(documents);	
				}
				else
				{
					for(int t=0; t < documents.size(); t++)
					{
						if(documents.get(t).equals(j.getSelectedFile().getName()))
						{
							checker = 1;
							break;
						}
						
					}
					
					if(checker == 1)
					{
						JOptionPane.showMessageDialog(this, "Already added the file");
					}
					else if(checker == 0)
					{
						JOptionPane.showMessageDialog(this, "Successfully chose the file name " + j.getSelectedFile().getName());
						documents.add(j.getSelectedFile().getName());
						System.out.println(documents);
					}
				}
				
				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Failed to choose the file correctly");
			}
		}
		
		
		else if(eventDetected.getSource() == showFiles)
		{
			ShowingFiles showingfiles1 = new ShowingFiles("Showing the Files",documents);
			showingfiles1.openingFilesNeeded();
			showingfiles1.readFileContents();
		}

	}
	
	
}

	}
	
	
}
