package com.example.invoice.controllers;

import com.example.invoice.controllers.base.BaseAbstractController;
import com.example.invoice.customResponse.Response;
import com.example.invoice.domains.Order;
import com.example.invoice.dtos.OrderCreateDto;
import com.example.invoice.dtos.OrderDetailDto;
import com.example.invoice.dtos.NumberOfProductsInYearDto;
import com.example.invoice.dtos.OrdersWithoutInvoicesDto;
import com.example.invoice.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController extends BaseAbstractController<OrdersService> {


    public OrderController(OrdersService service) {
        super(service);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> makeOrder(@RequestBody @Valid OrderCreateDto dto) {

        Integer invoiceId;
        try {
            invoiceId = service.makeOrder(dto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new Response<>("FAILED", e.getMessage(), -1));
        }

        return ResponseEntity.status(200).body(new Response<>("SUCCESS", invoiceId));
    }

    @GetMapping(value = "/details")
    public ResponseEntity<?> orderDetails(@RequestParam("order_id") Integer id) {
        List<OrderDetailDto> details = service.getDetails(id);
        return ResponseEntity.ok(details);
    }

    @GetMapping(value = "/orders_without_details")
    public ResponseEntity<?> ordersWithoutDetails() {
        List<Order> ordersWithoutDetails = service.ordersWithoutDetails();
        return ResponseEntity.ok(ordersWithoutDetails);
    }

    @GetMapping(value = "/number_of_products_in_year")
    public ResponseEntity<?> numberOfProductsInYear() {
        List<NumberOfProductsInYearDto> customers = service.numberOfProductsInYear();

        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/orders_without_invoices")
    public ResponseEntity<?> ordersWithoutInvoices() {
        List<OrdersWithoutInvoicesDto> customers = service.ordersWithoutInvoices();

        return ResponseEntity.ok(customers);
    }
}
