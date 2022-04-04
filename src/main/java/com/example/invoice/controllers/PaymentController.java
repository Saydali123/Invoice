package com.example.invoice.controllers;

import com.example.invoice.controllers.base.BaseAbstractController;
import com.example.invoice.customResponse.Response;
import com.example.invoice.domains.Payment;
import com.example.invoice.dtos.InvoiceCreateDto;
import com.example.invoice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController extends BaseAbstractController<PaymentService> {

    public PaymentController(PaymentService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody InvoiceCreateDto dto) {

        Payment payment;
        try {
            payment = service.makePayment(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new Response<>("FAILED", e.getMessage(), null));
        }
        return ResponseEntity.status(200).body(new Response<>("SUCCESS", payment));
    }

    @GetMapping(value = "/details")
    public ResponseEntity<Payment> get(@RequestParam(name = "id") Integer id) {
        Payment byId = service.getById(id);
        return ResponseEntity.accepted().body(byId);
    }
}
