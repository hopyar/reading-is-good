package com.getir.reading.is.good.statistics.controller;

import com.getir.reading.is.good.common.response.Response;
import com.getir.reading.is.good.common.util.ResponseUtil;
import com.getir.reading.is.good.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService service;

    @GetMapping("customer-orders")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Response getCustomerOrders(@QueryParam("id") String id, HttpServletResponse response) {
        try {
            return ResponseUtil.success(service.getCustomersOrders(id));
        } catch (Exception e) {
            return ResponseUtil.fail(500, e.getMessage(), response);
        }
    }
}
