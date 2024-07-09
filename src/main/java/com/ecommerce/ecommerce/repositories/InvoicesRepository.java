package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Long> {
}
