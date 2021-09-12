package com.getir.reading.is.good.book.controller;

import com.getir.reading.is.good.book.model.Book;
import com.getir.reading.is.good.book.model.StockModel;
import com.getir.reading.is.good.book.service.BookService;
import com.getir.reading.is.good.common.exception.ReadingIsGoodException;
import com.getir.reading.is.good.common.response.Response;
import com.getir.reading.is.good.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response saveBook(@RequestBody Book book, HttpServletResponse response) {
        try {
            return ResponseUtil.success(service.saveBook(book));
        } catch (ReadingIsGoodException e) {
            return ResponseUtil.fail(400, e.getMessage(), response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }

    @PutMapping("add-stock")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response addBookQuantity(@RequestBody StockModel stockInfo, HttpServletResponse response) {
        try {
            service.addBookQuantity(stockInfo.getBookId(), stockInfo.getQuantity());
            return ResponseUtil.successWithMessage("Book stock updated successfully");
        } catch (ReadingIsGoodException e) {
            return ResponseUtil.fail(400, e.getMessage(), response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }

    @PutMapping("decrease-stock")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response decreaseBookQuantity(@RequestBody StockModel stockInfo, HttpServletResponse response) {
        try {
            service.decreaseBookQuantity(stockInfo.getBookId(), stockInfo.getQuantity());
            return ResponseUtil.success("Book stock updated successfully");
        } catch (ReadingIsGoodException e) {
            return ResponseUtil.fail(400, e.getMessage(), response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }

    @GetMapping("by-id")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response getBookById(@QueryParam("id") String id, HttpServletResponse response) {
        try {
            return ResponseUtil.success(service.getBookById(id));
        } catch (ReadingIsGoodException e) {
            return ResponseUtil.fail(400, e.getMessage(), response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }
}
