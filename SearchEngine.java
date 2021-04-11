/***********************************
*Search Engine: This is the search engine class which allows the user to enter the word they wish to find and the file processing
*         class is called within this class so that the file can be opened and the word can be searched.
*Author: Amar Plakalo
*Date:11/04/2021
***********************************/


package com.javaapp.test;



import java.awt.Color;
import java.awt.FlowLayout;
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
		// inherits the title from the super class and sets the size of the GUI window
		super(titleOfApp);
		setSize(470,300);
		
		// create a panel which will store the title and set the background colour to red
		panel = new JPanel();
		panel.setBackground(Color.red);
		
		panel.setLayout(new FlowLayout());
		
		// create panel2 which stores textfield and three buttons and set background colour to gray.
		// Flowlayout is used
		
		FlowLayout flayout = new FlowLayout();
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(flayout);
		panel2.setBackground(Color.gray);
		
		// Title of the Application
		
		title = new JLabel("WELCOME TO THE SEARCH ENGINE! HAVE FUN!!!");
		
		// Initializing the button objects so that they have text
		
		searchButton = new JButton("Search");
		showFiles = new JButton("Show Files");
		chooseFileToRead = new JButton("Choose File");
		
		// calling action listener so that the buttons can perform an action
		searchButton.addActionListener(this);
		chooseFileToRead.addActionListener(this);
		showFiles.addActionListener(this);
		
		// Creating text field and setting the size of it and then calling the action listener so it can perform
		// an action, if used.
		
		searchForWords = new JTextField();
		searchForWords.setColumns(14);
		searchForWords.addActionListener(this);
		
		// Adding the panels and organizing them on screen
		
		add(panel,"North");
		add(panel2,"West");
		
		// Adding elements to the panels and setting it to be visible on screen.

		panel.add(title);
		panel2.add(searchForWords);
		panel2.add(searchButton);
		panel2.add(chooseFileToRead);
		panel2.add(showFiles);
		setVisible(true);
		
	}
	
	public String toString()
	{
		// Will be returned to the control class which will print the message below
		String messageToUser = "Welcome to the Search Engine app. Search a word and the program will tell you which "
				+ "documents contain that word and how many times the word occurs in the documents";
		
		return messageToUser;
							
	}
	public void actionPerformed(ActionEvent eventDetected)
	{
		if (eventDetected.getSource() == searchButton) // if the user chose the search button
		{
			if(documents.isEmpty()) // if there are no files selected
			{
				// tell the user that no files have been selected and prompt them to select files
				JOptionPane.showMessageDialog(this, "No files have been chosen. Please select files!");
			}
			else // otherwise, if there are files
			{
				// create a FileProcessing object documentsToRead and pass the documents inside the FileProcessing 
				// class
				FileProcessing documentsToRead = new FileProcessing(documents);
				
				// call the method openFile() to open the file/files needed
				documentsToRead.openFile();
				
				// save the word/words entered in the text field inside a string variable
				String wordsTyped = searchForWords.getText();
				
				
				// split the string variable contents and put inside the array that stores the words.
				String [] words = wordsTyped.split(" ");
				
				System.out.println(Arrays.toString(words));
				
				// creating this map so that I can put the results of the Results map inside the documentsToProcess
				// map. 
				Map<File, Integer> documentsToProcess = new HashMap<File, Integer>();
				
				Map<File, Integer> Results = new HashMap<File, Integer>();
				
		        
				// calling the readFile() method and passing the words entered and getting the results (map) inside
				// the Results map
				Results = documentsToRead.readFile(words);
			
				// put the Results map inside the documentsToProcess map
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
			        // This prints out the occurrences in the files and the percentage
				for(int x = 0; x < listContainingFilesWithOccurences.size(); x++)
				{
					String returnedPercentage = documentsToRead.calculatePercentage(listContainingFilesWithOccurences.get(x) , x);
					JOptionPane.showMessageDialog(null,listContainingFilesWithOccurences.get(x) + "    Percentage -> " + returnedPercentage + "%");
				}
			}
				
		
		}
		
		
		else if(eventDetected.getSource() == chooseFileToRead) // if the user chose the option to choose a file
		{
			int checker = 0;
			
			// this shows the file system
			// so the user can pick the file
		        // in whatever directory
			
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView()); 
			
			 // once the user chooses an option, either to save or cancel,
		         // the program will identify what the user chose and this will give
		        // a return value
			  
			int retVal = j.showSaveDialog(null); 
			
			
			if(retVal == JFileChooser.APPROVE_OPTION) // if the user chose to save the file they selected
			{
				if(documents.isEmpty()) // check is the file list empty. If yes, add the file into the list of files
				{
					JOptionPane.showMessageDialog(this, "Successfully chose the file name " + j.getSelectedFile().getName());
					documents.add(j.getSelectedFile().getName());
					System.out.println(documents);	
				}
				else // Otherwise
				{
					for(int t=0; t < documents.size(); t++) // Loop for amount of documents chosen
					{
						if(documents.get(t).equals(j.getSelectedFile().getName())) 
						// if the file chosen is already
					    	// in the list
						{
							checker = 1; // set checker to 1
							break; // immediately break because the file already occurred - no need to check more
						}
						
					}
					
					if(checker == 1) // if the checker value was 1 i.e. file is in the list
					{
						JOptionPane.showMessageDialog(this, "Already added the file"); // print message
					}
					else if(checker == 0) // If file is not in the list
					{
						// Add the file to the list
						JOptionPane.showMessageDialog(this, "Successfully chose the file name " + j.getSelectedFile().getName());
						documents.add(j.getSelectedFile().getName());
						System.out.println(documents);
					}
				}
				
				
			}
			else // if the user chose to cancel i.e. didn't select the file
			{
				JOptionPane.showMessageDialog(this, "Failed to choose the file correctly"); // print error message
			}
		}
		
		
		else if(eventDetected.getSource() == showFiles) // If the user chose to show the files
		{
			// call the class ShowingFiles and create an object that will be used to call the openingFilesNeeded()
			// method and the readFileContents() method.
			ShowingFiles showingfiles1 = new ShowingFiles("Showing the Files",documents);
			showingfiles1.openingFilesNeeded();
			showingfiles1.readFileContents();
		}

	}
	
	
}
