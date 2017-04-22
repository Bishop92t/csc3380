//package bookstore;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// if you're using NetBeans (maybe Eclipse) you need this line, otherwise comment it out to compile it


/**
 * @author Nathanael Bishop, Zachary Goad, Garret Aymond, Kevin Vo
 * CSC 3380 Group Project
 * 
 */

public class Book {
    private String ISBN; //ISBN of this book
    private String bookName; //name of the book
    private int numberInStock; //how many of this book are in stock
    private float cost; //cost of the book
    private Course[] courses;//array of Course objects 
    private int numCourses;  //number of courses in Course[] array
    
// for reference here is the basic outline of each file
//    9781680450262.txt                     (this is the ISBN)
//	Design Patterns: Elements           (this is title)
//	25                                  (number books in stock)
//	55.95 		                    (cost of book)
//	CSC1351_1                             (department/course#/section)
//	CSC1350_2		                    (etc)
    
    /**
     * constructor for this Book object
     * @param userInput the ISBN of the Book object
     */
    public Book (String userInput) {
        if ( checkIfValid(userInput) ) {
            ISBN = userInput;
            FileReader fr = null; //variable must be initialized in case the try block fails (which it can't)
            //Java requires this exception to be handled even though it kind of is already
            try {
            	fr = new FileReader(userInput + ".txt");
            } catch(FileNotFoundException e) {
            	
            }
            Scanner sc = new Scanner(fr);	//Start scanning the file now that we know it exists
            bookName = sc.nextLine();
            numberInStock = sc.nextInt();
            cost = sc.nextFloat();
            courses = new Course[100];		//Books can be used by a lot of courses, especially if we're separating them into sections
            numCourses = 0;
            while(sc.hasNext()) {
            	courses[numCourses] = new Course(sc.next(), userInput + ".txt");
            	numCourses++;
            }
            sc.close();
        }
        else {
            Bookstore.display(new String[] {"No book found matching " + userInput}, true);
        }
    }

    


// ************************
// Everything below this line is done
// ************************
    
    public String getISBN() {
        return ISBN;
    }
    
    public String getBookName() {
    	String s;
    	if(bookName.length() > 20) {
            s = bookName.substring(0, 17) + "...";
    	} else {
            s = bookName;
    	}
        return s;
    }
    
    public int NumberInStock() {
        return numberInStock;
    }
    
    public float getCost() {
        return cost;
    }
    
    /**
     * Determines whether the ISBN is valid by checking for a text
     * file with a matching name.
     * @param ISBN the ISBN (file name) to check for
     * @return whether the file was found
     */
    private boolean checkIfValid(String ISBN) {
    	try {
        	FileReader f = new FileReader(ISBN + ".txt");
        	f.close();
    	} catch(FileNotFoundException e) {
    		return false;
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	return true;
    }
    
    /**
     * Prints out all courses attached to the book. Currently only
     * used for testing purposes.
     * @return a string of courses
     */
    public String coursesString() {
    	String s = "";
    	for(int i = 0; i < numCourses; i++) {
    		s += courses[i].getCourse() + "\n";
    	}
    	return s;
    }
    
    /**
     * Finds the total amount of this book needed by its courses, i.e.
     * the total number of students in all the courses that require the
     * book.
     * @return the total amount of books required
     */
    public int findNeeded() {
    	int total = 0;
    	for(int i = 0; i < numCourses; i++) {
    		total += courses[i].getNumEnrolled();
    	}
    	return total;
    }
    
    /**
     * Finds the difference between the number of books required and the
     * number in stock
     * @return the amount of books needed to order
     */
    public int findDifference() {
    	int neededBooks = findNeeded() - NumberInStock();
    	if(neededBooks < 0) {
    		neededBooks = 0;
    	}
    	return neededBooks;
    }
    
    /**
     * Finds the order cost of the needed amount of books by multiplying
     * the number of students enrolled in courses requiring the book by
     * the number of books needed as found in findNeeded()
     * @return the cost of the needed books
     */
    public float findOrderCost() {
    	return findDifference() * getCost();
    }
}
