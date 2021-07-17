/***********************************
*WorkWithFiles: This is the WorkWithFiles class. This class opens the files, puts the words from each of the files
*into different maps, scans the files for stopwords and calculates the amount of overlap between the documents
*so that it can be determined whether the files are common.
*Author: Amar Plakalo
*Date:17/07/2021
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
	
	private ArrayList<String>userChoosesFiles = new ArrayList<String>();

	private ArrayList<String> allStopWords = new ArrayList<String>();

	// The constructor below takes in two parameters - an arraylist storing the files the user chose
	// and the string that holds the filename of the stopword file
	
	WorkWithFiles(ArrayList<String>userChoosesFiles,String stopWordFileName)
	{
		this.setuserChoosesFiles(userChoosesFiles);
		this.setstopWordFileName(stopWordFileName);
	}

	
	public void openFile()
	{
		// Tells the program how the big arraylist that stores the files in this class should be. This is 
		// because the other GUI class has the arraylist and it has a particular size which is needed here.
		
		filesUsed = new File[getuserChoosesFiles().size()];
		
		// The first map has been created that has a key which is a string and a value which is an int
		
		wordsInFirstFile = new HashMap<String,Integer>();
		
		// The second map has been created that has a key which is a string and a value which is an int
		
		wordsInSecondFile = new HashMap<String,Integer>();
		
		// The stopword filename is now used to create a file object called stopWordFile.
		
		stopWordFile = new File(stopWordFileName);
		
		// This for loop is used to put all the files inside the list that holds the files.
		
		for(int i = 0; i < getuserChoosesFiles().size(); i++)
		{
			filesUsed[i] = new File(getuserChoosesFiles().get(i));
		}
		
		
	}

	
	// This function will go through each file and put the words inside the map
	public void readFile()
	{
		String word = "";
		
		int fileNumber = 0;
		
		try
		{
			// The for loop runs and each file is scanned so that I can read each word and assess whether
			// I need to put it in the map
			
			for(int j = 0; j < getuserChoosesFiles().size();j++)
			{
				Scanner scanFileContents = new Scanner(filesUsed[j],"UTF-8");
				
				// This while loop will run as long as the file has a next line available.
				
				while(scanFileContents.hasNextLine())
				{
					// Scans the next word and puts it into the string variable "word"
					word = scanFileContents.next();
					
					// I do a number of operations on the string to clear any punctuation and special chars.
					// I also set the word to lower case so that the words so they can be compared.
					
					word = word.replaceAll("\\p{Punct}", "");
					
					word = word.replaceAll("[^a-zA-Z0-9]","");
					
					word = word.replaceAll("\n","");
					
					word = word.toLowerCase();
					
					// If the word is not a stop word
					
					if(!allStopWords.contains(word))
					{
						// If we are on the first file.
						if(fileNumber == 0)
						{
							// If the first map contains that word and the word is not empty
							if(wordsInFirstFile.containsKey(word) && !word.equals(""))
							{
								// put the word into the map and increment the value by 1
								wordsInFirstFile.put(word,wordsInFirstFile.get(word) + 1);
								
							}
							// Otherwise
							else if(!wordsInFirstFile.containsKey(word) && !word.equals(""))
							{
								// Just put the word in the map and put the value as 1
								wordsInFirstFile.put(word,1);
							}
						}
						
						// If we are on the second file
						
						else if(fileNumber == 1)
						{
							// If the second map contains that word and the word is not empty
							if(wordsInSecondFile.containsKey(word) && !word.equals(""))
							{
								// put the word into the map and increment the value by 1
								wordsInSecondFile.put(word,wordsInSecondFile.get(word) + 1);
								
							}
							// Otherwise
							else if(!wordsInSecondFile.containsKey(word) && !word.equals(""))
							{
								// Just put the word in the map and put the value as 1
								wordsInSecondFile.put(word,1);
							}
						}
					}
					
				}
				
				// when the first file is done, set the value of fileNumber to 1 meaning that we have moved
				// on to the next file
				
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
	
	// This function returns a list back to the GUI class. This is for the first map i.e. first file
	public List<Entry<String,Integer>> returnFirstMap()
	{
		// Convert the first map into an entryset
		Set<Entry<String,Integer>>setContainingWords = wordsInFirstFile.entrySet();
		
		// Convert entryset into arraylist
		List<Entry<String,Integer>>listContainingSet = new ArrayList<Entry<String,Integer>>(setContainingWords);
		
		// Use the collections.sort method and use the parameter which is the newly converted list and the 
		// comparator.
		
		Collections.sort(listContainingSet,new Comparator<Map.Entry<String,Integer>>()
		{
			// This compares each element in the list and sorts
			public int compare(Map.Entry<String,Integer>o1,Map.Entry<String,Integer>o2)
			{
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// return the sorted list
		return listContainingSet;
	}
	
	// This function returns a list back to the GUI class. This is for the second map i.e. second file
	public List<Entry<String,Integer>> returnSecondMap()
	{
		// Convert the second map into an entryset
		Set<Entry<String,Integer>>secondSetContainingWords = wordsInSecondFile.entrySet();
		
		// Convert entryset into arraylist
		List<Entry<String,Integer>>secondListContainingSet = new ArrayList<Entry<String,Integer>>(secondSetContainingWords);
		
		
		// Use the collections.sort method and use the parameter which is the newly converted list and the 
		// comparator.
		Collections.sort(secondListContainingSet,new Comparator<Map.Entry<String,Integer>>()
		{
			// This compares each element in the list and sorts
			public int compare(Map.Entry<String,Integer>o3,Map.Entry<String,Integer>o4)
			{
				return o4.getValue().compareTo(o3.getValue());
			}
		});
				
		// return the sorted list back
		return secondListContainingSet;
	}
	
	// This function readStopWordFile() will add each word from the file into the list so that it can be
	// removed later on
	
	public void readStopWordFile()
	{
		String currentStopWord = "";
		
		try
		{
			// Scanner object created which scans the stopword file
		
			Scanner scanCurrentStopWord = new Scanner(stopWordFile);
			
			// This while loop will be true so long as the file has a next line.
			
			while(scanCurrentStopWord.hasNextLine())
			{
				// Scans the current word in the stopword file and adds it to the stopword list
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
	
	// This function below is responsible for checking whether the documents are common. For them to be 
	// common, there has to be overlap in at least 7 words out of the 10.
	
	public String checkWhetherDocumentsAreCommon(List<Entry<String, Integer>> a1,List<Entry<String, Integer>> a2)
	{
		int occurrences = 0;
		
		String returnStringResult = "";
	
		// Nested for loop used. Entryset allows me to compare whether the key in the first list is equal
		// to the key in the second list i.e. is the word in the first list equal to the one in the second list
		for(Entry<String,Integer>element:a1)
		{
			for(Entry<String,Integer>element2:a2)
			{
				if(element.getKey().equals(element2.getKey()))
				{
					// occurrences are increased depending on the value of the key that is equal.
					// After increasing, a break is put so that you can move on to the next word
					occurrences += element2.getValue();
					break;
				}
			}
			
		}
		
		// Prints amount of overlap between the lists
		System.out.println("Amount of overlaps - > " + occurrences);
	
		// if the score is greater than or equal to 70 (in percentages i.e. 7/10 or more)
		if((((float)occurrences / (float)a2.size())*100) >= 70)
		{
			// set the string value to "Likely to be about the same topic"
			returnStringResult =  "Likely to be about the same topic";
		}
		
		// if score is greater or equal to 40 but less than 70
		else if((((float)occurrences / (float)a2.size())*100) >= 40 && ((occurrences / 10) * 100) < 70)
		{
			// set string value to "Not sure whether they are about the same topic"
			returnStringResult = "Not sure whether they are about the same topic";
		}
		// if score is less than 40
		else if((((float)occurrences / (float)a2.size())*100) < 40)
		{
			// set string value to "Not likely to be about the same topic"
			returnStringResult = "Not likely to be about the same topic";
		}
		
		// Return the string depending on what happened in the above scenarios
		
		return returnStringResult;
	}


	private ArrayList<String> getuserChoosesFiles() 
	{
		return userChoosesFiles;
	}


	private void setuserChoosesFiles(ArrayList<String> userChoosesFiles) 
	{
		this.userChoosesFiles = userChoosesFiles;
	}


	private void setstopWordFileName(String stopWordFileName) 
	{
		this.stopWordFileName = stopWordFileName;
	}

}
