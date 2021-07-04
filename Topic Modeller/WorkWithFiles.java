/***********************************
*WorkWithFiles: This is the WorkWithFiles class
*Author: Amar Plakalo
*Date:04/07/2021
*/

package com.topicmodeller.test;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class WorkWithFiles 
{
	private File[] filesUsed;
	
	private Map<String,Integer>wordsInFile = new HashMap<String,Integer>();
	
	private String fileName;
	
	private File file;
	
	private Map<String,Integer>secondMap;
	
	private ArrayList<String>textFileNames = new ArrayList<String>();

	private ArrayList<String> allStopWords = new ArrayList<String>();


	WorkWithFiles(ArrayList<String>textFileNames,String fileName)
	{
		this.setTextFileNames(textFileNames);
		this.setFileName(fileName);
	}

	
	public void openFile()
	{
		filesUsed = new File[getTextFileNames().size()];
		
		wordsInFile = new HashMap<String,Integer>();
		
		secondMap = new HashMap<String,Integer>();
		
		file = new File(fileName);
		
		
		for(int i = 0; i < getTextFileNames().size(); i++)
		{
			filesUsed[i] = new File(getTextFileNames().get(i));
		}
		
		
	}

	
	public void readFile()
	{
		String word = "";
		
		int fileNumber = 0;
		
		try
		{
			for(int j = 0; j < getTextFileNames().size();j++)
			{
				Scanner scanFileContents = new Scanner(filesUsed[j],"UTF-8");
				
				
				while(scanFileContents.hasNextLine())
				{
					word = scanFileContents.next();
					
					word = word.replace(",","");
					
					word = word.replaceAll("[^a-zA-Z0-9]","");
					
					word = word.replace(" ","");
					
					word = word.replaceAll("[-+^]*", "");  
					
					
					word = word.replaceAll("\\p{Punct}", "");
					
					
					if(!allStopWords.contains(word))
					{
					
						if(fileNumber == 0)
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
						else if(fileNumber == 1)
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
				
				fileNumber = 1;
				
				word = "";
			
				scanFileContents.close();
			}
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Could not find file");
			e.printStackTrace();
		}
		

		
	}
	
	public List<Entry<String,Integer>> returnFirstMap()
	{
		Set<Entry<String,Integer>>set = wordsInFile.entrySet();
		
		List<Entry<String,Integer>>list = new ArrayList<Entry<String,Integer>>(set);
		
		Collections.sort(list,new Comparator<Map.Entry<String,Integer>>()
		{
			public int compare(Map.Entry<String,Integer>o1,Map.Entry<String,Integer>o2)
			{
				return o2.getValue().compareTo(o1.getValue());
			}
		});

	
		return list;
	}
	
	
	public List<Entry<String,Integer>> returnSecondMap()
	{
		Set<Entry<String,Integer>>set1 = secondMap.entrySet();
		
		List<Entry<String,Integer>>list1 = new ArrayList<Entry<String,Integer>>(set1);
		
		Collections.sort(list1,new Comparator<Map.Entry<String,Integer>>()
		{
			public int compare(Map.Entry<String,Integer>o3,Map.Entry<String,Integer>o4)
			{
				return o4.getValue().compareTo(o3.getValue());
			}
		});
				
		return list1;
	}
	
	public void readStopWordFile()
	{
		String currentStopWord = "";
		
		try
		{
		
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
		
		catch (FileNotFoundException e)
		{
			System.out.println("Could not find file");
			e.printStackTrace();
		}
	}
	
	public String checkWhetherDocumentsAreCommon(List<Entry<String, Integer>> a1,List<Entry<String, Integer>> a2)
	{
		int occurrences = 0;
		
		String returnStringResult = "";
	
		for(Entry<String,Integer>element:a1)
		{
			for(Entry<String,Integer>element2:a2)
			{
				if(element.getKey().equals(element2.getKey()))
				{
					occurrences += 1;
					break;
				}
			}
			
		}
		
	
		System.out.println("Amount of overlaps - > " + occurrences);
	
		
		if((((float)occurrences / (float)10)*100) >= 70)
		{
			returnStringResult =  "Likely to be about the same topic";
		}
		
		else if((((float)occurrences / (float)10)*100) > 40 && ((occurrences / 10) * 100) < 70)
		{
			returnStringResult = "Not sure whether they are about the same topic";
		}
		else if((((float)occurrences / (float)10)*100) <= 40)
		{
			returnStringResult = "Not likely to be about the same topic";
		}
		
		return returnStringResult;
	}


	private ArrayList<String> getTextFileNames() {
		return textFileNames;
	}


	private void setTextFileNames(ArrayList<String> textFileNames) {
		this.textFileNames = textFileNames;
	}


	private String getFileName() {
		return fileName;
	}


	private void setFileName(String fileName) {
		this.fileName = fileName;
	}



}
