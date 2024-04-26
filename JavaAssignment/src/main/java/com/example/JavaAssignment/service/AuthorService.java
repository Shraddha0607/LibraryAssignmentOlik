package com.example.JavaAssignment.service;

import com.example.JavaAssignment.Exception.ResourceNotFoundException;
import com.example.JavaAssignment.model.Author;
import com.example.JavaAssignment.model.Book;
import com.example.JavaAssignment.repository.AuthorRepository;
import com.example.JavaAssignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    public AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author added successfully";
    }
   public List<Author> getAllAuthors(){
        return authorRepository.findAll();
   }
   public Author getAuthorById(Integer id){
       return authorRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
   }

   public Author updateAuthor(Integer id, Author author) {
       Optional<Author> existingAuthorOptional = authorRepository.findById(id);
       if(existingAuthorOptional.isEmpty()) throw new ResourceNotFoundException("Invalid Author id! Kindly check.");
       Author existingAuthor = existingAuthorOptional.get();

       // Update existingBook with fields from the provided book
       existingAuthor.setName(author.getName());
       existingAuthor.setBiography(author.getBiography());

       return authorRepository.save(existingAuthor);
   }

    public String deleteAuthor(Integer id) {

    Optional<Author> existingAuthorOptional = authorRepository.findById(id);
    if(existingAuthorOptional.isEmpty())  throw new ResourceNotFoundException("Author id is invalid!");
    Author existingAuthor = existingAuthorOptional.get();
    authorRepository.delete(existingAuthor);
    return "Author with id "+ id + " deleted successfully.";
}

}



