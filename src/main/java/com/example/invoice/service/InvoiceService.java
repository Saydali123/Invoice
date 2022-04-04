package com.example.invoice.service;

import com.example.invoice.domains.Invoice;
import com.example.invoice.dtos.InvoiceDto;
import com.example.invoice.dtos.OverpaidInvoiceDto;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.service.base.BaseAbstractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService extends BaseAbstractService<InvoiceRepository> {

    public InvoiceService(InvoiceRepository repository) {
        super(repository);
    }

    public List<Invoice> expiredInvoicesList() {
        Optional<List<Invoice>> byIssuedAfterDue = repository.findByIssuedAfterDue();

        if (byIssuedAfterDue.isPresent()) {
            return byIssuedAfterDue.get();
        } else
            throw new RuntimeException("Not found");

    }

    public List<InvoiceDto> wrongDateInvoices() {
        Optional<List<Invoice>> invoicesList = repository.findInvoicesByIssuedIsLessThanOrder_Date();

        if (invoicesList.isPresent()) {
            return getInvoiceDtosByInvoices(invoicesList.get());
        } else
            throw new RuntimeException("Not found");
    }

    private List<InvoiceDto> getInvoiceDtosByInvoices(List<Invoice> invoices) {
        List<InvoiceDto> list = new ArrayList<>();

        for (Invoice invoice : invoices) {
            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.id = invoice.getId();
            invoiceDto.issued = invoice.getIssued();
            invoiceDto.orderId = invoice.getOrder().getId();
            invoiceDto.orderDate = invoice.getOrder().getDate();

            list.add(invoiceDto);
        }

        return list;
    }

    public List<OverpaidInvoiceDto> overpaidInvoices() {
        String s = repository.overpaidInvoice();
        System.out.println(s);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<OverpaidInvoiceDto> dtos = objectMapper.readValue(s, new TypeReference<>() {
            });
            return dtos;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Not found");
        }

    }
}
