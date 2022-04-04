package com.example.invoice.dtos;

import com.example.invoice.domains.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDto {
    public Integer id;
    public Date date;
    public Customer customer;
    public String productName;
    public Short quantity;
    public BigDecimal productPrice;
}
