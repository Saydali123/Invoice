package com.example.invoice;

import com.example.invoice.repository.OrderRepository;
import com.example.invoice.service.OrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
public class OrderTests {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrdersService ordersService;

    @Test
    void orders_without_details() {

        Date date = new Date(2016, 9, 6);
        System.out.println(date.getMonth());
        System.out.println(orderRepository.findAllByDetailsIsNullAndDateBefore(date));
    }

    @Test
    void number_of_products_in_year() {
        System.out.println(ordersService.numberOfProductsInYear());
        System.out.println(orderRepository.findNumberOfProductsInYear(2016));

    }

    @Test
    void orders_without_invoices() {

        System.out.println(orderRepository.findOrdersWithoutInvoices());
        System.out.println(ordersService.ordersWithoutInvoices());
    }
}
