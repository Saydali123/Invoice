package com.example.invoice.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersWithoutInvoicesDto {
    public Integer id;
    public Date date;
    public Double total_price;
    public Integer quantity;
    public Double unit_price;
}
