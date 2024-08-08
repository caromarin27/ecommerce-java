package com.ecommerce.ecommerce.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represent a Client")
public class Client {
    @Schema(description = "Unique identifier for the client", example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "First name of the client", example = "Carolina")
    @Getter @Setter private String name;

    @Schema(description = "Last name of the client", example = "Marin")
    @Getter @Setter private String lastname;

    @Schema(description = "National identification number of the client", example = "12345678")
    @Getter @Setter private String dni;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<Invoice> invoices;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter private List<Cart> carts;
}
