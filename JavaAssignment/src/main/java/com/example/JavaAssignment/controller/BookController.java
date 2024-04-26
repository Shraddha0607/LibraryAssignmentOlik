package com.example.JavaAssignment.controller;

import com.example.JavaAssignment.model.Book;
import com.example.JavaAssignment.service.BookService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){
        String response = bookService.addBook(book);
        return response;
    }

    @PutMapping("/buildConnection")
    public String associateBookAndAuthor(@RequestParam("bookId") Integer bookId,
                                         @RequestParam("authorId") Integer authorId) throws Exception{
        try{
            String response = bookService.associateBookAndAuthor(bookId, authorId);
            return response;
        }catch(Exception e){
            return  e.getMessage();
        }

    }

    // Get all books
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // Update a book
    @PutMapping("updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        String response = bookService.deleteBook(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

