package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.entities.Client;
import com.ecommerce.ecommerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/clients")
public class ClientsController {
    @Autowired private ClientService service;

    @PostMapping
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

    @PatchMapping("/{id}")
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
