package com.example.JavaAssignment.service;

import com.example.JavaAssignment.model.Book;
import com.example.JavaAssignment.model.Rental;
import com.example.JavaAssignment.repository.BookRepository;
import com.example.JavaAssignment.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class RentalService {
    @Autowired
    public RentalRepository rentalRepository;
    @Autowired
    public BookRepository bookRepository;

    // Rent a book
    public String rentBook(Rental rental) {
        int id = rental.getBookId();

        // Check if the book is available for renting
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            return "Book not found";
        }
        Book book = bookOptional.get();
        // check whether book is available or not
        boolean bookAvailable = book.isAvailable();
        if (!bookAvailable) {
            return "Book is not available for renting";
        }

        // Update book availability status and mark it as rented
        book.setAvailable(false);
        bookRepository.save(book);

        return "Book rented successfully";
    }

    // Return a book
    public String returnBook(Integer id) {
        // Retrieve the rental record from the database based on the book ID
        Optional<Rental> rentalOptional = rentalRepository.findById(id);

        // Check if the rental record exists and if the book was rented
        if (rentalOptional.isEmpty()) {
            return "No rental record found for the book";
        }

        Rental rental = rentalOptional.get();
        // Calculate the number of days the book was rented
        //Fine amount to be calculated :
        Long timeDiffernceInMs = System.currentTimeMillis() - rental.getRentalDate().getTime();

        //This time is in MS, we need to convert this to days
        Long daysRented = TimeUnit.DAYS.convert(timeDiffernceInMs,TimeUnit.MILLISECONDS);


        // Check if the book is returned late (more than 14 days)
        long daysLate = daysRented - 14;
        boolean fineApplicable = false;
        double fine=0;
        if (daysLate > 0) {
            // Calculate the fine
            fine = daysLate * 5; // Assuming 5 Rs fine per day
            fineApplicable=  true;
        }

        // Mark the book as available for further renting
        Book book = bookRepository.findById(id).get();
        book.setAvailable(true);
        bookRepository.save(book);
        if(fineApplicable){
            return  "Book returned successfully. Noticed: Fine: Rs " + fine;
        }

        return "Book returned successfully";
    }

}
