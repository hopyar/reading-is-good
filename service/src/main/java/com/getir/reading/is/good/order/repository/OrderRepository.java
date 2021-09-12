package com.getir.reading.is.good.order.repository;

import com.getir.reading.is.good.order.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT entity FROM Order entity WHERE entity.orderDate > :startDate and entity.orderDate < :endDate")
    List<Order> getOrdersByInterval(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
