package com.personal.artbyirushishopapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.artbyirushishopapi.dtos.ItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemResponse  extends BaseResponse {
    private List<ItemDto> data;

    public ItemResponse(int statusCode, String status, String message, List<ItemDto> data) {
        super(statusCode, status, message);
        this.data = data;
    }

    public ItemResponse(int statusCode, String status, String message, Map<String, String> error) {
        super(statusCode, status, message, error);
    }
}
