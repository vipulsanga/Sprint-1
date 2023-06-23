package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.library.entitys.IssuedBooks;
import com.example.library.exceptions.BookIssueNotFound;
import com.example.library.exceptions.InsertBookIssueException;
import com.example.library.repository.IssuedRepository;

@Service
public class IssuedBookService
{
	 private final IssuedRepository issuedBookRepository;

	    @Autowired
	    public IssuedBookService(IssuedRepository issuedBookRepository) {
	        this.issuedBookRepository = issuedBookRepository;
	    }

	    public ResponseEntity<List<IssuedBooks>> getAllIssuedBooks() 
	    {
	    	if(issuedBookRepository.findAll().size() <=0) 
			{
				throw new BookIssueNotFound();
			}
	    	else
	    	{
	        return ResponseEntity.ok(issuedBookRepository.findAll());
	    	}
	    }

	    public ResponseEntity<IssuedBooks> getIssuedBookById(int id) 
	    {
	    	try
	    	{
	        return ResponseEntity.ok(issuedBookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("IssuedBook not found with id: " + id)));// issuedBookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("IssuedBook not found with id: " + id));
	    	}
	    	catch (Exception e) 
	    	{
	    		throw new BookIssueNotFound();
			}
	    }

	    public ResponseEntity<String> saveIssuedBook(IssuedBooks issuedBook) 
	    {
	    	try
	    	{
	    		issuedBookRepository.save(issuedBook);
	    		return ResponseEntity.ok("insert"); 
	    	}
	    	catch (Exception e) {
	    		throw new InsertBookIssueException();
			}
	    }

	    public ResponseEntity<String> deleteIssuedBook(int id)
	    {
	    	try
	    	{
	        issuedBookRepository.deleteById(id);
	        return ResponseEntity.ok("delete");
	    	}
	    	catch(Exception e) 
			{
				throw new BookIssueNotFound();
			}
	    }

	}


