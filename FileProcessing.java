/***********************************
*File Processing: This is the file processing class. This class allows the user to find which documents contain the word they typed
*		  by opening the file and reading it. 
*Author: Amar Plakalo
*Date:29/03/2021
***********************************/


package com.javaapp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.Scanner;



public class FileProcessing 
{
	private String fileName;
	private String otherFile;
	private File document;
	private Map<File, Integer> numberOfOccurences = new HashMap<File, Integer>();
	
	public FileProcessing(String fileName,String otherFile)
	{
		this.setFileName(fileName,otherFile);
		this.setNumberOfOccurences(numberOfOccurences);
	}
	
	public void openFile()
	{
		ArrayList<String>listOfFiles = new ArrayList<String>();
		listOfFiles.add(fileName);
		listOfFiles.add(otherFile);
	}
	
	public void readFile(String wordEnteredByUser)
	{
		String lineInFile = "";
		int counter = 0;

		
		try
		{
			Scanner searchForWord = new Scanner(listOfFiles);
			
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
			
			
			searchForWord.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found: ");
			e.printStackTrace();
		}
		
	}
	
	public void reverseMap()
	{
		LinkedHashMap<File, Integer> reverseSortedMap = new LinkedHashMap<>();
		
		numberOfOccurences.entrySet()
		    .stream()
		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
		    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
		 
		System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
	}

	
	private String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName,String otherFile) 
	{
		this.fileName = fileName;
		this.otherFile = otherFile;
	}

	Map getNumberOfOccurences() {
		return numberOfOccurences;
	}

	void setNumberOfOccurences(Map<File, Integer> numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}
}
