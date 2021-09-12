package com.getir.reading.is.good.customer.service;

import com.getir.reading.is.good.common.exception.ReadingIsGoodException;
import com.getir.reading.is.good.customer.model.Customer;
import com.getir.reading.is.good.customer.repository.CustomerRepository;
import com.getir.reading.is.good.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public String saveCustomer(Customer customer) {
        synchronized (customer.getEmail()) {
            return repository.save(customer).getId();
        }
    }

    public List<Order> getOrders(String customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getOrders();
    }

    public Customer getCustomerById(String customerId) {
        Optional<Customer> optionalCustomer = repository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        throw new ReadingIsGoodException("Customer not found with given id:" + customerId);
    }
}
