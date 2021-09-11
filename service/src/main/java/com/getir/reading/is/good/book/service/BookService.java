package com.getir.reading.is.good.book.service;

import com.getir.reading.is.good.book.model.Book;
import com.getir.reading.is.good.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public void saveBook(Book book) {
        repository.save(book);
    }
}
