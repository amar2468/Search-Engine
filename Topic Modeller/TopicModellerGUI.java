/***********************************
*TopicModellerGUI: This is the TopicModellerGUI class. This class allows the user to choose the files, check
*results and also check whether the documents are about the same topic. In this class, there is a file
*chooser implemented so that the user can pick which files they want to test.
*Author: Amar Plakalo
*Date:17/07/2021
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
		
		// Setting the layout as flowlayout so that the elements on screen are positioned in a good way
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER,20,20);
		FlowLayout flow2 = new FlowLayout(FlowLayout.CENTER,20,240);
		
		//This is the top panel meaning that the title will be put on top
		
		topPanel = new JPanel();
		topPanel.setBackground(Color.pink);
		
		// This is the lowest panel, which will hold the picture, which is a png one.
		
		lowestPanel = new JPanel();
		lowestPanel.setLayout(flow2);
		lowestPanel.setBackground(Color.pink);
		
		// This is the code below to put a picture in the gui window. Firstly, I made an ImageIcon object
		// which has a variable name "myPicture" and it takes in the pictures name followed by the 
		// ending .png. Then, I use the object to run the function getImage(). After this, I need to modify
		// the picture so that it is smaller than it is. After this, I put the modified picture back
		// into the first picture that was created. The picture has been modified.
		
		ImageIcon myPicture = new ImageIcon("random.png");
		Image img = myPicture.getImage();
		Image modifiedImage = img.getScaledInstance(210, 220, DO_NOTHING_ON_CLOSE);
		myPicture = new ImageIcon(modifiedImage);
		
		// This label will hold the picture. The picture will be placed in the lowest panel.
		
		JLabel picLabel = new JLabel((myPicture));
		lowestPanel.add(picLabel);
		
		// This panel below is just below the top panel. It will hold the buttons which the user can use
		
		belowTopPanel =  new JPanel();
		belowTopPanel.setLayout(flow);
		belowTopPanel.setBackground(Color.pink);
		
		// This button below is used to check whether the topic is the same. It will show a sentence that
		// tells the user if it is the same.
		
		checkIfSameTopic = new JButton("Check if same topic");
		checkIfSameTopic.setBackground(Color.green);
		checkIfSameTopic.addActionListener(this);
		
		// The results button will show the most common words in both documents
		
		resultsButton = new JButton("Results");
		resultsButton.setBackground(Color.green);
		resultsButton.addActionListener(this);
		
		// The choose files button allows the user to choose files using filechooser
		
		chooseFiles = new JButton("Choose File");
		chooseFiles.setBackground(Color.green);
		chooseFiles.addActionListener(this);
		
		// The title label holds the title of the app. It has a different colour and a different font + size
		
		titleLabel = new JLabel("Enjoy using the Topic Modeller App!!");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
		
		// Adding all three panels to screen and putting them in certain positions
		
		add(topPanel,"North");
		add(belowTopPanel);
		add(lowestPanel,"South");
		
		// Adding the title to the topPanel so it shows the title.
	
		topPanel.add(titleLabel);
		
		// The belowTopPanel will take three buttons which will be put just below the top panel.
		
		belowTopPanel.add(checkIfSameTopic);
		belowTopPanel.add(resultsButton);
		belowTopPanel.add(chooseFiles);
		
		// This is needed so that the GUI window can show up
		
		setVisible(true);
		
	}
	
	public void initialiseVariableWithResultString(String resultFromCheck)
	{
		// This function takes in the string from one class and initialises it to a variable from this class
		// so that it can be used in this class.
		stringToBePrinted = resultFromCheck;
	}
	
	public void actionPerformed(ActionEvent eventDetected)
	{
		
		// If the button pressed was the checkIfSameTopic
		if(eventDetected.getSource() == checkIfSameTopic)
		{	
			// When this button is pressed, the function below will be called and the parameters which are
			// the two lists will be passed in. This will return a string that will tell the user
			// whether the topics are common
			
			stringToBePrinted = fileobj.checkWhetherDocumentsAreCommon(returnedFirstMapNowList,returnedSecondMapNowList);
			
			JOptionPane.showMessageDialog(checkIfSameTopic, stringToBePrinted);
			
		}
		
		// If the button pressed was the resultsButton
		else if(eventDetected.getSource() == resultsButton)
		{
			// When this button is pressed, the two sorted lists will be displayed.
			
			JOptionPane.showMessageDialog(resultsButton, returnedFirstMapNowList);
			JOptionPane.showMessageDialog(resultsButton, returnedSecondMapNowList);

		
		}
		
		// If the button pressed was the chooseFiles
		
		else if(eventDetected.getSource() == chooseFiles)
		{
			// Make a filechooser object
			
			JFileChooser fileChooserWindow = new JFileChooser(FileSystemView.getFileSystemView());
			
			int checkIfFileAdded = 0;
			
			int value = fileChooserWindow.showSaveDialog(null);
			
			// If the user chooses a file
			
			if(value == JFileChooser.APPROVE_OPTION)
			{
				// Checks whether the list is empty
				
				if(filesChosen.isEmpty()) 
				{
					// If empty, then add the file to the list
					JOptionPane.showMessageDialog(this,"Successfully added a file");
					filesChosen.add(fileChooserWindow.getSelectedFile().getName());
					
				}
				
				
				// This else if block will execute if the list is not empty and the list has less than two
				// elements
				
				else if(!filesChosen.isEmpty() && filesChosen.size() < 2)
				{
					// For loop will run for the amount of elements in the list i.e. files in the list
					
					for(int f = 0; f < filesChosen.size(); f++)
					{
						// If the file is already added
						if(filesChosen.get(f).equals(fileChooserWindow.getSelectedFile().getName()))
						{
							// set the value to 1 so that the program knows that the file is already added
							checkIfFileAdded = 1;
							break;
						}
						
					}
					
					// If the value is 1 i.e. already added file
					if(checkIfFileAdded == 1)
					{
						JOptionPane.showMessageDialog(this,"File is already added");
					}
					
					// If the value is not 1 i.e. file not added
					else
					{
						// add the file to the list and tell user that it has been added.
						JOptionPane.showMessageDialog(this,"Successfully added filename - > " + fileChooserWindow.getSelectedFile().getName());
						filesChosen.add(fileChooserWindow.getSelectedFile().getName());
						
						// If there are two files in the list
						if(filesChosen.size() == 2)
						{
							// Open the file and run the stopwordfile function so that stopwords can be
							// removed from the files that were added. Also, run the readfile function
							// so that all the words and their occurrences can be counted.
							
							fileobj.openFile();
							
							fileobj.readStopWordFile();
							
							fileobj.readFile();
							
							// Now, I will put the result from the returnFirstMap() function into 
							// the list from this class. 
							
							returnedFirstMapNowList.addAll(fileobj.returnFirstMap());
							
							// As the list is sorted from descending order, I only want the first ten ones
							// So I will execute the for loop and it will run 10 times and it will add
							// all elements from the tenth one up until the end into the sublist. 
							
							int lengthOfreturnedFirstMapNowList = returnedFirstMapNowList.size();
							
							for(int v = 10; v < lengthOfreturnedFirstMapNowList;v++)
							{
								sublistToRemove.add(returnedFirstMapNowList.get(v));
								
							}
							
							// Now, I will remove all the elements I don't need using the sublist. 
							// The list will only contain the first 10 elements
							
							returnedFirstMapNowList.removeAll(sublistToRemove);
							
							// I clear the sublist so I can use it again later
							
							sublistToRemove.clear();
							
							// Now, I will do the same for the second list as I did for the first list
							
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
			
			// If no file was added i.e. you pressed the cancel option
			
			else
			{
				JOptionPane.showMessageDialog(this, "File not added");
			}
		}
			

	}
	
	public String toString()
	{
		String stringresult;
		
		stringresult = "Enjoy using the Topic Modeller App!!";

		return stringresult;
	}

}
