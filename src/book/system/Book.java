/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book.system;

/**
 *
 * @author Administrator
 */
public class Book {
    public String code;
    public String title;
    public int qua;
    public double price;
        
      public Book(String code,String title,int qua,double price){
         this.code=code;
         this.price=price;
         this.qua=qua;
         this.title=title;
      }

    public Book() {
    }

    public Book(String code, String title){
        this.code=code;

        this.title=title;
    }


      
      public String getTitle(){return this.title;}
      public String getCode(){return this.code;}
      public int getQua(){return this.qua;}
      public double getPrice(){return this.price;}
      
      public void setTitle(String title){this.title=title;}
      public void setCode(String code){this.code=code;}
      public void setPrice(double price){this.price=price;}
      public void setQua(int qua){this.qua=qua;}

      
}
