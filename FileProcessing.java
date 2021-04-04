/***********************************
*File Processing: This is the file processing class. This class allows the user to find which documents contain the word they typed
*		  by opening the file and reading it. 
*Author: Amar Plakalo
*Date:04/04/2021
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
	private File[] documentsNeeded = new File[2];
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
		
		for(int i = 0; i < files.length; i++)
		{
			documentsNeeded[i] = new File(files[i]);
		}
	}
	
	public Map<File, Integer> readFile(String[] wordsThatWereEntered)
	{
		String lineInFile = "";
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
							if (lineInFile.equals(wordsThatWereEntered[i]))
							{
								counter ++;
							
							}
						}
							
						
					}
					else if(wordsThatWereEntered.length == 1)
					{
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
