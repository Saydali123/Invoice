package com.example.invoice.repository;

import com.example.invoice.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = """
            select cast((
            select array_to_json(array_agg(row_to_json("table")))
            from (
                     select distinct p.id, p.price
                     from detail
                              inner join product p on p.id = detail.pr_id
                     where quantity >= 8) "table") as text);
                     """, nativeQuery = true)
    String findBulkProducts();

}
