package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.entities.InvoiceDetails;
import com.ecommerce.ecommerce.repositories.InvoicesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailsService {
    @Autowired private InvoicesDetailsRepository repository;

    public InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetails) {
        return repository.save(invoiceDetails);
    }

    public List<InvoiceDetails> readInvoiceDetails() {
        return repository.findAll();
    }

    public Optional<InvoiceDetails> getInvoiceDetailsById(Long id) {
        return repository.findById(id);
    }

    public void deleteInvoiceDetails(Long id) {
        repository.deleteById(id);
    }
}
