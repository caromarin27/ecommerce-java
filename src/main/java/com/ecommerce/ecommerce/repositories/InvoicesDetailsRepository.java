package com.ecommerce.ecommerce.repositories;

import com.ecommerce.ecommerce.entities.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesDetailsRepository extends JpaRepository<InvoiceDetails, Long> {
}
