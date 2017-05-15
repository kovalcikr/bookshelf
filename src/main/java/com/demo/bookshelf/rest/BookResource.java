package com.demo.bookshelf.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.bookshelf.dao.BookDao;
import com.demo.bookshelf.model.Book;

@Path("/books")
public class BookResource {

    private final BookDao bookDao;

    @Autowired
    public BookResource(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GET
    public Iterable<Book> getBooks(@QueryParam("title") String title) {
        if (title == null) {
            return bookDao.findAll();
        }
        return bookDao.findByTitle(title);
    }

    @GET
    @Path("/{bookId}")
    public Book getBook(@PathParam("bookId") Long bookId) {
        return bookDao.findOne(bookId);
    }

    @POST
    @Consumes("application/json")
    public Response createBook(Book book) {
        Book savedBook = bookDao.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
        return Response.created(location).entity(savedBook).build();
    }
}
