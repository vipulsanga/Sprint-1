package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.library.entitys.Books;
import com.example.library.entitys.Student;
import com.example.library.exceptions.BookIssueNotFound;
import com.example.library.exceptions.BookNotFound;
import com.example.library.exceptions.InsertBookIssueException;
import com.example.library.repository.BookRepository;

@Service
public class BookService 
{

	@Autowired
	private BookRepository bookrepo;
	
	public ResponseEntity<List<Books>> getAllbooks()
	{
		try
		{
		List<Books> list=(List<Books>)this.bookrepo.findAll();
		return ResponseEntity.ok(list) ;
		}
		catch (Exception e)
		{
			throw new BookIssueNotFound();
		}
	}
	
	public ResponseEntity<Books> getById(int bid)
	{
		Books book=null;
		try {
			book=this.bookrepo.findById(bid).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + bid));

			return ResponseEntity.ok(book);
		}
		catch(Exception e)
		{
			throw new BookIssueNotFound();
		}
	}
	public ResponseEntity<String> addBook(Books b)
	{
		try {
		//Books result=this.bookrepo.save(b);
			bookrepo.save(b);
			 return ResponseEntity.ok("insert");
		//return reresult;
		}
		catch(Exception e) 
		{
			throw new InsertBookIssueException();
		}
	}
	
	public ResponseEntity<String> deleteBook(int id)
	{
		try
		{
			bookrepo.deleteById(id);
			return ResponseEntity.ok("delete");
		}
		catch(Exception e) 
		{
			throw new BookNotFound();
		}
	}
	public ResponseEntity<String> updateBook(Books stud, int bid)
	{
		try
		{
		Books existingBook = bookrepo.findById(bid).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + bid));
		existingBook.setBname(stud.getBname());
		existingBook.setAuthor(stud.getAuthor());
		bookrepo.save(existingBook);
		return ResponseEntity.ok("update");
		}
		catch (Exception e) {
			throw new BookNotFound();
		}
	}
}

