package com.example.JavaAssignment.repository;

import com.example.JavaAssignment.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
