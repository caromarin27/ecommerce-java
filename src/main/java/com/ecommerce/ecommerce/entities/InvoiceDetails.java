package com.ecommerce.ecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice_details")
@Schema(description = "Represent a invoice details from an invoice")
public class InvoiceDetails {
    @Schema(description = "Unique identifier for the invoice detail", example = "50")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Amount of the product in the invoice detail", example = "2")
    @Getter @Setter private Integer amount;

    @Schema(description = "Price of the product in the invoice detail", example = "600.00")
    @Getter @Setter private Double price;

    @Schema(description = "Identifier of the invoice associated with the detail", example = "10")
    @ManyToOne @JoinColumn(name = "invoice_id", nullable = false)
    @Getter @Setter private Invoice invoice;

    @Schema(description = "Identifier of the product associated with the detail", example = "20")
    @ManyToOne @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;

}
