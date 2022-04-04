package com.example.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCreateDto {
    @JsonProperty(value = "invoice_id")
    public Integer invoiceId;
}
