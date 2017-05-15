package com.demo.bookshelf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.demo.bookshelf.rest.AuthorResource;
import com.demo.bookshelf.rest.BookResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(AuthorResource.class);
        register(BookResource.class);
    }
}
