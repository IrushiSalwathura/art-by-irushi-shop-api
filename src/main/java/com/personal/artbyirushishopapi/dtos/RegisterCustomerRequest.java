package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterCustomerRequest {
    private String name;
    private LocalDate dob;
    private String address;
    private String email;
    private String phone;
}
