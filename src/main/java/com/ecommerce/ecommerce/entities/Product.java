package com.ecommerce.ecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Schema(description = "Represent a Product")
public class Product {
    @Schema(description = "Unique identifier for the product", example = "101")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Description of the product", example = "Laptop Dell XPS 13")
    @Getter @Setter private String description;

    @Schema(description = "Unique code for the product", example = "100123456")
    @Getter @Setter private String code;

    @Schema(description = "Available stock for the product", example = "15")
    @Getter @Setter private Integer stock;

    @Schema(description = "Price of the product", example = "1200.00")
    @Getter @Setter private Double price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDetails> invoiceDetails;
}
