package com.example.invoice.domains;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "ord_id"))
    private Order order;

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "pr_id"))
    private Product product;

    private Short quantity;


}
