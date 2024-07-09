package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.entities.Client;
import com.ecommerce.ecommerce.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired private ClientsRepository repository;

    public Client saveClient(Client client) {
        return repository.save(client);
    }

    public List<Client> readClients() {
        return repository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return repository.findById(id);
    }

    public void deleteClient(Long id) {
        repository.deleteById(id);
    }
}
