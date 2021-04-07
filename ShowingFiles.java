/***********************************
*Showing Files: This class shows the user the contents of the files they are using
*Author: Amar Plakalo
*Date:07/04/2021
***********************************/

package com.javaapp.test;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class ShowingFiles extends JFrame
{
	private ArrayList<String> fileNamesNeeded;
	private File[] seeFileContents;
	JTextArea[] showingContentsOfFiles;
	JPanel newPanel;
	
	
	ShowingFiles(String titleOfWindow,ArrayList<String> fileNamesNeeded)
	{
		super(titleOfWindow);
		setSize(1500,1500);
		newPanel = new JPanel(new FlowLayout());
		
		this.setFileNamesNeeded(fileNamesNeeded);
		
		seeFileContents = new File[fileNamesNeeded.size()];
		
		showingContentsOfFiles = new JTextArea[fileNamesNeeded.size()];
	
		for(int s = 0; s < fileNamesNeeded.size();s++)
		{
			showingContentsOfFiles[s] = new JTextArea(5,10);
			newPanel.add(showingContentsOfFiles[s]);
			
		}
	

		add(newPanel);
		

		setVisible(true);
		
		
		
	}
	
	public void openingFilesNeeded()
	{
		for(int k = 0; k < fileNamesNeeded.size(); k++)
		{
			seeFileContents[k] = new File(getFileNamesNeeded().get(k));
		}
	}
	

	public void readFileContents()
	{
		String holdingCurrentLine = "";
		
		try
		{
			for(int p = 0; p < fileNamesNeeded.size(); p++)
			{
				Scanner s1 = new Scanner(seeFileContents[p],"UTF-8");
				
		
				while (s1.hasNextLine())
				{
					holdingCurrentLine = s1.nextLine();
					showingContentsOfFiles[p].append(holdingCurrentLine + "\n");
				
				}
				s1.close();


			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found: ");
			e.printStackTrace();
		}
	}

	private ArrayList<String> getFileNamesNeeded() 
	{
		return fileNamesNeeded;
	}

	private void setFileNamesNeeded(ArrayList<String> fileNamesNeeded) 
	{
		this.fileNamesNeeded = fileNamesNeeded;
	}
	

}