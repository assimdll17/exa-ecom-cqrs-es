package com.example.customerservice.commands.controllers;


import lombok.AllArgsConstructor;
import ma.enset.commands.CreateCustomerCommand;
import ma.enset.dtos.CreateCustomerRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/customer")
@AllArgsConstructor
public class CustomerCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO request) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getAdresse(),
                request.getEmail(),
                request.getPhone()
        ));
        return commandResponse;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        ResponseEntity<String> entity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @GetMapping("/eventStore/{customerId}")
    public Stream evenStore(@PathVariable String customerId) {
        return eventStore.readEvents(customerId).asStream();
    }
}
