package com.getir.reading.is.good.order.service;

import com.getir.reading.is.good.book.service.BookService;
import com.getir.reading.is.good.common.exception.ReadingIsGoodException;
import com.getir.reading.is.good.order.model.Order;
import com.getir.reading.is.good.order.model.OrderStatus;
import com.getir.reading.is.good.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookService bookService;

    public String placeOrder(Order order) {
        synchronized (order.getOrderedBook().getId()) {
            bookService.decreaseBookQuantity(order.getOrderedBook().getId(), order.getQuantity());
            return orderRepository.save(order).getId();
        }
    }

    public String updateStatus(String orderId) {
        synchronized (orderId) {
            Order order = getOrderById(orderId);
            if (order.getStatus() == OrderStatus.NEW) {
                order.setStatus(OrderStatus.ON_DELIVERY);
            } else if (order.getStatus() == OrderStatus.ON_DELIVERY) {
                order.setStatus(OrderStatus.DELIVERED);
            } else {
                throw new ReadingIsGoodException("Order is already delivered. Status can not be changed");
            }
            orderRepository.save(order);
            return order.getStatus().name();
        }
    }

    public Order getOrderById(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        }
        throw new ReadingIsGoodException("Order not found with given id:" + orderId);
    }

    public List<Order> getOrdersByInterval(Date startDate, Date endDate) {
        return orderRepository.getOrdersByInterval(startDate, endDate);
    }
}
