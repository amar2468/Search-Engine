/***********************************
*Control: This is the control class which creates an object in the search engine class.
*Author: Amar Plakalo
*Date:15/04/2021
***********************************/

 
package com.javaapp.test;

public class Control 
{

	public static void main(String[] args) 
	{
		// Creating an object of the class Search Engine. The parameter inside will be the title of the GUI.
		
		SearchEngine searchengine1 = new SearchEngine("My Search Engine - Amar Plakalo");
	
		// Print the object using the method toString() in that class.
		System.out.println(searchengine1);
		
		
	}

}
