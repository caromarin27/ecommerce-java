package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.entities.Invoice;
import com.ecommerce.ecommerce.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired private InvoicesRepository repository;

    public Invoice saveInvoice(Invoice invoice) {
        return repository.save(invoice);
    }

    public List<Invoice> readInvoices() {
        return repository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return repository.findById(id);
    }

    public void deleteInvoice(Long id) {
        repository.deleteById(id);
    }
}
