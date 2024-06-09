package com.springboot.rest.springrestapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.springrestapi.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
    public Book findById(long id);
}
