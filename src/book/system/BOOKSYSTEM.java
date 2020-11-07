/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book.system;

import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;


/**
 *
 * @author Administrator
 */
public class BOOKSYSTEM {

    /**
     * @param args the command line arguments
     */
  public static void main(String args[]) throws IOException {
    int i; Book x; String xCode; double xPrice;
    Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    DbContext db = new DbContext();
    String fname2 = "book100.txt";
    String fileListReader = "listReader.txt";
    BookList u = new BookList(db);
    ManageReader m = new ManageReader(db);
    String fname;
    while(true){
      System.out.println();
      System.out.println(" 1. Input & add book(s) to the end.");
      System.out.println(" 2. Display all books.");
      System.out.println(" 3. Search a book for given code.");
      System.out.println(" 4. Update the book's price for given code.");
      System.out.println(" 5. Find the (first) max price value.");
      System.out.println(" 6. Sort the list ascendingly by code.");
      System.out.println(" 7. Remove the book having given code.");
      System.out.println(" 8. Load data from file.");
      System.out.println(" 9. Borrow books .");
      System.out.println(" 10.Display list of reader and money must to pay .");
      System.out.println(" 11. Return book ");
      System.out.println(" 0. Exit ");
      System.out.print("  Your selection (0 -> 8): ");
      System.out.println();
      String choice = sc.nextLine();


      if(choice.equals("0")) {
         u.saveFile(fname2);
           break;
      }
      switch(choice){
        case "1": u.input();
                break;
        case "2": u.display();
                break;
        case "3": System.out.print("Enter code: ");
                xCode = sc.nextLine();
                i = u.search(xCode);
                if(i==-1)
                  System.out.println("Not found.");
                else {
                  System.out.println("Found at the position " + i);
                  u.displayBook(i);
                }
                break;
        case "4": System.out.print("Enter code: ");
                xCode = sc.nextLine();
                System.out.print("Enter new price: ");
                xPrice = sc.nextDouble();
                u.update(xCode, xPrice);
                u.display();
                break;
        case "5": if(db.getBooks().isEmpty()) break;
                i = u.max();
                System.out.println("Max is at the position " + i);
                break;
        case "6": u.sortByCode();
                u.display();
                break;   
        case "7": System.out.print("Enter code: ");
                xCode = sc.nextLine();
                u.display();
                u.remove(xCode);
                u.display();
                break;    
        case "8": System.out.print("Enter file name (b = book.txt): ");
                fname = sc.nextLine();
                if(fname.trim().equalsIgnoreCase("b")) 
                    fname = "book10.txt";
                u.loadFile(fname);
                System.out.println("=====================================================LOAD FILE SUCCESS!=========================================================================");
                break;
          case "9" :
              m.borrowBook();
              break;
          case "10":
              m.displayReader();
              break;
          case "11":
              m.returnsBooks();
              break;

        default:
            System.out.print("Wrong selection!");
       } 
     }      
    System.out.println();
   }
    
}
