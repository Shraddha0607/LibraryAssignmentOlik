package com.example.JavaAssignment.repository;

import com.example.JavaAssignment.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
