package com.springboot.rest.springrestapi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.springrestapi.entity.Book;
import com.springboot.rest.springrestapi.services.BookService;

// @Controller
// public class restcontroller {
//     @RequestMapping(value="/books", method=RequestMethod.GET)
//     @ResponseBody
//     public String getBooks() {
//         return "All books";
//     }
// }
@RestController
public class restcontroller {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> book = this.bookService.getBooks();
        if (book.size() <= 0) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(book);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
        Book book = bookService.getBook(id);
        if (book == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        try {
            Book b = this.bookService.addBook(book);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        try {
            this.bookService.deleteBook(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
        // this.bookService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable("id") long id) {
        try {
            this.bookService.updateBook(book, id);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
        // this.bookService.updateBook(book, id);
    }
}
