package com.getir.reading.is.good.book.controller;

import com.getir.reading.is.good.book.model.Book;
import com.getir.reading.is.good.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import javax.ws.rs.Consumes;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public void saveCustomer(@RequestBody Book book) {
        service.saveBook(book);
    }
}
