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

public class Course {
    private String course;
    private String section;
    private int numEnrolled;  //number of students enrolled in the class
    
// for reference here is the basic outline of each file
//    CSC3380_1.txt           (department/course number/section file)
//    80                    (number enrolled)
//    9781680450262         (1st ISBN)
//    9780201633610         (etc)
    
    /**
     * constructor for this course object
     * @param userInput string that the user has input
     * @param s whether the books array should be created (to stop infinite loops)
     * @throws Exception 
     */
    public Course(String userInput) {
        if( checkIfValid(userInput) ) {
            this.course=userInput;
            //construct the rest of the object using the data in the file above
            section = userInput.substring(userInput.length() - 1, userInput.length());	//Assuming there are no more than 9 sections per class
            FileReader fr = null; //variable must be initialized in case the try block fails (which it can't)
            //Java requires this exception to be handled even though it kind of is already
            try {
            	fr = new FileReader(userInput + ".txt");
            } catch(FileNotFoundException e) {
            	
            }
            Scanner sc = new Scanner(fr);	//Start scanning the file now that we know it exists
            numEnrolled = sc.nextInt();
            sc.close();
        }
        else {
            Bookstore.display("No course found\nmatching " + userInput);
        	//throw new Exception("Course not found!");
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
     * check if the file exists, i.e. is the course valid?  return true if file
     * found
     * @param course the course to check
     * @return true if course is valid, false otherwise
     */
    public boolean checkIfValid(String course) {
    	try {
        	FileReader f = new FileReader(course + ".txt");
        	f.close();
    	} catch(FileNotFoundException e) {
    		return false;
    	} catch (IOException e) {
    		//not sure what would cause this
			e.printStackTrace();
		}
    	return true;
    }
    
    
}
