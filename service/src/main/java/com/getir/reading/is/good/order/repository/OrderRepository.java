package com.getir.reading.is.good.order.repository;

import com.getir.reading.is.good.order.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT entity FROM Order entity WHERE entity.orderDate > :startDate and entity.orderDate < :endDate")
    List<Order> getOrdersByInterval(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT MONTH(cOrder.orderDate) as month, count(cOrder) as orderCount,  SUM(cOrder.quantity) as orderQuantity, SUM(cOrder.purchaseAmount) as totalPurchase FROM Order cOrder LEFT JOIN cOrder.customer customer WHERE customer.id = :customerId GROUP BY MONTH(cOrder.orderDate)")
    List<Map<String, Object>> getCustomerOrderStatistics(@Param("customerId") String customerId);
}
