package com.example.JavaAssignment.service;

import com.example.JavaAssignment.Exception.ResourceNotFoundException;
import com.example.JavaAssignment.model.Author;
import com.example.JavaAssignment.model.Book;
import com.example.JavaAssignment.repository.AuthorRepository;
import com.example.JavaAssignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public AuthorRepository authorRepository;
    public String addBook(Book book) {
        book.setAvailable(Boolean.TRUE);            // by default true means available
        Book book1 = bookRepository.save(book);

//        bookRepository.save(book1);
        return "Book added successfully";
    }


    public String associateBookAndAuthor(Integer bookId, Integer authorId) throws Exception {
        //Get the book from the bookId
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(bookOptional.isEmpty()) {
            throw new Exception("BookId Entered is incorrect");
            //Throw an exception that book is not found
        }

        Book book = bookOptional.get();

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()) {
            //thow an exception saying AuthorId entered is incorrect
            throw new Exception("AuthorId entered is incorrect");
        }

        Author author = optionalAuthor.get();

        //associate book and author Entity
        book.setAuthor(author);

        bookRepository.save(book);
        return "Book and Author have been associated";
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    public Book updateBook(Integer id, Book book) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if(existingBookOptional.isEmpty()) throw new ResourceNotFoundException("Invalid Book id! Kindly check.");
        Book existingBook = existingBookOptional.get();

        // Update existingBook with fields from the provided book
        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublicationYear(book.getPublicationYear());

        return bookRepository.save(existingBook);
    }

    public String deleteBook(Integer id) {

        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if(existingBookOptional.isEmpty())  throw new ResourceNotFoundException("Book id is invalid!");
        Book existingBook = existingBookOptional.get();
        bookRepository.delete(existingBook);
        return "Book with id "+ id + " deleted successfully.";
    }
}
