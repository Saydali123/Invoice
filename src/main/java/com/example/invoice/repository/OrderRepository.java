package com.example.invoice.repository;

import com.example.invoice.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<List<Order>> findAllByDetailsIsNullAndDateBefore(Date date);

    @Query(value = """
            select cast((
                select array_to_json(array_agg(row_to_json("table")))
                from (
                         select country, count(orders.id)
                         from orders
                                  inner join customer c on c.id = orders.cust_id
                         where date_part('year', date) = :year_
                         group by country) "table") as text);
                   """, nativeQuery = true)
    String findNumberOfProductsInYear(@Param(value = "year_") Integer year);

    @Query(value = """
            select cast((
                select array_to_json(array_agg(row_to_json("table")))
                from (
                         select ord.id, ord.date, sum(p.price * d.quantity) as total_price, sum(d.quantity) as quantity, p.price as unit_price
                         from orders ord
                                  inner join detail d on ord.id = d.ord_id
                                  inner join product p on p.id = d.pr_id
                         where ord.id not in (select distinct orders.id
                                              from orders
                                                       inner join invoice i on orders.id = i.ord_id)
                         group by ord.id, ord.date, p.price
                     ) "table") as text);
                               """, nativeQuery = true)
    String findOrdersWithoutInvoices();

}
