/***********************************
*Control: This is the control class
*Author: Amar Plakalo
*Date:01/07/2021
*/
package com.topicmodeller.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Control 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		ArrayList <String> fileNames = new ArrayList <String>();
		
		fileNames.add("text2.txt");
		
		fileNames.add("randomtextf.txt");
		
		WorkWithFiles setOfFiles = new WorkWithFiles(fileNames,"stopwords.txt");
		
		setOfFiles.openFile();

		setOfFiles.openFile();
		
		setOfFiles.readStopWordFile();

		setOfFiles.readFile();

	}

}
