package com.getir.reading.is.good.book.service;

import com.getir.reading.is.good.book.model.Book;
import com.getir.reading.is.good.book.repository.BookRepository;
import com.getir.reading.is.good.common.exception.ReadingIsGoodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public String saveBook(Book book) {
        if (book.getNumberOfStock() < 0) {
            throw new ReadingIsGoodException("Book quantity can not be less than 0");
        }
        return repository.save(book).getId();
    }

    public void addBookQuantity(String bookId, int quantity) {
        if (quantity < 1) {
            throw new ReadingIsGoodException("Added quantity must be bigger than zero(0)");
        }
        Book book = getBookById(bookId);
        book.setNumberOfStock(book.getNumberOfStock() + quantity);
        repository.save(book);
    }

    public void decreaseBookQuantity(String bookId, int quantity) {
        if (quantity < 1) {
            throw new ReadingIsGoodException("Decrease quantity must be bigger than zero(0)");
        }
        Book book = getBookById(bookId);

        if (quantity > book.getNumberOfStock()) {
            throw new ReadingIsGoodException("Decrease quantity can not bigger than current quantity");
        }

        book.setNumberOfStock(book.getNumberOfStock() - quantity);
        repository.save(book);
    }

    public Book getBookById(String bookId) {
        Optional<Book> optionalBook = repository.findById(bookId);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }
        throw new ReadingIsGoodException("Book not found with given id:" + bookId);
    }
}
