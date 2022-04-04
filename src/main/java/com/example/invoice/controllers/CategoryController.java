package com.example.invoice.controllers;

import com.example.invoice.controllers.base.BaseAbstractController;
import com.example.invoice.domains.Category;
import com.example.invoice.service.CategoryService;
import com.example.invoice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseAbstractController<CategoryService> {

    final
    ProductService productService;

    public CategoryController(CategoryService service, ProductService productService) {
        super(service);
        this.productService = productService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> allCategories = service.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }


    @GetMapping
    public ResponseEntity<?> getCategoryByProductId(@RequestParam(value = "product_id") Integer integer) {
        Category productCategoryDetails = productService.getProductCategoryDetails(integer);
        return ResponseEntity.ok(productCategoryDetails);
    }


}
