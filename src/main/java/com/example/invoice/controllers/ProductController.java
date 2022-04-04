package com.example.invoice.controllers;

import com.example.invoice.controllers.base.BaseAbstractController;
import com.example.invoice.domains.Detail;
import com.example.invoice.domains.Product;
import com.example.invoice.dtos.BulkProductsDto;
import com.example.invoice.dtos.HighDemandProductsDto;
import com.example.invoice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseAbstractController<ProductService> {

    public ProductController(ProductService service) {
        super(service);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> allProducts = service.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }


    @GetMapping(value = "/details")
    public ResponseEntity<?> getProductDetailsById(@RequestParam(value = "product_id") Integer id) {
        List<Detail> productDetails = service.getProductDetails(id);
        return ResponseEntity.ok(productDetails);

    }


    @GetMapping(value = "/high_demand_products")
    public ResponseEntity<?> highDemandProducts() {
        List<HighDemandProductsDto> productDetails = service.highDemandProducts();
        return ResponseEntity.ok(productDetails);
    }

    @GetMapping(value = "/bulk_products")
    public ResponseEntity<?> bulkProducts() {
        List<BulkProductsDto> productDetails = service.bulkProducts();
        return ResponseEntity.ok(productDetails);
    }
}
