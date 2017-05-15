package com.demo.bookshelf.rest;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bookshelf.model.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    String book = "{\"title\":\"title1\", \"abstractText\":\"abstract1\", \"authors\": [{\"name\":\"autror\", \"surname\": \"1\"}, {\"name\":\"another\", \"surname\": \"author\"}]}";

    @Test
    public void testEmptyBooks() {
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("/books", Object[].class);
        Object[] objects = responseEntity.getBody();
        assertEquals(0, objects.length);
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testEmpty() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(book, headers);
        URI uri = restTemplate.postForLocation("/books", entity);
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(uri, Book.class);
        Book restBook = responseEntity.getBody();
        assertEquals("title1", restBook.getTitle());
        assertEquals("abstract1", restBook.getAbstractText());
        assertEquals(2, restBook.getAuthors().size());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
