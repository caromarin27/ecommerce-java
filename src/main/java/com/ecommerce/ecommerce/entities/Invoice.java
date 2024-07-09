package com.ecommerce.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private LocalDateTime created_at;
    @Getter @Setter private Double total;

    @ManyToOne @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<InvoiceDetails> invoiceDetails;
}
