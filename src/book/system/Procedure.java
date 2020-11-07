/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book.system;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;
/**
 *
 * @author Administrator
 */
public class Procedure {

    Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    public String getString(String str){
          System.out.print(str);
          String s = sc.next();
          s+=sc.nextLine();
          return s;
    }
     public double getDouble(String s){
     double p=0.0;
     boolean check=false;
     do{
          System.out.print(s);
          if(sc.hasNextDouble() ){
             p = sc.nextDouble();
             if(p>=0) check = true;
             else {
                 System.out.println("Invalid! Quantity must be more than 0!!");
                 check = false;
             }
          }else{

             sc.next();
             check = false;
          }
     }while(!check);    
     return p;
 }
     public int getInt(String s){
         int b = 0;
         boolean check = false;
         
         do{
            System.out.print(s);
            if(sc.hasNextInt()){
               b = sc.nextInt();
                if(b>=0) check = true;
                else {
                    System.out.println("Invalid! Price must be more than 0!!");
                    check = false;
                }
            }
            else{
              System.out.println("INVALID!");
              sc.next();
              check = false;
            }
         }while(!check);
           return b;
  }
     
     }
   

