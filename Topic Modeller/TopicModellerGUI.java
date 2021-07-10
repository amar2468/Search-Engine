/***********************************
*TopicModellerGUI: This is the TopicModellerGUI class. This class allows the user to choose the files, check
*results and also check whether the documents are about the same topic. In this class, there is a file
*chooser implemented so that the user can pick which files they want to test.
*Author: Amar Plakalo
*Date:10/07/2021
*/

package com.topicmodeller.test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;


@SuppressWarnings("serial")
public class TopicModellerGUI extends JFrame implements ActionListener
{
	JButton checkIfSameTopic,resultsButton,chooseFiles;
	
	JPanel topPanel,belowTopPanel,lowestPanel;
	
	JLabel titleLabel;
	
	String stringToBePrinted = "";
	
	private ArrayList<String>filesChosen = new ArrayList<String>();
	
	private List<Entry<String,Integer>>returnedFirstMapNowList = new ArrayList<Entry<String,Integer>>();
	
	private List<Entry<String,Integer>>sublistToRemove = new ArrayList<Entry<String,Integer>>();
	
	private List<Entry<String,Integer>>returnedSecondMapNowList = new ArrayList<Entry<String,Integer>>();

	WorkWithFiles fileobj = new WorkWithFiles(filesChosen,"stopwords.txt");
	
	TopicModellerGUI(String title)
	{
		super(title);
		
		setSize(450,830);
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER,20,20);
		FlowLayout flow2 = new FlowLayout(FlowLayout.CENTER,20,240);
		
		topPanel = new JPanel();
		topPanel.setBackground(Color.pink);
		
		lowestPanel = new JPanel();
		lowestPanel.setLayout(flow2);
		lowestPanel.setBackground(Color.pink);
		ImageIcon myPicture = new ImageIcon("random.png");
		
		Image img = myPicture.getImage();
		
		Image modifiedImage = img.getScaledInstance(210, 220, DO_NOTHING_ON_CLOSE);
		
		myPicture = new ImageIcon(modifiedImage);
		
		JLabel picLabel = new JLabel((myPicture));
		
		lowestPanel.add(picLabel);
		
		belowTopPanel =  new JPanel();
		belowTopPanel.setLayout(flow);
		belowTopPanel.setBackground(Color.pink);
		
		
		checkIfSameTopic = new JButton("Check if same topic");
		checkIfSameTopic.setBackground(Color.green);
		checkIfSameTopic.addActionListener(this);
		
		resultsButton = new JButton("Results");
		resultsButton.setBackground(Color.green);
		resultsButton.addActionListener(this);
		
		chooseFiles = new JButton("Choose File");
		chooseFiles.setBackground(Color.green);
		chooseFiles.addActionListener(this);
		
		titleLabel = new JLabel("Enjoy using the Topic Modeller App!!");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
	
		
		add(topPanel,"North");
		add(belowTopPanel);
		add(lowestPanel,"South");
	
		topPanel.add(titleLabel);
		
		belowTopPanel.add(checkIfSameTopic);
		belowTopPanel.add(resultsButton);
		belowTopPanel.add(chooseFiles);
		
		
		setVisible(true);
		
	}
	
	public void initialiseVariableWithResultString(String resultFromCheck)
	{
		stringToBePrinted = resultFromCheck;
	}
	
	public void actionPerformed(ActionEvent eventDetected)
	{
		if(eventDetected.getSource() == checkIfSameTopic)
		{	
			stringToBePrinted = fileobj.checkWhetherDocumentsAreCommon(returnedFirstMapNowList,returnedSecondMapNowList);
			
			JOptionPane.showMessageDialog(checkIfSameTopic, stringToBePrinted);
			
		}
		
		else if(eventDetected.getSource() == resultsButton)
		{
			JOptionPane.showMessageDialog(resultsButton, returnedFirstMapNowList);
			JOptionPane.showMessageDialog(resultsButton, returnedSecondMapNowList);

		
		}
		
		else if(eventDetected.getSource() == chooseFiles)
		{
			JFileChooser fileChooserWindow = new JFileChooser(FileSystemView.getFileSystemView());
			
			int checkIfFileAdded = 0;
			
			int value = fileChooserWindow.showSaveDialog(null);
			
			if(value == JFileChooser.APPROVE_OPTION)
			{
				if(filesChosen.isEmpty())
				{
					JOptionPane.showMessageDialog(this,"Successfully added a file");
					filesChosen.add(fileChooserWindow.getSelectedFile().getName());
					
				}
				
				
				
				else if(!filesChosen.isEmpty() && filesChosen.size() < 2)
				{
					for(int f = 0; f < filesChosen.size(); f++)
					{
						if(filesChosen.get(f).equals(fileChooserWindow.getSelectedFile().getName()))
						{
							checkIfFileAdded = 1;
							break;
						}
						
					}
					
					if(checkIfFileAdded == 1)
					{
						JOptionPane.showMessageDialog(this,"File is already added");
					}
					
					else
					{
						JOptionPane.showMessageDialog(this,"Successfully added filename - > " + fileChooserWindow.getSelectedFile().getName());
						filesChosen.add(fileChooserWindow.getSelectedFile().getName());
						
						if(filesChosen.size() == 2)
						{
							fileobj.openFile();
							
							fileobj.readStopWordFile();
							
							fileobj.readFile();
							
							returnedFirstMapNowList.addAll(fileobj.returnFirstMap());
							
							int lengthOfreturnedFirstMapNowList = returnedFirstMapNowList.size();
							
							for(int v = 10; v < lengthOfreturnedFirstMapNowList;v++)
							{
								sublistToRemove.add(returnedFirstMapNowList.get(v));
								
							}
							
							returnedFirstMapNowList.removeAll(sublistToRemove);
							
							sublistToRemove.clear();
							
							returnedSecondMapNowList.addAll(fileobj.returnSecondMap());
							
							int lenOfreturnedSecondMapNowList = returnedSecondMapNowList.size();
							
							for(int b = 10; b < lenOfreturnedSecondMapNowList; b++)
							{
								sublistToRemove.add(returnedSecondMapNowList.get(b));
							}
							
							returnedSecondMapNowList.removeAll(sublistToRemove);
						}
					}
				}
			}
		}
			
			
			else
			{
				JOptionPane.showMessageDialog(this,"You have not added the file to this app!");
			}
			

	}
	
	public String toString()
	{
		String stringresult;
		
		stringresult = "Enjoy using the Topic Modeller App!!";

		return stringresult;
	}

}
