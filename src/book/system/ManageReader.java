package book.system;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class ManageReader {
     Scanner sc = new Scanner(System.in);
     public Reader reader;

//     ArrayList<Reader> listReader = new ArrayList<>();
//     Procedure pr = new Procedure();


     Procedure pr = new Procedure();
     DbContext db;

     public ManageReader(DbContext db){
          this.db = db;
     }

     public void addReader() {
         ArrayList<Book> listBook = db.getBooks();
          while (true) {
               reader = new Reader();
               //reader.name = pr.getString("Enter name of reader : ");
               reader.idReader = pr.getString("Enter ID of reader : (=0 exit): ");
               if (reader.idReader.equals("0")) {
                    break;
               } else if (this.search(reader.idReader) != -1) {
                    System.out.println("This code is already exist! ");
                    System.out.println("You have not completely returned the book >>");
               } else {
                    //reader.status = false;
                    reader.name = pr.getString("Enter name of reader : ");
                    int i =0;
                   while (i<2){
                        String titleOfBook = "";

                        boolean check = true;
                        Book book = new Book();

                        do{

                             book.code= pr.getString("Enter code of book you want : (=0 exit): ");
                             if(book.code.equals("0")){
                                  break;
                             }

                             boolean checkfindbook = true;
                             for (int j = 0; j < listBook.size(); j++) {
                                  if (listBook.get(j).code.equalsIgnoreCase(book.code) && listBook.get(j).getQua() >= 1) {
                                       checkfindbook = false;
                                       check = true;
                                       listBook.get(j).setQua(listBook.get(j).getQua() - 1);
                                       titleOfBook = listBook.get(j).title;
                                       book.title = titleOfBook;
                                       reader.addBook(book);
                                       break;
                                  }
                             }
                                  if(checkfindbook) {
                                       System.out.println("Please choose another book:  ");
                                        //check = false;


                                  }



                        }while(!check);
                        if(book.code.equals("0")){
                             break;
                        }

//                             book.title = titleOfBook;
//                             reader.addBook(book);
                             //db.addReaders(reader);
                             i++;
//                        if(reader.getBooks().size() > 0){
//                             reader.status = true;
//                        }

                   }
                    reader.date=pr.getString("Please input date of borrow by format (dd-MM-yyyy) :");
                    db.addReaders(reader);


               }
          }

     }


     public int search (String id){
          ArrayList<Reader> listReader = db.getReaders();
          for (int i = 0; i < listReader.size(); i++) {
               if(listReader.get(i).idReader.equalsIgnoreCase(id)) return i;

          }
          return -1;
     }

     // function trang thai muon cua thang nay xem tra het sach chưa..

     public boolean checkStatusOfReader(String idReader){
          ArrayList<Reader> listReader = db.getReaders();
          for (int i = 0; i < listReader.size(); i++) {
               if(listReader.get(i).idReader.equalsIgnoreCase(idReader)) return false;
          }
          return true;

     }
     public void borrowBook(){
          System.out.println(" DO you first come to library (Y or N ) : ");
          String answer = sc.nextLine();

          if(answer.equals("Y")){
               addReader();

          }else{
               System.out.println("You must return all of book : ");
          }


     }

     public void displayReader(){
          updateMoneyOfLate();
          ArrayList<Reader> list = db.getReaders();
          System.out.println("==============================================================================================================================================================");
          System.out.format("%10s%30s%30s%30s%30s%20s\n","NAME","ID","DATE","MONEYOFLATE","CODE","TITLE");
          for(int i = 0; i< list.size(); i++){

               System.out.format("%10s%30s%30s%30s", list.get(i).name, list.get(i).idReader,list.get(i).date,list.get(i).moneyOfLate);
               for (int j = 0; j < list.get(i).getBooks().size();j+=2 ) {
                    System.out.format("%30s%20s\n", list.get(i).getBooks().get(j).code, list.get(i).getBooks().get(j).title);
                    for (int k = j+1; k < list.get(i).getBooks().size(); k++) {
                         System.out.format("%130s%20s\n", list.get(i).getBooks().get(k).code, list.get(i).getBooks().get(k).title);
                         System.out.println();
                    }


               }
          }
     }

     public void returnsBooks(){
          System.out.println(" DO you want to return books ??");
          String codeOfStudent = pr.getString("Firsly ,please input code of student : ");
          ArrayList<Book> listBook = db.getBooks();
          ArrayList<Reader> listReader = db.getReaders();
          boolean check = true;
//          String endDate = pr.getString("Please input date of return : ");
          while (check){
               if(search(codeOfStudent) >= 0){
//                    String endDate = pr.getString("Please input date of return : ");
                    for (int i = 0; i < listReader.size(); i++) {
                        // listReader.get(search(codeOfStudent)).moneyOfLate = countMoneyOfBorrowLate(listReader.get(search(codeOfStudent)).date, endDate);
                         listReader.get(i).moneyOfLate = countMoneyOfBorrowLate(listReader.get(i).date, getTimeCurent());
                    }
                    displayReader();

                    String codeOfBook = pr.getString(" Please input code of book , which you want to return (=0 exit) : ");
                    // THOAT KHOI CHUONG TRINH TRA SACH
                    if(codeOfBook.equals("0")){
                         break;
                    }
                    for (int i = 0; i < listReader.get(search(codeOfStudent)).getBooks().size(); i++) {
                         if(listReader.get(search(codeOfStudent)).getBooks().get(i).code.equals(codeOfBook)&&listReader.get(search(codeOfStudent)).getBooks().size()>=1) {
                              listReader.get(search(codeOfStudent)).getBooks().remove(i);
                              for (int j = 0; j < listBook.size(); j++) {
                                   if (listBook.get(j).code.equals(codeOfBook)) {
                                        listBook.get(i).setQua(listBook.get(i).getQua() + 1);
                                   }
                              }
                         }
//                         }else{
//                              // THOAT KHOI CHUONG TRINH TRA SACH
//                              System.out.println("Please choose return book again : ");
//                              check = false;
//
//                         }

                    }
                    if(listReader.get(search(codeOfStudent)).getBooks().size()==0){
                         listReader.remove(search(codeOfStudent));

                         System.out.println("You have returned all of book >> See you");
                         check = false;

                    }

               }else{
                    System.out.println("you don't borrow books here: ");
                    break;
               }
          }


     }

     public long countMoneyOfBorrowLate(String startDate , String endDate){
          DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
          Date date1 = null;
          Date date2 = null;
          long getDaysDiff = 0;
          try {




               date1 = simpleDateFormat.parse(startDate);
               date2 = simpleDateFormat.parse(endDate);

               long getDiff = date2.getTime() - date1.getTime();

                getDaysDiff = getDiff / (24 * 60 * 60 * 1000) ;

             if(getDaysDiff > 31 ){
                  return (getDaysDiff - 31)*5000;
             }
          } catch (Exception e) {
               e.printStackTrace();
          }


          return 0;




     }

     public void updateMoneyOfLate(){
          ArrayList<Reader> listReader = db.getReaders();
//          String currentDate = pr.getString("Please input current date : ");
          for (int i = 0; i < listReader.size(); i++) {
               // listReader.get(search(codeOfStudent)).moneyOfLate = countMoneyOfBorrowLate(listReader.get(search(codeOfStudent)).date, endDate);
               listReader.get(i).moneyOfLate = countMoneyOfBorrowLate(listReader.get(i).date, getTimeCurent());
          }

     }

     public String getTimeCurent(){
          Date thoiGian = new Date();

          //Khai bao dinh dang ngay thang
          SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("dd-MM-yyyy ");

          //parse ngay thang sang dinh dang va chuyen thanh string.
          String showTime = dinhDangThoiGian.format(thoiGian.getTime());

          return showTime;


     }
}
