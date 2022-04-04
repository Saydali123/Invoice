package com.example.invoice.service;

import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.repository.PaymentRepository;
import com.example.invoice.domains.Invoice;
import com.example.invoice.domains.Payment;
import com.example.invoice.dtos.InvoiceCreateDto;
import com.example.invoice.service.base.BaseAbstractService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PaymentService extends BaseAbstractService<PaymentRepository> {

    private final InvoiceRepository invoiceRepository;

    public PaymentService(PaymentRepository repository, InvoiceRepository invoiceRepository) {
        super(repository);
        this.invoiceRepository = invoiceRepository;
    }

    public Payment makePayment(InvoiceCreateDto dto) {
        Integer invoiceId = dto.invoiceId;

        if (repository.existsByInvoice_Id(invoiceId)) {
            throw new RuntimeException("payment has been done");
        }

        Invoice invoice = getInvoiceById(invoiceId);
        Payment payment = new Payment();
        payment.setAmount(invoice.getAmount());
        payment.setInvoice(invoice);
        payment.setTime(new Timestamp(System.currentTimeMillis()));
        return repository.save(payment);
    }

    private Invoice getInvoiceById(Integer invoiceId) {
        Optional<Invoice> byId = invoiceRepository.findById(invoiceId);

        if (byId.isPresent()) {
            return byId.get();
        } else
            throw new RuntimeException("Invoice not found");
    }

    public Payment getById(Integer id) {
        Optional<Payment> byId = repository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else
            throw new RuntimeException("Payment not found");
    }
}
