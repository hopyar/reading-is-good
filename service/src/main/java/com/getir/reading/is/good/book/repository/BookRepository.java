package com.getir.reading.is.good.book.repository;

import com.getir.reading.is.good.book.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
