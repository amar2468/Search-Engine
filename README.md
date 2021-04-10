# Java
CA Assignment

The program is a search engine which allows the user to input a term or terms and the program tells the user how many times this word occurs and in which files. 

The program consists of four classes:
1. FileProcessing class, which takes the file and compare each word in the file with the terms entered
2. SearchEngine class, which is a GUI class that allows user to enter the word in a text field and allows the user to click a button that checks whether the word occurs in the files.
3. ShowingFiles class, which shows the contents of the files that were chosen by the user
4. Control class, which creates a search engine object that gives the title to the app


CLASSES EXPLAINED - FileProcessing Class

This class takes the word/words entered and searches through the files provided to see whether they occur in the files. After all files are checked, the amount of times the word/words occurred will be available on screen. A couple of imports were made. The file module was imported because this class would deal with files and this module is needed. Also, an arraylist was imported because the textfile names were stored inside an arraylist. HashMap is imported so that the map can be used in order to store the key (file) and the value (the amount of occurrences of a word in that file). The scanner was needed in order to iterate through the file.

In the FileProcessing class, three data structures were declared. The arraylist would store the filenames of the files that the user chose. The File data structure stores the files that the user chose. The map stores the key,value pair which means the file and the number of occurrences. The constructor takes in the arraylist that stores the textfile names so that it can be encapsulated using getters and setters. 

The next method in this class was the openFile(). In order to know how big the arraylist should be for the files that are stored, the files arraylist that stores the textfile names is measured in size so that this will be the length of the documentsNeeded arraylist of files. In order to get each file inside the files arraylist data structure, you have to iterate throught the textfile names and put each of them inside the arraylist that stores the files. This way, the files will be populated with the files chosen by the user. 

The next method is readFile() that takes in the array of words entered by the user. This method will check whether the word/words occur in a file and it will count how many times this happens and put it inside a map data structure. A try/catch block is used so that the program can check whether the file is found or not and bring up an error if something occurs. A for loop is used to loop around for the length of the textfiles arraylist and it uses the scanner variable searchForWord in order to scan the next word in the textfile and puts it inside the variable wordInFile. Then, all punctuation is removed from the word so that the user can find the word without having to put fullstops or commas. Then, there is an if...else if... statement which checks the length of the words entered by the user. If the length is greater than 1, a for loop is used to iterate over the length of the words. Punctuation is removed from each word that the user enters so that both words can be checked and the correct outcome will be made as to whether the words are equal or not. If words are equal, the counter variable is incremented. Otherwise, if only one word was entered, it is checked against the word in the file. If they are equal, the counter is incremented. Once the while loop finishes with looping i.e. going through the whole file, the file and the number of occurrences are saved inside the map. Counter value is back to 0. The scanner is then closed for this file and the for loop moves to the next file. Same process is repeated. Once all the files have been checked, the numberofoccurrences map is returned to the SearchEngine class.

CLASS EXPLAINED - SearchEngine Class

