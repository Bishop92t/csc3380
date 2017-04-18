// if you're using NetBeans (maybe Eclipse) you need this line, otherwise comment it out to compile it
package bookstore;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Nathanael Bishop, Zachary Goad, Garret Aymond, Kevin Vo
 * CSC 3380 Group Project
 * 
 */

public class Bookstore {

    /**
     * presents the user with a choice of searching via ISBN or course
     * @param args not used at this time
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
//I think for ease of use we have one text field and 2 buttons, either button sends the textfield to search()    
    	//just printing to the console for now for testing purposes
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter a course and section or ISBN\n"
    			+ "Examples:\n"
    			+ "CSC1350_2\n"
    			+ "9781680450262");
    	String userInput = sc.next();
    	System.out.println("Search as a course or ISBN?\n"
    			+ "0 - course\n"
    			+ "1 - ISBN");
    	int inputID = sc.nextInt();
    	
    	Book[] books;
    	if(inputID == 0) {
    		books = getRequiredBooks(userInput);	//If a course was entered, set the books array
    	} else {
        	books = new Book[1];
			books[0] = new Book(userInput); //If a book was entered, use only that book
    	}
    	System.out.printf("%s\t\t\t%s\t%s\t%s\t%s\n", "Book", "In stock", "Required", "Need to order", "Cost of order");
    	for(int i = 0; i < books.length; i++) {
    		System.out.printf("%-16s\t%-8d\t%-8d\t%-8d\t%-8.2f\n", books[i].getBookName(), 
    				books[i].NumberInStock(), books[i].findNeeded(), books[i].findDifference(), books[i].findOrderCost());
    	}
    	
    	
    	sc.close();
    }
    
    /**
     * general purpose method, can be used to display output to the user or error
     * messages. 
     * @param textToDisplay the text that will be displayed
     */
    public static void display(String textToDisplay) {
//create a new window, display the message with an OK button to close the window        
    }
    
    /**
     * Gets the array of books required by a course
     * exactly as long as needed.
     * @param courseFile the file to check
     * @return length of books array
     * @throws IOException 
     */
    public static Book[] getRequiredBooks(String courseFile) throws IOException {
    	int requiredBooks = 0;
    	Book[] books = new Book[0];
    	FileReader fr;
    	try {
    		fr = new FileReader(courseFile + ".txt");
    	} catch(FileNotFoundException e) {
    		return new Book[0];
    	}
    	Scanner sc = new Scanner(fr);
    	//Advance past the first line in the course file
    	sc.nextLine();
    	
    	//get the number of different books required by the course
    	while(sc.hasNext()) {
    		requiredBooks++;
    		sc.next();
    	}
    	books = new Book[requiredBooks];
    	
    	//reset the scanner
    	sc.close();
    	fr.close();	//throws IOException
    	fr = new FileReader(courseFile + ".txt");	//throws IOException
    	sc = new Scanner(fr);
		sc.nextLine();
    	
    	//set the books array
    	for(int i = 0; i < books.length; i++) {
    		books[i] = new Book(sc.next());
    	}
    	sc.close();
    	return books;
    }
    
    /**
     * Verifies the user's input to the main menu
     * @param menuSelection the text the user typed in (ISBN or course)
     * @param isISBN flagged true if user typed an ISBN, false for course
     * @return whether the item was found.
     */
    public static boolean search(String menuSelection, boolean isISBN) {
    	return false;
    }
}
