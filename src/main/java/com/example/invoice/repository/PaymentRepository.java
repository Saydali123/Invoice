package com.example.invoice.repository;

import com.example.invoice.domains.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    boolean existsByInvoice_Id(Integer invoice_id);

}
