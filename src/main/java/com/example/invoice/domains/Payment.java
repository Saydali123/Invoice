package com.example.invoice.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 14)
    private Timestamp time;

    @Column(precision = 8, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "inv_id")
    private Invoice invoice;

}
