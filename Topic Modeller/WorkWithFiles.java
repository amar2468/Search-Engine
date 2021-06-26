package com.topicmodeller.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WorkWithFiles 
{
	private ArrayList <String> textFileNames;
	
	private File[] filesUsed;
	
	private Map<String,Integer>wordsInFile;
	
	private File file;
	
	private String fileName;
	
	private Map<String,Integer>secondMap;

	private ArrayList<String> allStopWords = new ArrayList<String>();
	

	WorkWithFiles(ArrayList<String> textFileNames,String fileName)
	{
		this.textFileNames = textFileNames;
		
		this.fileName = fileName;
	}

	
	public void openFile()
	{
		filesUsed = new File[textFileNames.size()];
		
		wordsInFile = new HashMap<String,Integer>();
		
		secondMap = new HashMap<String,Integer>();
		
		file = new File(fileName);
		
		
		for(int i = 0; i < textFileNames.size(); i++)
		{
			filesUsed[i] = new File(textFileNames.get(i));
		}
		
		
	}

	
	public void readFile()
	{
		String word = "";
		
		int var = 0;
		
		try
		{
			for(int j = 0; j < textFileNames.size();j++)
			{
				Scanner scanFileContents = new Scanner(filesUsed[j]);
				
				
				while(scanFileContents.hasNextLine())
				{
					word = scanFileContents.next();
					
					
					word = word.replace(",","");
					
					word = word.replaceAll("\\p{Punct}", "");
					
					
					if(!allStopWords.contains(word))
					{
					
						if(var == 0)
						{
						
							if(wordsInFile.containsKey(word))
							{
								wordsInFile.put(word,wordsInFile.get(word) + 1);
								
							}
							else
							{
								wordsInFile.put(word,1);
							}
						}
						else if(var == 1)
						{
							if(secondMap.containsKey(word))
							{
								secondMap.put(word,secondMap.get(word) + 1);
								
							}
							else
							{
								secondMap.put(word,1);
							}
						}
					}
					
				}
				
				var = 1;
				
				word = "";
			
				scanFileContents.close();
			}
		
			
			System.out.println(wordsInFile);
			System.out.println(secondMap);
			
			
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Could not find file");
			e.printStackTrace();
		}
		
	}
	
	public void readStopWordFile() throws FileNotFoundException
	{
		String currentStopWord = "";
		
		Scanner scanCurrentStopWord = new Scanner(file);
		
		while(scanCurrentStopWord.hasNextLine())
		{
			currentStopWord = scanCurrentStopWord.next();
			
			currentStopWord = currentStopWord.replace(",","");
			
			currentStopWord = currentStopWord.replaceAll("\\p{Punct}", "");
			
			allStopWords.add(currentStopWord);
			
		}
	
		
		
		scanCurrentStopWord.close();

	}
	
	public void checkWhetherDocumentsAreCommon()
	{
		int counter = 0;
		
		for(String key:wordsInFile.keySet())
		{
			if(secondMap.containsKey(key))
			{
				counter += 1;
			}
		}
		
		System.out.println("Amount of overlaps - > " + counter);
		
		
		int totalAmountOfWords = 0;
		
		if(wordsInFile.size() > secondMap.size())
		{
			totalAmountOfWords = wordsInFile.size();
		}
		
		else if(wordsInFile.size() == secondMap.size())
		{
			totalAmountOfWords = wordsInFile.size();
		}
		
		else if(wordsInFile.size() < secondMap.size())
		{
			totalAmountOfWords = secondMap.size();
		}
		
		System.out.println("Total amount of words - > " + totalAmountOfWords);
		
		
		if((((float)counter / (float)totalAmountOfWords)*100) >= 70)
		{
			System.out.println("Likely to be about the same topic");
		}
		
		else if((((float)counter / (float)totalAmountOfWords)*100) > 40 && ((counter / totalAmountOfWords) * 100) < 70)
		{
			System.out.println("Not sure whether they are about the same topic");
		}
		else if((((float)counter / (float)totalAmountOfWords)*100) <= 40)
		{
			System.out.println("Not likely to be about the same topic");
		}
	}

	public String getFile() {
		return fileName;
	}

	public void setFile(String fileName2) {
		this.fileName = fileName2;
	}


}
