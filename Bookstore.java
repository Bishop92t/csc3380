// if you're using NetBeans (maybe Eclipse) you need this line, otherwise comment it out to compile it
package bookstore;

/**
 * @author Nathanael Bishop, Zachary Goad, Garret Aymond, K
 * CSC 3380 Group Project
 * 
 */

public class Bookstore {

    /**
     * presents the user with a choice of searching via ISBN or course
     * @param args not used at this time
     */
    public static void main(String[] args) {
//I think for ease of use we have one text field and 2 buttons, either button sends the textfield to search()    
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
     * 
     * @param menuSelection the text the user typed in (ISBN or course)
     * @param isISBN flagged true if user typed an ISBN, false for course
     */
    public static void search(String menuSelection, boolean isISBN) {
        Book book;
        Course course;
        if(isISBN)
            book=new Book(menuSelection);
        else
            course=new Course(menuSelection);
    }
}
