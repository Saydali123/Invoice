package com.example.invoice;

import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvoiceTests {

    @Autowired
    public InvoiceService invoiceService;

    @Autowired
    public InvoiceRepository invoiceRepository;

    @Test
    void invoiceExpired() {
        System.out.println(invoiceService.expiredInvoicesList());
    }

    @Test
    void wrong_date_invoices() {
        System.out.println(invoiceService.wrongDateInvoices());
    }

    @Test
    void overPaidInvoice() {
        System.out.println(invoiceRepository.overpaidInvoice());
        System.out.println(invoiceService.overpaidInvoices());
    }
}
