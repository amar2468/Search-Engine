package com.javaapp.test;

public class Control {

	public static void main(String[] args) 
	{
		FileProcessing firstDocument = new FileProcessing("ap_docs2.txt");
		
		firstDocument.openFile();
		firstDocument.readFile();
		
		
		SearchEngine searchengine1 = new SearchEngine("My Search Engine");
		System.out.println(searchengine1);
		
	}

}