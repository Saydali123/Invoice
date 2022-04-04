package com.example.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    public Integer id;

    public Date issued;

    @JsonProperty(value = "order_id")
    public Integer orderId;

    public Date orderDate;
}
