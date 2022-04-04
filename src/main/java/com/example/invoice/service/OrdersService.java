package com.example.invoice.service;

import com.example.invoice.domains.*;
import com.example.invoice.dtos.OrderCreateDto;
import com.example.invoice.dtos.OrderDetailDto;
import com.example.invoice.dtos.NumberOfProductsInYearDto;
import com.example.invoice.dtos.OrdersWithoutInvoicesDto;
import com.example.invoice.repository.*;
import com.example.invoice.service.base.BaseAbstractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
public class OrdersService extends BaseAbstractService<OrderRepository> {

    private final DetailRepository detailRepository;
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrdersService(OrderRepository repository,
                         DetailRepository detailRepository,
                         InvoiceRepository invoiceRepository,
                         CustomerRepository customerRepository,
                         ProductRepository productRepository) {
        super(repository);
        this.detailRepository = detailRepository;
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Integer makeOrder(OrderCreateDto dto) {
        Integer customerId = dto.customerId;
        Integer productId = dto.productId;
        Short quantity = dto.quantity;

        Order order = createOrder(customerId);
        BigDecimal productPrice = createDetail(order, productId, quantity);

        return createInvoice(order, productPrice, quantity);
    }

    private Integer createInvoice(Order order, BigDecimal productPrice, Short quantity) {
        Invoice invoice = new Invoice();
        Date issued = new Date(System.currentTimeMillis());
        Date due = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
        BigDecimal multiply = productPrice.multiply(BigDecimal.valueOf(quantity));

        invoice.setIssued(issued);
        invoice.setDue(due);
        invoice.setOrder(order);
        invoice.setAmount(multiply);

        Invoice save = invoiceRepository.save(invoice);
        return save.getId();
    }

    private BigDecimal createDetail(Order order, Integer productId, Short quantity) {
        Detail detail = new Detail();

        Product product = getProductFromId(productId);
        BigDecimal price = product.getPrice();

        detail.setOrder(order);
        detail.setProduct(product);
        detail.setQuantity(quantity);
        detailRepository.save(detail);

        return price;
    }

    private Product getProductFromId(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        return optionalProduct.orElseThrow(() -> {
            throw new RuntimeException("Product not found");
        });
    }

    private Order createOrder(Integer customerId) {
        Optional<Customer> byId = customerRepository.findById(customerId);
        if (byId.isPresent()) {
            Order order = new Order();
            order.setCustomer(byId.get());
            order.setDate(new java.util.Date(System.currentTimeMillis()));
            return repository.save(order);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    public List<OrderDetailDto> getDetails(Integer id) {
        Optional<Order> orderById = repository.findById(id);

        if (orderById.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        Order order = orderById.get();
        Optional<List<Detail>> details = detailRepository.findAllByOrder_id(order.getId());

        if (details.isEmpty()) {
            throw new RuntimeException("details not found");
        }

        return getDetailDtoList(details.get());
    }

    private List<OrderDetailDto> getDetailDtoList(List<Detail> details) {
        List<OrderDetailDto> detailList = new ArrayList<>();
        for (Detail detail : details) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.id = detail.getId();
            orderDetailDto.customer = detail.getOrder().getCustomer();
            orderDetailDto.date = detail.getOrder().getDate();
            orderDetailDto.productName = detail.getProduct().getName();
            orderDetailDto.quantity = detail.getQuantity();
            orderDetailDto.productPrice = detail.getProduct().getPrice();
            detailList.add(orderDetailDto);
        }
        return detailList;
    }

    public List<Order> ordersWithoutDetails() {
        Calendar calendar = new GregorianCalendar(2016, Calendar.SEPTEMBER, 6);
        java.util.Date time = calendar.getTime();
        Optional<List<Order>> optionalOrders = repository.findAllByDetailsIsNullAndDateBefore(time);

        return optionalOrders.orElse(Collections.emptyList());
    }

    public List<NumberOfProductsInYearDto> numberOfProductsInYear() {
        String s = repository.findNumberOfProductsInYear(2016);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(s, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Some problem happen");
        }
    }

    public List<OrdersWithoutInvoicesDto> ordersWithoutInvoices() {
        String s = repository.findOrdersWithoutInvoices();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(s, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Some problem happen");
        }
    }
}
