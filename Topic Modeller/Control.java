/***********************************
*Control: This is the control class
*Author: Amar Plakalo
*Date:04/07/2021
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
