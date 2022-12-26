package ma.enset.inventoryservice.commands.aggregates;

import ma.enset.inventoryservice.commonapi.commands.CreateProductCommand;
import ma.enset.inventoryservice.commonapi.enums.ProductStatus;
import ma.enset.inventoryservice.commonapi.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private ProductStatus status;
    private String categoryId;

    public ProductAggregate() {
        //Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        if(createProductCommand.getName() == null ) throw new RuntimeException("Product name cannot be null");

        AggregateLifecycle.apply(new ProductCreatedEvent(
                createProductCommand.getId(),
                createProductCommand.getName(),
                createProductCommand.getPrice(),
                createProductCommand.getQuantity(),
                ProductStatus.DISPONIBLE,
                createProductCommand.getCategoryId()
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.status = ProductStatus.DISPONIBLE;
        this.categoryId = event.getCategoryId();

    }


}
