package com.demo.bookshelf.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"name", "surname"})
})
public class Author {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books;

    public Author() {
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname, Set<Book> books) {
        this.name = name;
        this.surname = surname;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
