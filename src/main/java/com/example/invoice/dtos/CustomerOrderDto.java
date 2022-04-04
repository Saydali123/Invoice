package com.example.invoice.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerOrderDto {

    public Integer id;
    public String name;
    public Date date;
}
