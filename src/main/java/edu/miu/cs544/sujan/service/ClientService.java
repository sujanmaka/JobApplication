package edu.miu.cs544.sujan.service;

import edu.miu.cs544.sujan.entity.Client;

import java.util.List;

public interface ClientService {
    Client getClientById(Long id);

    List<Client> getClients();

    Client createClient(Client client);

    String deleteClient(Long id);

    Client updateClient(Long id, Client client);
}
