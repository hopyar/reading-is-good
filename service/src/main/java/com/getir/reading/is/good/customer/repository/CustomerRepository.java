package com.getir.reading.is.good.customer.repository;

import com.getir.reading.is.good.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
}
