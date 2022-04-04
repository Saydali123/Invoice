package com.example.invoice.service;

import com.example.invoice.repository.CustomerRepository;
import com.example.invoice.domains.Customer;
import com.example.invoice.dtos.CustomerOrderDto;
import com.example.invoice.service.base.BaseAbstractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends BaseAbstractService<CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public List<Customer> customersWithoutOrders() {
        Optional<List<Customer>> customers = repository.findByOrders_Date_YearNotIn(2016);

        return customers.orElse(Collections.emptyList());
    }


    public List<CustomerOrderDto> customersLastOrders() {
        String customersByLastOrder = repository.findCustomersByLastOrder();

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(customersByLastOrder, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Not found");
        }
    }
}
