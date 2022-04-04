package com.example.invoice.service;

import com.example.invoice.domains.Category;
import com.example.invoice.domains.Detail;
import com.example.invoice.domains.Product;
import com.example.invoice.dtos.BulkProductsDto;
import com.example.invoice.dtos.HighDemandProductsDto;
import com.example.invoice.repository.DetailRepository;
import com.example.invoice.repository.ProductRepository;
import com.example.invoice.service.base.BaseAbstractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends BaseAbstractService<ProductRepository> {

    final
    DetailRepository detailRepository;

    public ProductService(ProductRepository repository, DetailRepository detailRepository) {
        super(repository);
        this.detailRepository = detailRepository;
    }


    public Category getProductCategoryDetails(Integer integer) {
        Optional<Product> byId = repository.findById(integer);
        if (byId.isPresent()) {
            return byId.get().getCategory();
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public List<Product> getAllProducts() {
        return repository.findAll();

    }

    public List<Detail> getProductDetails(Integer id) {
        Optional<List<Detail>> allByProduct_id = detailRepository.findAllByProduct_Id(id);
        if (allByProduct_id.isPresent()) {
            return allByProduct_id.get();
        } else {
            throw new RuntimeException("Some Problem");
        }

    }

    public List<HighDemandProductsDto> highDemandProducts() {
        String s = detailRepository.findHighDemandProducts();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(s, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Some Problem");
        }
    }

    public List<BulkProductsDto> bulkProducts() {
        String s = repository.findBulkProducts();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(s, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Some Problem");
        }
    }
}
