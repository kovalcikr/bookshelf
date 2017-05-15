package com.demo.bookshelf.model;

import java.util.List;

public class Book {

    private String name;
    private String abstractText;
    private List<Author> authors;

    public Book() {
    }

    public Book(String name, String abstractText, List<Author> authors) {
        this.name = name;
        this.abstractText = abstractText;
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
