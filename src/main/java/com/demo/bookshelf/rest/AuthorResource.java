package com.demo.bookshelf.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.bookshelf.dao.AuthorDao;
import com.demo.bookshelf.dao.BookDao;
import com.demo.bookshelf.model.Author;
import com.demo.bookshelf.model.Book;

@Path("/authors")
public class AuthorResource {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorResource(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GET
    public Iterable<Author> getAuthors() {
        return authorDao.findAll();
    }

    @POST
    @Consumes("application/json")
    public Response createAuthor(Author author) {
        Author savedAuthor = authorDao.save(author);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAuthor.getId()).toUri();
        return Response.created(location).entity(savedAuthor).build();
    }

    @GET
    @Path("/{authorId}")
    public Author getAuthor(@PathParam("authorId") Long authorId) {
        return authorDao.findOne(authorId);
    }

    @GET
    @Path("/{authorId}/books")
    public Set<Book> getAuthorBooks(@PathParam("authorId") Long authorId) {
        Author author = authorDao.findOne(authorId);
        return author.getBooks();
    }
}
