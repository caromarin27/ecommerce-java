package com.ecommerce.ecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represent a Cart from Client")
public class Cart {
    @Schema(description = "Unique identifier for the client cart", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Identifier of the client cart", example = "1")
    @ManyToOne @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;

    @Schema(description = "Price of the product", example = "1200.00")
    @Getter @Setter private Double price;

    @Schema(description = "Amount of the product", example = "2")
    @Getter @Setter private Integer amount;

    @Schema(description = "Delivered status")
    @Getter @Setter private Boolean delivered;

    @Schema(description = "Identifier of the product associated", example = "20")
    @ManyToOne @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;
}
