package com.personal.artbyirushishopapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.artbyirushishopapi.dtos.OrderRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse extends BaseResponse {
    private OrderRequestDto data;

    public OrderResponse(int statusCode, String status, String message, OrderRequestDto data) {
        super(statusCode, status, message);
        this.data = data;
    }

    public OrderResponse(int statusCode, String status, String message, Map<String, String> error) {
        super(statusCode, status, message, error);
    }
}
