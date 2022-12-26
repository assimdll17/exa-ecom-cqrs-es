package com.example.customerservice.commonapi.commands;

import lombok.Getter;

public class CreateCustomerCommand extends BaseCommand<String> {
    @Getter private String name;
    @Getter private String adresse;
    @Getter private String email;
    @Getter private String phone;

    public CreateCustomerCommand(String id, String name, String adresse, String email, String phone) {
        super(id);
        this.name = name;
        this.adresse = adresse;
        this.email = email;
        this.phone = phone;
    }
}

