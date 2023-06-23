package com.example.library.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entitys.Books;
public interface BookRepository extends JpaRepository<Books,Integer>
{
	
}
