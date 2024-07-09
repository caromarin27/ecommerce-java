package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.entities.InvoiceDetails;
import com.ecommerce.ecommerce.services.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/invoices-details")
public class InvoiceDetailsController {
    @Autowired private InvoiceDetailsService invoiceDetailsService;

    @PostMapping
    public ResponseEntity<InvoiceDetails> createInvoiceDetails(@RequestBody InvoiceDetails data) {
        try {
            InvoiceDetails invoiceDetails = invoiceDetailsService.saveInvoiceDetails(data);
            return new ResponseEntity<>(invoiceDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDetails>> readInvoicesDetails() {
        try {
            List<InvoiceDetails> invoicesDetails = invoiceDetailsService.readInvoiceDetails();
            return ResponseEntity.ok(invoicesDetails);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readInvoiceDetailsById(@PathVariable  Long id) {
        try {
            Optional<InvoiceDetails> invoiceDetails = invoiceDetailsService.getInvoiceDetailsById(id);
            if(invoiceDetails.isPresent()) {
                return ResponseEntity.ok(invoiceDetails.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InvoiceDetails> updateInvoiceDetails(@PathVariable Long id, @RequestBody InvoiceDetails data) {
        try {
            Optional<InvoiceDetails> optionalInvoiceDetails = invoiceDetailsService.getInvoiceDetailsById(id);
            if (optionalInvoiceDetails.isPresent()) {
                InvoiceDetails invoiceDetails = optionalInvoiceDetails.get();
                invoiceDetails.setInvoice(data.getInvoice());
                invoiceDetails.setAmount(data.getAmount());
                invoiceDetails.setProduct(data.getProduct());
                invoiceDetails.setPrice(data.getPrice());
                return ResponseEntity.ok(invoiceDetailsService.saveInvoiceDetails(invoiceDetails));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceDetails> deleteInvoiceDetails(@PathVariable Long id) {
        try {
            invoiceDetailsService.deleteInvoiceDetails(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}