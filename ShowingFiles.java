/***********************************
*Showing Files: This class shows the user the contents of the files they are using
*Author: Amar Plakalo
*Date:05/04/2021
***********************************/

package com.javaapp.test;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ShowingFiles extends JFrame
{
	private String fileNamesNeeded;
	private File seeFileContents;
	JTextArea showingContentsOfFiles;
	JPanel newPanel;
	
	
	ShowingFiles(String titleOfWindow,String fileNamesNeeded)
	{
		super(titleOfWindow);
		setSize(400,400);
		
		this.setFileNamesNeeded(fileNamesNeeded);
	
		
		showingContentsOfFiles = new JTextArea(5,10);
		
		
		newPanel = new JPanel(new FlowLayout());
		
		newPanel.add(showingContentsOfFiles);
		add(newPanel);
		

		setVisible(true);
		
		
		
	}
	
	public void openingFilesNeeded()
	{

		seeFileContents = new File(getFileNamesNeeded());
	}
	

	public void readFileContents() 
	{
		String holdingCurrentLine = "";
		
		try
		{
			Scanner s1 = new Scanner(seeFileContents);
		
			while (s1.hasNextLine())
			{
				holdingCurrentLine = s1.nextLine();
				showingContentsOfFiles.append(holdingCurrentLine + "\n");
			
			}
			s1.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found: ");
			e.printStackTrace();
		}
	}

	private String getFileNamesNeeded() {
		return fileNamesNeeded;
	}

	private void setFileNamesNeeded(String fileNamesNeeded) {
		this.fileNamesNeeded = fileNamesNeeded;
	}
	

}


