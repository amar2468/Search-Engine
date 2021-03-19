package com.javaapp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessing 
{
	private String fileName;
	private File document;
	
	public FileProcessing(String fileName)
	{
		this.setFileName(fileName);
	}
	
	public void openFile()
	{
		document = new File(fileName);
	}
	
	public void readFile()
	{
		String lineInFile = "";
		
		try
		{
			Scanner searchForWord = new Scanner(document);
			
			while(searchForWord.hasNext())
			{
				lineInFile = searchForWord.next();
				System.out.println(lineInFile);
			}
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
}
