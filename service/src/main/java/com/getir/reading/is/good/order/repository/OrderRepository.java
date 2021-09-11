package com.getir.reading.is.good.order.repository;

import com.getir.reading.is.good.order.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
}
