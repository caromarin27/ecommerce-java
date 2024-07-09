package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.entities.Client;
import com.ecommerce.ecommerce.entities.Invoice;
import com.ecommerce.ecommerce.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/invoices")
public class InvoiceController {
    @Autowired private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice data) {
        try {
            Invoice invoice = invoiceService.saveInvoice(data);
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> readInvoices() {
        try {
            List<Invoice> invoices = invoiceService.readInvoices();
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readInvoiceById(@PathVariable  Long id) {
        try {
            Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
            if(invoice.isPresent()) {
                return ResponseEntity.ok(invoice.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice data) {
        try {
            Optional<Invoice> optionalInvoice = invoiceService.getInvoiceById(id);
            if (optionalInvoice.isPresent()) {
                Invoice invoice = optionalInvoice.get();
                invoice.setClient(data.getClient());
                invoice.setTotal(data.getTotal());
                invoice.setCreated_at(data.getCreated_at());
                invoice.setInvoiceDetails(data.getInvoiceDetails());
                return ResponseEntity.ok(invoiceService.saveInvoice(invoice));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
