 package com.example.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entitys.IssuedBooks;
import com.example.library.service.IssuedBookService;

@RestController
//@RequestMapping("/issuedbooks")
public class IssuedController 
{
	private IssuedBookService issuedBookService;

    @Autowired
    public IssuedController(IssuedBookService issuedBookService) {
        this.issuedBookService = issuedBookService;
    }

    @GetMapping(value="/issuedbooks") 
    public ResponseEntity<List<IssuedBooks>> getAllIssuedBooks()
    {
        //List<IssuedBooks> issuedBooks = 
        	 return issuedBookService.getAllIssuedBooks();
        //return ResponseEntity.ok(issuedBooks);
    }

    @GetMapping("/issuedbooks/{id}")
    public ResponseEntity<IssuedBooks> getIssuedBookById(@PathVariable("id") int id) {
       // IssuedBooks issuedBook = 
        		return issuedBookService.getIssuedBookById(id);
        //return ResponseEntity.ok(issuedBook);
    }

    @PostMapping("/issuedbooks")
    public ResponseEntity<String> createIssuedBook(@RequestBody IssuedBooks issuedBook) {
      //  IssuedBooks savedIssuedBook = 
        		return issuedBookService.saveIssuedBook(issuedBook);
       // return ResponseEntity.status(HttpStatus.CREATED).body(savedIssuedBook);
    }

    @DeleteMapping("/issuedbooks/{id}")
    public ResponseEntity<String> deleteIssuedBook(@PathVariable("id") int id)
    {
    	return issuedBookService.deleteIssuedBook(id);
       // return ResponseEntity.noContent().build();
    }
    
    
}
