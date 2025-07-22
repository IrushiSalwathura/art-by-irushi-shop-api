package com.personal.artbyirushishopapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.artbyirushishopapi.dtos.PaymentDto;
import com.personal.artbyirushishopapi.dtos.PaymentResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse extends BaseResponse {
    private PaymentResponseDto data;

    public PaymentResponse(int statusCode, String status, String message, PaymentResponseDto data) {
        super(statusCode, status, message);
        this.data = data;
    }

    public PaymentResponse(int statusCode, String status, String message, Map<String, String> error) {
        super(statusCode, status, message, error);
    }
}
