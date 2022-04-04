package com.example.invoice.repository;

import com.example.invoice.domains.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, Integer> {

    Optional<List<Detail>> findAllByProduct_Id(Integer product_id);

    Optional<List<Detail>> findAllByOrder_id(Integer order_id);

    @Query(value = """
            select cast((
                select array_to_json(array_agg(row_to_json("table")))
                from (
                         select detail.pr_id, count(detail.pr_id)
                         from detail
                                  inner join product p on p.id = detail.pr_id
                         group by detail.pr_id
                         having count(detail.pr_id) > 10) "table") as text);
                         """, nativeQuery = true)
    String findHighDemandProducts();


}
