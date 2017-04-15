// if you're using NetBeans (maybe Eclipse) you need this line, otherwise comment it out to compile it
package bookstore;

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
//	CSC1351                             (department/course#)
//	CSC1350		                    (etc)
    
    /**
     * constructor for this Book object
     * @param userInput the ISBN of the Book object
     */
    public Book (String userInput) {
        if ( checkIfValid(userInput) ) {
            this.ISBN=userInput;
            //construct the rest of the object using the data in the file above
        }
        else
            Bookstore.display("Book not found!");
    }
    
    private boolean checkIfValid(String ISBN) {
        //if file exists, return true....else
        return false;
    }

    


// ************************
// Everything below this line is done
// ************************
    
    public String getISBN() {
        return ISBN;
    }
    
    public String getBookName() {
        return bookName;
    }
    
    public int NumberInStock() {
        return numberInStock;
    }
    
    public float getCost() {
        return cost;
    }
}
