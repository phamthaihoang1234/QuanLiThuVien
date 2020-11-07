package book.system;

import java.util.ArrayList;

public class DbContext {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Reader> readers = new ArrayList<>();





        public void addBook(Book book){
            books.add(book);

        }
        public void addReaders(Reader reader){
            readers.add(reader);

        }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Reader> getReaders() {
        return readers;
    }

    public void setReaders(ArrayList<Reader> readers) {
        this.readers = readers;
    }
}
