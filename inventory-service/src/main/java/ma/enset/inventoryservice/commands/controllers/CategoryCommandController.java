package ma.enset.inventoryservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.inventoryservice.commonapi.commands.CreateCategoryCommand;
import ma.enset.inventoryservice.commonapi.commands.CreateProductCommand;
import ma.enset.inventoryservice.commonapi.dtos.CreateCategoryRequestDTO;
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
@RequestMapping("/commands/category")
@AllArgsConstructor
public class CategoryCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateCategoryRequestDTO request) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCategoryCommand(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription()
        ));
        return commandResponse;
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        ResponseEntity<String> entity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @GetMapping("/eventStore/{categoryId}")
    public Stream evenStore(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }
}
