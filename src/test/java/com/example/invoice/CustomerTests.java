package com.example.invoice;

import com.example.invoice.repository.CustomerRepository;
import com.example.invoice.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class CustomerTests {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    @Test
    void customers_without_orders() {
        System.out.println(customerRepository.findByOrders_Date_YearNotIn(2016));

    }
    @Test
    void customerWithOrder(){
        System.out.println(customerRepository.findCustomersByLastOrder());
        System.out.println(customerService.customersLastOrders());
    }
}
