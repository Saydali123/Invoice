package com.example.invoice.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OverpaidInvoiceDto {

    public Integer inv_id;

    public Double diff;

}
