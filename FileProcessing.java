/***********************************
*File Processing: This is the file processing class. This class allows the user to find which documents contain the word they typed
*		  by opening the file and reading it. 
*Author: Amar Plakalo
*Date:05/04/2021
***********************************/


package com.javaapp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;



public class FileProcessing 
{
	private String[] files;
	private File[] documentsNeeded;
	private Map<File, Integer> numberOfOccurences = new HashMap<File, Integer>();
	
	public FileProcessing(String[] files)
	{
		this.setFiles(files);
		this.setNumberOfOccurences(numberOfOccurences);
		getFiles();
		getNumberOfOccurences();
	}
	
	public void openFile()
	{
		// The files.length calculates the length of the array so the program nows how many files are being used
		documentsNeeded = new File[files.length];
		
		// Loops around for the length of the file and creates new file objects in the array
		for(int i = 0; i < files.length; i++)
		{
			documentsNeeded[i] = new File(files[i]);
		}
	}
	
	public Map<File, Integer> readFile(String[] wordsThatWereEntered)
	{
		// LineInFile string is used to store the line in the file so it can be checked against the search term/terms
		String lineInFile = "";
		
		
		// Counter counts the amount of occurences of a word in a file
		int counter = 0;

		
		try
		{
			for(int m = 0; m < files.length; m++)
			{
				Scanner searchForWord = new Scanner(documentsNeeded[m]);
				
				while(searchForWord.hasNext())
				{
					lineInFile = searchForWord.next();
	
					lineInFile = lineInFile.replaceAll("\\p{Punct}", "");

					if (wordsThatWereEntered.length > 1)
					{
						for(int i = 0; i < wordsThatWereEntered.length; i++)
						{
							wordsThatWereEntered[i] = wordsThatWereEntered[i].replaceAll("\\p{Punct}", "");
							if (lineInFile.equals(wordsThatWereEntered[i]))
							{
								counter ++;
							
							}
						}
							
						
					}
					else if(wordsThatWereEntered.length == 1)
					{
						wordsThatWereEntered[0] = wordsThatWereEntered[0].replaceAll("\\p{Punct}", "");
						if (lineInFile.equals(wordsThatWereEntered[0]))
						{	
							counter += 1;
						}
					}
		
				}
				numberOfOccurences.put(documentsNeeded[m], counter);
				counter = 0;
			
	
				
				
				searchForWord.close();
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found: ");
			e.printStackTrace();
		}
		return numberOfOccurences;
		
	}


	
	private String[] getFiles() {
		return files;
	}

	private void setFiles(String[] files) 
	{
		this.files = files;
	}

	Map<File, Integer> getNumberOfOccurences() {
		return numberOfOccurences;
	}

	void setNumberOfOccurences(Map<File, Integer> numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}
}
