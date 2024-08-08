package com.ecommerce.ecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
@Schema(description = "Represent an Invoice")
public class Invoice {
    @Schema(description = "Unique identifier for the invoice", example = "10")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Date and time when the invoice was created", example = "2024-07-05T14:48:00")
    @Getter @Setter private LocalDateTime created_at;

    @Schema(description = "Total amount of the invoice", example = "1500.00")
    @Getter @Setter private Double total;

    @Schema(description = "Identifier of the client associated with the invoice", example = "1")
    @ManyToOne @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<InvoiceDetails> invoiceDetails;
}
