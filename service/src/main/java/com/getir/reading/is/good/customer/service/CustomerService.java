package com.getir.reading.is.good.customer.service;

import com.getir.reading.is.good.customer.model.Customer;
import com.getir.reading.is.good.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public String saveCustomer(Customer customer) {
        return repository.save(customer).getId();
    }
}
