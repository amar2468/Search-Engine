/***********************************
*File Processing: This is the file processing class. This class allows the user to find which documents contain the word they typed
*		  by opening the file and reading it. 
*Author: Amar Plakalo
*Date:03/04/2021
***********************************/


package com.javaapp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;



public class FileProcessing 
{
	private String fileName;
	private File document;
	private Map<File, Integer> numberOfOccurences = new HashMap<File, Integer>();
	
	public FileProcessing(String fileName)
	{
		this.setFileName(fileName);
		this.setNumberOfOccurences(numberOfOccurences);
		getFileName();
		getNumberOfOccurences();
	}
	
	public void openFile()
	{
		document = new File(fileName);
	}
	
	public Map<File, Integer> readFile(String[] wordsThatWereEntered)
	{
		String lineInFile = "";
		int counter = 0;

		
		try
		{
			Scanner searchForWord = new Scanner(document);
			
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
			numberOfOccurences.put(document, counter);

			
			
			searchForWord.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found: ");
			e.printStackTrace();
		}
		return numberOfOccurences;
		
	}


	
	private String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	Map<File, Integer> getNumberOfOccurences() {
		return numberOfOccurences;
	}

	void setNumberOfOccurences(Map<File, Integer> numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}
}
