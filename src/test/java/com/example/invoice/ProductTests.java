package com.example.invoice;

import com.example.invoice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTests {


    @Autowired
    ProductService productService;
    @Test
    void bulk_products(){

        System.out.println(productService.bulkProducts());
    }
}
