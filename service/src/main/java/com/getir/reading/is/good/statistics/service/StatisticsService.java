package com.getir.reading.is.good.statistics.service;

import com.getir.reading.is.good.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private OrderRepository repository;

    public List<Map<String, Object>> getCustomersOrders(String customerId) {
        return repository.getCustomerOrderStatistics(customerId);
    }
}
