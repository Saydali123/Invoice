package com.example.invoice.controllers;

import com.example.invoice.controllers.base.BaseAbstractController;
import com.example.invoice.domains.Customer;
import com.example.invoice.dtos.CustomerOrderDto;
import com.example.invoice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController extends BaseAbstractController<CustomerService> {

    public CustomerController(CustomerService service) {
        super(service);
    }

    @GetMapping(value = "/customers_without_orders")
    public ResponseEntity<?> customersWithoutOrders() {
        List<Customer> customers = service.customersWithoutOrders();

        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/customers_last_orders")
    public ResponseEntity<?> customersLastOrders() {
        List<CustomerOrderDto> customers = service.customersLastOrders();

        return ResponseEntity.ok(customers);
    }


}
