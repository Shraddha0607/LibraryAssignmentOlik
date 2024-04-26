package com.example.JavaAssignment.repository;

import com.example.JavaAssignment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
