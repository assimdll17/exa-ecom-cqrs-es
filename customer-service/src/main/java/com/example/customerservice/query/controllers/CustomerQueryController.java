package com.example.customerservice.query.controllers;

import com.example.customerservice.commonapi.queries.GetAllCustomersQuery;
import com.example.customerservice.commonapi.queries.GetCustomerByIdQuery;
import com.example.customerservice.query.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/query/customer")
public class CustomerQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allCustomers")
    public List<Customer> customerList() {
        List<Customer> response = queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();
        return response;
    }

    @GetMapping("/allCustomers/{id}")
    public Customer getCustomer(@PathVariable String id) {
        return queryGateway.query(new GetCustomerByIdQuery(id), ResponseTypes.instanceOf(Customer.class)).join();
    }
}
