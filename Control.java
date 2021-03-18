package com.javaapp.test;

public class Control {

	public static void main(String[] args) 
	{
		FileProcessing firstDocument = new FileProcessing("roles.txt");
		firstDocument.openFile();
		firstDocument.readFile();

	}

}
