package com.topicmodeller.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Control 
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		TopicModellerGUI topicmodeller = new TopicModellerGUI("Topic Modeller App - By Amar Plakalo");
		
		System.out.println(topicmodeller);
		
		ArrayList <String> fileNames = new ArrayList <String>();
		
		fileNames.add("text2.txt");
		
		fileNames.add("randomtextf.txt");
		
		WorkWithFiles setOfFiles = new WorkWithFiles(fileNames,"stopwords.txt");
		
		setOfFiles.openFile();

		setOfFiles.openFile();
		
		setOfFiles.readStopWordFile();

		setOfFiles.readFile();
		
		setOfFiles.checkWhetherDocumentsAreCommon();
	

	}

}
