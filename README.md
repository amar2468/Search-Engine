# Java
CA Assignment

The program is a search engine which allows the user to input a term or terms and the program tells the user how many times this word occurs and in which files. 

The program consists of four classes:
1. FileProcessing class, which takes the file and compare each word in the file with the terms entered
2. SearchEngine class, which is a GUI class that allows user to enter the word in a text field and allows the user to click a button that checks whether the word occurs in the files.
3. ShowingFiles class, which shows the contents of the files that were chosen by the user
4. Control class, which creates a search engine object that gives the title to the app

**
CLASSES EXPLAINED - FileProcessing Class
**
This class takes the word/words entered and searches through the files provided to see whether they occur in the files. After all files are checked, the amount of times the word/words occurred will be available on screen. A couple of imports were made. The file module was imported because this class would deal with files and this module is needed. Also, an arraylist was imported because the textfile names were stored inside an arraylist. HashMap is imported so that the map can be used in order to store the key (file) and the value (the amount of occurrences of a word in that file). The scanner was needed in order to iterate through the file.

In the FileProcessing class, three data structures were declared. The arraylist would store the filenames of the files that the user chose. The File data structure stores the files that the user chose. The map stores the key,value pair which means the file and the number of occurrences. The constructor takes in the arraylist that stores the textfile names so that it can be encapsulated using getters and setters. 

The next method in this class was the openFile(). In order to know how big the arraylist should be for the files that are stored, the files arraylist that stores the textfile names is measured in size so that this will be the length of the documentsNeeded arraylist of files. In order to get each file inside the files arraylist data structure, you have to iterate throught the textfile names and put each of them inside the arraylist that stores the files. This way, the files will be populated with the files chosen by the user. 

The next method is readFile() that takes in the array of words entered by the user. This method will check whether the word/words occur in a file and it will count how many times this happens and put it inside a map data structure. A try/catch block is used so that the program can check whether the file is found or not and bring up an error if something occurs. A for loop is used to loop around for the length of the textfiles arraylist and it uses the scanner variable searchForWord in order to scan the next word in the textfile and puts it inside the variable wordInFile. Then, all punctuation is removed from the word so that the user can find the word without having to put fullstops or commas. Then, there is an if...else if... statement which checks the length of the words entered by the user. If the length is greater than 1, a for loop is used to iterate over the length of the words. Punctuation is removed from each word that the user enters so that both words can be checked and the correct outcome will be made as to whether the words are equal or not. If words are equal, the counter variable is incremented. Otherwise, if only one word was entered, it is checked against the word in the file. If they are equal, the counter is incremented. Once the while loop finishes with looping i.e. going through the whole file, the file and the number of occurrences are saved inside the map. Counter value is back to 0. The scanner is then closed for this file and the for loop moves to the next file. Same process is repeated. Once all the files have been checked, the numberofoccurrences map is returned to the SearchEngine class.

**CLASS EXPLAINED - SearchEngine Class**

This class is a GUI class that allows the user to choose the file they wish to use and to type in the word/words they want to search for. After they added the file they wish to use, they can click the "Show files" button which shows the contents of the files entered. 

Many imports were made in this class. Most of them are quite self-explanatory. Some of those that I used that are new are, for example, the comparator and the collections modules that were used to list the values in the map by descending order. This was going to ensure that the file that had the most occurrences of word was going to be put at the top of the list. Also, filechooser filesystem was imported so that I could use JFileChooser to let the user choose which files they want to use in this program. 

At the start of the class, a textfield was created to allow the user to enter the word. Also, three buttons were created to allow the user to search for a word, to choose a file and to show the contents of the file. The label was used as a heading in the program. The panel was used so that the buttons, textfields etc could be nicely organised in the GUI window. 

The constructor takes in the title of the app, that was passed from the control class. The panel is created and a background colour is given to this panel. Also, the flowlayout is used in the panel. Another panel is created which will store the textfield and the buttons. Here, a flowlayout is used because I want the search box and the buttons to be on the same line. ActionListeners are added to the buttons so that when the user clicks on them, something will occur, depending on the option selected. An important thing to note would be that when the panels are added, the first panel is set to appear "north" while the other panel is to appear "west". This assures that the panels are close to one another and they aren't all over the place on the screen. Then, the title is added to the first panel while everything else is added to the second panel.

The next method used is toString() which, when called, prints out important information about the class. In this case, the user is given a message that tells the that this is a search engine app that allows the user to search for words. Then, this is returned back to the control class to be printed. 

The next method is actionPerformed() which detects the action and contains functionality for each option. If the user clicked the search button, a check first occurs. If there are no files, the user is told that they haven't chosen files. Otherwise, an object of the FileProcessing class is created which will store the textfile names. The openFile() method from the FileProcessing class is called so that the file can be opened. Then, the search words that the user entered are saved and they are splitted and put inside an array of words. Then, two maps are created. One will get the map result from the FileProcessing class and return it to the SearchEngine class while the other will store the result from the FileProcessing class and put it inside the new map. 

Then, it is necessary to sort the values in the map by descending order. To do this, the map is converted to a set using the entrySet() because I need both the key and value from the map. Then, I need to use the collections.sort method but the issue is that it takes an arraylist as parameter. That means that the set has to be converted to a list in order for this method to work. Then, the files values are compared in the list to see which file has the most amount of occurrences. This is returned each time until the list is finished. Then, the result is shown on screen as a message dialog. 

If the user chose the choose the file button, the program needs to know what to do if the file is selected or if it is not. If a file was selected, the program will check whether the documents are empty. If there are no files, tell the user that the file was added. Otherwise, a for loop is used to check whether the file was already chosen. If it was, the value of the checker int variable will be set to 1. Then, an if statement is used. If the value of the checker was 1, tell the user that the file was already added. Otherwise, if it is 0, add the file. If the user clicked cancel, meaning that they didn't select a file, tell the user that they did not select a file. Finally, if the user chooses the show files option, an object is created and parameters are sent to the ShowingFiles class which has the job of showing the contents of the files chosen. This is sent to the constructor of the class. Two methods are called which are openFilesNeeded() and readFileContents(). This will allow the file/files to be shown to the screen.

**CLASS - ShowingFiles class**

The ShowingFiles class allows the user to see the files that they selected,i.e. the contents of these files. This is a GUI class that contains elements of file processing also. 

Again, most of the imports are quite self-explanatory. 

In the class constructor, it takes in the title provided from the SearchEngine class and the arraylist that stores the textfile names. Panel is created which uses flowlayout. Then, an object is created that stores the files the user chose. The length of the arraylist is found out by calculating the length of the arraylist that stores the textfile names. Then, a for loop is used to add TextAreas depending on the amount of files selected. So, if the user chose 3 files, 3 textareas will be displayed. The panel is added. 

The next method is openingFilesNeeded(). This method stores the textfile name as a file inside the arraylist that stores the File data. 

The next method is readFileContents(). A string variable is declared that will store the line in the file to be printed. Yet again, a try/catch is used so that there is a system in place to throw an error if the file does not exist. A for loop is used to loop for the amount of textfiles selected. The scanner is created which will scan each file so that then later, each line is printed to screen. While loop is used to iterate over each line in the file. The string variable stores the result from the scanner object that scanned the line in the file. The string variable is then appended to the textarea that will contain the contents of the files. Each time a file has been completed i.e. it has been fully scanned, the scanner object closes and it opens again if there is another file and so on. 

**
CLASS - Control class**

The control class has an object of the SearchEngine class so that it can open the GUI window of the SearchEngine. Then, the object is printed which prints out the toString() method in the SearchEngine class.

