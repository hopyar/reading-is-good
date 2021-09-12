package com.getir.reading.is.good.order.model;

import com.getir.reading.is.good.book.model.Book;
import com.getir.reading.is.good.common.model.BaseEntity;
import com.getir.reading.is.good.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    private Date orderDate = new Date();

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    private int quantity;

    @ManyToOne(optional = false)
    private Book orderedBook;

    @ManyToOne(optional = false)
    private Customer customer;
}
