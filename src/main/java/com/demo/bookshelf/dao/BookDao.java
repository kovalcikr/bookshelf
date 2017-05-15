package com.demo.bookshelf.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.bookshelf.model.Book;

public interface BookDao extends CrudRepository<Book, Long> {

    Iterable<Book> findByTitle(String title);
}
