package com.example.invoice.repository;

import com.example.invoice.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @Query(value = """
            select *
            from customer
            where id not in (select distinct cust_id
                             from orders
                             where date_part('year', date) = :year_
            );
            """, nativeQuery = true)
    Optional<List<Customer>> findByOrders_Date_YearNotIn(@Param(value = "year_") Integer year);


    @Query(value = """
            select cast((select array_to_json(array_agg(row_to_json("table")))
                                                                                                                                         from (
                                                                                                                                                  select distinct c.id, c.name, orders.date
                                                                                                                                                  from orders
                                                                                                                                                           inner join customer c on c.id = orders.cust_id
                                                                                                                                                  where date = (select date from orders where cust_id = c.id order by date desc limit 1)
                                                                                                                                                  order by date desc) "table") as text);
            """, nativeQuery = true)
    String findCustomersByLastOrder();




}
