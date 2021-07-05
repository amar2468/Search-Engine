/***********************************
*WorkWithFiles: This is the WorkWithFiles class. This class opens the files, puts the words from each of the files
*into different maps, scans the files for stopwords and calculates the amount of overlap between the documents
*so that it can be determined whether the files are common.
*Author: Amar Plakalo
*Date:05/07/2021
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
	
	private Map<String,Integer>wordsInFirstFile = new HashMap<String,Integer>();
	
	private Map<String,Integer>wordsInSecondFile;
	
	private String stopWordFileName;
	
	private File stopWordFile;
	
	private ArrayList<String>textstopWordFileNames = new ArrayList<String>();

	private ArrayList<String> allStopWords = new ArrayList<String>();


	WorkWithFiles(ArrayList<String>textstopWordFileNames,String stopWordFileName)
	{
		this.setTextstopWordFileNames(textstopWordFileNames);
		this.setstopWordFileName(stopWordFileName);
	}

	
	public void openFile()
	{
		filesUsed = new File[getTextstopWordFileNames().size()];
		
		wordsInFirstFile = new HashMap<String,Integer>();
		
		wordsInSecondFile = new HashMap<String,Integer>();
		
		stopWordFile = new File(stopWordFileName);
		
		
		for(int i = 0; i < getTextstopWordFileNames().size(); i++)
		{
			filesUsed[i] = new File(getTextstopWordFileNames().get(i));
		}
		
		
	}

	
	public void readFile()
	{
		String word = "";
		
		int fileNumber = 0;
		
		try
		{
			for(int j = 0; j < getTextstopWordFileNames().size();j++)
			{
				Scanner scanFileContents = new Scanner(filesUsed[j],"UTF-8");
				
				
				while(scanFileContents.hasNextLine())
				{
					word = scanFileContents.next();
					
					word = word.replaceAll("\\p{Punct}", "");
					
					word = word.replaceAll("[^a-zA-Z0-9]","");
					
					word = word.replaceAll("\n","");
					
					
					if(!allStopWords.contains(word))
					{
					
						if(fileNumber == 0)
						{
						
							if(wordsInFirstFile.containsKey(word) && !word.equals(""))
							{
								wordsInFirstFile.put(word,wordsInFirstFile.get(word) + 1);
								
							}
							else if(!wordsInFirstFile.containsKey(word) && !word.equals(""))
							{
								wordsInFirstFile.put(word,1);
							}
						}
						else if(fileNumber == 1)
						{
							if(wordsInSecondFile.containsKey(word) && !word.equals(""))
							{
								wordsInSecondFile.put(word,wordsInSecondFile.get(word) + 1);
								
							}
							else if(!wordsInSecondFile.containsKey(word) && !word.equals(""))
							{
								wordsInSecondFile.put(word,1);
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
		Set<Entry<String,Integer>>setContainingWords = wordsInFirstFile.entrySet();
		
		List<Entry<String,Integer>>listContainingSet = new ArrayList<Entry<String,Integer>>(setContainingWords);
		
		Collections.sort(listContainingSet,new Comparator<Map.Entry<String,Integer>>()
		{
			public int compare(Map.Entry<String,Integer>o1,Map.Entry<String,Integer>o2)
			{
				return o2.getValue().compareTo(o1.getValue());
			}
		});

	
		return listContainingSet;
	}
	
	
	public List<Entry<String,Integer>> returnSecondMap()
	{
		Set<Entry<String,Integer>>secondSetContainingWords = wordsInSecondFile.entrySet();
		
		List<Entry<String,Integer>>secondListContainingSet = new ArrayList<Entry<String,Integer>>(secondSetContainingWords);
		
		Collections.sort(secondListContainingSet,new Comparator<Map.Entry<String,Integer>>()
		{
			public int compare(Map.Entry<String,Integer>o3,Map.Entry<String,Integer>o4)
			{
				return o4.getValue().compareTo(o3.getValue());
			}
		});
				
		return secondListContainingSet;
	}
	
	public void readStopWordFile()
	{
		String currentStopWord = "";
		
		try
		{
		
			Scanner scanCurrentStopWord = new Scanner(stopWordFile);
			
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


	private ArrayList<String> getTextstopWordFileNames() {
		return textstopWordFileNames;
	}


	private void setTextstopWordFileNames(ArrayList<String> textstopWordFileNames) {
		this.textstopWordFileNames = textstopWordFileNames;
	}


	private String getstopWordFileName() {
		return stopWordFileName;
	}


	private void setstopWordFileName(String stopWordFileName) {
		this.stopWordFileName = stopWordFileName;
	}

}
