package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entitys.IssuedBooks;
public interface IssuedRepository  extends JpaRepository<IssuedBooks,Integer>  
{

}
