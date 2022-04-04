package com.example.invoice.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NumberOfProductsInYearDto {
    public Integer count;
    public String country;
}
