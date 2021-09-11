package com.getir.reading.is.good.customer.controller;

import com.getir.reading.is.good.common.response.Response;
import com.getir.reading.is.good.common.util.ResponseUtil;
import com.getir.reading.is.good.customer.model.Customer;
import com.getir.reading.is.good.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response saveCustomer(@RequestBody Customer customer, HttpServletResponse response) {
        try {
            return ResponseUtil.success(service.saveCustomer(customer));
        } catch (DataIntegrityViolationException e) {
            return ResponseUtil.fail(400, "This email is already registered", response);
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }
}
