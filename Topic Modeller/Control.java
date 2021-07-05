/***********************************
*Control: This is the control class. This class calls the TopicModellerGUI constructor and passes in the title
*of the GUI window.
*Author: Amar Plakalo
*Date:05/07/2021
*/
package com.topicmodeller.test;

import java.io.FileNotFoundException;

public class Control 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		TopicModellerGUI obj = new TopicModellerGUI("Enjoy your app!!");
		System.out.println(obj);
	}

}
