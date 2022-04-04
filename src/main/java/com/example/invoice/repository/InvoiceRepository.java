package com.example.invoice.repository;

import com.example.invoice.domains.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "select * from invoice i where i.issued > i.due;", nativeQuery = true)
    Optional<List<Invoice>> findByIssuedAfterDue();

    @Query(value = """
            select *
            from invoice inv
                     inner join orders o on o.id = inv.ord_id
            where inv.issued < o.date;""", nativeQuery = true)
    Optional<List<Invoice>> findInvoicesByIssuedIsLessThanOrder_Date();


    @Query(value = """
            select cast((
                                                  select array_to_json(array_agg(row_to_json("table")))
                                                  from (
                                                           select inv_id, sum(payment.amount) - i.amount as diff
                                                           from payment
                                                                    inner join invoice i on i.id = payment.inv_id
                                                           group by inv_id, i.amount
                                                           having sum(payment.amount) > i.amount) "table") as text);
                                                           """, nativeQuery = true)
    String overpaidInvoice();


}
