package com.example.JavaAssignment.controller;

import com.example.JavaAssignment.model.Author;
import com.example.JavaAssignment.model.Book;
import com.example.JavaAssignment.service.AuthorService;
import com.example.JavaAssignment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping("addAuthor")
    public String addAuthor(@RequestBody Author author){
        String response = authorService.addAuthor(author);
        return response;
    }
    // Get all books
    @GetMapping("/getAllAuthor")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Integer id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    // Update a book
    @PutMapping("updateAuthor/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(updatedAuthor);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        String response = authorService.deleteAuthor(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
