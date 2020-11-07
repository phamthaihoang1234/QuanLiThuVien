/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book.system;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Comparator;
import java.util.Collections;

import java.io.*;

/**
 *
 * @author Administrator
 */
public class BookList<T extends Book> {

    Procedure pr = new Procedure();
    DbContext db;

    public BookList(DbContext db){
        this.db = db;
    }

    void input(){
    int qua;
    double price;
    String title;
    String code;
     while(true){  
      code = pr.getString("Enter code (=0 exit): ");
      if(code.equals("0")){break;}
      else if(this.search(code)!=-1){
        System.out.println("This code is already exist!");
      }
      else{
      title= pr.getString("Enter title: ");
      qua= pr.getInt("Enter quantity: ");
      price= pr.getDouble("Enter price: ");
      db.addBook(new Book(code,title,qua,price));
      //books.add(new Book(code,title,qua,price));
      }
     }
    }
    public void display(){
     boolean check;
     ArrayList<Book> books = db.getBooks();
     if (books.isEmpty()){
      System.out.println("There is no existed book in the librady yet");
     }else{
         System.out.println("==============================================================================================================================================================");
     System.out.format("%10s%30s%30s%40s\n","CODE","TITLE","QUANTITY","PRICE");    
     for(int i = 0; i< books.size(); i++){
     System.out.format("%10s%30s%30d%40.2f\n", books.get(i).getCode(), books.get(i).getTitle(), books.get(i).getQua(), books.get(i).getPrice());
      }
     }
    }
    public int search(String code){
        ArrayList<Book> books = db.getBooks();
      for(int i = 0; i< books.size(); i++){
        if(books.get(i).getCode().equalsIgnoreCase(code)){return i;}
      }return -1;
    }                    

    
    public void update(String code,double price){
        ArrayList<Book> books = db.getBooks();
      int index = this.search(code);
      if(index==-1){System.out.println("Code doesn't exist!");}
      else{
          
          books.get(index).setPrice(price);
          System.out.println("Te price of "+ books.get(index).getCode() + " Changed to "+ books.get(index).getPrice());
      }
    }
      public void displayBook(int i){
          ArrayList<Book> books = db.getBooks();
         System.out.println( "code: "+ books.get(i).getCode()+"\ntitle: "+
             books.get(i).getTitle()+"\n"+"price: "+
             books.get(i).getPrice()+"\nquan: "+
             books.get(i).getQua());
     }
      public int max(){
          ArrayList<Book> books = db.getBooks();
       double maxVal = books.get(0).getPrice();
       int rec=0;
       for(int i = 0; i< books.size(); i++){
          if(maxVal< books.get(i).getPrice()){maxVal = books.get(i).getPrice();}
       }
       for(int i = 0; i< books.size(); i++){
          if(maxVal == books.get(i).getPrice()){ rec = i ;
          this.displayBook(i);
          return i;}
       }
           return rec;
      }

       
      Comparator<Book> compareByCode = Comparator.comparing(Book::getCode);
  
     public void sortByCode(){
         ArrayList<Book> books = db.getBooks();
       Collections.sort(books,compareByCode);
     }
     
     public void remove(String code){
         ArrayList<Book> books = db.getBooks();
       int index = this.search(code);
       if(index==-1){
         System.out.println("This code doesn't exist!");
       }
       else{
        books.remove(index);
       }
       System.out.print("\n\n\n=============================================================================After Remove================================================================================\n\n\n");
     }
     
   
     void saveFile(String name) throws IOException {
         ArrayList<Book> books = db.getBooks();
      FileWriter fw= new FileWriter(name);
      PrintWriter pw = new PrintWriter(fw);
      Book x;
      for(int i = 0; i< books.size(); i++){
          x= books.get(i);
          pw.printf("%10s||%30s||%30d||%40.2f\n", books.get(i).getCode(), books.get(i).getTitle(), books.get(i).getQua(), books.get(i).getPrice());
      }
      fw.close();
      pw.close();
     }
     
      void loadFile(String name) throws IOException {
          ArrayList<Book> books = db.getBooks();
         RandomAccessFile f = new RandomAccessFile(name,"rw");
         String s;
         String[]a;
         String xCode,xTitle;
         double xPrice;
         int xQua;
            while(true){
             s = f.readLine();
             if(s==null)break;
             a = s.split("[||]");
             xCode = a[0].trim();
             xTitle= a[2].trim();
             xQua=Integer.parseInt(a[4].trim());
             xPrice=Double.parseDouble(a[6].trim());
             books.add(new Book(xCode,xTitle,xQua,xPrice));
            }
      }



 }

