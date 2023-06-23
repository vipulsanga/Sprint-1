package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entitys.Books;
import com.example.library.service.BookService;

@RestController
public class BookController 
{
	 private final BookService bookService;

	    @Autowired
	    public BookController(BookService bookService) {
	        this.bookService = bookService;
	    }

	    @GetMapping(value="/book")
	    public ResponseEntity<List<Books>> getAllBooks() {
	        return bookService.getAllbooks();
	    }

	    @GetMapping("/book/{id}")
	    public ResponseEntity<Books> getBookById(@PathVariable int id) {
	        return bookService.getById(id);
	    }

	    @PostMapping("/book")
	    public ResponseEntity<String> addBook(@RequestBody Books book) {
	        return bookService.addBook(book);
	    }

	    @PutMapping("/book/{id}")
	    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Books book) {
	        return bookService.updateBook(book, id);
	    }

	    @DeleteMapping("/book/{id}")
	    public ResponseEntity<String> deleteBook(@PathVariable int id) {
	        return bookService.deleteBook(id);
	    }
}
