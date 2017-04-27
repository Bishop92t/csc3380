Authors:
Zachary Goad
Nathanael Bishop
Garrett Aymond
Kevin Vo

The class files are included, so compilation should not be necessary. Simply place all the files in the same folder and the program should be able to run. Compilation instructions are included below anyway.

Compilation:
In a command shell, compile the three java files (i.e. javac Bookstore.java Book.java Course.java) and run (i.e. java Bookstore).
If using an IDE, you will need to remove the "//" from the package declaration at the top of each file.

Using the program:
The program will first prompt you for an ISBN or course index. Simply type in the name of one of the text files in the working directory without the ".txt" (i.e. 9780201633610, CSC1350_2, or even csc 1350 2) and use the corresponding button to search for the type of data you entered. A window will display information detailing the amount of books needed to order and the cost of the order.

About the program:
This program is intended for use by a bookstore (specifically the LSU bookstore, as it deals with course names in LSU's format) to calculate vital statistics such as the amount of books in stock, the amount needed before the semester starts, and the cost of such an order. The user can enter an ISBN to calculate how many of that particular book are needed, or a course index to make the same calculation for each book required by the course, while totaling the order cost for each book.