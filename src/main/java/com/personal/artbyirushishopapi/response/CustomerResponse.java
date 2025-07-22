package com.personal.artbyirushishopapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.artbyirushishopapi.dtos.RegisterCustomerRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) //this does not show fileds which are null
public class CustomerResponse extends BaseResponse{
    private RegisterCustomerRequest data;


    public CustomerResponse(int statusCode, String status, String message, RegisterCustomerRequest data) {
        super(statusCode, status, message);
        this.data = data;
    }

    public CustomerResponse(int statusCode, String status, String message, Map<String, String> error) {
        super(statusCode, status, message, error);
    }
}
