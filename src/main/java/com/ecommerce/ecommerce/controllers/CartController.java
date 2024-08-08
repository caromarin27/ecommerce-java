package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.entities.Cart;
import com.ecommerce.ecommerce.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carts")
@Tag(name= "Routes of Client Cart", description = "CRUD of Client Cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    @Operation(summary = "Get all carts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carts retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getAllCarts() {
        try {
            List<Cart> carts = cartService.getAllCarts();
            return ResponseEntity.ok(carts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Cart not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> getCartById(@PathVariable Long id) {
        try {
            Optional<Cart> cart = cartService.getCartById(id);
            if (cart.isPresent()) {
                return ResponseEntity.ok(cart.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{clid}/{pid}/{q}")
    @Operation(summary = "Create a new Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cart created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Cart> createCart(@RequestBody Cart data) {
        try {
            Cart createdCart = cartService.createCart(data);
            return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a Cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart updated successfully"),
            @ApiResponse(responseCode = "404", description = "Cart not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        try {
            Optional<Cart> cart = cartService.getCartById(id);
            if (cart.isPresent()) {
                Cart existingCart = cart.get();
                existingCart.setClient(cartDetails.getClient());
                existingCart.setProduct(cartDetails.getProduct());
                existingCart.setPrice(cartDetails.getPrice());
                existingCart.setAmount(cartDetails.getAmount());
                existingCart.setDelivered(cartDetails.getDelivered());
                Cart updatedCart = cartService.updateCart(existingCart);
                return ResponseEntity.ok(updatedCart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cart deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Cart not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> deleteCart(@PathVariable Long id) {
        try {
            Optional<Cart> cart = cartService.getCartById(id);
            if (cart.isPresent()) {
                cartService.deleteCart(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
