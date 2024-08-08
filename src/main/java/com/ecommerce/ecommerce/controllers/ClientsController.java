package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.entities.Client;
import com.ecommerce.ecommerce.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("api/v1/auth")
@Tag(name= "Routes of Clients", description = "CRUD of Clients")
public class ClientsController {
    @Autowired private ClientService service;

    @PostMapping("/register")
    @Operation(summary = "Create a new Client")
    public ResponseEntity<Client> createClient(@RequestBody Client data) {
        try {
            Client client = service.saveClient(data);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @Operation(summary = "Get a list of all Clients")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Client retrieved successfully"),
            @ApiResponse( responseCode = "201", description = "Client created successfully"),
            @ApiResponse( responseCode = "404", description = "Client not found"),
            @ApiResponse( responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Client>> readClient() {
        try {
            List<Client> clients = service.readClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Client by ID")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Client created successfully"),
            @ApiResponse( responseCode = "404", description = "Client not found"),
            @ApiResponse( responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> readClientById(@PathVariable  Long id) {
        try {
            Optional<Client> client = service.getClientById(id);
            if(client.isPresent()) {
                return ResponseEntity.ok(client.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/me/{id}")
    @Operation(summary = "Update a Client")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Client update successfully"),
            @ApiResponse( responseCode = "404", description = "Client not found"),
            @ApiResponse( responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client data) {
        try {
            Optional<Client> optionalClient = service.getClientById(id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                client.setName(data.getName());
                client.setLastname(data.getLastname());
                client.setDni(client.getDni());
                return ResponseEntity.ok(service.saveClient(client));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Client")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "Client deleted successfully"),
            @ApiResponse( responseCode = "404", description = "Client not found"),
            @ApiResponse( responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        try {
            service.deleteClient(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
