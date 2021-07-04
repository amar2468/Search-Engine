/***********************************
*TopicModellerGUI: This is the TopicModellerGUI class
*Author: Amar Plakalo
*Date:04/07/2021
*/

package com.topicmodeller.test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
	JButton button1,button2,chooseFiles;
	
	JPanel panel1,panel2;
	
	JLabel titleLabel;
	
	String stringToBePrinted = "";
	
	private ArrayList<String>filesChosen = new ArrayList<String>();
	
	private List<Entry<String,Integer>>randomlist = new ArrayList<Entry<String,Integer>>();
	
	private List<Entry<String,Integer>>randomlist2 = new ArrayList<Entry<String,Integer>>();
	
	private List<Entry<String,Integer>>randomlist3 = new ArrayList<Entry<String,Integer>>();

	WorkWithFiles fileobj = new WorkWithFiles(filesChosen,"stopwords.txt");
	
	TopicModellerGUI(String title)
	{
		super(title);
		
		setSize(400,340);
		
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.pink);
		
		FlowLayout flay = new FlowLayout();
		panel2 =  new JPanel();
		panel2.setLayout(flay);
		panel2.setBackground(Color.cyan);
		
		
		button1 = new JButton("Check if same topic");
		button1.setBackground(Color.green);
		button1.addActionListener(this);
		
		button2 = new JButton("Results");
		button2.setBackground(Color.green);
		button2.addActionListener(this);
		
		chooseFiles = new JButton("Choose File");
		chooseFiles.setBackground(Color.green);
		chooseFiles.addActionListener(this);
		
		titleLabel = new JLabel("Enjoy using the Topic Modeller App!!");
		titleLabel.setBackground(Color.darkGray);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 23));
	
		
		add(panel1,"North");
		add(panel2,"Center");
		
		panel1.add(titleLabel);
		
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(chooseFiles);
		
		setVisible(true);
		
	}
	
	public void initialiseVariableWithResultString(String resultFromCheck)
	{
		stringToBePrinted = resultFromCheck;
	}
	
	public void actionPerformed(ActionEvent eventDetected)
	{
		if(eventDetected.getSource() == button1)
		{	
			stringToBePrinted = fileobj.checkWhetherDocumentsAreCommon(randomlist,randomlist3);
			
			JOptionPane.showMessageDialog(button1, stringToBePrinted);
			
		}
		
		else if(eventDetected.getSource() == button2)
		{
			JOptionPane.showMessageDialog(button2, randomlist);
			JOptionPane.showMessageDialog(button2, randomlist3);

		
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
							
							randomlist.addAll(fileobj.returnFirstMap());
							
							int lengthOfRandomList = randomlist.size();
							
							for(int v = 10; v < lengthOfRandomList;v++)
							{
								randomlist2.add(randomlist.get(v));
								
							}
							
							randomlist.removeAll(randomlist2);
							
							randomlist2.clear();
							
							randomlist3.addAll(fileobj.returnSecondMap());
							
							int lenOfRandomList3 = randomlist3.size();
							
							for(int b = 10; b < lenOfRandomList3; b++)
							{
								randomlist2.add(randomlist3.get(b));
							}
							
							randomlist3.removeAll(randomlist2);
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
