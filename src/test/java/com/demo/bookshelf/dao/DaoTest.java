package com.demo.bookshelf.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bookshelf.model.Author;
import com.demo.bookshelf.model.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DaoTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;

    HashSet<Author> originalAuthors = new HashSet<Author>() {{
        add(new Author("author", "1"));
        add(new Author("author", "2"));
    }};

    @Before
    public void setUp() {
        bookDao.save(new Book("book1", "abstract 1", originalAuthors));
    }

    @Test
    public void testBookDao() {
        Iterator<Book> bookIterator = bookDao.findAll().iterator();
        Book book = bookIterator.next();
        assertEquals("book1", book.getTitle());
        assertEquals("abstract 1", book.getAbstractText());
        assertEquals(originalAuthors, book.getAuthors());
        assertFalse(bookIterator.hasNext());
    }

    @Test
    public void testBookDao_findByTitle() {
        Iterator<Book> bookIterator = bookDao.findByTitle("book1").iterator();
        Book book = bookIterator.next();
        assertEquals("book1", book.getTitle());
        assertEquals("abstract 1", book.getAbstractText());
        assertEquals(originalAuthors, book.getAuthors());
        assertFalse(bookIterator.hasNext());
    }

    public void testAuthorDao() {
        Set<Author> authorInDb = new HashSet<>();
        authorDao.findAll().forEach(authorInDb::add);
        assertEquals(2, authorInDb.size());
        assertEquals(originalAuthors, authorInDb);
    }
}
