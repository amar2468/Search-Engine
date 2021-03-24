package com.javaapp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class FileProcessing 
{
	private String fileName;
	private File document;
	private Dictionary<File, Integer> numberOfOccurences = new Hashtable<File, Integer>();
	
	public FileProcessing(String fileName)
	{
		this.setFileName(fileName);
		this.setNumberOfOccurences(numberOfOccurences);
	}
	
	public void openFile()
	{
		document = new File(fileName);
	}
	
	public void readFile(String wordEnteredByUser)
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
				if (lineInFile.equals(wordEnteredByUser))
				{
					counter += 1;
				}
	
			}
			numberOfOccurences.put(document, counter);
			System.out.println("\n" + document + ":" + numberOfOccurences.get(document));
			searchForWord.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found: ");
			e.printStackTrace();
		}
	}


	private String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	Dictionary getNumberOfOccurences() {
		return numberOfOccurences;
	}

	void setNumberOfOccurences(Dictionary<File, Integer> numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}
}
