package com.demo.bookshelf.model;

import java.util.List;

public class Book {

    private String title;
    private String abstractText;
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, String abstractText, List<Author> authors) {
        this.title = title;
        this.abstractText = abstractText;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
