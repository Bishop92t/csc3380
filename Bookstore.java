// if you're using NetBeans (maybe Eclipse) you need this line, otherwise comment it out to compile it
package bookstore;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author Nathanael Bishop, Zachary Goad, Garret Aymond, Kevin Vo
 * CSC 3380 Group Project
 * 
 *
 * 
 *
 ***********************  TO DO LIST  **************************
 *      figure out why user can input invalid course and not get error
 *      figure out why an invalid ISBN throws a null pointer at Book line 77
 *      maybe expand flexibility of course input, i.e. accept it in any format
 *                  CSC 1350 1    or    CSC1350_1   or     csc1350 1    or   CSC1350 001
 */

public class Bookstore {

    /**
     * presents the user with a choice of searching via ISBN or course
     * @param args not used at this time
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //setup the frame
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //setup the 2 buttons
        JButton courseButton = new JButton("Search by Course");
        JButton bookButton = new JButton("Search by ISBN");
        courseButton.setBounds(220, 45, 200, 25);
        bookButton.setBounds(10, 45, 200, 25);
        frame.add(courseButton);
        frame.add(bookButton);
        
        //setup the user input field 
        JLabel userInputLabel = new JLabel("What are you looking for today?");
        userInputLabel.setBounds(10, 10, 225, 25);
        frame.add(userInputLabel);
        JTextField userInput = new JTextField(40);
        userInput.setBounds(230, 10, 125, 25);
        frame.add(userInput);
        
        frame.setVisible(true);

        ActionListener bookButtonListener = (ActionEvent e) -> {      //book button listener
            String userIn=userInput.getText().replaceAll("\\s+", ""); //delete white space
            if(userIn.equals(""))
                display(new String[] {"no Book inputted!", ""}, true);
            else
                try {
                    search(true, userIn); //bring up menu for search by book
                } catch (IOException ex) {
                    Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
                }
        };
        bookButton.addActionListener(bookButtonListener);

        ActionListener courseButtonListener = (ActionEvent e) -> {    //listener for course button
            String userIn=userInput.getText().replaceAll("\\s+", ""); //delete white space
            if(userIn.equals(""))
                display(new String[] {"no Course inputted!", ""}, true);
            else 
                try {
                    search(false, userIn); //bring up menu for search by course
                } catch (IOException ex) {
                    Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
                }
        };
        courseButton.addActionListener(courseButtonListener);
        frame.getRootPane().setDefaultButton(courseButton);//allows enter to activate the course button
    }
    
    
    /**
     * from main menu the user has typed in an ISBN or course number, so search for
     * either one and send the output to display() so the user can see it.
     * @param isISBN flagged true if user wants to search by ISBN, false for course
     * @param userInput the text that the user typed in
     * @throws java.io.IOException
     */
    public static void search(boolean isISBN, String userInput) throws IOException {
    	Book[] books;
    	if(!isISBN)  //user is searching by course
            books = getRequiredBooks(userInput); //If a course was entered, set the books array
    	else {      //else user is searching by book
            books = new Book[1];
            books[0] = new Book(userInput); //If a book was entered, use only that book
    	}
        String[] bookListing = new String[books.length+1]; //one extra line required for headers
        String tab="     "; //for code readability
        bookListing[0]="Book "+tab+tab+tab+tab+"In stock   "+tab+"Required  "+tab+"Need to order"+tab+"Cost of order";
    	
        //iterate through the books[] and format the strings to be stored in bookListing[]
        for(int i=1; i<=books.length; i++) 
            bookListing[i]=formatString(books[i-1]);
        display(bookListing, false);
    }    
    
    
    /**
     * general purpose method, can be used to display output to the user or error
     * messages. Pass it an array of Strings and it will print extra next element
     * on its own line
     * @param textToDisplay the array of strings that will be displayed 
     * @param error true if only displaying the first element in the array
     */
    public static void display(String[] textToDisplay, boolean error) {
        JFrame frame = new JFrame("Output");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        String temp="<html>"+formatForJLabel(textToDisplay[0])+"<br>";
        if (!error)
            for(int i=1; i<textToDisplay.length; i++) 
                temp+=formatForJLabel(textToDisplay[i])+"<br>";
        temp+="</html>";

        JLabel userInput;
        userInput=new JLabel(String.format(temp));
        userInput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        userInput.setBounds(10, 10, 780, 400);
        frame.add(userInput);
        
        JButton okButton = new JButton("Ok");
        okButton.setBounds(375, 500, 50, 25);
        frame.add(okButton);
        frame.setVisible(true);
        
        //setup the listener for the OK Button
        ActionListener okButtonListener = (ActionEvent e) -> { frame.dispose(); };
        okButton.addActionListener(okButtonListener);
        frame.getRootPane().setDefaultButton(okButton);//allows enter to activate the ok button
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
    	Book[] books;
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
     * reformat the string to be HTML friendly for the JFrame
     * @param in the string to be reformatted
     * @return the changed string, now with 20% more &nbsp;!
     */
    public static String formatForJLabel(String in) {
        String out=in.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        out=out.replaceAll(" ", "&nbsp;");
        return out;
    }
    

    /**
     * this method hard formats all the info from the Book object 
     * since JFrame's don't cleanly do printf formatting
     * @param book the object to read data from
     * @return a properly formatted string
     */
    public static String formatString(Book book) {
        String out=""; 
        String tab="     ";
        
        //if the book name is <20 chars long, pad it with spaces
        out=book.getBookName();
        if(book.getBookName().length()<20)
            for(int i=0; i<20-book.getBookName().length(); i++)
                out+=" ";
        out+=tab;
        
        //pad the number of books in stock to left align
        if(book.NumberInStock()<10)         //1 character long
            out+=Integer.toString(book.NumberInStock())+"     ";
        else if(book.NumberInStock()<100)   //2 characters long
            out+=Integer.toString(book.NumberInStock())+"    ";
        else if(book.NumberInStock()<1000)  //3 characters long
            out+=Integer.toString(book.NumberInStock())+"  ";
        else if(book.NumberInStock()<10000) //4 characters long
            out+=Integer.toString(book.NumberInStock())+" ";
        out+=tab+tab;
        
        //pad the number of needed books to left align
        if(book.findNeeded()<10)
            out+=Integer.toString(book.findNeeded())+"    ";
        else if(book.findNeeded()<100)
            out+=Integer.toString(book.findNeeded())+"   ";
        else if(book.findNeeded()<1000)
            out+=Integer.toString(book.findNeeded())+"  ";
        else if(book.findNeeded()<10000)
            out+=Integer.toString(book.findNeeded())+" ";
        out+=tab+tab;

        //pad the difference b/w stocked and needed by left alignment
        if(book.findDifference()<10)
            out+=Integer.toString(book.findDifference())+"    ";
        else if(book.findDifference()<100)
            out+=Integer.toString(book.findDifference())+"   ";
        else if(book.findDifference()<1000)
            out+=Integer.toString(book.findDifference())+"  ";
        else if(book.findDifference()<10000)
            out+=Integer.toString(book.findDifference())+" ";
        out+=tab+tab+"   ";
        
        //no need to pad order cost, just force it to 2 decimals and return the final string
        out+=String.format("%.2f",book.findOrderCost());
        return out;
    }
}
