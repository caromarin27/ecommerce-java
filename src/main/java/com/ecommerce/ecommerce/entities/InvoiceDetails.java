package com.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private Integer amount;
    @Getter @Setter private Double price;

    @ManyToOne @JoinColumn(name = "invoice_id", nullable = false)
    @Getter @Setter private Invoice invoice;

    @ManyToOne @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;

}
