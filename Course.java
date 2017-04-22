// if you're using NetBeans (maybe Eclipse) you need this line, otherwise comment it out to compile it
//package bookstore;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Nathanael Bishop, Zachary Goad, Garret Aymond, Kevin Vo
 * CSC 3380 Group Project
 * 
 */

public class Course {
    private String course;
    private String section;
    private int numEnrolled;  //number of students enrolled in the class
    private Book[] books;
    private int numBooks = 0;
    
// for reference here is the basic outline of each file
//    CSC3380_1.txt           (department/course number/section file)
//    80                    (number enrolled)
//    9781680450262         (1st ISBN)
//    9780201633610         (etc)
    
    /**
     * Constructor for this course object. Intended for use only in Book constructor.
     * @param courseName string that the user has input
     * @param bookFile the filename for the Book creating this course (this Course constructor is only used in the Book constructor)
     */
    public Course(String courseName, String bookFile) {
        if( checkIfValid(courseName) ) {
            this.course = fixCourseName(courseName);
            section = courseName.substring(courseName.length() - 1, courseName.length());	//Assuming there are no more than 9 sections per class
            FileReader fr = null; //variable must be initialized in case the try block fails (which it can't)
            //Java requires this exception to be handled even though it kind of is already
            try {
            	fr = new FileReader(course + ".txt");
            } catch(FileNotFoundException e) {
            	//this is already handled by checkIfValid
            }
            Scanner sc = new Scanner(fr);
            numEnrolled = sc.nextInt();
            sc.close();
        }
        else 
            Bookstore.display(new String[] {"Warning: ignoring undocumented course '" + courseName + "' found in file " + bookFile}, true);
    }
    
    /**
     * Course constructor. Not to be used inside the Book constructor to avoid infinite looping.
     * @param courseName the name of the course
     */
    public Course(String courseName) {
    	if(checkIfValid(courseName)) {
    		course = fixCourseName(courseName);
    		section = courseName.substring(courseName.length() - 1, courseName.length());	//Assuming there are no more than 9 sections per class
    		FileReader fr = null;
    		try {
    			fr = new FileReader(course + ".txt") ;
    		} catch(FileNotFoundException e) {
    			
    		}
    		Scanner sc = new Scanner(fr);
    		numEnrolled = sc.nextInt();
    		books = new Book[10];
    		while(sc.hasNext()) {
    			books[numBooks] = new Book(sc.next());
    			numBooks++;
    		}
    		sc.close();
    	} else {
    		Bookstore.display(new String[] {"Invalid course: " + courseName, ""}, true);
    	}
    }
    
    
    
// ************************
// Everything below this line is done
// ************************
            
    /**
     * return number of students in the class
     * @return the number of students enrolled in this course
     */
    public int getNumEnrolled() {
        return numEnrolled;
    }
    
    /**
     * returns the string that identifies this course object
     * @return this course from this object
     */
    public String getCourse() {
        return course;
    }
    
    /**
     * Finds the cost of all books required by the course.
     * @return the cost of all books in the Book array
     */
    public float getOrderTotal() {
    	float total = 0;
    	for(int i = 0; i < numBooks; i++) {
    		total += books[i].findOrderCost();
    	}
    	return total;
    }
    
    /**
     * check if the file exists, i.e. is the course valid?  return true if file
     * found
     * @param course the course to check
     * @return true if course is valid, false otherwise
     */
    public static boolean checkIfValid(String course) {
    	String fixedName = fixCourseName(course);
    	try {
        	FileReader f = new FileReader(fixedName + ".txt");
        	f.close();
    	} catch(FileNotFoundException e) {
    		return false;
    	} catch (IOException e) {
    		//not sure what would cause this
			e.printStackTrace();
		}
    	return true;
    }
    
    /**
     * Formats a String to be readable as a Course file
     * @param courseName the input
     * @return a String that can be passed to a FileReader
     */
    public static String fixCourseName(String courseName) {
    	String fixedName = courseName.toUpperCase();	
    	if(!fixedName.substring(fixedName.length() - 2, fixedName.length() - 1).equals("_")) {
    		fixedName = fixedName.substring(0, fixedName.length() - 1) + "_" + fixedName.substring(fixedName.length() - 1, fixedName.length());
    	}	//name is now in format: CSC1350_2
    	return fixedName;
    }
    
}
