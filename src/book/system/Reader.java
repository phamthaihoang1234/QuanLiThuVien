package book.system;


import java.util.ArrayList;

public class Reader {
    public String idReader;
    public String name;
    public String date;
    public boolean status = false;
    public long moneyOfLate = 0;

    public ArrayList<Book> listBorrowBook = new ArrayList<>();
    public ArrayList<String> dateOfBorrow = new ArrayList<>();

    public ArrayList<String> getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(ArrayList<String> dateOfBorrow) {
        this.dateOfBorrow = dateOfBorrow;
    }

    public void addDate(String date){
        dateOfBorrow.add(date);
    }
    public void addBook(Book book){
        listBorrowBook.add(book);

    }
    public ArrayList<Book> getBooks() {
        return listBorrowBook;
    }

    public Reader() {
    }

    public Reader(String idReader, String name, boolean status,  ArrayList<Book> list) {
        this.idReader = idReader;
        this.name = name;
        this.status = status;

        this.listBorrowBook = list;
    }

    public String getIdReader() {
        return idReader;
    }

    public void setIdReader(String idReader) {
        this.idReader = idReader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    public ArrayList<Book> getList() {
        return listBorrowBook;
    }

    public void setList(ArrayList<Book> list) {
        this.listBorrowBook = list;
    }
}
