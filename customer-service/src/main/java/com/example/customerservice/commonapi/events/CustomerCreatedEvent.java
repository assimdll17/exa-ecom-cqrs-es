package com.example.customerservice.commonapi.events;

import lombok.Getter;

public class CustomerCreatedEvent extends BaseEvent<String> {
    @Getter private String name;
    @Getter private String adresse;
    @Getter private String email;
    @Getter private String phone;

    public CustomerCreatedEvent(String id,String name, String adresse, String email, String phone) {
        super(id);
        this.name = name;
        this.adresse = adresse;
        this.email = email;
        this.phone = phone;
    }

}
