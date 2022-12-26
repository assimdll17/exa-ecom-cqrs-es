package ma.enset.inventoryservice.commands.aggregates;

import ma.enset.commands.CreateCategoryCommand;
import ma.enset.events.CategoryCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String categoryId;
    private String name;
    private String description;

    public CategoryAggregate() {
        //Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand createCategoryCommand) {
        if(createCategoryCommand.getName()==null) throw new RuntimeException("Category name cannot be null");

        AggregateLifecycle.apply(new CategoryCreatedEvent(
                createCategoryCommand.getId(),
                createCategoryCommand.getName(),
                createCategoryCommand.getDescription()
        ));

    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.categoryId = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
    }


}
