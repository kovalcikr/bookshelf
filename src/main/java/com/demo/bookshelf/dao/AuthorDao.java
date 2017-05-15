package com.demo.bookshelf.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.bookshelf.model.Author;

public interface AuthorDao extends CrudRepository<Author, Long> {
}
