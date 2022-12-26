package com.example.customerservice.query.service;

import com.example.customerservice.commonapi.events.CustomerCreatedEvent;
import com.example.customerservice.commonapi.queries.GetAllCustomersQuery;
import com.example.customerservice.commonapi.queries.GetCustomerByIdQuery;
import com.example.customerservice.query.entities.Customer;
import com.example.customerservice.query.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CustomerServiceHandler {
    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("*******************************");
        log.info("CustomerCreatedEvent received");
        Customer customer = new Customer();
        customer.setId(event.getId());
        customer.setName(event.getName());
        customer.setAdresse(event.getAdresse());
        customer.setEmail(event.getEmail());
        customer.setPhone(event.getPhone());
        customerRepository.save(customer);
    }


    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query)   {
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer on(GetCustomerByIdQuery query) {
        return customerRepository.findById(query.getId()).get();
    }
}
