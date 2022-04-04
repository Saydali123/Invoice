package com.example.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {

    @JsonProperty("customer_id")
    public Integer customerId;

    @JsonProperty("product_id")
    public Integer productId;

    public Short quantity;
}
