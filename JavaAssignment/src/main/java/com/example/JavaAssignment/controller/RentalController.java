package com.example.JavaAssignment.controller;

import com.example.JavaAssignment.model.Book;
import com.example.JavaAssignment.model.Rental;
import com.example.JavaAssignment.service.BookService;
import com.example.JavaAssignment.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @PostMapping("/rentBook")
    public ResponseEntity<String> rentBook(@RequestBody Rental rental) throws Exception {
        String response = rentalService.rentBook(rental);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Return a book
    @PostMapping("/returnBook/{id}")
    public ResponseEntity<String> returnBook(@PathVariable Integer id) {
        String response = rentalService.returnBook(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}