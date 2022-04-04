package com.example.invoice.controllers;

import com.example.invoice.controllers.base.BaseAbstractController;
import com.example.invoice.domains.Invoice;
import com.example.invoice.dtos.InvoiceDto;
import com.example.invoice.dtos.OverpaidInvoiceDto;
import com.example.invoice.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceController extends BaseAbstractController<InvoiceService> {

    public InvoiceController(InvoiceService service) {
        super(service);
    }

    @GetMapping(value = "/expired_invoices")
    public ResponseEntity<?> getAllExpiredInvoices() {
        List<Invoice> invoices = service.expiredInvoicesList();
        return ResponseEntity.ok(invoices);
    }


    @GetMapping(value = "/wrong_date_invoices")
    public ResponseEntity<?> wrongDateInvoices() {
        List<InvoiceDto> invoices = service.wrongDateInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping(value = "/overpaid_invoices")
    public ResponseEntity<?> overpaidInvoices() {
        List<OverpaidInvoiceDto> invoices = service.overpaidInvoices();
        return ResponseEntity.ok(invoices);
    }



}
