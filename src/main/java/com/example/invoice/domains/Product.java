package com.example.invoice.domains;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String name;

    @ManyToOne
    private Category category;

    @Column(length = 20)
    private String description;

    @Column(precision = 6, scale = 2)
    private BigDecimal price;

    @Column(length = 1024)
    private String photo;

}
