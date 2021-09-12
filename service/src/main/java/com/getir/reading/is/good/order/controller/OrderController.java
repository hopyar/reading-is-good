package com.getir.reading.is.good.order.controller;

import com.getir.reading.is.good.common.exception.ReadingIsGoodException;
import com.getir.reading.is.good.common.response.Response;
import com.getir.reading.is.good.common.util.ResponseUtil;
import com.getir.reading.is.good.order.model.Order;
import com.getir.reading.is.good.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Date;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response placeOrder(@RequestBody Order order, HttpServletResponse response, HttpServletRequest request) {
        try {
            return ResponseUtil.success(service.placeOrder(order));
        } catch (ReadingIsGoodException e) {
            return ResponseUtil.fail(400, e.getMessage(), response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }

    @PutMapping("{order-id}/update-status")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response updateStatus(@PathVariable("order-id") String orderId, HttpServletResponse response) {
        try {
            return ResponseUtil.success(service.updateStatus(orderId));
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
            return ResponseUtil.success(service.getOrderById(id));
        } catch (ReadingIsGoodException e) {
            return ResponseUtil.fail(400, e.getMessage(), response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }

    @GetMapping("by-interval")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response getOrderByInterval(@QueryParam("start-date") Date startDate, @QueryParam("end-date") Date endDate, HttpServletResponse response) {
        try {
            return ResponseUtil.success(service.getOrdersByInterval(startDate, endDate));
        } catch (ReadingIsGoodException e) {
            return ResponseUtil.fail(400, e.getMessage(), response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }
}
