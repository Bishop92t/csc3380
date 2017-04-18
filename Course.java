// if you're using NetBeans (maybe Eclipse) you need this line, otherwise comment it out to compile it
package bookstore;

/**
 * @author Nathanael Bishop, Zachary Goad, Garret Aymond, Kevin Vo
 * CSC 3380 Group Project
 * 
 */

public class Course {
    private String course;
    private int numEnrolled;  //number of students enrolled in the class
    private Book[] books; //array of book objects
    private int numBooks; //number of books in the Book[] array
    
// for reference here is the basic outline of each file
//    CSC3380.txt           (department/course number file)
//    80                    (number enrolled)
//    9781680450262         (1st ISBN)
//    9780201633610         (etc)
    
    /**
     * constructor for this course object
     * @param userInput string that the user has input
     */
    public Course(String userInput) {
        if( checkIfValid(userInput) ) {
            this.course=userInput;
            //construct the rest of the object using the data in the file above
        }
        else
            Bookstore.display("Course not found!");
    }
    
    
    /**
     * check if the file exists, i.e. is the course valid?  return true if file
     * found
     * @param course the course to check
     * @return true if course is valid, false otherwise
     */
    public boolean checkIfValid(String course) {
        //check if file exists here
        return false;
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
    
    
}
