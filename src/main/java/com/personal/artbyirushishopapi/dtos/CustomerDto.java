package com.personal.artbyirushishopapi.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private Date dob;
    private String address;
    private String email;
    private String phone;
}
