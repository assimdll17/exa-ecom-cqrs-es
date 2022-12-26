package com.example.customerservice.commands.aggregates;

import com.example.customerservice.commonapi.commands.CreateCustomerCommand;
import com.example.customerservice.commonapi.events.CustomerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CustomerAggregate {
    @AggregateIdentifier
    private String customerId;
    private String name;
    private String adresse;
    private String email;
    private String phone;

    public CustomerAggregate() {
        //Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand createAccountCommand) {
        if(createAccountCommand.getName()==null) throw new RuntimeException("Name cannot be null");

        AggregateLifecycle.apply(new CustomerCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getName(),
                createAccountCommand.getAdresse(),
                createAccountCommand.getEmail(),
                createAccountCommand.getPhone()
        ));

    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.customerId = event.getId();
        this.name = event.getName();
        this.adresse = event.getAdresse();
        this.email = event.getEmail();
        this.phone = event.getPhone();

    }


}
