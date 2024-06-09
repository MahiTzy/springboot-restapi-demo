package com.springboot.rest.springrestapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.rest.springrestapi.dao.BookRepo;
import com.springboot.rest.springrestapi.entity.Book;

@Component
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    // private List<Book> books= new ArrayList<>(Arrays.asList(
    //     // new Book(1, "Java", "James Gosling"),
    //     new Book(2, "Python", "Guido van Rossum"),
    //     new Book(3, "C++", "Bjarne Stroustrup")
    // ));
    public List<Book> getBooks() {
        return this.bookRepo.findAll();
        // return books;
    }
    public Book getBook(long bookId) {
        try {
            // return books.stream().filter(e->e.getId()==bookId).findFirst().get();
            return this.bookRepo.findById(bookId);
        } catch (Exception e) {
            return null;
        }
        // return books.stream().filter(e->e.getId()==bookId).findFirst().get();
    }
    public Book addBook(Book book) {
        return bookRepo.save(book);
        // books.add(book);
        // return book; 
    }
    public void deleteBook(long bookId) {
        // books.removeIf(e->e.getId()==bookId);
        bookRepo.deleteById(bookId);
    }
    public void updateBook(Book book, long bookId) {
        // for(int i=0; i<books.size(); i++) {
        //     Book b = books.get(i);
        //     if(b.getId()==bookId) {
        //         books.set(i, book);
        //         return;
        //     }
        // }
        bookRepo.save(book);
    }
}
