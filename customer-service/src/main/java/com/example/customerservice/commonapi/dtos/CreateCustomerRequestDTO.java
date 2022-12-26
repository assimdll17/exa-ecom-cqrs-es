package com.example.customerservice.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateCustomerRequestDTO {
private String name;
private String adresse;
private String email;
private String phone;
}
