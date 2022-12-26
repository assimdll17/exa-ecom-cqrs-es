package ma.enset.inventoryservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.inventoryservice.commonapi.commands.CreateProductCommand;
import ma.enset.inventoryservice.commonapi.dtos.CreateProductRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/product")
@AllArgsConstructor
public class AccountCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO request) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getPrice(),
                request.getQuantity(),
                request.getStatus(),
                request.getCategoryId()
        ));
        return commandResponse;
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        ResponseEntity<String> entity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @GetMapping("/eventStore/{productId}")
    public Stream evenStore(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }
}
